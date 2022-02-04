package model.dao;

import model.entities.Questao;
import model.entities.Tema;

public interface TemaDao {
	void insert(Tema obj);
	void deleteById(Integer id);
	Tema findById(Integer id);
	void insertTemaxQuestao(Questao obj);
}
