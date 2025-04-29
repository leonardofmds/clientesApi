package leonardofmds.clientesApi.repositories;

import leonardofmds.clientesApi.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    @Query("SELECT c FROM Cliente c ORDER BY c.nome")
    List<Cliente> findAllOrderByNome();


}
