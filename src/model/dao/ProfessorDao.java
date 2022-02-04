package model.dao;

import model.entities.Professor;

public interface ProfessorDao {
	void insert(Professor obj);
	void deleteById(Integer id);
	Professor findById(Integer id);
}
