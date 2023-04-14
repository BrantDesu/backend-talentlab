package com.nttlab.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.ui.LoginPageGeneratingWebFilter;

import com.nttlab.springboot.auth.handler.LoginSuccessHandler;
import com.nttlab.springboot.models.service.JpaUserDetailService;


@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	private LoginSuccessHandler logiSuccessHandler;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private JpaUserDetailService userDetailService;

	/*
	@Bean
	public UserDetailsService userDetailsService() throws Exception{
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User
				.withUsername("user")
				.password(passwordEncoder().encode("12345"))
				.roles("USER")
				.build());
		
		manager.createUser(User
				.withUsername("admin")
				.password(passwordEncoder().encode("12345"))
				.roles("ADMIN")
				.build());
		
		
		return manager;
	}*/
	
	@Autowired
	public void userDetailService(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailService)
		.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
			.requestMatchers("/css/**","/js/**","/img/**").permitAll()
			.requestMatchers("","/","/home","/index").hasAnyRole("USER","ADMIN")
			.requestMatchers("/cart-item/**").hasAnyRole("USER","ADMIN")
			.requestMatchers("/cart/**").hasAnyRole("USER","ADMIN")
			.requestMatchers("/user/**","/user/new/**").hasRole("ADMIN")
			.requestMatchers("/sale/list/**").hasRole("ADMIN")
			.requestMatchers("/sale/create/**", "/sale/download/**").hasAnyRole("USER","ADMIN")
			.requestMatchers("/product/**").hasRole("ADMIN")
			.requestMatchers("/signup").permitAll()
			.anyRequest().authenticated()
			.and()
				.formLogin()
					.successHandler(logiSuccessHandler)
					.loginPage("/login").permitAll()
			.and()
				.logout().permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/error_403");	

		return http.build();

	}
	
}
