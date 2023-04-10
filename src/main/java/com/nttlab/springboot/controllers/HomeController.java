package com.nttlab.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
