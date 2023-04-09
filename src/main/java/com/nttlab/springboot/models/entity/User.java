package com.nttlab.springboot.models.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@NotEmpty
	private boolean role;

	@Email
	@NotEmpty
	private String email;

	@Column(name = "created_at")
	@Temporal(TemporalType.DATE)
	@NotNull
	@PastOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	public User()
	{
		
	}
	
	public User(String name, String lastName, String rut, String email, boolean role) {
		super();
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
	
	
	
	
	
	

}
