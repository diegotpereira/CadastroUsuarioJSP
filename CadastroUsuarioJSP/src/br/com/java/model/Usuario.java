package br.com.java.model;

public class Usuario {
	
	protected int id;
	protected String nome;
	protected String email;
	protected int telefone;
	protected String nacionalidade;
	
	
	public Usuario(String nome, String email, int telefone, String nacionalidade) {
		// TODO Auto-generated constructor stub
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.nacionalidade = nacionalidade;
	}
	
	public Usuario(int id, String nome, String email, int telefone, String nacionalidade) {
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.nacionalidade = nacionalidade;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	

}
