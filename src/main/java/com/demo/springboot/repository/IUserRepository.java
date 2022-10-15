package com.demo.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.springboot.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long>{
	
	public User findByUsername(@Param("username") String username);

}
