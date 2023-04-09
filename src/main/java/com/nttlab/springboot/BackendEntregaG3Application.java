package com.nttlab.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nttlab.springboot.models.entity.Product;
@SpringBootApplication
public class BackendEntregaG3Application {

	public static void main(String[] args) {
		
		Product p1 = new Product("Zelda",500000,"Aventura",100);
		System.out.println(p1);
		SpringApplication.run(BackendEntregaG3Application.class, args);
	}

}
