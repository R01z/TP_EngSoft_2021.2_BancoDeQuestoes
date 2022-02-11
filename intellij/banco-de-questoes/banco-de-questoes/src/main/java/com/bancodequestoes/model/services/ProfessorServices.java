package com.bancodequestoes.model.services;

import com.bancodequestoes.model.dao.DaoFactory;
import com.bancodequestoes.model.dao.ProfessorDao;
import com.bancodequestoes.model.entities.Professor;

public class ProfessorServices {
	private final ProfessorDao dao = DaoFactory.createProfessorDao();
	
	public void insereProfessor(Professor obj) {
		dao.insert(obj);
	}
	
	public Professor pesquisaProfessorId(Professor obj) {
		return dao.findById(obj.getIdUsr());
	}
	public Professor pesquisaProfessorNomeUsrSenha(String nomeUsr, String senha) {
		return dao.findByNomeUsrSenha(nomeUsr, senha);
	}

	public void deletaPorId(Professor obj) {
		dao.deleteById(obj.getIdUsr());
	}

	public Boolean checaNomeUsuario(String nomeUsr) {
		return dao.checkByNomeUsr(nomeUsr);
	}
}
