package com.desafioevaristo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafioevaristo.spring.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	//public Boolean existsUserById(Long id);
}
