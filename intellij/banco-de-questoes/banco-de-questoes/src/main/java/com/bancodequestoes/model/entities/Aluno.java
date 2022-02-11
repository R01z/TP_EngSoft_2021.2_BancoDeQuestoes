package com.bancodequestoes.model.entities;

public class Aluno extends Usuario {
	
	private long matricula;

	public Aluno(String nomeUsr, Integer idUsr, long matricula) {
		super(nomeUsr, idUsr, Boolean.TRUE);
		this.matricula = matricula;
	}

	public Aluno(){
	}

	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}
	
	
}
