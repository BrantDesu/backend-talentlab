package com.nttlab.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nttlab.springboot.models.entity.Cart;
import com.nttlab.springboot.models.entity.CartItem;
import com.nttlab.springboot.models.entity.Product;
import com.nttlab.springboot.models.entity.Sale;
import com.nttlab.springboot.models.entity.User;
import com.nttlab.springboot.models.service.iCartService;
import com.nttlab.springboot.models.service.iSaleService;
import com.nttlab.springboot.models.service.iUserService;

import jakarta.validation.Valid;

@Controller
public class SaleController {
	
	@Autowired
	private iSaleService saleService;
	
	@Autowired
	private iCartService cartService;
	
	@Autowired
	private iUserService userService;
	
	@PostMapping(value = { "/sale" })
	public String saveSale(@Valid Cart cart, Model model) {
		
        Sale sale = new Sale(cart.getUser(), cart, cart.getTotal());
		model.addAttribute("titulo", "Sale");
		model.addAttribute("sale", saleService.findAll());
		return "sale";
	}
	

	
}


