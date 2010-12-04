package modelo.Apresentacao;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

import modelo.Dominio.Cheque;

import controle.Utilitarios.Utilitarios;


public class ChequeVO {

	private String numero;
	private String data_desconto;
	private String data_vencimento;
	private String taxa_desconto;
	private String taxa_efetiva;
	private double valor_bruto;
	private double valor_descontado;
	private double receita;
	private String CPF;
	private String CNPJ;
	private String vencido;
	private String devolvido;
	
	
	public ChequeVO() {
		
	}
	
	public ChequeVO(Cheque cheque) {
		this.numero = cheque.getNumero();
		this.valor_bruto = cheque.getValor_bruto();
		this.valor_descontado = cheque.getValor_descontado();
		this.CPF = cheque.getCpf();
		this.CNPJ = cheque.getCnpj();		
		this.data_vencimento = Utilitarios.dateToString(cheque.getData_vencimento());
		
		// Setando a data de Desconto, se ela tiver sido cadastrada
		Date dataDesconto = cheque.getData_desconto();
		
		if(dataDesconto != null) {
			this.data_desconto = Utilitarios.dateToString(dataDesconto);
			
		} else {
			this.data_desconto = "-/-/-";
		}
		
		// Setando se o cheque está vencido
		Calendar cal = Calendar.getInstance();
		Date hoje = cal.getTime();
		
		if(hoje.compareTo(cheque.getData_vencimento()) != -1) {
			this.vencido = "Sim";
		} else {
			this.vencido = "Não";
		}
		
		// Setando se o cheque foi devolvido
		if(cheque.getDevolvido() == 1) {
			this.devolvido = "Sim";
		} else {
			this.devolvido = "Não";
		}
		
		// Setando receita e taxas
		NumberFormat nf = NumberFormat.getInstance();
		
		nf.setMaximumFractionDigits(4);
		
		double taxaDesconto = (1 - valor_descontado/valor_bruto) * 100;
		this.taxa_desconto = nf.format(taxaDesconto) + "%";
		
		double taxaEfetiva = (valor_bruto/valor_descontado - 1) * 100;
		this.taxa_efetiva = nf.format(taxaEfetiva) + "%";
		
		this.receita = valor_bruto - valor_descontado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getData_desconto() {
		return data_desconto;
	}

	public void setData_desconto(String data_desconto) {
		this.data_desconto = data_desconto;
	}

	public String getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(String data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public String getTaxa_desconto() {
		return taxa_desconto;
	}

	public void setTaxa_desconto(String taxa_desconto) {
		this.taxa_desconto = taxa_desconto;
	}

	public String getTaxa_efetiva() {
		return taxa_efetiva;
	}

	public void setTaxa_efetiva(String taxa_efetiva) {
		this.taxa_efetiva = taxa_efetiva;
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

	public double getReceita() {
		return receita;
	}

	public void setReceita(double receita) {
		this.receita = receita;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getVencido() {
		return vencido;
	}

	public void setVencido(String vencido) {
		this.vencido = vencido;
	}

	public String getDevolvido() {
		return devolvido;
	}

	public void setDevolvido(String devolvido) {
		this.devolvido = devolvido;
	}
	
	
}
