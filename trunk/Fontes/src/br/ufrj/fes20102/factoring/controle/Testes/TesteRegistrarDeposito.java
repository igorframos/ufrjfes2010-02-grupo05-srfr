package br.ufrj.fes20102.factoring.controle.Testes;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufrj.fes20102.factoring.controle.Cheque.RegistraDepositoServlet;
import br.ufrj.fes20102.factoring.modelo.Dominio.Cheque;
import br.ufrj.fes20102.factoring.modelo.Persistencia.ChequeDAO;

/**
 * 
 * Classe Teste associada ao Caso de Uso de Registrar Dep�sito
 * 
 */
public class TesteRegistrarDeposito {

	private RegistraDepositoServlet servlet;
	private ChequeDAO dao;
	private Cheque cheque;

	@Before
	public void prepara() {

		servlet = new RegistraDepositoServlet();

		cheque = new Cheque();
		cheque.setNumero("00666");
		cheque.setCpf("12357");
		cheque.setCnpj("456");
		cheque.setData_vencimento(Calendar.getInstance().getTime());
		cheque.setValor_bruto(98.74);
		cheque.setValor_descontado(90.5);

		try {
			dao = new ChequeDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@After
	public void limpa() {
		dao.deleta("00666");
		dao.encerra();
	}

	@Test
	public void testaRegistraDeposito() {
		try {
			dao.insere(cheque);

			boolean dataErrada = servlet.validaForm(cheque.getNumero(),
					"04/11/2010", 0);

			assertTrue("Problema em validar os dados", (dataErrada == false));

			cheque.setData_desconto(Calendar.getInstance().getTime());
			
			dao.atualiza(cheque);

			boolean jaDescontado = servlet.validaForm("00666", "10/10/2011", 0);

			assertTrue("Cheque j� descontado", (jaDescontado == false));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
