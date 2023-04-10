package com.nttlab.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nttlab.springboot.models.entity.User;
import com.nttlab.springboot.models.service.iUserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private iUserService userService;

	@GetMapping(value= "/user/list")
	public String ProductList(Model model) {
		model.addAttribute("title","Listado de Usuarios");
		model.addAttribute("users", userService.findAll());
		return "listUser";
	}
	
	@GetMapping(value = "/user/new")
	public String crearUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("title", "Formulario Creación usuario");
		return "formUser";
	}

	@PostMapping(value = "/user/form")
	public String guardarUser(@Valid User user, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		String mensajeFlash = null;		
		if(result.hasErrors()) {
			model.addAttribute("title","Formulario Creación usuario");
			return "formUser";
		}
		
		
		if(user.getIdUser() != null) {
			userService.save(user);
			return "redirect:/user/list";			
		}
		else {
			if(userService.findByRut(user.getRut()) != null ) {
				flash.addFlashAttribute("error", "Rut ya registrado en nuestro sistema");
				return "redirect:/user/new";
			}
			else if(userService.findByEmail(user.getEmail()) != null) {
				flash.addFlashAttribute("error", "Email ya registrado en nuestro sistema");
				return "redirect:/user/new";
			}
			
			userService.save(user);
			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			return "redirect:/user/list";
		}
	}
	
	@GetMapping(value="/user/edit/{id_user}")
	public String editarUser(@PathVariable(value="id_user") Long id_user, Model model, RedirectAttributes flash) {
		User user = null;
		if(id_user > 0) {
			user = userService.findOne(id_user);
			if(user == null) {
				flash.addFlashAttribute("clase", "danger");
				flash.addFlashAttribute("error", "El user buscado no se encuentra en nuestros registros");
				return "redirect:/user/list";
			}
			else {
				model.addAttribute("user", user);
				model.addAttribute("titulo", "Formulario Edición usuario ");
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
		User user = userService.findOne(id_user);
		if(user == null) {
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
