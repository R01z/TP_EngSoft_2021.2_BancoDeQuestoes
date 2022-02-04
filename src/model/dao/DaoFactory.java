package model.dao;

import db.DB;
import model.dao.impl.AlunoDaoJDBC;
import model.dao.impl.ProfessorDaoJDBC;
import model.dao.impl.QuestaoDaoJDBC;
import model.dao.impl.TemaDaoJDBC;

public class DaoFactory {
	public static ProfessorDao createProfessorDao() {
		return new ProfessorDaoJDBC(DB.getConnection());
	}
	
	public static AlunoDao createAlunoDao() {
		return new AlunoDaoJDBC(DB.getConnection());
	}
	
	public static QuestaoDao createQuestaoDao() {
		return new QuestaoDaoJDBC(DB.getConnection());
	}
	
	public static TemaDao createTemaDao() {
		return new TemaDaoJDBC(DB.getConnection());
	}
}
