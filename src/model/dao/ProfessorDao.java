package model.dao;

public interface ProfessorDao {
	void insert(Professor obj);
	void deleteById(Integer id);
	Professor findById(Integer id);
}
