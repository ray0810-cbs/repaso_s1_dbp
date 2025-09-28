package com.example.repasito1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Repasito1Application {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("JDBC_DATABASE_URL", dotenv.get("JDBC_DATABASE_URL"));
        System.setProperty("JDBC_DATABASE_USERNAME", dotenv.get("JDBC_DATABASE_USERNAME"));
        System.setProperty("JDBC_DATABASE_PASSWORD", dotenv.get("JDBC_DATABASE_PASSWORD"));
        SpringApplication.run(Repasito1Application.class, args);
    }

}
