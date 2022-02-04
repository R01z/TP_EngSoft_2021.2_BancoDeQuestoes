package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.QuestaoDao;
import model.entities.Questao;
import model.entities.Tema;
import model.entities.Usuario;

public class bancoDeQuestoes {
	
	private QuestaoDao dao = DaoFactory.createQuestaoDao();
	
	public List<Questao> pesquisaQuestao(Tema tema, Usuario usr) {
		return dao.findByTemas(tema, usr.getUsrAluno());	
	}
	
	public void insereQuestao(Questao questao) {
		dao.insert(questao);
	}
}
