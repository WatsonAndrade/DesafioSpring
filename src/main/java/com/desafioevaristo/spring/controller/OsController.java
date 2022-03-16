package com.desafioevaristo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	public ResponseEntity<Object> createOs(@RequestBody Os os) {
		return ResponseEntity.status(HttpStatus.CREATED).body(osService.createOs(os));
	}
}
