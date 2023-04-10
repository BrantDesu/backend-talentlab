package com.nttlab.springboot.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttlab.springboot.models.dao.iProductDAO;
import com.nttlab.springboot.models.entity.Product;

import java.util.List;

@Service
public class ProductServiceImplement implements iProductService{
	@Autowired
	private iProductDAO productDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productDao.findAll();
	}

	@Override
	@Transactional
	public void save(Product product) {
		productDao.save(product);
	}

	@Override
	@Transactional(readOnly = true)
	public Product findOne(Long id) {
		return productDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productDao.deleteById(id);
	}

	@Override
	@Transactional()
	public void deleteAll() {
		productDao.deleteAll();
	}

	@Override
	@Transactional()
	public List<Product> findByName(String name) {
		List<Product> p = productDao.findByName(name);
		if(!p.isEmpty()) {
			return p;
		}
		return null;
	}
}
