package com.higor.banco.service;

import com.higor.banco.exception.CustomRuntimeException;
import com.higor.banco.exception.ICustomException;
import com.higor.banco.exception.enums.EnumContaException;
import com.higor.banco.model.dto.request.ContaRequestDto;
import com.higor.banco.model.dto.response.ConsultaExtratoResponse;
import com.higor.banco.model.dto.response.MovimentoContaResponseDto;
import com.higor.banco.model.entity.Cliente;
import com.higor.banco.model.entity.Conta;
import com.higor.banco.model.entity.MovimentoConta;
import com.higor.banco.model.enums.EnumTipoES;
import com.higor.banco.model.enums.EnumTipoMovimento;
import com.higor.banco.repository.ContaRepository;
import com.higor.banco.util.DateUtil;
import com.higor.banco.util.ListUtil;
import com.higor.banco.util.NumericUtil;
import com.higor.banco.validador.ValidadorConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class ContaService {

    private final ClienteService clienteService;

    private final MovimentoContaService movimentoContaService;

    private final ContaRepository contaRepository;

    private final ValidadorConta validadorConta = new ValidadorConta();

    @Autowired
    public ContaService(ClienteService clienteService, ContaRepository contaRepository, MovimentoContaService movimentoContaService) {
        this.clienteService = clienteService;
        this.contaRepository = contaRepository;
        this.movimentoContaService = movimentoContaService;
    }

    public Conta encontrarConta(final Integer idConta) {
        return contaRepository.findByAtivoAndId(true, idConta)
                .orElseThrow(() -> new CustomRuntimeException(EnumContaException.CONTA_NAO_ENCONTRADA));
    }

    public Conta encontrarConta(final Integer numeroAgencia, final Integer numeroConta) {
        return encontrarConta(numeroAgencia, numeroConta, EnumContaException.CONTA_NAO_ENCONTRADA);
    }

    public Conta encontrarConta(final Integer numeroAgencia, final Integer numeroConta, final ICustomException excecao) {
        return contaRepository.findByAtivoAndNumeroAgenciaAndNumeroConta(true, numeroAgencia, numeroConta)
                .orElseThrow(() -> new CustomRuntimeException(excecao));
    }

    public Conta criarConta(ContaRequestDto contaRequestDto) {
        validadorConta.validarDadosNovaConta(contaRequestDto);

        Cliente cliente;

        if (contaRequestDto.getCliente().getId() != null) {
            cliente = clienteService.encontrarCliente(contaRequestDto.getCliente().getId());
        } else {
            cliente = clienteService.encontrarCliente(contaRequestDto.getCliente().getNome(), contaRequestDto.getCliente().getCpfCnpj());
        }

        Conta conta = new Conta();
        conta.setDataHoraCriacao(DateUtil.getDataHoraAtual());
        conta.setAtivo(true);
        conta.setCliente(cliente);
        conta.setSaldoAtual(BigDecimal.ZERO);

        validadorConta.validarCamposNovaConta(conta);

        conta = contaRepository.save(conta);

        if (NumericUtil.isGreaterThanZero(contaRequestDto.getSaldoInicial())) {
            movimentoContaService.movimentarConta(conta, contaRequestDto.getSaldoInicial(), EnumTipoES.ENTRADA, EnumTipoMovimento.DEPOSITO);
        }

        return conta;
    }

    public void desativarConta(final Integer idConta) {
        final Conta conta = encontrarConta(idConta);
        conta.setAtivo(false);
        contaRepository.save(conta);
    }

    public List<Conta> listarContasCliente(final Integer idCliente) {
        Cliente cliente = clienteService.encontrarCliente(idCliente);

        return contaRepository.findAllByAtivoAndCliente(true, cliente);
    }

    public ConsultaExtratoResponse obterExtrato(final Integer idConta, Date dataInicio, Date dataFim) {

        dataInicio = dataInicio == null ? DateUtil.getDataAtual() : DateUtil.zerarHora(dataInicio).getTime();
        dataFim = dataFim == null ? DateUtil.maxHour(DateUtil.getDataAtual()) : DateUtil.maxHour(dataFim);

        final List<MovimentoConta> movimentos = movimentoContaService.listarMovimentosConta(
                encontrarConta(idConta),
                dataInicio,
                dataFim
        );

        final ConsultaExtratoResponse extratoResponse = new ConsultaExtratoResponse();
        extratoResponse.setMovimentacoes(new ArrayList<>());
        extratoResponse.setDataInicio(dataInicio);
        extratoResponse.setDataFim(dataFim);

        for (MovimentoConta mc : movimentos) {
            extratoResponse.getMovimentacoes().add(new MovimentoContaResponseDto(mc));
        }

        final Optional<MovimentoConta> primeiroMovimento = ListUtil.stream(movimentos)
                .sorted(Comparator.comparing(MovimentoConta::getDataHoraMovimento))
                .findFirst();

        final Optional<MovimentoConta> ultimoMovimento = ListUtil.stream(movimentos)
                .sorted((m1, m2) -> m2.getDataHoraMovimento().compareTo(m1.getDataHoraMovimento()))
                .findFirst();

        extratoResponse.setSaldoInicial(
                primeiroMovimento.isPresent()
                        ? primeiroMovimento.get().getSaldoConta().subtract(primeiroMovimento.get().getValorMovimento())
                        : BigDecimal.ZERO
        );

        extratoResponse.setSaldoFinal(
                ultimoMovimento.isPresent()
                        ? ultimoMovimento.get().getSaldoConta()
                        : BigDecimal.ZERO
        );

        return extratoResponse;
    }

}
