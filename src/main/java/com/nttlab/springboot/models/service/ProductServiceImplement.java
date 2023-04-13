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
	public Product save(Product product) {
		return productDao.save(product);
	}

	@Override
	@Transactional(readOnly = true)
	public Product findOne(Long id_product) {
		return productDao.findById(id_product).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id_product) {
		productDao.deleteById(id_product);
	}

	@Override
	@Transactional
	public void deleteAll() {
		productDao.deleteAll();
	}

	@Override
	@Transactional
	public List<Product> findByName(String name) {
		List<Product> p = productDao.findByName(name);
		if(!p.isEmpty()) {
			return p;
		}
		return null;
	}

	@Override
	public List<Product> findByCategory(String category) {
		List<Product> p = productDao.findByCategory(category);
		if(!p.isEmpty()) {
			return p;
		}
		return null;
	}

	@Override
	public Product findByIdProduct(String id_product) {
		// TODO Auto-generated method stub
		return null;
	}

}
