package com.nttlab.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nttlab.springboot.models.entity.Cart;
import com.nttlab.springboot.models.entity.CartItem;
import com.nttlab.springboot.models.service.iCartService;

@Controller
public class CartController {

	@Autowired
	private iCartService cartService;
	
	
	
	@GetMapping(value= "/cart/{cart_id}")
	public String userCart(@PathVariable(name="cart_id") Long cart_id, Model model) {
		Cart cart = cartService.findOne(cart_id);
		CartItem cartItem = new CartItem(); 
		model.addAttribute("title","Carrito de Compra");
		model.addAttribute("cart", cart);
		model.addAttribute("cartItem", cartItem);
		return "cart";
	}
	
}
