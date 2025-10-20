package com.devsDoAgi.almoxarifado;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlmoxarifadoApplication {

	public static void main(String[] args) {
        // Carrega o arquivo .env automaticamente (deve estar na raiz do projeto)
        Dotenv dotenv = Dotenv.load();

        // Define as variáveis no sistema, para o Spring conseguir ler no application.properties
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(AlmoxarifadoApplication.class, args);
	}

}
