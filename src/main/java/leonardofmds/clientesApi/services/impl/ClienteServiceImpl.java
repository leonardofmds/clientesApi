package leonardofmds.clientesApi.services.impl;

import leonardofmds.clientesApi.dtos.ClienteRequestDto;
import leonardofmds.clientesApi.dtos.ClienteResponseDto;
import leonardofmds.clientesApi.entities.Cliente;
import leonardofmds.clientesApi.entities.Endereco;
import leonardofmds.clientesApi.repositories.ClienteRepository;
import leonardofmds.clientesApi.services.interfaces.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired ClienteRepository clienteRepository;


    @Override
    public ClienteResponseDto cadastrar(ClienteRequestDto request) throws Exception {

        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(request.getEndereco(),endereco);
        BeanUtils.copyProperties(request,cliente);
        cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(request.getDataNascimento())); //capturando o campo data;
        cliente.setEnderecos(new ArrayList<Endereco>());
        cliente.getEnderecos().add(endereco);

        clienteRepository.save(cliente);

        ClienteResponseDto response = new ClienteResponseDto();
        BeanUtils.copyProperties(cliente,response);
        response.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento()));

        return response;
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
