package model.dao;

public interface AlunoDao {
	void insert(Aluno obj);
	void deleteById(Integer id);
	Aluno findById(Integer id);
	Aluno findByMatricula(Integer matricula);
}
