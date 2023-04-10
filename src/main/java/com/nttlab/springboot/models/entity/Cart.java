package com.nttlab.springboot.models.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCart;
	
	@OneToOne//(mappedBy = "cart", cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user")
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@Column(name="total")
	private int total = 0;
	
	@Column(name = "active")
	private boolean active = true;
	
	public Cart() {
		
	}

	public Cart(User user) {
		this.user = user;
	}
	
	public Cart(User user, int total, boolean active) {
		this.user = user;
		this.total = total;
		this.active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public Long getIdCart() {
		return idCart;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
