package br.ufrj.fes20102.factoring.controle.Testes;

import com.thoughtworks.selenium.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

/**
 * 
 * Teste utilizando o Selenium que registra um depósito que não foi devolvido
 *
 */
public class TesteRegistraDepositoSelenium extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://localhost:8080/") {
			public void open(String url) {
				commandProcessor.doCommand("open", new String[] {url, "true"});
			}
		};
		selenium.start();
	}

	@Test
	public void testERegistraDeposito1Selenium() throws Exception {
		selenium.open("/fonte/visao/registrarDeposito/registraDepositoForm.jsp");
		selenium.type("numero", "3");
		selenium.type("data", "17/08/2029");
		selenium.click("//input[@value='Registrar']");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
