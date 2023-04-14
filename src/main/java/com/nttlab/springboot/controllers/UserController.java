package com.nttlab.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nttlab.springboot.models.entity.Cart;
import com.nttlab.springboot.models.entity.Client;
import com.nttlab.springboot.models.service.iUserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private iUserService userService;

	@GetMapping(value= "/user/list")
	public String UserList(Model model) {
		model.addAttribute("title","Listado de Usuarios");
		model.addAttribute("clients", userService.findAll());
		return "listUser";
	}
	
	@GetMapping(value = "/user/new")
	public String crearUser(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		model.addAttribute("title", "Formulario Creación usuario");
		return "formUser";
	}

	@PostMapping(value = "/user/form")
	public String guardarUser(@Valid Client client, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		String mensajeFlash = null;
		if(result.hasErrors()) {
			model.addAttribute("title","Formulario Creación usuario");
			return "formUser";
		}
		
		
		if(client.getIdUser() != null) {
			userService.save(client);
			return "redirect:/user/list";			
		}
		else {
			if(userService.findByRut(client.getRut()) != null ) {
				flash.addFlashAttribute("error", "Rut ya registrado en nuestro sistema");
				return "redirect:/user/new";
			}
			else if(userService.findByEmail(client.getEmail()) != null) {
				flash.addFlashAttribute("error", "Email ya registrado en nuestro sistema");
				return "redirect:/user/new";
			}
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(client.getPassword());
			client.setPassword(encodedPassword);
			Cart cart = new Cart(client);
			client.setCart(cart);
			userService.save(client);
			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			return "redirect:/user/list";
		}
	}
	
	@GetMapping(value="/user/edit/{id_user}")
	public String editarUser(@PathVariable(value="id_user") Long id_user, Model model, RedirectAttributes flash) {
		Client client = null;
		if(id_user > 0) {
			client = userService.findOne(id_user);
			if(client == null) {
				flash.addFlashAttribute("clase", "danger");
				flash.addFlashAttribute("error", "El user buscado no se encuentra en nuestros registros");
				return "redirect:/user/list";
			}
			else {
				model.addAttribute("client", client);
				model.addAttribute("isEdit", true);
				model.addAttribute("title", "Formulario Edición usuario ");
				return "formUser";
			}
			
		}
		else
		{
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("error", "Debes ingresar un valor mayor a 0(cero)!!!");
			return "redirect:/user/list";
		}
		
		
	}
	
	
	@GetMapping(value="/user/delete/{id_user}")
	public String eliminarUser(@PathVariable(value="id_user") Long id_user, Model model, RedirectAttributes flash) {
		Client client = userService.findOne(id_user);
		if(client == null) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("error", "El usuario buscado no se encuentra en nuestros registros");
			return "redirect:/user/list";
		}
		else
		{
			userService.delete(id_user);
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("success", "Usuario eliminado con éxito!!!");
			return "redirect:/user/list";
		}
	}
	
	
}
