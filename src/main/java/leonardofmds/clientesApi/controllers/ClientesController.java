package leonardofmds.clientesApi.controllers;

import jakarta.validation.Valid;
import leonardofmds.clientesApi.dtos.post.ClienteRequestDto;
import leonardofmds.clientesApi.dtos.ClienteResponseDto;
import leonardofmds.clientesApi.dtos.put.ClienteRequestPutDto;
import leonardofmds.clientesApi.services.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/clientes")
public class ClientesController {

    @Autowired ClienteService clienteService;

    @PostMapping
    public ClienteResponseDto criarCliente(@RequestBody @Valid ClienteRequestDto request) throws Exception {
        return clienteService.cadastrar(request);
    }
    @PutMapping
    public ClienteResponseDto atualizarCliente(@RequestBody @Valid ClienteRequestPutDto request) throws Exception {
        return clienteService.atualizar(request);

    }
    @DeleteMapping("/{id}")
    public ClienteResponseDto deletarCliente(@PathVariable UUID id) throws Exception {
        return clienteService.excluir(id);
    }
    @GetMapping
    public List<ClienteResponseDto> listarClientes() throws Exception {
        return clienteService.consultar();
    }

    @GetMapping("/{id}")
    public ClienteResponseDto obterCliente(@PathVariable UUID id) throws Exception {

        return clienteService.obter(id);
    }



}
