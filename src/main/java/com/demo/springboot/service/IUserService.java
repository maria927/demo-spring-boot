package com.demo.springboot.service;


import com.demo.springboot.entity.User;

public interface IUserService {
	
	public User findByUsername(String username);

}
