package com.nttlab.springboot.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;

	@Column(name = "name")
	@NotEmpty
	@Size(min = 2, max = 50)
	private String name;

	@Column(name = "price")
	@NotNull
	private int price;
	
	@Column(name = "category")
	@NotEmpty
	private String category;

	@Column(name = "stock")
	@NotNull
	private int stock;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	public Product()
	{
		
	}

	public Product(@Size(min = 2, max = 50) String name, int price, String category, int stock) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.stock = stock;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
    
	@Override
	public String toString() {
		return "Product ID = " + idProduct + ", name=" + name + ", price=" + price + ", category=" + category
				+ ", stock=" + stock + ", createdAt=" + createdAt;
	}
	
	

}
