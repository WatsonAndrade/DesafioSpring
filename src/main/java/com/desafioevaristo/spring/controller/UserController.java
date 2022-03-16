package com.desafioevaristo.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioevaristo.spring.entities.User;
import com.desafioevaristo.spring.repository.UserRepository;
import com.desafioevaristo.spring.service.UserService;

@RestController
@RequestMapping(value = "api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public List<User> UsersAll(){
		List<User> user = userRepository.findAll();
		return user;
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id){
		if (userService.existsUserById(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@RequestBody User userAccount){
		if(userService.existsUserAccountByEmail(userAccount.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ja Cadastrado!");
		}
			return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userAccount));
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User userAccount){
		Boolean existsByid = userService.existsUserById(id);
		if (existsByid) {
			return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userAccount));
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id){
		Boolean existsByid = userService.existsUserById(id);
		if (existsByid) {
			userService.deleteUser(id);
			return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com Sucesso!");
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
	}
	
}
