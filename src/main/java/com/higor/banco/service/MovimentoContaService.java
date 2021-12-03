package com.higor.banco.service;

import com.higor.banco.model.entity.Conta;
import com.higor.banco.model.entity.MovimentoConta;
import com.higor.banco.model.entity.Transacao;
import com.higor.banco.model.enums.EnumTipoES;
import com.higor.banco.model.enums.EnumTipoMovimento;
import com.higor.banco.repository.ContaRepository;
import com.higor.banco.repository.MovimentoContaRepository;
import com.higor.banco.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentoContaService {

    private final MovimentoContaRepository movimentoContaRepository;

    private final ContaRepository contaRepository;

    @Autowired
    public MovimentoContaService(MovimentoContaRepository movimentoContaRepository, ContaRepository contaRepository) {
        this.movimentoContaRepository = movimentoContaRepository;
        this.contaRepository = contaRepository;
    }

    public MovimentoConta movimentarConta(Conta conta, final BigDecimal valor, final EnumTipoES tipoEntradaSaida, final EnumTipoMovimento tipoMovimento) {
        return movimentarConta(conta, valor, tipoEntradaSaida, tipoMovimento, DateUtil.getDataHoraAtual());
    }

    public MovimentoConta movimentarConta(Conta conta, final BigDecimal valor, final EnumTipoES tipoEntradaSaida, final EnumTipoMovimento tipoMovimento, final Date dataMovimento) {

        final Optional<MovimentoConta> ultimoMovimentoConta = movimentoContaRepository.encontrarUltimoMovimentoConta(conta);

        final MovimentoConta movimentoConta = new MovimentoConta();
        movimentoConta.setConta(conta);
        movimentoConta.setTipoES(tipoEntradaSaida);
        movimentoConta.setValorMovimento(valor);
        movimentoConta.setTipoMovimento(tipoMovimento);
        movimentoConta.setDataHoraMovimento(dataMovimento);

        BigDecimal saldoContaMovimento;
        switch (tipoEntradaSaida) {
            case ENTRADA:
                saldoContaMovimento = ultimoMovimentoConta.isPresent()
                        ? ultimoMovimentoConta.get().getSaldoConta().add(valor)
                        : valor;

                movimentoConta.setSaldoConta(saldoContaMovimento);
                conta.setSaldoAtual(conta.getSaldoAtual().add(valor));
                break;
            case SAIDA:
                saldoContaMovimento = ultimoMovimentoConta.isPresent()
                        ? ultimoMovimentoConta.get().getSaldoConta().subtract(valor)
                        : valor;

                movimentoConta.setSaldoConta(saldoContaMovimento);
                conta.setSaldoAtual(conta.getSaldoAtual().subtract(valor));
                break;
        }

        contaRepository.save(conta);

        return movimentoContaRepository.save(movimentoConta);
    }

    public List<MovimentoConta> listarMovimentosConta(final Conta conta, final Date dataInicio, final Date dataFim) {
        return movimentoContaRepository.listarMovimentosPorContaPeriodo(conta, dataInicio, dataFim);
    }

    public void movimentarContasTransferencia(Transacao transacao, final BigDecimal valor, final Date dataTransacao) {

        movimentarConta(transacao.getContaOrigem(), valor, EnumTipoES.SAIDA, EnumTipoMovimento.TRANSFERENCIA, dataTransacao);
        movimentarConta(transacao.getContaDestino(), valor, EnumTipoES.ENTRADA, EnumTipoMovimento.TRANSFERENCIA, dataTransacao);

    }
}
