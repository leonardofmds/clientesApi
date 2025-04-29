package leonardofmds.clientesApi.repositories;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import leonardofmds.clientesApi.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    @Query("SELECT c FROM Cliente c ORDER BY c.nome")
    List<Cliente> findAllOrderByNome();


    Optional<Object> findByCpf(@NotEmpty @Pattern(regexp="\\d{11}", message="CPF inválido") String cpf);
}
