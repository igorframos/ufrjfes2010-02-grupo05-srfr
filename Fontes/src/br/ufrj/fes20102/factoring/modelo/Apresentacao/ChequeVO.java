package br.ufrj.fes20102.factoring.modelo.Apresentacao;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

import br.ufrj.fes20102.factoring.controle.Utilitarios.Utilitarios;
import br.ufrj.fes20102.factoring.modelo.Dominio.Cheque;

/**
 * 
 * Classe que visa tornar a apresentação de instâncias de Cheque mais fácil e
 * clara sem gerar incompatibilidade de tipos com o banco e nem sobrecarregar o
 * mesmo.
 * 
 * @author Leticia
 * 
 */
public class ChequeVO {

	private String numero;
	private String data_desconto;
	private String data_vencimento;
	private String taxa_desconto;
	private String taxa_efetiva;
	private String valor_bruto;
	private String valor_descontado;
	private String receita;
	private String CPF;
	private String CNPJ;
	private String vencido;
	private String devolvido;

	public ChequeVO() {

	}

	public ChequeVO(Cheque cheque) {
		this.numero = cheque.getNumero();
		this.CPF = cheque.getCpf();
		this.CNPJ = cheque.getCnpj();
		this.data_vencimento = Utilitarios.dateToString(cheque
				.getData_vencimento());

		// Setando a data de Desconto, se ela tiver sido cadastrada
		Date dataDesconto = cheque.getData_desconto();

		if (dataDesconto != null) {
			this.data_desconto = Utilitarios.dateToString(dataDesconto);

		} else {
			this.data_desconto = "-/-/-";
		}

		// Setando se o cheque está vencido
		Calendar cal = Calendar.getInstance();
		Date hoje = cal.getTime();

		if (hoje.compareTo(cheque.getData_vencimento()) != -1) {
			this.vencido = "Sim";
		} else {
			this.vencido = "Não";
		}

		// Setando se o cheque foi devolvido
		if (cheque.getDevolvido() == 1) {
			this.devolvido = "Sim";
		} else {
			this.devolvido = "Não";
		}

		// Setando receita, valores e taxas
		NumberFormat nf = NumberFormat.getInstance();

		nf.setMaximumFractionDigits(4);

		double taxaDesconto = (1 - cheque.getValor_descontado()
				/ cheque.getValor_bruto()) * 100;
		this.taxa_desconto = nf.format(taxaDesconto) + "%";

		double taxaEfetiva = (cheque.getValor_bruto()
				/ cheque.getValor_descontado() - 1) * 100;
		this.taxa_efetiva = nf.format(taxaEfetiva) + "%";

		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);

		this.valor_bruto = "R$ " + nf.format(cheque.getValor_bruto());
		this.valor_descontado = "R$ " + nf.format(cheque.getValor_descontado());
		this.receita = "R$ "
				+ nf.format(cheque.getValor_bruto()
						- cheque.getValor_descontado());
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

	public String getValor_bruto() {
		return valor_bruto;
	}

	public void setValor_bruto(String valor_bruto) {
		this.valor_bruto = valor_bruto;
	}

	public String getValor_descontado() {
		return valor_descontado;
	}

	public void setValor_descontado(String valor_descontado) {
		this.valor_descontado = valor_descontado;
	}

	public String getReceita() {
		return receita;
	}

	public void setReceita(double receita) {
		this.receita = receita + "";
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
