package com.bancodequestoes.model.entities;

public class Tema {
	private Integer idTema;
	private String nome;

	public Tema(String nome, Integer idTema) {
		this.nome = nome;
		this.idTema = idTema;
	}
	
	public Tema() {}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdTema() {
		return idTema;
	}

	public void setIdTema(Integer idTema) {
		this.idTema = idTema;
	}
	
	
}
