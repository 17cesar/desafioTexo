package com.texo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "studio")
public class Studio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	public Long getId() {
		if (id == null) {
			id = 0L;
		}
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		if (nome == null) {
			nome = "";
		}
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
