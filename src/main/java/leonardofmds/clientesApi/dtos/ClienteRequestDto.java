package leonardofmds.clientesApi.dtos;

import leonardofmds.clientesApi.entities.Endereco;
import lombok.Data;

import java.util.UUID;

@Data
public class ClienteRequestDto {

    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private String dataNascimento;
    private Endereco endereco;


}
