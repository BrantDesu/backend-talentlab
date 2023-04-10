package com.nttlab.springboot.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nttlab.springboot.models.entity.CartItem;

public interface iCartItemDAO extends CrudRepository<CartItem, Long>{
	
	@Query("select ci from CartItem ci where ci.cart.id = ?1") //se buscan todos los items del carrito que pertenecen a su carrito especifico
    public List<CartItem> findAllCartItemsByCartId(long cartId);

}
