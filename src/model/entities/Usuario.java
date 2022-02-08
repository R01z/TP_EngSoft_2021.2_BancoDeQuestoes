package model.entities;

public class Usuario {
	private String nomeUsr;
	private String nome;
	private Integer idUsr;
	private Boolean usrAluno;
	
	public Usuario(String nomeUsr,String nome, Integer idUsr, Boolean usrAluno ) {
		this.nomeUsr = nomeUsr;
		this.idUsr = idUsr;
		this.usrAluno = usrAluno;
		this.nome = nome;
	}
	
	public Usuario() {}

	public String getNomeUsr() {
		return nomeUsr;
	}

	public void setNomeUsr(String nomeUsr) {
		this.nomeUsr = nomeUsr;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
