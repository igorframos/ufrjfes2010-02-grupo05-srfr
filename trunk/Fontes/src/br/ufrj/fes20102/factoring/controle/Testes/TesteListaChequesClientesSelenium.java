package br.ufrj.fes20102.factoring.controle.Testes;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

/**
 * 
 * Teste utilizando o Selenium que lista todos os cheques de um cliente
 * 
 */
public class TesteListaChequesClientesSelenium extends SeleneseTestCase {
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
	public void testEListaChequesClientes() throws Exception {
		selenium.open("/fonte/visao/gerarRelatorioCliente/geraRelatorioClienteForm.jsp");
		selenium.type("cnpj", "123");
		selenium.click("//input[@value='Gerar Relatorio']");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
