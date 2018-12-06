package com.cesarjuniort.springboot.apirest.repositories;

import org.springframework.data.repository.CrudRepository;

import com.cesarjuniort.springboot.apirest.models.entity.User;

public interface IUserRepository extends CrudRepository<User,Long>{
	 
	public User findByUsername(String username);
}
