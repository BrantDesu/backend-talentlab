package com.nttlab.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.DependsOn;

import com.nttlab.springboot.models.entity.Client;
import com.nttlab.springboot.models.entity.Product;
import com.nttlab.springboot.models.service.iProductService;
import com.nttlab.springboot.models.service.iUserService;

@SpringBootTest
class BackendEntregaG3ApplicationTests {

	@Autowired
	private iUserService clientService;
	
	@Autowired
	private iProductService productService;
	
	@Test
	void addClient() throws ParseException{
	
		Client client = new Client("9094750-8","Carlos","Ibañez","ROLE_ADMIN","carlos@correo.com","123");
		
		Client nuevo = clientService.save(client);
		
		assertThat(nuevo.getPassword().equals("123"));
	}
	
	@Test
	void findClient() throws ParseException{
		
		
		Client find = clientService.findByRut("19932825-5");
		
		assertThat(find.getEmail().equals("chamorrobrant@gmail.com"));
	}
	
	
	@Test
	@DependsOn("findClient")
	void deleteClients() throws ParseException{
		
		clientService.deleteAll();
		List<Client> client = clientService.findAll();
		
		assertThat(client.size() == 0);
		
	}
	
	
	@Test
	void findProduct() throws ParseException{	
		
		Product product = new Product("Metroid Prime", 39990, "Aventura/FPS", 25);
		
		Product nuevo = productService.save(product);
		
		Product find = productService.findOne(nuevo.getIdProduct());
		
		assertThat(find.getName().equals("Metroid Prime") && find.getCategory().equals("Aventura/FPS") );
	}
	
	
	
	@Test
	void addProduct() throws ParseException{
			
		Product product = new Product("Zelda: Tears Of The Kingdom", 59990, "Aventura", 25);
		
		Product nuevo = productService.save(product);
		
		assertThat(nuevo.getName().equals("Zelda: Tears Of The Kingdom") && nuevo.getCategory().equals("Aventura") );
	}
	

	
	@Test
	@DependsOn("findProduct")
	void deleteProduct() throws ParseException{
		
		productService.deleteAll();
		List<Product> product = productService.findAll();
		
		assertThat(product.size() == 0);
		
	}
	

}
