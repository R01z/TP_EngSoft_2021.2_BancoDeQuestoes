package com.bancodequestoes.model.services;

import com.bancodequestoes.model.dao.DaoFactory;
import com.bancodequestoes.model.dao.QuestaoDao;
import com.bancodequestoes.model.entities.Questao;
import com.bancodequestoes.model.entities.Tema;

import java.util.List;

public class BancoDeQuestoes {
	
	private QuestaoDao dao = DaoFactory.createQuestaoDao();
	
	public List<Questao> pesquisaQuestao(Tema tema, Boolean usrBoolean) {
		return dao.findByTemas(tema, usrBoolean);
	}
	
	public void insereQuestao(Questao questao) {
		dao.insert(questao);
	}
	
	public void insereOuAtualizaRelatorio(Questao questao) {
		if(questao.getRelatorio().getIdRel() == null)
			dao.insertRelatorio(questao);
		else dao.updateRelatorio(questao);
	}
}
