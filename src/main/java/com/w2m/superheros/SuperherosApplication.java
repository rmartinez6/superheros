package com.w2m.superheros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SuperherosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperherosApplication.class, args);
	}

}
