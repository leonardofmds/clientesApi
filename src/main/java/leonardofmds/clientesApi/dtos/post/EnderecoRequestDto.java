package leonardofmds.clientesApi.dtos.post;

import lombok.Data;

@Data
public class EnderecoRequestDto {

    private String logradouro;
    private String complemento;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

}
