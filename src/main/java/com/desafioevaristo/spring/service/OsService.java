package com.desafioevaristo.spring.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafioevaristo.spring.dto.OsDTO;
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
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<Os> findAll(){
		return osRepostitory.findAll();
	}
	
	public OsDTO osDto(Os os) {
		return modelMapper.map(os, OsDTO.class);
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

	public boolean existsOsById(Long id) {
		return osRepostitory.existsById(id);
	}
	
	public Optional<Os> findById(Long id){
		return osRepostitory.findById(id);
	}
	
	public Os uptadeOs(Long id, Os os) {
			User user = userRepository.findByEmail(os.getUser().getEmail());
			os.setId(id);
			os.setStatus(osRepostitory.findById(id).get().getStatus());
			os.setUser(user);
			os.setCreateDate(osRepostitory.findById(id).get().getCreateDate());
			return osRepostitory.save(os);
	}
	
	public void delete(Long id) {
		Os os = new Os();
		os.setId(id);
		osRepostitory.deleteById(id);
	}
	
}
