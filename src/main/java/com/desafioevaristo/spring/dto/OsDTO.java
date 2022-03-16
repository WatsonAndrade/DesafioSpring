package com.desafioevaristo.spring.dto;

import java.io.Serializable;

import com.desafioevaristo.spring.entities.Os;
import com.desafioevaristo.spring.entities.OsStatus;

import lombok.Data;

@Data
public class OsDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String description;
	private OsStatus status;
	
	public OsDTO(Os os) {
		this.id = os.getId();
		this.description  = os.getDescription();
		this.status = os.getStatus();
	}

}
