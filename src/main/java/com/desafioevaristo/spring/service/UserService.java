package com.desafioevaristo.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafioevaristo.spring.entities.User;
import com.desafioevaristo.spring.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public Boolean existsUserById(Long id) {
		return userRepository.existsById(id);
	}
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
}
