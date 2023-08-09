package com.epic.counter.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                description = "OpenApi documentation for interview test project",
                title = "OpenApi specification"),
        servers = {@Server(
                description = "Local Environment",
                url = "http://localhost:8095")}
)

public class SwaggerConfig {
}
