package leonardofmds.clientesApi.services.interfaces;

import leonardofmds.clientesApi.dtos.post.ClienteRequestDto;
import leonardofmds.clientesApi.dtos.ClienteResponseDto;
import leonardofmds.clientesApi.dtos.put.ClienteRequestPutDto;

import java.util.List;
import java.util.UUID;

public interface ClienteService {

    ClienteResponseDto cadastrar(ClienteRequestDto request) throws Exception;

    ClienteResponseDto atualizar(ClienteRequestPutDto request) throws Exception;

    ClienteResponseDto excluir(UUID id) throws Exception;

    List<ClienteResponseDto> consultar() throws Exception;

    ClienteResponseDto obter(UUID id) throws Exception;
}
