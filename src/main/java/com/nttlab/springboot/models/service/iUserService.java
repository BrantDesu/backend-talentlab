package com.nttlab.springboot.models.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nttlab.springboot.models.entity.User;

@Repository
public interface iUserService {
	
	public List<User> findAll();
	
	public void save(User user);
	
	public User findOne(Long id);
	
	public void delete(Long id);
	
	public void deleteAll();
	
	public User findByRut(String rut);
	
	public User findByEmail(String email);
	
	public List<User> findByApellido(String apellido);

}
