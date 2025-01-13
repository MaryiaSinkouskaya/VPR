package com.vpr.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "API for distributed access to the updated Republican database of congenital malformations",
        description = "API for RDCM",
        version = "1.0.0",
        contact = @Contact(
            name = "Maryia Sinkouskaya",
            url= "https://github.com/MaryiaSinkouskaya",
            email = "masha25112000@gmail.com"
        )
    )
)
public class OpenApiConfig {

}
