package com.nttlab.springboot.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nttlab.springboot.models.entity.Sale;

public interface iSaleDAO extends CrudRepository<Sale, Long>{
	
	@Query("select s from Sale s where s.client.id = ?1")
    public Sale findByUserId(long userId);
	
	@Query("select ci from CartItem ci where ci.cart.id = ?1") 
    public Sale findByCartId(long cartId);

}
