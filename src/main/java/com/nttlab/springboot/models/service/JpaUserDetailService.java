package com.nttlab.springboot.models.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nttlab.springboot.models.dao.iUserDAO;
import com.nttlab.springboot.models.entity.Client;

import jakarta.transaction.Transactional;



@Service("jpaUserDetailService")
public class JpaUserDetailService implements UserDetailsService{

	@Autowired
	private iUserDAO userDao;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailService.class);
	
	
	private List<String> ROLES = List.of("ROLE_ADMIN", "ROLE_USER");
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Client client = userDao.findByEmail(email); 
		
		if(client == null) {
			logger.error("Error de acceso: no existe el correo [" + email + "]");
			throw new UsernameNotFoundException("Correo: [" + email + "] No existe en nuestros registros.");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(client.getAuthority()));

		User u = new User(client.getEmail(), client.getPassword(), true, true, true, true, authorities);
		return u;
	}

}
