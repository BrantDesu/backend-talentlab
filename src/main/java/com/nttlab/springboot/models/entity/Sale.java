package com.nttlab.springboot.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales")
public class Sale implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSale;
	

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Client client;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="cart_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Cart cart;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "total")
	private int total;
	
	public Sale() {
		
	}

	public Sale(Client client, Cart cart, int total) {
		this.client = client;
		this.cart = cart;
		this.total = total;
	}

	public Long getIdSale() {
		return idSale;
	}

	public Client getUser() {
		return client;
	}

	public void setUser(Client client) {
		this.client = client;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Sale [idSale=" + idSale + ", client=" + client + ", cart=" + cart + ", createdAt=" + createdAt
				+ ", total=" + total + "]";
	}
	
	
	
	

}
