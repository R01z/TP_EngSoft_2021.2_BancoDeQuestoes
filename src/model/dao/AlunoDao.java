package model.dao;

import model.entities.Aluno;

public interface AlunoDao {
	void insert(Aluno obj);
	void deleteById(Integer id);
	Aluno findById(Integer id);
	Aluno findByMatricula(long matricula);
	Aluno findByUsr(String usr);
}
