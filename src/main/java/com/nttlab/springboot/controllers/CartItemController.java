package com.nttlab.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
import com.nttlab.springboot.models.service.iUserService;

@Controller
public class CartItemController {

	@Autowired
	private iCartItemService cartItemService;

	@Autowired
	private iProductService productService;

	@Autowired
	private iUserService clientService;

	@GetMapping(value = { "/cart-item/create/{product_id}" })
	public String saveCartItem(
		@PathVariable(value = "product_id") Long product_id, 
		RedirectAttributes flash, 
		SessionStatus status
	) 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
			Product product = productService.findOne(product_id);
			Cart cart = clientService.findByEmail(currentUserName).getCart();
			int total = product.getPrice();
			if (cart.getCartProductsIds().contains(product_id)) {
				flash.addFlashAttribute("danger", "El producto ya se encuentra en el carrito, si quieres aumentar su cantidad, dirigete a 'Carrito'.");
			} else {
				CartItem cartItem = new CartItem(cart, product, 1, total);
				flash.addFlashAttribute("success", "El producto ha sido añadido al carrito");

				cartItemService.save(cartItem);
			}
			return "redirect:/";
		} else {
			flash.addFlashAttribute("danger", "La sesión expiró");
			return "error_404";
		}


	}

	@PostMapping(value = { "/cart-item/edit" })
	public String editCartItem(@RequestParam(name="id_cart_item") Long cartItemId, @RequestParam(name="quantity") Integer quantity, //Integer porque pueden llegar null
			RedirectAttributes flash, SessionStatus status) {
		CartItem cartItem = cartItemService.findOne(cartItemId);
		cartItem.setQuantity(quantity);
		cartItemService.save(cartItem);
		return "redirect:/cart";
	}
	
	@PostMapping(value = { "/cart-item/delete" })
	public String deleteCartItem(@RequestParam(name="id_cart_item") Long cartItemId,
			RedirectAttributes flash, SessionStatus status) {
		CartItem cartItem = cartItemService.findOne(cartItemId);
		cartItemService.delete(cartItem);
		return "redirect:/cart";
	}

}
