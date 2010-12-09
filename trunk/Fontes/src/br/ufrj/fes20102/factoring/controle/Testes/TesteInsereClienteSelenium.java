package br.ufrj.fes20102.factoring.controle.Testes;

import com.thoughtworks.selenium.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

/**
 * 
 * Teste usando o Selenium que insere um novo cliente
 * 
 */
public class TesteInsereClienteSelenium extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox",
				"http://localhost:8080/") {
			public void open(String url) {
				commandProcessor
						.doCommand("open", new String[] { url, "true" });
			}
		};
		selenium.start();
	}

	@Test
	public void testEInsereClienteSelenium() throws Exception {
		selenium.open("/fonte/visao/inserirCliente/insereClienteForm.jsp");
		selenium.type("nome", "Potencial");
		selenium.type("cnpj", "36729810056732");
		selenium.type("endereco", "Rua Arlindo Santana 45");
		selenium.type("contato", "contato@potencial.com");
		selenium.click("//input[@value='Inserir']");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
