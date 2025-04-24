package leonardofmds.clientesApi.services.impl;

import leonardofmds.clientesApi.dtos.post.ClienteRequestDto;
import leonardofmds.clientesApi.dtos.ClienteResponseDto;
import leonardofmds.clientesApi.dtos.EnderecoResponseDto;
import leonardofmds.clientesApi.dtos.put.ClienteRequestPutDto;
import leonardofmds.clientesApi.entities.Cliente;
import leonardofmds.clientesApi.entities.Endereco;
import leonardofmds.clientesApi.repositories.ClienteRepository;
import leonardofmds.clientesApi.services.interfaces.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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


        endereco.setCliente(cliente);
        cliente.setEnderecos(new ArrayList<>());
        cliente.getEnderecos().add(endereco);

        ClienteResponseDto response = new ClienteResponseDto();
        BeanUtils.copyProperties(clienteRepository.save(cliente), response);
        response.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento()));
        response.setEnderecos(new ArrayList<>());

        for (Endereco e : cliente.getEnderecos()) {
            EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto();
            BeanUtils.copyProperties(e, enderecoResponseDto);
            response.getEnderecos().add(enderecoResponseDto);
        }

        return response;
    }

    @Override
    public ClienteResponseDto atualizar(ClienteRequestPutDto request) throws Exception {

        Cliente cliente = clienteRepository.findById(UUID.fromString(request.getId())).get();

        BeanUtils.copyProperties(request, cliente);
        cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(request.getDataNascimento()));

        for (Endereco endereco : cliente.getEnderecos()) {
            if (endereco.getId().equals(UUID.fromString(request.getEndereco().getId()))) {
                // Atualiza os campos diretamente
                BeanUtils.copyProperties(request.getEndereco(), endereco, "id", "cliente");
                break;
            }
        }

        Cliente savedCliente = clienteRepository.save(cliente);

        ClienteResponseDto response = new ClienteResponseDto();
        BeanUtils.copyProperties(savedCliente, response);
        response.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento()));

        List<EnderecoResponseDto> enderecoDtos = new ArrayList<>();
        for (Endereco e : savedCliente.getEnderecos()) {
            EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto();
            BeanUtils.copyProperties(e, enderecoResponseDto);
            enderecoDtos.add(enderecoResponseDto);
        }

        response.setEnderecos(enderecoDtos);
        return response;
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
