package com.nttlab.springboot.models.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttlab.springboot.models.dao.iClientDAO;
import com.nttlab.springboot.models.entity.Client;

@Service
public class ClientServiceImplement implements iClientService{
	
	@Autowired
	private iClientDAO clientDao;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) clientDao.findAll();
	}

	@Override
	@Transactional()
	public void save(Client client) {
		clientDao.save(client);
	}

	@Override
	@Transactional(readOnly = true)
	public Client findOne(Long id) {
		return clientDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clientDao.deleteById(id);
	}

	@Override
	@Transactional()
	public void deleteAll() {
		clientDao.deleteAll();
	}

	@Override
	@Transactional()
	public Client findByRut(String rut) {
		Client a = clientDao.findByRut(rut);
		if(a != null) {
			return a;
		}
		return null;
	}
	
	@Override
	@Transactional()
	public Client findByEmail(String email) {
		Client a = clientDao.findByEmail(email);
		if(a != null) {
			return a;
		}
		return null;
	}
	
	@Override
	@Transactional()
	public Client findByName(String name) {
		Client a = clientDao.findByName(name);
		if(a != null) {
			return a;
		}
		return null;
	}

	@Override
	@Transactional()
	public List<Client> findByApellido(String lastName) {
		return clientDao.findBylastName(lastName);
	}
	
	

}
