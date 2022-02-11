package com.bancodequestoes.model.dao;

import com.bancodequestoes.model.entities.Aluno;

public interface AlunoDao {
	int insert(Aluno obj);
	void deleteById(Integer id);
	Aluno findById(Integer id);
	Aluno findByMatricula(long matricula);
	Aluno findByNomeUsrSenha(String nomeUsr, String senha);
	Boolean checkByNomeUsr(String nomeUsr);


}
