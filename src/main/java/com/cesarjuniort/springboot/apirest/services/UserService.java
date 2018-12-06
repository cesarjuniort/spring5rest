package com.cesarjuniort.springboot.apirest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesarjuniort.springboot.apirest.models.entity.User;
import com.cesarjuniort.springboot.apirest.repositories.IUserRepository;

@Service
public class UserService implements UserDetailsService{

	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private IUserRepository userRepo;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(username == null || username.length() == 0) {
			throw new UsernameNotFoundException("Invalid username provided!");
		}
		
		User user = userRepo.findByUsername(username);
		
		if(user == null) {
			logger.warn("Invalid username provided. '"+username+"' does not exists in the database.");
			throw new UsernameNotFoundException("Invalid username provided!");
		}
		
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());		
		
		org.springframework.security.core.userdetails.User springUser
			= new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(),
				true, true, true, authorities);
		
		return springUser;
	}

}
