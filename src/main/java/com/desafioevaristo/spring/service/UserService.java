package com.desafioevaristo.spring.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafioevaristo.spring.dto.UserDTO;
import com.desafioevaristo.spring.entities.User;
import com.desafioevaristo.spring.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public UserDTO userDto(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	
	public boolean existsUserById(Long id) {
		return userRepository.existsById(id);
	}
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public boolean existsUserAccountByEmail(String email) {
		return userRepository.existsUserByEmail(email);
	}
	
	public User createUser(User userAccount) {
		userAccount.setCreateDate(LocalDateTime.now());
		User user = userRepository.save(userAccount);
		return user;
	}
	
	public User updateUser(Long id, User userAccount) {
		userAccount.setId(id);
		userAccount.setCreateDate(userRepository.findById(id).get().getCreateDate());
		User user = userRepository.save(userAccount);
		return user;
	}
	
	public void deleteUser(Long id) {
		User user = new User();
		user.setId(id);
		userRepository.delete(user);
	}
	
}
