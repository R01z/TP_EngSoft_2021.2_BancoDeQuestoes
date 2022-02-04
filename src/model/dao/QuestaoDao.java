package model.dao;

import java.sql.ResultSet;
import java.util.List;

import model.entities.Questao;
import model.entities.Tema;

public interface QuestaoDao {
	void insert(Questao obj);
	void deleteById(Integer id);
	List<Questao> findByTemas(Tema tema, Boolean publica);
}
