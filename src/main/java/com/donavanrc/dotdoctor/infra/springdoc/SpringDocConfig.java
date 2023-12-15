package com.donavanrc.dotdoctor.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title(".Doctor")
                        .description("The <strong>\".Doctor\"</strong> project was designed with an essentially <strong>educational purpose</strong>. This application simulates an online medical appointment scheduling system, where doctors and patients can register. Users can easily schedule appointments with healthcare professionals from different specialties, such as <strong>Cardiology, Dermatology, Orthopedics and Gynecology<strong>.")
                        .contact(new Contact()
                                .name("DevTeam")
                                .email("devteam@dotdoctor.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
