package com.bancodequestoes.model.services;

import com.bancodequestoes.model.dao.AlunoDao;
import com.bancodequestoes.model.dao.DaoFactory;
import com.bancodequestoes.model.entities.Aluno;

public class Cadastro {
  public boolean checkNomeUsuario(String nomeUsr, Boolean isAluno) {
    AlunoDao alunoDao = DaoFactory.createAlunoDao();
    return alunoDao.checkByNomeUsr(nomeUsr) == null;

  }

  public static int cadastraAluno(Aluno aluno) {
    AlunoDao alunoDao = DaoFactory.createAlunoDao();
    return alunoDao.insert(aluno);
  }
}
