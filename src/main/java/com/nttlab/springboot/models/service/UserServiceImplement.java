package com.nttlab.springboot.models.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttlab.springboot.models.dao.iUserDAO;
import com.nttlab.springboot.models.entity.User;

@Service
public class UserServiceImplement implements iUserService{
	
	@Autowired
	private iUserDAO userDao;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	@Transactional()
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User findOne(Long id) {
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
	public User findByRut(String rut) {
		User a = userDao.findByRut(rut);
		if(a != null) {
			return a;
		}
		return null;
	}
	
	@Override
	@Transactional()
	public User findByEmail(String email) {
		User a = userDao.findByEmail(email);
		if(a != null) {
			return a;
		}
		return null;
	}
	

	@Override
	@Transactional()
	public List<User> findByApellido(String lastName) {
		return userDao.findBylastName(lastName);
	}

}
