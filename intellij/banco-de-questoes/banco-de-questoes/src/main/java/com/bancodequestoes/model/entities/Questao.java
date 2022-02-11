package com.bancodequestoes.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Questao {
	
	private Integer idQuestao;
	private String enunciado;
	private String resposta;
	private Boolean publica;
	private List<Tema> temas = new ArrayList<>();
	private Relatorio relatorio;
	
	public Questao(Integer idQuestao, String enunciado, String resposta, Boolean publica, List<Tema> temas) {
		this.idQuestao = idQuestao;
		this.enunciado = enunciado;
		this.resposta = resposta;
		this.publica = publica;
		this.temas = temas;
		relatorio = null;
	}
	
	public Questao() {}

	public Integer getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Integer idQuestao) {
		this.idQuestao = idQuestao;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Boolean getPublica() {
		return publica;
	}

	public void setPublica(Boolean publica) {
		this.publica = publica;
	}

	public List<Tema> getTemas() {
		return temas;
	}
	
	public void addTema(Tema tema) {
		temas.add(tema);
	}
	
	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public Relatorio getRelatorio() {
		return relatorio;
	}
}
