package com.nttlab.springboot.models.service;

import org.springframework.stereotype.Repository;

import com.nttlab.springboot.models.entity.Product;

import java.util.List;

@Repository
public interface iProductService {
	public List<Product> findAll();
	
	public Product save(Product product);
	
	public Product findOne(Long id_product);
	
	public Product findByIdProduct(String id_product);
	
	public void delete(Long id_product);
	
	public void deleteAll();
	
	public List<Product> findByName(String name);
	
	public List<Product> findByCategory(String category);


}
