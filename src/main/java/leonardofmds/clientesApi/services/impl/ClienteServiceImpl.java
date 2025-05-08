package leonardofmds.clientesApi.services.impl;

import leonardofmds.clientesApi.components.RabbitMQProducerComponent;
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
    @Autowired RabbitMQProducerComponent rabbitMQProducerComponent;


    @Override
    public ClienteResponseDto cadastrar(ClienteRequestDto request) throws Exception {

        clienteRepository.findByCpf(request.getCpf()).ifPresent(cliente -> {
            throw new RuntimeException("Cliente já cadastrado com o CPF: " + request.getCpf());
        });

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

        rabbitMQProducerComponent.sendMessage(response);

        return response;
    }

    @Override
    public ClienteResponseDto atualizar(ClienteRequestPutDto request) throws Exception {

        Cliente cliente = clienteRepository.findById(UUID.fromString(request.getId())).get();

        if(!request.getCpf().equals(cliente.getCpf())){
            clienteRepository.findByCpf(request.getCpf()).ifPresent(client -> {
                throw new RuntimeException("Cliente já cadastrado com o CPF: " + request.getCpf());
            });
        }

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
    public ClienteResponseDto excluir(UUID id) throws Exception {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado"));
        clienteRepository.delete(cliente);

        ClienteResponseDto response = new ClienteResponseDto();
        BeanUtils.copyProperties(cliente, response);
        response.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento()));
        List<EnderecoResponseDto> enderecoDtos = new ArrayList<>();
        for (Endereco e : cliente.getEnderecos()) {
            EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto();
            BeanUtils.copyProperties(e, enderecoResponseDto);
            enderecoDtos.add(enderecoResponseDto);
        }
        response.setEnderecos(enderecoDtos);
        return response;
    }

    @Override
    public List<ClienteResponseDto> consultar() throws Exception {
        ArrayList<ClienteResponseDto> clientesResponse = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findAllOrderByNome();

        if (clientes.isEmpty()) {
            throw new Exception("Nenhum cliente encontrado");
        }

        for (Cliente cliente : clientes) {
            ClienteResponseDto response = new ClienteResponseDto();
            BeanUtils.copyProperties(cliente, response);
            response.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento()));
            List<EnderecoResponseDto> enderecoDtos = new ArrayList<>();
            for (Endereco e : cliente.getEnderecos()) {
                EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto();
                BeanUtils.copyProperties(e, enderecoResponseDto);
                enderecoDtos.add(enderecoResponseDto);
            }
            response.setEnderecos(enderecoDtos);
            clientesResponse.add(response);
        }

        return clientesResponse;
    }

    @Override
    public ClienteResponseDto obter(UUID id) throws Exception {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado"));
        ClienteResponseDto response = new ClienteResponseDto();
        BeanUtils.copyProperties(cliente, response);
        response.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento()));
        List<EnderecoResponseDto> enderecoDtos = new ArrayList<>();
        for (Endereco e : cliente.getEnderecos()) {
            EnderecoResponseDto enderecoResponseDto = new EnderecoResponseDto();
            BeanUtils.copyProperties(e, enderecoResponseDto);
            enderecoDtos.add(enderecoResponseDto);
        }
        response.setEnderecos(enderecoDtos);
        return response;
    }
}
