package com.nttlab.springboot.models.entity;

public class Usuario {
	
	private int idUsuario;
	private String nombreUsuario;
	private String apellidoUsuario;
	private String rut;
	private String email;
	private boolean rol;
	
	public Usuario(String nombreUsuario, String apellidoUsuario, String rut, String email, boolean rol) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.apellidoUsuario = apellidoUsuario;
		this.rut = rut;
		this.email = email;
		this.rol = rol;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
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

	public boolean isRol() {
		return rol;
	}

	public void setRol(boolean rol) {
		this.rol = rol;
	}
	
	

}
