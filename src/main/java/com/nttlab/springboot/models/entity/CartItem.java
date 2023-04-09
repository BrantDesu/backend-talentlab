package com.nttlab.springboot.models.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cart_items")
public class CartItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long idCartItem;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="cart_id", nullable = false)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="product_id", nullable = false)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="total")
	private int total;
	
	public CartItem() {
		
	}

	public CartItem(Cart cart, Product product, int quantity, int total) {
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.total = total;
	}

	public Long getIdCartItem() {
		return idCartItem;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	

}
