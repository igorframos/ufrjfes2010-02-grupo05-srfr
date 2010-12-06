package controle.Testes;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import modelo.Dominio.Cheque;
import modelo.Persistencia.ChequeDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controle.Cheque.RegistraDepositoServlet;

/**
 * 
 * Classe Teste associada ao Caso de Uso de Registrar Depósito
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
		cheque.setNumero("101");
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
		dao.encerra();
	}
	
	@Test
	public void testaRegistraDeposito() {
		try {
			dao.insere(cheque);
			
			boolean dataErrada = servlet.validaForm("101", "04/12/2010", 0);
			
			assertTrue("Problema em validar os dados", (dataErrada == false) );			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
