package com.sample.demothree.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.port}")
    private Integer serverPort;

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @Value("${spring.application.name:Demo Application}")
    private String applicationName;

    @Bean
    public OpenAPI openAPI() {
        String serverUrl = String.format("http://localhost:%d%s", serverPort, contextPath);

        Server server = new Server()
                .url(serverUrl)
                .description("Local Server");

        return new OpenAPI()
                .servers(List.of(server))
                .info(new Info()
                        .title(applicationName)
                        .description("API documentation for " + applicationName));
    }
}
