package com.eazybytes.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice Rest API Documentation",
				description = "EasyBank Accounts for microservice Rest API",
				version = "v1",
				contact = @Contact(
						name = "Venelin Davidov",
						email = "venelin.davidov@gmail.com",
						url = "https://github.com/venelin-davidov"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/venelin-davidov"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EasyBank Accounts for microservice Rest API",
				url = "https://github.com/venelin-davidov"
		)
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
