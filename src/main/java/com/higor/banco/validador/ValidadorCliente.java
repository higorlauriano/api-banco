package com.higor.banco.validador;

import com.higor.banco.exception.CustomRuntimeException;
import com.higor.banco.exception.enums.EnumClienteException;
import com.higor.banco.model.dto.request.ClienteRequestDto;
import com.higor.banco.model.entity.Cliente;
import com.higor.banco.util.StringUtil;

public class ValidadorCliente {

    public void validarDadosCliente(final ClienteRequestDto clienteRequestDto) {
        if (clienteRequestDto == null) {
            throw new CustomRuntimeException(EnumClienteException.DADOS_INVALIDOS);
        }
    }

    public void validarCamposCliente(final Cliente cliente) {
        validarNome(cliente);
        validarCpfCnpj(cliente);
    }

    public void validarNome(final Cliente cliente) {
        final String trimmedNome = StringUtil.trimWhitespace(cliente.getNome());

        if (!StringUtil.hasLength(trimmedNome)) {
            throw new CustomRuntimeException(EnumClienteException.NOME_NAO_INFORMADO);
        }

        if (trimmedNome.length() < 2
                || trimmedNome.length() > 100) {
            throw new CustomRuntimeException(EnumClienteException.NOME_INVALIDO);
        }
    }

    public void validarCpfCnpj(final Cliente cliente) {
        String cpfCnpj = StringUtil.trimWhitespace(cliente.getCpfCnpj());

        if (!StringUtil.hasLength(cpfCnpj)) {
            throw new CustomRuntimeException(EnumClienteException.CPF_CNPJ_NAO_INFORMADO);
        }

        if (cpfCnpj.length() != 11
                && cpfCnpj.length() != 14) {
            throw new CustomRuntimeException(EnumClienteException.CPF_CNPJ_INVALIDO);
        }

        cpfCnpj = StringUtil.somenteNumeros(cpfCnpj);

        if (cpfCnpj.length() != 11
                && cpfCnpj.length() != 14) {
            throw new CustomRuntimeException(EnumClienteException.CPF_CNPJ_INVALIDO);
        }
    }


}
