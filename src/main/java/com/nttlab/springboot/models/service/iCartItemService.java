package com.nttlab.springboot.models.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nttlab.springboot.models.entity.Cart;
import com.nttlab.springboot.models.entity.CartItem;


@Repository
public interface iCartItemService {
	
	public List<CartItem> findAll();
	
    public CartItem findOne(Long id);
    
    public void save(CartItem cartItem);
    
    public void delete(CartItem cartItem);
    
    public void deleteAll(Cart cart);
    
}
