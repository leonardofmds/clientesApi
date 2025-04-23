package leonardofmds.clientesApi.services.impl;

import leonardofmds.clientesApi.dtos.ClienteRequestDto;
import leonardofmds.clientesApi.dtos.ClienteResponseDto;
import leonardofmds.clientesApi.repositories.ClienteRepository;
import leonardofmds.clientesApi.services.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired ClienteRepository clienteRepository;


    @Override
    public ClienteResponseDto cadastrar(ClienteRequestDto request) throws Exception {
        return null;
    }

    @Override
    public ClienteResponseDto atualizar(String id, ClienteRequestDto request) throws Exception {
        return null;
    }

    @Override
    public ClienteResponseDto excluir(String id) throws Exception {
        return null;
    }

    @Override
    public List<ClienteResponseDto> consultar() throws Exception {
        return List.of();
    }

    @Override
    public ClienteResponseDto obter(String id) throws Exception {
        return null;
    }
}
