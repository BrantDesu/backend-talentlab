package com.nttlab.springboot.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nttlab.springboot.models.entity.Cart;
import com.nttlab.springboot.models.entity.Client;
import com.nttlab.springboot.models.service.iUserService;
import com.nttlab.springboot.util.validator.EmailValidator;
import com.nttlab.springboot.util.validator.RutValidator;

import jakarta.validation.Valid;

@Controller
public class LoginController {
	@Autowired
	private iUserService clientService;
	
	@GetMapping(value="/login")
	public String login(@RequestParam(value="error",required = false) String error, 
			@RequestParam(value="logout", required=false) String logout,
			Model model,
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
	
//	@PostMapping(value= "/client")
//	public ResponseEntity<?> createClient(@RequestBody Client client){
//		Client new_client = null;
//		Map<String, Object> response = new HashMap<>();
//		try {
//			boolean rutValido = new RutValidator().isValid(client.getRut(),null);
//			if(!rutValido) {
//				response.put("mensaje", "Error al realizar el registro del usuario. El rut ingresado no es válido.");
//				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
//			}
//			
//			boolean emailValido = new EmailValidator().isValid(client.getEmail(), null);
//			if(!emailValido) {
//				response.put("mensaje", "Error al realizar el registro del alumno. El email ingresado no es válido.");
//				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
//			}
//
//			List<Client> client = client.findAll();
//			for(Client c: client) {
//				if(c.getEmail().equalsIgnoreCase(client.getEmail())) {
//					response.put("mensaje", "Error al realizar el registro del alumno. El correo indicado ya existe en nuestros registros.");
//					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
//				}
//				if(c.getRut().equalsIgnoreCase(client.getRut())) {
//					response.put("mensaje", "Error al realizar el registro del alumno. El rut indicado ya existe en nuestros registros.");
//					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
//				}
//			}
//			
//			alumno_nuevo = alumnoService.save(alumno);
//			response.put("mensaje", "Alumno registrado satisfactoriamente.");
//			response.put("alumno", alumno_nuevo);
//			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
//		}
//		catch(DataAccessException ex) {
//			response.put("mensaje", "Error al realizar el proceso de registro de un nuevo alumno.");
//			response.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause().getMessage());
//			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@PostMapping(value="/signup")
	public String signUp(@Valid Client client, @RequestBody String password)
	{
		System.out.println("holaaa");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(password);
		//crear el cliente con las variables de la clase Client. antes de, crear un carrito
		client.setAuthority("ROLE_USER");
		client.setPassword(encodedPassword);
		Cart cart = new Cart(client);
		client.setCart(cart);
		clientService.save(client);
		return "home";
	}

}
