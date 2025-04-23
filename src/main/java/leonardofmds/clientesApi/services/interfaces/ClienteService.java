package leonardofmds.clientesApi.services.interfaces;

import leonardofmds.clientesApi.dtos.ClienteRequestDto;
import leonardofmds.clientesApi.dtos.ClienteResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClienteService {

    ClienteResponseDto cadastrar(ClienteRequestDto request) throws Exception;

    ClienteResponseDto atualizar(String id, ClienteRequestDto request) throws Exception;

    ClienteResponseDto excluir(String id) throws Exception;

    List<ClienteResponseDto> consultar() throws Exception;

    ClienteResponseDto obter(String id) throws Exception;
}
