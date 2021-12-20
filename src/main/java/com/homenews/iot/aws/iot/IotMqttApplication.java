package com.homenews.iot.aws.iot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class IotMqttApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotMqttApplication.class, args);
	}

}
