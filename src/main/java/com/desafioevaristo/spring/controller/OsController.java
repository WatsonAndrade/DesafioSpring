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

import com.desafioevaristo.spring.entities.Os;
import com.desafioevaristo.spring.service.OsService;

@RestController
@RequestMapping("/os")
public class OsController {

	@Autowired
	private OsService osService;
	
	@GetMapping
	public List<Os> OsAll(){
		List<Os> os = osService.findAll();
		return os;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id){
		if (osService.existsOsById(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(osService.findById(id));
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Os não encontrado!");
	}

	@PostMapping
	public ResponseEntity<Object> createOs(@RequestBody Os os) {
		return ResponseEntity.status(HttpStatus.CREATED).body(osService.createOs(os));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Os os){
		if (osService.existsOsById(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(osService.uptadeOs(id, os));
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		if(osService.existsOsById(id)) {
			osService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso!");
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado!");
	}
}
