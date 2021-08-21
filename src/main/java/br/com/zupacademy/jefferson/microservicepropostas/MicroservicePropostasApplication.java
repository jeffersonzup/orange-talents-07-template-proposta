package br.com.zupacademy.jefferson.microservicepropostas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroservicePropostasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePropostasApplication.class, args);
	}

}
