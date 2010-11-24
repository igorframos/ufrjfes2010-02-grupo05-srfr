package modelo;

public class Cliente {
	private String cnpj;
	private String nome;
	private String endereco;
	private String contato;
	private int confiavel;
	private int num_operacoes_realizadas;
	private int num_operacoes_atuais;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public int getNum_operacoes_realizadas() {
		return num_operacoes_realizadas;
	}
	public void setNum_operacoes_realizadas(int num_operacoes_realizadas) {
		this.num_operacoes_realizadas = num_operacoes_realizadas;
	}
	public int getNum_operacoes_atuais() {
		return num_operacoes_atuais;
	}
	public void setNum_operacoes_atuais(int num_operacoes_atuais) {
		this.num_operacoes_atuais = num_operacoes_atuais;
	}
	public void setConfiavel(int confiavel) {
		this.confiavel = confiavel;
	}
	public int getConfiavel() {
		return confiavel;
	}
	
	
	
}
