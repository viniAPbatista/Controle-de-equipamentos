package com.devsDoAgi.email_service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailServiceApplication {

	public static void main(String[] args) {
        // Carrega o arquivo .env automaticamente (deve estar na raiz do projeto)
        Dotenv dotenv = Dotenv.load();

        // Define as variáveis no sistema, para o Spring conseguir ler no application.properties
        System.setProperty("EMAIL_USERNAME", dotenv.get("EMAIL_USERNAME"));
        System.setProperty("EMAIL_PASSWORD", dotenv.get("EMAIL_PASSWORD"));

		SpringApplication.run(EmailServiceApplication.class, args);
	}

}
