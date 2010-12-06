package controle.Testes;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import modelo.Dominio.Cheque;
import modelo.Persistencia.ChequeDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controle.Cheque.InsereChequeServlet;

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
			
			assertTrue("Problema em inserir cheque", (cheque2 != null) );
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testaInsereControle() {
		
		boolean faltandoInfo = servlet.validaFormulario("", "", "123", "70", "65", "06/12/2010");
		
		assertTrue("Problema em validar os dados", (faltandoInfo == false) );
		
		boolean dadosErrados = servlet.validaFormulario("12345", "123", "456", "10", "20", "06/12/2010");
		
		assertTrue("Problema em validar os dados", (dadosErrados == false) );
		
		boolean clienteInexistente = servlet.validaFormulario("12345", "9", "cliente que nao existe", "80", "70", "06/12/2010");
		
		assertTrue("Problema em validar os dados", (clienteInexistente == false) );
		
	}
	
}
