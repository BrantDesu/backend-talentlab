package com.nttlab.springboot.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nttlab.springboot.models.entity.Cart;
import com.nttlab.springboot.models.entity.CartItem;

public interface iCartDAO extends CrudRepository<Cart, Long> {
	
	@Query("select c from Cart c where c.client.id = ?1")  //busca un cart por el id del usuario, ya que cada usuario tiene su propio carrito
    public Cart findByUserId(long userId);

    @Query("select ci from CartItem ci where ci.cart.id = ?1") //busca todos los cartitems (productos) dentro del carrito por el id de cart
    public List<CartItem> findAllCartItems(long cartId);
    
}


