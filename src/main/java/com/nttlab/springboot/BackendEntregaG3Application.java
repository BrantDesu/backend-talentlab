package com.nttlab.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendEntregaG3Application implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(BackendEntregaG3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String bcrypt = passwordEncoder.encode("123");
		System.out.println(bcrypt);	
	}
	
}
