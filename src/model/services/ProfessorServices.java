package model.services;

import model.dao.ProfessorDao;
import model.dao.DaoFactory;
import model.entities.Professor;

public class ProfessorServices {
	private ProfessorDao dao = DaoFactory.createProfessorDao();
	
	public void insereProfessor(Professor obj) {
		dao.insert(obj);
	}
	
	public Professor pesquisaProfessorId(Professor obj) {
		return dao.findById(obj.getIdUsr());
	}
	
	public void deletaPorId(Professor obj) {
		dao.deleteById(obj.getIdUsr());
	}
}
