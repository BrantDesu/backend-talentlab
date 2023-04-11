package com.nttlab.springboot.models.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nttlab.springboot.models.entity.Product;
import com.nttlab.springboot.models.entity.Sale;

@Repository
public interface iSaleService {
	
	public List<Sale> findAll();
	
    public void save(Sale sale);
	
	public  Sale findOne(Long id);
	
    public Sale findByUserId(long userId);
		
	public Sale findByCartId(long cartId);
	
	
	

}
