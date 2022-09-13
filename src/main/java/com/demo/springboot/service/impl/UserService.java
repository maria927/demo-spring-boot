package com.demo.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.springboot.entity.User;
import com.demo.springboot.repository.IUserRepository;
import com.demo.springboot.service.IUserService;

/**
 * Clase que implementa las interface UserDetailsService de springframework.security.core
 * @author mia97
 *
 */
@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	IUserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/**
		 * Se obtiene el usuario por UserName.
		 * Tambien se podria llamar a un cliente o microservicio que retorne el usuario
		 */
		User user= userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		
		//Se convierten List<Roles> a List<GrantedAuthority>
		
		List<GrantedAuthority> authorities= user.getRoles()
				.stream()
				.map(role-> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(), 
				true, true, true, authorities);
	}

}
