package model.services;

import model.dao.DaoFactory;
import model.dao.TemaDao;
import model.entities.Tema;

public class TemaServices {
	private TemaDao dao = DaoFactory.createTemaDao();
	
	public void insereTema(Tema obj) {
		dao.insert(obj);
	}
	
	public Tema pesquisaTemaId(Tema obj) {
		return dao.findById(obj.getIdTema());
	}
	
	public void deletaPorId(Tema obj) {
		dao.deleteById(obj.getIdTema());
	}
}
