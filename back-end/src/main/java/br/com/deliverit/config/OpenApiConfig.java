package br.com.deliverit.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI deliverItOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("deliverIT - Pay Accounts API")
                        .description("REST service for practical test of pay accounts")
                        .version("v1"));
    }
}
