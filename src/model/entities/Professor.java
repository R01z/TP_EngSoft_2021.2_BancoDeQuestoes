package model.entities;

public class Professor extends Usuario{

	public Professor(String nomeUsr, Integer idUsr) {
		super(nomeUsr, idUsr, Boolean.FALSE);
	}
	
	public Professor() {
		setUsrAluno(Boolean.FALSE);
	}
}
