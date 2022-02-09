package com.bancodequestoes.model.dao;

import com.bancodequestoes.config.db.DB;
import com.bancodequestoes.model.dao.impl.AlunoDaoJDBC;
import com.bancodequestoes.model.dao.impl.ProfessorDaoJDBC;
import com.bancodequestoes.model.dao.impl.QuestaoDaoJDBC;
import com.bancodequestoes.model.dao.impl.TemaDaoJDBC;

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
