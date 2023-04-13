package com.nttlab.springboot.models.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nttlab.springboot.models.entity.Client;

@Repository
public interface iUserService {
	
	public List<Client> findAll();
	
	public Client save(Client client);
	
	public Client findOne(Long id);
	
	public void delete(Long id);
	
	public void deleteAll();
	
	public Client findByName(String name);
	
	public Client findByRut(String rut);
	
	public Client findByEmail(String email);
	
	public List<Client> findByApellido(String apellido);

}
