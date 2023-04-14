package com.nttlab.springboot.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.nttlab.springboot.models.entity.Cart;
import com.nttlab.springboot.models.entity.Client;
import com.nttlab.springboot.models.entity.Sale;
import com.nttlab.springboot.models.service.iCartService;
import com.nttlab.springboot.models.service.iSaleService;
import com.nttlab.springboot.models.service.iUserService;


import com.nttlab.springboot.models.entity.PDFGenerator;

@Controller
public class SaleController {
	
	@Autowired
	private iSaleService saleService;

	@Autowired
	private iCartService cartService;
	
	@Autowired
	private iUserService clientService;
	
	@GetMapping(value= "/sale/list")
	public String SalesList(Model model) {
		model.addAttribute("title","Listado de Ventas");
		model.addAttribute("sales", saleService.findAll());
		return "listSale";
	}

	@PostMapping(value = "/sale/create/{cart_id}")
	public String saveSale(@PathVariable Long cart_id, Model model, @RequestParam(value="error",required = false) String error, RedirectAttributes flash){
	    Cart cart = cartService.findOne(cart_id);
	    if (cart.getCart_items().isEmpty()) {
	    	flash.addFlashAttribute("danger", "El carro de compras está vacío... intenta agregar algún producto.");
	    	return "redirect:/home";
		}

	    Sale sale = new Sale(cart.getUser(), cart, cart.calculateCartTotal());
	    Client client = sale.getUser();
	    cart.setActive(false);
	    cart.setUser(null);
		client.setCart(new Cart(client));
		clientService.save(client);
	    saleService.save(sale);
	    model.addAttribute("titulo", "Sale");
	    model.addAttribute("sale", sale);
	    return "sale";

	}
	
	
	
	@GetMapping(value={"/sale/download/{sale_id}"})
	public ResponseEntity<InputStreamResource> downloadPdf(@PathVariable (name="sale_id") Long sale_id) {
		
		Sale sale = saleService.findOne(sale_id);
		PDFGenerator pdfGenerator = new PDFGenerator();
	    return pdfGenerator.generatePDF(sale);
	    
	}
	


	
}


