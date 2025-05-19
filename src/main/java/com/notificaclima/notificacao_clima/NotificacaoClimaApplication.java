package com.notificaclima.notificacao_clima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotificacaoClimaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificacaoClimaApplication.class, args);
	}

}
