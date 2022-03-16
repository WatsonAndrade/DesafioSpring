package com.desafioevaristo.spring.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafioevaristo.spring.entities.Os;
import com.desafioevaristo.spring.entities.OsStatus;
import com.desafioevaristo.spring.entities.User;
import com.desafioevaristo.spring.repository.OsRepostitory;
import com.desafioevaristo.spring.repository.UserRepository;

@Service
public class OsService {

	@Autowired
	private OsRepostitory osRepostitory;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Os> findAll(){
		return osRepostitory.findAll();
	}
	
	public Os createOs(Os os) {
		User user = userRepository.findByEmail(os.getUser().getEmail());
		if(user != null) {
			os.setCreateDate(LocalDateTime.now());
			os.setStatus(OsStatus.OPEN);
			os.setUser(user);
		}
		return osRepostitory.save(os);
	}
}
