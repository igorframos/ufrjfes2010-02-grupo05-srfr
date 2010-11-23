package modelo;

import java.util.Date;

public class Cheque {
	private String numero;
	private Date data_desconto;
	private Date data_vencimento;
	private float taxa_desconto;
	private float taxa_efetiva;
	private float valor_bruto;
	private float valor_descontado;
	private float receita;
	private String CPF;
	private String CNPJ;
	private Boolean vencido;
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
	public float getTaxa_desconto() {
		return taxa_desconto;
	}
	public void setTaxa_desconto(float taxa_desconto) {
		this.taxa_desconto = taxa_desconto;
	}
	public float getTaxa_efetiva() {
		return taxa_efetiva;
	}
	public void setTaxa_efetiva(float taxa_efetiva) {
		this.taxa_efetiva = taxa_efetiva;
	}
	public float getValor_bruto() {
		return valor_bruto;
	}
	public void setValor_bruto(float valor_bruto) {
		this.valor_bruto = valor_bruto;
	}
	public float getValor_descontado() {
		return valor_descontado;
	}
	public void setValor_descontado(float valor_descontado) {
		this.valor_descontado = valor_descontado;
	}
	public float getReceita() {
		return receita;
	}
	public void setReceita(float receita) {
		this.receita = receita;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public Boolean getVencido() {
		return vencido;
	}
	public void setVencido(Boolean vencido) {
		this.vencido = vencido;
	}
	public int getDevolvido() {
		return devolvido;
	}
	public void setDevolvido(int devolvido) {
		this.devolvido = devolvido;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	
	
}
