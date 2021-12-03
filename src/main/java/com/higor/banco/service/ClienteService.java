package com.higor.banco.service;

import com.higor.banco.exception.CustomRuntimeException;
import com.higor.banco.exception.enums.EnumClienteException;
import com.higor.banco.model.dto.request.ClienteRequestDto;
import com.higor.banco.model.entity.Cliente;
import com.higor.banco.repository.ClienteRepository;
import com.higor.banco.util.StringUtil;
import com.higor.banco.validador.ValidadorCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    private final ValidadorCliente validadorCliente = new ValidadorCliente();

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente encontrarCliente(final Integer idCliente) {
        return clienteRepository.findByAtivoAndId(true, idCliente)
                .orElseThrow(() -> new CustomRuntimeException(EnumClienteException.CLIENTE_NAO_ENCONTRADO));
    }

    public Cliente encontrarCliente(final String nome, final String cpfCnpj) {
        return clienteRepository.findByAtivoAndNomeAndCpfCnpj(true, nome, cpfCnpj)
                .orElseThrow(() -> new CustomRuntimeException(EnumClienteException.CLIENTE_NAO_ENCONTRADO));
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAllByAtivo(true);
    }

    public Cliente criarCliente(ClienteRequestDto clienteRequestDto) {
        validadorCliente.validarDadosCliente(clienteRequestDto);

        final Cliente cliente = new Cliente();
        cliente.setAtivo(true);
        cliente.setNome(clienteRequestDto.getNome());
        cliente.setCpfCnpj(clienteRequestDto.getCpfCnpj());

        validadorCliente.validarCamposCliente(cliente);

        cliente.setNome(StringUtil.trimWhitespace(cliente.getNome()));
        cliente.setCpfCnpj(StringUtil.somenteNumeros(cliente.getCpfCnpj()));

        validarExistente(cliente);

        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Integer idCliente, ClienteRequestDto clienteRequestDto) {
        validadorCliente.validarDadosCliente(clienteRequestDto);

        Cliente persistedCliente = encontrarCliente(idCliente);

        if (clienteRequestDto.getNome() != null
                && !persistedCliente.getNome().equals(clienteRequestDto.getNome())) {
            persistedCliente.setNome(clienteRequestDto.getNome());
        }

        if (clienteRequestDto.getCpfCnpj() != null
                && !persistedCliente.getCpfCnpj().equals(persistedCliente.getCpfCnpj())) {
            persistedCliente.setCpfCnpj(clienteRequestDto.getCpfCnpj());
        }

        validadorCliente.validarCamposCliente(persistedCliente);

        persistedCliente.setNome(StringUtil.trimWhitespace(clienteRequestDto.getNome()));
        persistedCliente.setCpfCnpj(StringUtil.somenteNumeros(clienteRequestDto.getCpfCnpj()));

        return clienteRepository.save(persistedCliente);
    }

    public void removerCliente(final Integer idCliente) {
        Cliente persistedCliente = encontrarCliente(idCliente);

        persistedCliente.setAtivo(false);
        clienteRepository.save(persistedCliente);
    }


    private void validarExistente(final Cliente cliente) {
        Optional<Cliente> persistedCli = clienteRepository.findByNomeAndCpfCnpj(cliente.getNome(), cliente.getCpfCnpj());

        if (persistedCli.isPresent()) {
            throw new CustomRuntimeException(EnumClienteException.CLIENTE_JA_CADASTRADO);
        }

        persistedCli = clienteRepository.findByCpfCnpj(cliente.getCpfCnpj());

        if (persistedCli.isPresent()) {
            throw new CustomRuntimeException(EnumClienteException.CLIENTE_JA_CADASTRADO);
        }
    }


}
