package modelo;

import java.util.Date;

public class Cheque {
	private String numero;
	private Date data_desconto;
	private Date data_vencimento;
	private double valor_bruto;
	private double valor_descontado;
	private String cpf;
	private String cnpj;
	private int devolvido;
	
	public Cheque() {
		
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getData_desconto() {
		return data_desconto;
	}
	public void setData_desconto(Date data_desconto) {
		this.data_desconto = data_desconto;
	}
	public Date getData_vencimento() {
		return data_vencimento;
	}
	public void setData_vencimento(Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getDevolvido() {
		return devolvido;
	}
	public void setDevolvido(int devolvido) {
		this.devolvido = devolvido;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCnpj() {
		return cnpj;
	}

	public double getValor_bruto() {
		return valor_bruto;
	}

	public void setValor_bruto(double valor_bruto) {
		this.valor_bruto = valor_bruto;
	}

	public double getValor_descontado() {
		return valor_descontado;
	}

	public void setValor_descontado(double valor_descontado) {
		this.valor_descontado = valor_descontado;
	}
	
	
}
