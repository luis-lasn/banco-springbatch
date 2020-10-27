package com.projeto.banco;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableBatchProcessing
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BancoSpringbatchApplication {
	
	@Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(BancoSpringbatchApplication.class, args)));
	}

}
