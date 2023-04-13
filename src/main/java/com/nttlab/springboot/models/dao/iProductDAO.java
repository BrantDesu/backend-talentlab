package com.nttlab.springboot.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nttlab.springboot.models.entity.Product;


public interface iProductDAO extends CrudRepository<Product, Long> {
	
	@Query("select p from Product p where p.name like %?1%")
	public List<Product> findByName(String name);
	
	@Query("select p from Product p where p.category like %?1%")
	public List<Product> findByCategory(String category);
	
	@Query("select p from Product p where p.idProduct like %?1%")
	public Product findByIdProduct(String idProduct);
	
}
