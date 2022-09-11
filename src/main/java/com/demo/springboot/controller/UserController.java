package com.demo.springboot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.entity.User;
import com.demo.springboot.service.IUserService;

@RestController
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@GetMapping("/user/username/{username}")
	public User listarUsername(@PathVariable String username){
		return userService.findByUsername(username);
	}

}
