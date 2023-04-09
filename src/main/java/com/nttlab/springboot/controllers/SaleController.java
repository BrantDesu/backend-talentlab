package com.nttlab.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SaleController {
	
	@GetMapping(value= "saleList")
	public String ProductList(Model model) {
		model.addAttribute("title","Listado de Productos");
		return "saletList";
	}

}
