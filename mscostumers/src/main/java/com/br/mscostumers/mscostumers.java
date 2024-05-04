package com.br.mscostumers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class mscostumers {

	public static void main(String[] args) {
		SpringApplication.run(mscostumers.class, args);
	}

}
