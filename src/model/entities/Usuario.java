package model.entities;

public class Usuario {
	private String nomeUsr;
	private Integer idUsr;
	private Boolean usrAluno;
	
	public Usuario(String nomeUsr, Integer idUsr, Boolean usrAluno ) {
		this.nomeUsr = nomeUsr;
		this.idUsr = idUsr;
		this.usrAluno = usrAluno;
	}
	
	public Usuario() {}

	public String getNomeUsr() {
		return nomeUsr;
	}

	public void setNomeUsr(String nomeUsr) {
		this.nomeUsr = nomeUsr;
	}

	public int getIdUsr() {
		return idUsr;
	}

	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}
	
	public Boolean getUsrAluno() {
		return usrAluno;
	}
	
	public void setUsrAluno(Boolean usuario) {
		this.usrAluno = usuario;
	}
}
