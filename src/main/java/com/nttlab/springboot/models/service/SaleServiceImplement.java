package com.nttlab.springboot.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttlab.springboot.models.dao.iSaleDAO;
import com.nttlab.springboot.models.entity.Sale;

import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleServiceImplement implements iSaleService {
	
	@Autowired
	private iSaleDAO saleDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Sale> findAll()
	{
		return (List<Sale>) saleDao.findAll();
	}

	@Override
	public void save(Sale sale) {
		
		saleDao.save(sale);
	}
	

	@Override
	@Transactional(readOnly = true)
	public Sale findOne(Long id) {
		
		return saleDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Sale findByUserId(long userId) {
		Sale s = saleDao.findByUserId(userId);
		if(s != null) {
			return s;
		}
		return null;
	}
	
	@Override
	@Transactional
	public Sale findByCartId(long cartId) {
		Sale s = saleDao.findByCartId(cartId);
		if(s != null) {
			return s;
		}
		return null;
	}

}
