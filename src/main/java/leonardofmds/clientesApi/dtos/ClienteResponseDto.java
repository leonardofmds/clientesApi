package leonardofmds.clientesApi.dtos;

import leonardofmds.clientesApi.entities.Endereco;

import java.util.List;
import java.util.UUID;

public class ClienteResponseDto {

    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private String dataNascimento;
    private List<Endereco> enderecos;
}
