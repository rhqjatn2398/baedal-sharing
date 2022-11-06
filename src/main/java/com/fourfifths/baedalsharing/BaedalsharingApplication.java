package com.fourfifths.baedalsharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BaedalsharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaedalsharingApplication.class, args);
	}

}
