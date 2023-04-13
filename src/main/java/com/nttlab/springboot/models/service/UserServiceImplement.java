package com.nttlab.springboot.models.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttlab.springboot.models.dao.iUserDAO;
import com.nttlab.springboot.models.entity.Client;

@Service
public class UserServiceImplement implements iUserService{
	
	@Autowired
	private iUserDAO userDao;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) userDao.findAll();
	}

	@Override
	@Transactional()
	public Client save(Client client) {
		return userDao.save(client);
	}

	@Override
	@Transactional(readOnly = true)
	public Client findOne(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		userDao.deleteById(id);
	}

	@Override
	@Transactional()
	public void deleteAll() {
		userDao.deleteAll();
	}

	@Override
	@Transactional()
	public Client findByRut(String rut) {
		Client a = userDao.findByRut(rut);
		if(a != null) {
			return a;
		}
		return null;
	}
	
	@Override
	@Transactional()
	public Client findByEmail(String email) {
		Client a = userDao.findByEmail(email);
		if(a != null) {
			return a;
		}
		return null;
	}
	
	@Override
	@Transactional()
	public Client findByName(String name) {
		Client a = userDao.findByName(name);
		if(a != null) {
			return a;
		}
		return null;
	}

	@Override
	@Transactional()
	public List<Client> findByApellido(String lastName) {
		return userDao.findBylastName(lastName);
	}
	
	

}
