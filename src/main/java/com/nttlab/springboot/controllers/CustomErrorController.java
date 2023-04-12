package com.nttlab.springboot.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController{
	
	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request, Model model) {
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(status != null && Integer.valueOf(status.toString()) == HttpStatus.NOT_FOUND.value()) {
			model.addAttribute("titulo","Page Not Found");
			return "error_404";
		}
		
		return "error";
	}

}
