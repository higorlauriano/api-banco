package com.higor.banco.controller;

import com.higor.banco.model.dto.request.ContaRequestDto;
import com.higor.banco.model.dto.response.*;
import com.higor.banco.model.entity.Conta;
import com.higor.banco.service.ContaService;
import com.higor.banco.service.MovimentoContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "Conta", description = "Endpoints relacionados à Conta", tags = {"Conta"})
@RestController
@RequestMapping(path = ContaController.PATH)
public class ContaController {

    public static final String PATH = "${api.prefix}${api.version}/conta";

    private final ContaService contaService;

    private final MovimentoContaService movimentoContaService;

    @Autowired
    public ContaController(ContaService contaService, MovimentoContaService movimentoContaService) {
        this.contaService = contaService;
        this.movimentoContaService = movimentoContaService;
    }

    @ApiOperation(value = "Criar Conta para um Cliente", tags = {"Conta"})
    @PostMapping(path = "/criar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ContaResponseDto criarConta(@RequestBody ContaRequestDto contaRequestDto) {
        return new ContaResponseDto(contaService.criarConta(contaRequestDto));
    }

    @ApiOperation(value = "Consultar Dados de uma Conta", tags = {"Conta"})
    @GetMapping(path = "/{idConta}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ContaResponseDto consultarConta(@PathVariable(name = "idConta") Integer idConta) {
        return new ContaResponseDto(contaService.encontrarConta(idConta));
    }

    @ApiOperation(value = "Desativar uma Conta", tags = {"Conta"})
    @DeleteMapping(path = "/desativar/{idConta}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto desativarConta(@PathVariable(name = "idConta") Integer idConta) {
        contaService.desativarConta(idConta);
        return new ResponseDto("Conta desativada com sucesso.");
    }

    @ApiOperation(value = "Listar as Contas de um Cliente", tags = {"Conta"})
    @GetMapping(path = "/listar/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetAllContasClienteResponse listarContasCliente(@PathVariable(name = "idCliente") final Integer idCliente) {
        final List<Conta> contas = contaService.listarContasCliente(idCliente);

        return new GetAllContasClienteResponse(contas);
    }


}
