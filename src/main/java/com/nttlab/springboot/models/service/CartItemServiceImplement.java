package com.nttlab.springboot.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttlab.springboot.models.dao.iCartItemDAO;
import com.nttlab.springboot.models.entity.Cart;
import com.nttlab.springboot.models.entity.CartItem;

@Service
public class CartItemServiceImplement implements iCartItemService{

	@Autowired
    private iCartItemDAO cartItemDao;

	@Override
	@Transactional(readOnly = true)
	public List<CartItem> findAll() {
		return (List<CartItem>) cartItemDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public CartItem findOne(Long id) {
		return cartItemDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(CartItem cartItem) {
		cartItemDao.save(cartItem);
		
	}

	@Override
	@Transactional
	public void delete(CartItem cartItem) {
		cartItemDao.delete(cartItem);
		
	}

	@Override
	@Transactional
	public void deleteAll(Cart cart) {
		cartItemDao.deleteAll();
		
	}

	
}
