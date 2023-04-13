package com.nttlab.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nttlab.springboot.models.entity.Product;
import com.nttlab.springboot.models.service.iProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {
	
	@Autowired
	private iProductService productService;
	
	@GetMapping(value= "/product/list")
	public String ProductList(Model model) {
		model.addAttribute("title","Listado de Productos");
		model.addAttribute("products", productService.findAll());
		return "listProduct";
	}
	
	@GetMapping(value = "/product/new")
	public String createProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("title", "Formulario Creación producto");
		return "formProduct";
	}
	
	@PostMapping(value = "/product/form")
	public String saveProduct(@Valid Product product, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		String mensajeFlash = null;		
		if(result.hasErrors()) {
			model.addAttribute("title","Formulario Creación producto");
			return "formProduct";
		}
		
		
		if(product.getIdProduct() != null) {
			productService.save(product);
			return "redirect:/product/list";			
		}
		else {
			if(productService.findByName(product.getName()) != null ) {
				flash.addFlashAttribute("error", "Rut ya registrado en nuestro sistema");
				return "redirect:/product/list";
			}
			productService.save(product);
			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			return "redirect:/product/list";
		}
	}
	
	@GetMapping(value="/product/edit/{id_product}")
	public String editProduct(@PathVariable(value="id_product") Long id_product, Model model, RedirectAttributes flash) {
		Product product = null;
		if(id_product > 0) {
			product = productService.findOne(id_product);
			if(product == null) {
				flash.addFlashAttribute("clase", "danger");
				flash.addFlashAttribute("error", "El producto buscado no se encuentra en nuestros registros");
				return "redirect:/product/list";
			}
			else {
				model.addAttribute("product", product);
				model.addAttribute("title", "Formulario Edición producto");
				return "formProduct";
			}
		}
		else
		{
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("error", "Debes ingresar un valor mayor a 0");
			return "redirect:/product/list";
		}
	}
	
	
	@GetMapping(value="/product/delete/{id_product}")
	public String deleteProduct(@PathVariable(value="id_product") Long id_product, Model model, RedirectAttributes flash) {
		Product product = productService.findOne(id_product);
		if(product == null) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("error", "El producto buscado no se encuentra en nuestros registros");
			return "redirect:/product/list";
		}
		else
		{
			productService.delete(id_product);
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("success", "Producto eliminado con éxito");
			return "redirect:/product/list";
		}

	}
	
}
