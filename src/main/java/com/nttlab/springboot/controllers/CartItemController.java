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

import com.nttlab.springboot.models.entity.Cart;
import com.nttlab.springboot.models.entity.CartItem;
import com.nttlab.springboot.models.entity.Product;
import com.nttlab.springboot.models.service.iCartItemService;
import com.nttlab.springboot.models.service.iCartService;
import com.nttlab.springboot.models.service.iProductService;

import jakarta.validation.Valid;

@Controller
public class CartItemController {

	@Autowired
	private iCartItemService cartItemService;

	@Autowired
	private iProductService productService;

	@Autowired
	private iCartService cartService;

	@GetMapping(value = { "/cart-item/create/{cart_id}/{product_id}" })
	public String saveCartItem(@PathVariable(value = "cart_id") Long cart_id,
			@PathVariable(value = "product_id") Long product_id, RedirectAttributes flash, SessionStatus status) {

		List<Product> list = productService.findAll();
		System.out.println(list);

		Product product = productService.findOne(product_id);
		Cart cart = cartService.findOne(cart_id);
		int total = product.getPrice();

		System.out.println(cart.getCart_items());

		CartItem cartItem = new CartItem(cart, product, 1, total);

		cartItemService.save(cartItem);

		return "redirect:/";

	}

	@PostMapping(value = { "/cart-item/edit" })
	public String editCartItem(@RequestParam(name="id_cart_item") Long cartItemId, @RequestParam(name="quantity") Integer quantity, //Integer porque pueden llegar null
			RedirectAttributes flash, SessionStatus status) {
		CartItem cartItem = cartItemService.findOne(cartItemId);
		System.out.println(cartItem);
		// editar
		cartItem.setQuantity(quantity);
		cartItemService.save(cartItem);
		return "redirect:/cart/" + cartItem.getCart().getIdCart();
	}
	
//	@PostMapping(value = { "/cart-item/delete/{cart_item_id}" })
//	public String deleteCartItem(@PathVariable(value = "cart_item_id") Long cart_item_id,
	@PostMapping(value = { "/cart-item/delete" })
	public String deleteCartItem(@RequestParam(name="id_cart_item") Long cartItemId,
			RedirectAttributes flash, SessionStatus status) {
		CartItem cartItem = cartItemService.findOne(cartItemId);
		cartItemService.delete(cartItem);
		return "redirect:/cart/" + cartItem.getCart().getIdCart();
	}

}
