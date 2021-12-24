package model.dao;

import java.util.List;

public interface QuestaoDao {
	void insert(Questao obj);
	void deleteById(Integer id);
	List<Questao> findByTemas(enum temas, bool publica);
}
