package com.bancodequestoes.model.entities;

public class Relatorio {
	private Integer idRel;
	private String semestre;
	private String turma;
	private String atividade;
	private double notaMedia;
	
	public Relatorio(Integer idRel, String semestre, String turma, String atividade, double notaMedia) {
		this.idRel = idRel;
		this.semestre = semestre;
		this.turma = turma;
		this.atividade = atividade;
		this.notaMedia = notaMedia;
	}
	
	public Relatorio(){}

	public Integer getIdRel() {
		return idRel;
	}

	public void setIdRel(Integer idRel) {
		this.idRel = idRel;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public double getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(double notaMedia) {
		this.notaMedia = notaMedia;
	}
	
	
}
