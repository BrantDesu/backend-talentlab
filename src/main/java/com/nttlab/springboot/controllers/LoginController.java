package com.nttlab.springboot.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nttlab.springboot.models.entity.Cart;
import com.nttlab.springboot.models.entity.Client;
import com.nttlab.springboot.models.service.iUserService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@Controller
public class LoginController {
	@Autowired
	private iUserService clientService;
	
	@GetMapping(value="/login")
	public String login(@RequestParam(value="error",required = false) String error, 
			@RequestParam(value="logout", required=false) String logout,
			org.springframework.ui.Model model,
			Principal principal,
			RedirectAttributes redirectAttributes) {
		
		if(principal != null) {
			redirectAttributes.addFlashAttribute("info", "Ya ha iniciado sesión anteriormente");		
			return "redirect:/";
		}
		
		if(error != null) {
			model.addAttribute("danger","Error en el login. Nombre de usuario o contraseña incorrectos. Por favor vuelva a intentarlo!!!");
		}
		
		if(logout != null) {
			model.addAttribute("success","Se ha cerrado la sessión exitosamente!!!");
		}
		
		return "login";
		
	}
	@GetMapping(value="/signup")
	public String getSignUpView(Model model) {
		
		Client client = new Client();
		model.addAttribute("client", client);
		return "signup";
		
	}
	
	@PostMapping(value="/signup")
	public String signUp(@ModelAttribute("client") Client client,BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status)
	{
		
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario Creación alumno");
			return "signUp";
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(client.getPassword());
		//crear el cliente con las variables de la clase Client. antes de, crear un carrito
		client.setAuthority("ROLE_USER");
		client.setPassword(encodedPassword);
		client.setCart(new Cart(client));
		clientService.save(client);
		return "login";
	}
	
	 @ExceptionHandler(value = { ConstraintViolationException.class})
	    public ModelAndView handleConstraintViolationException(ConstraintViolationException ex) {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("error");
	        
	        // Obtener los errores de ConstraintViolation
	        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
	        List<String> messages = new ArrayList<>(violations.size());
	        
	        for (ConstraintViolation<?> violation : violations) {
	            messages.add(violation.getMessage());
	        }
	        
	        // Agregar los errores al modelo
	        modelAndView.addObject("errors", messages);
	        
	        return modelAndView;
	    }
	

}
