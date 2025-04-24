package leonardofmds.clientesApi.dtos.put;

import lombok.Data;

import java.util.UUID;

@Data
public class EnderecoRequestPutDto {

    private String id;
    private String logradouro;
    private String complemento;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

}
