package leonardofmds.clientesApi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private UUID id;
    private String nome;
    private String email;
    private String CPF;
    private Date dataNascimento;
    private List<Endereco> enderecos;

}
