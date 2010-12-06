package controle.Testes;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import controle.Usuario.LoginServlet;

/**
 * 
 * Classe Teste associada ao Login
 *
 */
public class TesteLogin {

	private LoginServlet servlet;
	
	@Before
	public void prepara() {
		servlet = new LoginServlet();
	}
	
	@Test
	public void testa() {
		
		boolean infoFaltando = servlet.validaUsuario("", "abc");
		
		assertTrue("Dados incompletos", (infoFaltando == false) );
		
	}
	
}
