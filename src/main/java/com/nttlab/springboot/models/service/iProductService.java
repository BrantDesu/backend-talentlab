package com.nttlab.springboot.models.service;

import org.springframework.stereotype.Repository;

import com.nttlab.springboot.models.entity.Product;

import java.util.List;

@Repository
public interface iProductService {
	public List<Product> findAll();
	
	public void save(Product product);
	
	public Product findOne(Long id);
	
	public void delete(Long id);
	
	public void deleteAll();
	
	public List<Product> findByName(String name);
}
