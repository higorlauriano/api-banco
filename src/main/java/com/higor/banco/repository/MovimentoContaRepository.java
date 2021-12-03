package com.higor.banco.repository;

import com.higor.banco.model.entity.Conta;
import com.higor.banco.model.entity.MovimentoConta;
import com.higor.banco.pattern.DefaultRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovimentoContaRepository extends DefaultRepository<MovimentoConta> {

    @Query("Select mc from MovimentoConta mc where mc.conta = :conta and mc.dataHoraMovimento between :inicio and :fim")
    List<MovimentoConta> listarMovimentosPorContaPeriodo(final Conta conta, final Date inicio, final Date fim);

    @Query("Select mc from MovimentoConta mc where mc.conta = :conta and mc.id = (select max(mc1.id) from MovimentoConta mc1 where mc1.conta = mc.conta)")
    Optional<MovimentoConta> encontrarUltimoMovimentoConta(final Conta conta);

}
