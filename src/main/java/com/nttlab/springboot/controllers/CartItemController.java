package com.nttlab.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nttlab.springboot.models.entity.Cart;
import com.nttlab.springboot.models.entity.CartItem;
import com.nttlab.springboot.models.entity.Product;
import com.nttlab.springboot.models.service.iCartItemService;
import com.nttlab.springboot.models.service.iCartService;
import com.nttlab.springboot.models.service.iProductService;

import jakarta.validation.Valid;

@Controller
public class CartItemController {

	@Autowired
	private iCartItemService cartItemService;

	@Autowired
	private iProductService productService;
	
	@Autowired
	private iCartService cartService;

	@PostMapping(value = { "/cart-item/create" })
	public String saveCartItem(Model model) {

		model.addAttribute("titulo", "Macro Plei");
		model.addAttribute("cartItems", cartItemService.findAll());
		return "home";
	}

	@GetMapping(value = { "/cart-item/create/{cart_id}/{product_id}" })
	public String saveCartItem(@PathVariable(value = "cart_id") Long cart_id,
			@PathVariable(value = "product_id") Long product_id,
			RedirectAttributes flash, SessionStatus status) {
		//String mensajeFlash = null;
		
		List<Product> list = productService.findAll();
		System.out.println(list);
		
		Product product = productService.findOne(product_id);
		Cart cart = cartService.findOne(cart_id);
		int total = product.getPrice();
		
		CartItem cartItem = new CartItem(cart, product, 1, total);

		cartItemService.save(cartItem);

		return "redirect:/";

	}

}
