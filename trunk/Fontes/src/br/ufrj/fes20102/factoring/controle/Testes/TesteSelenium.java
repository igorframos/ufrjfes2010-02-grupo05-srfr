package br.ufrj.fes20102.factoring.controle.Testes;

import com.thoughtworks.selenium.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

/**
 * 
 * Teste utilizando o Selenium Neste teste, um cliente e um cheque são
 * inseridos. Depois é registrado o depósito desse cheque como devolvido
 * 
 */
public class TesteSelenium extends SeleneseTestCase {
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
	public void testESelenium() throws Exception {
		selenium.open("/fonte/visao/menuPrincipal.jsp");
		selenium.click("link=Inserir Cliente");
		selenium.waitForPageToLoad("30000");
		selenium.type("nome", "Empresa");
		selenium.type("cnpj", "154332");
		selenium.type("endereco", "Rua Onofre de Azevedo");
		selenium.type("contato", "contato@empresa.com");
		selenium.click("//input[@value='Inserir']");
		selenium.waitForPageToLoad("30000");
		selenium.open("/fonte/visao/menuPrincipal.jsp");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Inserir Cheque");
		selenium.waitForPageToLoad("30000");
		selenium.type("caixaEntrada", "189");
		selenium.type("cpf", "13455763298");
		selenium.type("cnpj", "154332");
		selenium.type("valorBruto", "150.46");
		selenium.type("valorDescontado", "90.00");
		selenium.type("dataVencimento", "08/09/2011");
		selenium.click("//input[@value='Inserir']");
		selenium.waitForPageToLoad("30000");
		selenium.open("/fonte/visao/menuPrincipal.jsp");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Registrar Depósito");
		selenium.waitForPageToLoad("30000");
		selenium.type("numero", "189");
		selenium.type("data", "09/10/2012");
		selenium.click("devolvido");
		selenium.click("//input[@value='Registrar']");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
