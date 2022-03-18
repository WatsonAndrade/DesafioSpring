package com.desafioevaristo.spring.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "O campo nome não pode ser nulo") @NotEmpty
	private String name;
	@NotNull(message = "O campo email não pode ser nulo") @NotEmpty
	private String email;
	@NotNull(message = "O campo senha não pode ser nulo") @Length(min = 6, max = 18) @NotEmpty
	private String password;
	@NotNull(message = "O campo data de nascimento não pode ser nulo")
	private LocalDate birthdate;
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void prePersist() {
		setCreateDate(LocalDateTime.now());
	}
}
