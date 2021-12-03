package com.higor.banco.repository;

import com.higor.banco.model.entity.Cliente;
import com.higor.banco.model.entity.Conta;
import com.higor.banco.pattern.DefaultRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends DefaultRepository<Conta> {

    Optional<Conta> findByAtivoAndId(final boolean ativo, final Integer id);

    Optional<Conta> findByAtivoAndNumeroAgenciaAndNumeroConta(final boolean ativo, final Integer numeroAgencia, final Integer numeroConta);

    List<Conta> findAllByAtivoAndCliente(final boolean ativo, final Cliente cliente);

}
