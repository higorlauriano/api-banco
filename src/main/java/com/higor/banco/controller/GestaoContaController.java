package com.higor.banco.controller;

import com.higor.banco.model.dto.request.TransferenciaRequestDto;
import com.higor.banco.model.dto.response.ConsultaExtratoResponse;
import com.higor.banco.model.dto.response.ConsultaSaldoResponseDto;
import com.higor.banco.model.dto.response.TransferenciaResponseDto;
import com.higor.banco.service.ContaService;
import com.higor.banco.service.TransferenciaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "Gestão Conta", description = "Endpoints relacionados à funcionalidades da Conta", tags = {"Gestão Conta"})
@RestController
@RequestMapping(path = GestaoContaController.PATH)
public class GestaoContaController {

    public static final String PATH = "${api.prefix}${api.version}/gestao-conta";

    private final TransferenciaService transferenciaService;

    private final ContaService contaService;

    @Autowired
    public GestaoContaController(TransferenciaService transferenciaService, ContaService contaService) {
        this.transferenciaService = transferenciaService;
        this.contaService = contaService;
    }


    @ApiOperation(value = "Obter o Saldo de uma Conta", tags = {"Gestão Conta"})
    @GetMapping(path = "/{idConta}/saldo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ConsultaSaldoResponseDto consultarSaldoConta(@PathVariable(name = "idConta") final Integer idConta) {
        return new ConsultaSaldoResponseDto(contaService.encontrarConta(idConta));
    }

    @ApiOperation(value = "Obter o Extrato de uma Conta", tags = {"Gestão Conta"})
    @GetMapping(path = "/{idConta}/extrato", produces = MediaType.APPLICATION_JSON_VALUE)
    public ConsultaExtratoResponse consultarExtratoConta(
            @PathVariable(name = "idConta") final Integer idConta,
            @ApiParam(value = "Data no formato yyyy-MM-dd")
            @RequestParam(value = "dataInicio", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
            @ApiParam(value = "Data no formato yyyy-MM-dd")
            @RequestParam(value = "dataFim", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim
    ) {
        return contaService.obterExtrato(idConta, dataInicio, dataFim);
    }

    @ApiOperation(value = "Realizar Transferência entre Contas", tags = {"Gestão Conta"})
    @PostMapping(path = "/transferir", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TransferenciaResponseDto efetuarTransferencia(@RequestBody TransferenciaRequestDto transferenciaRequestDto) {
        return new TransferenciaResponseDto(transferenciaService.efetuarTransferenciaEntreContas(transferenciaRequestDto));
    }


}
