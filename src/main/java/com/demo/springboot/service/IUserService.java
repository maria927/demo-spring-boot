package com.demo.springboot.service;


import com.demo.springboot.entity.User;

public interface IUserService {
	
	/**
	 * Obtener usuario por "username"
	 * @param username
	 * @return User
	 */
	public User findByUsername(String username);

}
