package com.higor.banco.repository;

import com.higor.banco.model.entity.Cliente;
import com.higor.banco.pattern.DefaultRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends DefaultRepository<Cliente> {

    Optional<Cliente> findByAtivoAndId(final boolean ativo, final Integer id);

    Optional<Cliente> findByNomeAndCpfCnpj(final String nome, final String cpfCnpj);

    Optional<Cliente> findByCpfCnpj(final String cpfCnpj);

    Optional<Cliente> findByAtivoAndNomeAndCpfCnpj(final boolean ativo, final String nome, final String cpfCnpj);

    List<Cliente> findAllByAtivo(final boolean ativo);

}
