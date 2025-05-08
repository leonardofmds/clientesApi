package leonardofmds.clientesApi.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import leonardofmds.clientesApi.dtos.ClienteResponseDto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducerComponent {

    @Autowired RabbitTemplate rabbitTemplate;
    @Autowired ObjectMapper objectMapper;
    @Autowired Queue queue;

    public void sendMessage(ClienteResponseDto cliente) {
        try {
            //serializar os dados da conta para formato JSON
            String json = objectMapper.writeValueAsString(cliente);
            //enviando a mensagem para a fila
            rabbitTemplate.convertAndSend(queue.getName(), json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
