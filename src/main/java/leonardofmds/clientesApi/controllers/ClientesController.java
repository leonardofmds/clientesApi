package leonardofmds.clientesApi.controllers;

import jakarta.validation.Valid;
import leonardofmds.clientesApi.dtos.ClienteRequestDto;
import leonardofmds.clientesApi.dtos.ClienteResponseDto;
import leonardofmds.clientesApi.services.impl.ClienteServiceImpl;
import leonardofmds.clientesApi.services.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clientes")
public class ClientesController {

    @Autowired ClienteService clienteService;

    @PostMapping
    public ClienteResponseDto criarCliente(@RequestBody @Valid ClienteRequestDto request) throws Exception {
        return clienteService.cadastrar(request);
    }
    @PutMapping("/{id}")
    public String atualizarCliente() {
        return "Cliente atualizado com sucesso!";
    }
    @DeleteMapping("/{id}")
    public String deletarCliente() {
        return "Cliente deletado com sucesso!";
    }
    @GetMapping
    public String listarClientes() {
        return "Lista de clientes";
    }
    @GetMapping("/{id}")
    public String obterCliente(@PathVariable int id) {
        return "Cliente com ID: " + id;
    }



}
