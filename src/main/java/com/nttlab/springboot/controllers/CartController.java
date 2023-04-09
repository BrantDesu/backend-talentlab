package com.nttlab.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

	@GetMapping(value= "cartList")
	public String ProductList(Model model) {
		model.addAttribute("title","Carrito de Compra");
		return "cart";
	}
	
}
