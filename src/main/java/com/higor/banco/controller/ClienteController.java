package com.higor.banco.controller;

import com.higor.banco.model.dto.request.ClienteRequestDto;
import com.higor.banco.model.dto.response.ClienteResponseDto;
import com.higor.banco.model.dto.response.ResponseDto;
import com.higor.banco.model.entity.Cliente;
import com.higor.banco.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "Cliente", description = "Endpoints relacionados à Cliente", tags = {"Cliente"})
@RestController
@RequestMapping(path = ClienteController.PATH)
public class ClienteController {

    public static final String PATH = "${api.prefix}${api.version}/cliente";

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @ApiOperation(value = "Criar Cliente", tags = {"Cliente"})
    @PostMapping(path = "/criar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteResponseDto criarCliente(@RequestBody ClienteRequestDto cliente) {
        return new ClienteResponseDto(clienteService.criarCliente(cliente));
    }

    @ApiOperation(value = "Obter Dados do Cliente", tags = {"Cliente"})
    @GetMapping(path = "/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteResponseDto consultarCliente(@PathVariable(name = "idCliente") Integer idCliente) {
        return new ClienteResponseDto(clienteService.encontrarCliente(idCliente));
    }

    @ApiOperation(value = "Atualizar Dados do Cliente", tags = {"Cliente"})
    @PatchMapping(path = "/atualizar/{idCliente}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteResponseDto atualizarCliente(@PathVariable(name = "idCliente") Integer idCliente, @RequestBody ClienteRequestDto cliente) {
        return new ClienteResponseDto(clienteService.atualizarCliente(idCliente, cliente));
    }

    @ApiOperation(value = "Desativar Cadastro do Cliente", tags = {"Cliente"})
    @DeleteMapping(path = "/desativar/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto desativarCliente(@PathVariable(name = "idCliente") Integer idCliente) {
        clienteService.removerCliente(idCliente);
        return new ResponseDto("Cliente removido com sucesso.");
    }

    @ApiOperation(value = "Listar todos os Clientes", tags = {"Cliente"})
    @GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClienteResponseDto> listarClientes() {
        final List<Cliente> clientes = clienteService.listarClientes();
        final List<ClienteResponseDto> clientesResponse = new ArrayList<>();

        for (Cliente cliente : clientes) {
            clientesResponse.add(new ClienteResponseDto(cliente));
        }

        return clientesResponse;
    }

}
