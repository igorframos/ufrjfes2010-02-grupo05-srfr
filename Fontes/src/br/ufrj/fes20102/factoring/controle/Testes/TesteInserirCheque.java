package br.ufrj.fes20102.factoring.controle.Testes;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufrj.fes20102.factoring.controle.Cheque.InsereChequeServlet;
import br.ufrj.fes20102.factoring.modelo.Dominio.Cheque;
import br.ufrj.fes20102.factoring.modelo.Persistencia.ChequeDAO;

/**
 * 
 * Classe Teste associada ao Caso de Uso de Inserir Cheque
 * 
 */
public class TesteInserirCheque {

	private Cheque cheque;
	private ChequeDAO dao;
	private InsereChequeServlet servlet;

	@Before
	public void prepara() {
		cheque = new Cheque();
		cheque.setNumero("101");
		cheque.setCpf("123");
		cheque.setCnpj("33.041.260/0299-01");
		Calendar calendario = Calendar.getInstance();
		calendario.set(2010, 12, 9);
		cheque.setData_vencimento(calendario.getTime());
		cheque.setValor_bruto(98.74);
		cheque.setValor_descontado(90.5);

		try {
			dao = new ChequeDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}

		servlet = new InsereChequeServlet();
	}

	@After
	public void limpa() {
		dao.encerra();
	}

	@Test
	public void testaInsereDAO() {

		try {
			dao.insere(cheque);

			Cheque cheque2 = dao.filtraNumero(cheque.getNumero());

			assertTrue("Problema em inserir cheque", (cheque2 != null));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testaInsereControle() {

		boolean faltandoInfo = servlet.validaFormulario("", "", "123", "70",
				"65", "06/12/2010");

		assertTrue("Problema em validar os dados", (faltandoInfo == false));

		boolean dadosErrados = servlet.validaFormulario("12345", "123",
				"33.041.260/0299-01", "10", "20", "06/12/2010");

		assertTrue("Problema em validar os dados", (dadosErrados == false));

		boolean clienteInexistente = servlet.validaFormulario("12345", "123",
				"cliente que nao existe", "80", "70", "06/12/2010");

		assertTrue("Problema em validar os dados",
				(clienteInexistente == false));

	}

}
