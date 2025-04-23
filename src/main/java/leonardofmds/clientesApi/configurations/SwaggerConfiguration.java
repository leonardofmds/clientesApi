package leonardofmds.clientesApi.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfiguration {
    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI().components(new Components())
                .info(new Info()
                        .title("Clientes API")
                        .description("API Spring Boot para controle de clientes")
                        .version("v1")
                        .contact(new Contact()
                                .name("Leonardo Menezes")
                                .email("leonardomenezes.dev@gmail.com")
                                .url("https://github.com/leonardofmds"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}

