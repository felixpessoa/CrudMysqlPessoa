package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pessoa {

	@Id
	@Column(name = "CPF", length = 11)
	private String cpf;
	@Column(name = "NOME")
	private String nome;
	@Column(name = "IDADE")
	private int idade;
	
	
}
