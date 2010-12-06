package br.ufrj.fes20102.factoring.modelo.Dominio;

/**
 * Classe de Domínio que indica se o cpf é inválido ou não
 *
 */
public class CpfInvalido {

	private String cpf;
	
	public CpfInvalido() {
		
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}
	
}
