package com.demo.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.springboot.entity.User;
import com.demo.springboot.repository.IUserRepository;
import com.demo.springboot.service.IUserService;

@Service
public class UserService implements IUserService{
	
	@Autowired
	IUserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
