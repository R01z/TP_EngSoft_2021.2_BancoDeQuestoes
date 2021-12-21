package model.dao;

import model.dao.impl.ProfessorDaoJDBC;

public class DaoFactory {
	public static ProfessorDao createProfessorDao() {
		return new ProfessorDaoJDBC();
	}
}
