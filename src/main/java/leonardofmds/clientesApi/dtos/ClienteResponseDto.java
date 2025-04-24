package leonardofmds.clientesApi.dtos;

import leonardofmds.clientesApi.entities.Endereco;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ClienteResponseDto {

    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private String dataNascimento;
    private List<EnderecoResponseDto> enderecos;
}
