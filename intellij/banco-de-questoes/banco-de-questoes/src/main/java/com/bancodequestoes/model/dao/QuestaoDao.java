package com.bancodequestoes.model.dao;

import com.bancodequestoes.model.entities.Questao;
import com.bancodequestoes.model.entities.Tema;

import java.util.List;

public interface QuestaoDao {
	void insert(Questao obj);
	void deleteById(Integer id);
	List<Questao> findByTemas(Tema tema, Boolean publica);
	void instanciaRelatorio(Questao obj);
	void instanciaTemas(Questao obj);
	void updateRelatorio(Questao obj);
	void insertRelatorio(Questao obj);
}
