package model.entities;

public class Aluno extends Usuario{
	
	private long matricula;

	public Aluno(String nomeUsr, Integer idUsr, String nome, long matricula) {
		super(nomeUsr, nome, idUsr, Boolean.TRUE);
		this.matricula = matricula;
	}

	public Aluno(){
		setUsrAluno(Boolean.TRUE);
	}

	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}
	
	
}
