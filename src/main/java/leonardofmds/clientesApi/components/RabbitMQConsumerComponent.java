package leonardofmds.clientesApi.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.internet.MimeMessage;
import leonardofmds.clientesApi.dtos.ClienteResponseDto;
import leonardofmds.clientesApi.dtos.EnderecoResponseDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumerComponent {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    JavaMailSender mailSender;

    /*
     * Método para ler e capturar os registros de contas
     * armazenados na fila do RabbitMQ
     */
    @RabbitListener(queues = "clientesApi_clientes")
    public void consume(@Payload String message) {
        try {
            //deserializando os dados de JSON para objeto (classe)
            var cliente = objectMapper.readValue
                    (message, ClienteResponseDto.class);
            //enviando o email
            sendEmail(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendEmail(ClienteResponseDto cliente) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(cliente.getEmail());
            helper.setSubject("🎉 Cadastro realizado com sucesso!");
            helper.setFrom("noreply@seudominio.com");

            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html>");
            html.append("<html><head><meta charset='UTF-8'></head><body>");
            html.append("<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto;'>");

            html.append("<h2 style='color: #2E86C1;'>Olá, ").append(cliente.getNome()).append("!</h2>");
            html.append("<p>Seu cadastro foi realizado com sucesso em nosso sistema. 🎉</p>");

            html.append("<h3>📄 Seus dados:</h3>");
            html.append("<ul>");
            html.append("<li><strong>CPF:</strong> ").append(cliente.getCpf()).append("</li>");
            html.append("<li><strong>Data de Nascimento:</strong> ").append(cliente.getDataNascimento()).append("</li>");
            html.append("</ul>");

            html.append("<h3>📍 Endereços cadastrados:</h3>");
            html.append("<ul>");
            for (EnderecoResponseDto endereco : cliente.getEnderecos()) {
                html.append("<li>");
                html.append(endereco.getLogradouro()).append(", nº ").append(endereco.getNumero());
                if (endereco.getComplemento() != null && !endereco.getComplemento().isBlank()) {
                    html.append(" - ").append(endereco.getComplemento());
                }
                html.append(" - ").append(endereco.getBairro());
                html.append(" - ").append(endereco.getCidade()).append("/").append(endereco.getUf());
                html.append(" - CEP: ").append(endereco.getCep());
                html.append("</li>");
            }
            html.append("</ul>");

            html.append("<p style='margin-top: 20px;'>Seja muito bem-vindo(a) à nossa plataforma!</p>");
            html.append("<hr style='margin-top: 30px;'/>");
            html.append("<p style='font-size: 0.9em; color: gray;'>Este é um e-mail automático, por favor, não responda.</p>");
            html.append("</div></body></html>");

            helper.setText(html.toString(), true); // true indica que é HTML

            mailSender.send(message);

            System.out.println("E-mail enviado com sucesso para: " + cliente.getEmail());

        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

