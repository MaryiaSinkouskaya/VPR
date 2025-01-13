package com.vpr.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Value("${vpr.openapi.dev-url}")
  private String devUrl;

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    Contact contact = new Contact();
    contact.setEmail("masha25112000@gmail.com");
    contact.setName("Maryia Sinkouskaya");
    contact.setUrl("https://github.com/MaryiaSinkouskaya");

    Info info = new Info()
        .title(
            "API for distributed access to the updated Republican database of congenital malformations")
        .version("1.0")
        .contact(contact)
        .description("API for RDCM");

    return new OpenAPI().info(info).addServersItem(devServer);
  }
}