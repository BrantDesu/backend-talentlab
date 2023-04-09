package com.nttlab.springboot.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.PrePersist;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="cart_id", nullable = false)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private Cart cart;

	@Column(name = "rut", unique = true)
	@NotEmpty
	private String rut;

	@Column(name = "name")
	@NotEmpty
	@Size(min = 2, max = 50)
	private String name;

	@Column(name = "last_name")
	@NotEmpty
	@Size(min = 2, max = 50)
	private String lastName;
	
	@Column(name = "role")
	private boolean role;

	@Email
	@NotEmpty
	private String email;

	@Column(name = "created_at")

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@OneToMany(mappedBy = "user")
	private List<Sale> sales;

	
	@PrePersist
	private void onCreate() {
		createdAt = new Date();
	}
	
	public User()
	{
		
	}
	
	public User(String name, String lastName, String rut, String email, boolean role) {
		this.name = name;
		this.lastName = lastName;
		this.rut = rut;
		this.email = email;
		this.role = role;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "User ID = " + idUser + ", rut =" + rut + ", name=" + name + ", lastName=" + lastName + ", role="
				+ role + ", email=" + email + ", createdAt=" + createdAt + "]";
	}
	
	
	
	
	

}
