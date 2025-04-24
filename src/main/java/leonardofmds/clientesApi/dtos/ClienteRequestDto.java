package leonardofmds.clientesApi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import leonardofmds.clientesApi.entities.Endereco;
import lombok.Data;

import java.util.UUID;

@Data
public class ClienteRequestDto {

    @NotEmpty
    @Size(min = 8, max = 100, message = "Informe um nome de 8 a 100 caracteres.")
    private String nome;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp="\\d{11}", message="CPF inválido")
    private String cpf;

    @NotEmpty
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$",
            message = "Por favor, informe a data no formato dd/MM/yyyy.")
    private String dataNascimento;

    private EnderecoRequestDto endereco;


}
