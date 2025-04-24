package leonardofmds.clientesApi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Endereco {
    @Id
    private UUID id;

    @Column(length = 150, nullable = false)
    private String logradouro;

    @Column(length = 150)
    private String complemento;

    @Column(length = 10)
    private String numero;

    @Column(length = 150)
    private String bairro;

    @Column(length = 150)
    private String cidade;

    @Column(length = 2)
    private String uf;

    @Column(length = 8)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}
