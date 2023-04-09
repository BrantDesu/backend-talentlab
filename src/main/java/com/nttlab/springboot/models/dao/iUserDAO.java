package com.nttlab.springboot.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nttlab.springboot.models.entity.User;

public interface iUserDAO extends CrudRepository<User, Long>{

	@Query("select u from User u where u.rut like %?1%")
	public User findByRut(String rut);
	
	public User findByEmail(String email);
	
	public List<User> findBylastName(String lastName);
	
	
}
