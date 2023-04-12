package com.nttlab.springboot.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nttlab.springboot.models.entity.Client;

public interface iClientDAO extends CrudRepository<Client, Long>{

	@Query("select u from User u where u.rut like %?1%")
	public Client findByRut(String rut);
	
	public Client findByEmail(String email);
	
	public Client findByName(String name);
	
	public List<Client> findBylastName(String lastName);
	
	
}