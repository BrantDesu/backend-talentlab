package com.nttlab.springboot.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.nttlab.springboot.util.validator.ValidatorRut;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clients")
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cart")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Cart cart;
	
	@ValidatorRut(message = "El rut ingresado no es válido")
	@Column(name = "rut", unique = true)
	@NotEmpty
	private String rut;

	@Column(name = "name")
	@NotEmpty
	@Size(min = 2, max = 50)
	private String name;

	@Column(name = "last_name")
	@NotEmpty(message = "El apellido es requerido")
	@Size(min = 2, max = 50)
	private String lastName;
	
	@Column(name = "authority")
	private String authority;

	@Email(message = "Formato ingresado no es válido")
	@NotEmpty(message = "Este campo debe ser ingresado")
	@Column(name = "email", unique = true)
	private String email;

	@Column(length = 255)
	@NotEmpty(message = "Este campo debe ser ingresado")
	private String password;
	
	
	@Column(name = "created_at")

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@PrePersist
	private void onCreate() {
		createdAt = new Date();
	}
	
	public Client()
	{
		
	}
	
	

	public Client(@NotEmpty String rut, @NotEmpty @Size(min = 2, max = 50) String name,
			@NotEmpty @Size(min = 2, max = 50) String lastName, String authority, @Email @NotEmpty String email,
			@NotEmpty String password) {
		super();
		this.rut = rut;
		this.name = name;
		this.lastName = lastName;
		this.authority = authority;
		this.email = email;
		this.password = password;
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

	
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Client [idUser=" + idUser + ", cart=" + cart + ", rut=" + rut + ", name=" + name + ", lastName="
				+ lastName + ", authority=" + authority + ", email=" + email + ", password=" + password + ", createdAt="
				+ createdAt + "]";
	}
	
	
	
	
	
	
	
	

}
