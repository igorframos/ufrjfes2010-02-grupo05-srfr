package br.ufrj.fes20102.factoring.controle.Testes;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.ufrj.fes20102.factoring.controle.Cliente.InsereClienteServlet;


/**
 * 
 * Classe Teste associada ao caso de uso de Inserir Cliente
 *
 */
public class TesteInserirCliente {

	private InsereClienteServlet servlet;
	
	@Before
	public void prepara() {
		servlet = new InsereClienteServlet();
	}
	
	@Test
	public void testa() {		
		boolean faltandoDado = servlet.valida("", "456", "a", "b");
		
		assertTrue("Problema em validar os dados", (faltandoDado == false) );
	}
	
}
