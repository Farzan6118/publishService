package com.sample.demothree.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class SwaggerConfig {
    @Value("${server.port}")
    private Integer serverPort;


    @Bean
    public OpenAPI openAPI() {
        List<Server> servers = new ArrayList<>();
        Server server = new Server();
        servers.add(server);
        return new OpenAPI().servers(servers)
                .info(new Info().title("")
                        .description(""));
    }

}
