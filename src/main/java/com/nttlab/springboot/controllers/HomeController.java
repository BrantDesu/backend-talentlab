package com.nttlab.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(value= {"","/","/home","/index"})
	public String home(Model model) {
		model.addAttribute("titulo","Springboot + JPA");
		return "home";
	}

}
