package leonardofmds.clientesApi.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clientes")
public class ClientesController {


    @PostMapping
    public String criarCliente() {
        return "Cliente criado com sucesso!";
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
