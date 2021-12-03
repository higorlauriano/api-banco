package com.higor.banco.service;

import com.higor.banco.exception.CustomRuntimeException;
import com.higor.banco.exception.enums.EnumTransferenciaException;
import com.higor.banco.model.dto.request.TransferenciaContaDto;
import com.higor.banco.model.dto.request.TransferenciaRequestDto;
import com.higor.banco.model.entity.Conta;
import com.higor.banco.model.entity.Transacao;
import com.higor.banco.repository.TransacaoRepository;
import com.higor.banco.util.DateUtil;
import com.higor.banco.validador.ValidadorTransferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TransferenciaService {

    private final ValidadorTransferencia validadorTransferencia = new ValidadorTransferencia();
    private MovimentoContaService movimentoContaService;
    private ContaService contaService;
    private TransacaoRepository transacaoRepository;

    @Autowired
    public TransferenciaService(MovimentoContaService movimentoContaService, ContaService contaService, TransacaoRepository transacaoRepository) {
        this.movimentoContaService = movimentoContaService;
        this.contaService = contaService;
        this.transacaoRepository = transacaoRepository;
    }

    public Transacao efetuarTransferenciaEntreContas(TransferenciaRequestDto transferenciaRequestDto) {

        validarTransferencia(transferenciaRequestDto);

        final TransferenciaContaDto dadosContaPartida = transferenciaRequestDto.getContaPartida();
        final TransferenciaContaDto dadosContaDestino = transferenciaRequestDto.getContaDestino();

        final Conta contaPartida;
        final Conta contaDestino;

        if (dadosContaPartida.getIdConta() != null) {
            contaPartida = contaService.encontrarConta(dadosContaPartida.getIdConta());
        } else {
            contaPartida = contaService.encontrarConta(
                    dadosContaPartida.getNumeroAgencia(),
                    dadosContaPartida.getNumeroConta()
            );
        }

        if (dadosContaDestino.getIdConta() != null) {
            contaDestino = contaService.encontrarConta(dadosContaDestino.getIdConta());
        } else {
            contaDestino = contaService.encontrarConta(
                    dadosContaDestino.getNumeroAgencia(),
                    dadosContaDestino.getNumeroConta()
            );
        }

        if (contaPartida.getId().equals(contaDestino.getId())) {
            throw new CustomRuntimeException(EnumTransferenciaException.CONTAS_PARTIDA_DESTINO_IGUAIS);
        }

        final Date dataMovimento = DateUtil.getDataHoraAtual();

        final Transacao transacao = new Transacao();
        transacao.setContaOrigem(contaPartida);
        transacao.setContaDestino(contaDestino);
        transacao.setProtocolo(UUID.randomUUID().toString());
        transacao.setDataHoraTransacao(dataMovimento);

        movimentoContaService.movimentarContasTransferencia(transacao, transferenciaRequestDto.getValor(), dataMovimento);

        return transacaoRepository.save(transacao);
    }

    private void validarTransferencia(TransferenciaRequestDto transferenciaRequestDto) {
        validadorTransferencia.validarDadosTransferencia(transferenciaRequestDto);

        final TransferenciaContaDto dadosContaPartida = transferenciaRequestDto.getContaPartida();
        final TransferenciaContaDto dadosContaDestino = transferenciaRequestDto.getContaDestino();

        final Conta contaPartida = contaService.encontrarConta(
                dadosContaPartida.getNumeroAgencia(),
                dadosContaPartida.getNumeroConta(),
                EnumTransferenciaException.CONTA_PARTIDA_NAO_ENCONTRADA
        );

        validadorTransferencia.validarSaldoConta(transferenciaRequestDto.getValor(), contaPartida.getSaldoAtual());

        final Conta contaDestino = contaService.encontrarConta(
                dadosContaDestino.getNumeroAgencia(),
                dadosContaDestino.getNumeroConta(),
                EnumTransferenciaException.CONTA_DESTINO_NAO_ENCONTRADA
        );
    }


}
