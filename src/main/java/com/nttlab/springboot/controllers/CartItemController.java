package com.nttlab.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nttlab.springboot.models.entity.CartItem;
import com.nttlab.springboot.models.entity.Product;
import com.nttlab.springboot.models.service.iCartItemService;
import com.nttlab.springboot.models.service.iProductService;

import jakarta.validation.Valid;


@Controller
public class CartItemController {
	
	@Autowired
	private iCartItemService cartItemService;

	@Autowired
	private iProductService productService;
	
	@PostMapping(value= {"/cart-item/create"})
	public String saveCartItem(Model model) {
		
		model.addAttribute("titulo","Macro Plei");
		model.addAttribute("cartItems", cartItemService.findAll());
		return "home";
	}
	
	@PostMapping(value= {"/cart-item/create/{product_id}"})
	public String saveCartItem(@PathVariable(value="product_id") Long product_id, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		String mensajeFlash = null;

		CartItem cartItem = new CartItem();
		
		cartItemService.save(cartItem);
		
		return "home";
		
	}

}
