package com.saad.io.Redit_SpringBoot;

import org.springframework.boot.SpringApplication;

public class TestReditSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.from(ReditSpringBootApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
