package com.nttlab.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttlab.springboot.models.entity.Product;
import com.nttlab.springboot.models.service.iProductService;

@Controller
public class HomeController {
	
	@Autowired
	private iProductService productService;
	
	@GetMapping(value= {"","/","/home","/index"})
	public String home(Model model) {
		model.addAttribute("titulo","Macro Plei");
		model.addAttribute("products", productService.findAll());
		return "home";
	}

	@GetMapping(value= "/home/search")
	public String ProductList(
			@RequestParam(value="filter") String filter,
			@RequestParam(value="search") String search,
			Model model
		) 
	{
		List<Product> products = null;

		if (filter.equals("name")) {
			products = productService.findByName(search);
		}
		else {
			products = productService.findByCategory(search);
		}
		
		model.addAttribute("title","Listado de Productos");
		model.addAttribute("search", search);
		model.addAttribute("filter", filter);
		model.addAttribute("products", products);
		return "home";
	}
}
