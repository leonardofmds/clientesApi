package leonardofmds.clientesApi.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String QUEUE_NAME = "clientesApi_clientes";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }
}
