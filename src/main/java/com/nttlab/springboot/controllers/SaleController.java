package com.nttlab.springboot.controllers;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.DocumentException;
import com.nttlab.springboot.models.entity.Cart;
import com.nttlab.springboot.models.entity.Sale;
import com.nttlab.springboot.models.service.iCartService;
import com.nttlab.springboot.models.service.iSaleService;
import com.nttlab.springboot.models.service.iUserService;

import jakarta.validation.Valid;

import com.nttlab.springboot.models.entity.PDFGenerator;

@Controller
public class SaleController {
	
	@Autowired
	private iSaleService saleService;

	@Autowired
	private iCartService cartService;

	@Autowired
	private iUserService userService;

	@PostMapping(value = { "/sale/create" })
	public String saveSale(@RequestParam Long cart_id, Model model)  {
	    Cart cart = cartService.findOne(cart_id);
	    Sale sale = new Sale(cart.getUser(), cart, cart.calculateCartTotal());

	    saleService.save(sale);

	    model.addAttribute("titulo", "Sale");
	    model.addAttribute("sale", sale);
	    return "sale";
	}
	
	
	
	@GetMapping(value={"/sale/download/{sale_id}"})
	public ResponseEntity<InputStreamResource> downloadPdf(@PathVariable (name="sale_id") Long sale_id) {
		System.out.println("llegue");
		Sale sale = saleService.findOne(sale_id);
		PDFGenerator pdfGenerator = new PDFGenerator();
	    return pdfGenerator.generatePDF(sale);
	    
	}
	
	

	/*@GetMapping(value={"/sale/download"})
	public ResponseEntity<InputStreamResource> downloadPdf(@RequestParam Long sale_id) throws IOException, FileNotFoundException, DocumentException {
	    Sale sale = saleService.findOne(sale_id);
	    PDFGenerator pdfGenerator = new PDFGenerator();
	    ByteArrayInputStream bis = pdfGenerator.generatePDF(sale);

	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "attachment; filename=boleta.pdf");

	    InputStreamResource isr = new InputStreamResource(bis);

	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentType(MediaType.APPLICATION_PDF)
	            .body(isr);
	}*/

	
	

	
}


