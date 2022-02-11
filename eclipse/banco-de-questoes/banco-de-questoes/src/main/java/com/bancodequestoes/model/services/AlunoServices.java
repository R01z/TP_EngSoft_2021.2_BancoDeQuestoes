package com.bancodequestoes.model.services;

import com.bancodequestoes.model.dao.AlunoDao;
import com.bancodequestoes.model.dao.DaoFactory;
import com.bancodequestoes.model.entities.Aluno;

public class AlunoServices {
	private AlunoDao dao = DaoFactory.createAlunoDao();
	
	public int insereAluno(Aluno obj) {
		return dao.insert(obj);
	}
	
	public Aluno pesquisaAlunoId(Aluno obj) {
		return dao.findById(obj.getIdUsr());
	}

	public Aluno pesquisaAlunoNomeUsrSenha(String nomeUsr, String senha){ return dao.findByNomeUsrSenha(nomeUsr, senha);}

	public Aluno pesquisaAlunoMatricula(Aluno obj) {
		return dao.findByMatricula(obj.getMatricula());
	}

	public Boolean checaNomeUsuario(String nomeUsr){
		return dao.checkByNomeUsr(nomeUsr);
	}
	public void deletaPorId(Aluno obj) {
		dao.deleteById(obj.getIdUsr());
	}
}
