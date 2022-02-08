package model.entities;

public class Professor extends Usuario{

	public Professor(String nomeUsr, String nome, Integer idUsr) {
		super(nomeUsr, nome, idUsr, Boolean.FALSE);
	}
	
	public Professor() {
		setUsrAluno(Boolean.FALSE);
	}
}
