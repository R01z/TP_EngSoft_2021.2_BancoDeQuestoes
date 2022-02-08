package model.services;

import model.dao.AlunoDao;
import model.dao.DaoFactory;
import model.entities.Aluno;

public class AlunoServices {
	private AlunoDao dao = DaoFactory.createAlunoDao();
	
	public void insereAluno(Aluno obj) {
		dao.insert(obj);
	}
	
	public Aluno pesquisaAlunoId(Aluno obj) {
		return dao.findById(obj.getIdUsr());
	}
	
	public Aluno pesquisaAlunoUsr(Aluno obj) {
		return dao.findByUsr(obj.getNomeUsr());
	}
	
	public Aluno pesquisaAlunoMatricula(Aluno obj) {
		return dao.findByMatricula(obj.getMatricula());
	}
	
	public void deletaPorId(Aluno obj) {
		dao.deleteById(obj.getIdUsr());
	}
}
