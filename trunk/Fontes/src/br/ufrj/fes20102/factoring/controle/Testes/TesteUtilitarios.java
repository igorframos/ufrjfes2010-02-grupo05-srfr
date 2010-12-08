package br.ufrj.fes20102.factoring.controle.Testes;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.ufrj.fes20102.factoring.controle.Utilitarios.Utilitarios;


/**
 * 
 * Classe de Teste associada à classe Utilitários
 *
 */
public class TesteUtilitarios {

	private Calendar cal;
	
	private Date hoje;
	private Date ontem;
	private Date mesPassado;
	
	private String diaUm;
	private String diaDois;
	private String diaTres;
	
	@Before
	public void prepara() {
		
		cal = Calendar.getInstance();
		
		cal.set(2010, 11, 06);
		hoje = cal.getTime();
		
		cal.set(2010, 11, 04);
		ontem = cal.getTime();
		
		cal.set(2010, 10, 05);
		mesPassado = cal.getTime();
		
		diaUm = "01/01/2011";
	}
	
	public boolean datasIguais(Calendar cal1, Calendar cal2) {
		if(cal1.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)) {
			return false;
		}
		
		if(cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
			return false;
		}
		
		if(cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
			return false;
		}
		
		return true;
	}
	
	@Test
	public void testaDataString() {
		System.out.println(hoje);
		System.out.println(Utilitarios.dateToString(hoje));
		assertTrue("Problema em passar Data para String", Utilitarios.dateToString(hoje).equals("06/12/2010"));
		
		assertTrue("Problema em passar Data para String", Utilitarios.dateToString(ontem).equals("04/12/2010"));
		
		assertTrue("Problema em passar Data para String", Utilitarios.dateToString(mesPassado).equals("05/11/2010"));
	}
	
	@Test
	public void testaStringData() {
		Calendar temp = Calendar.getInstance();
		Calendar dataTeste = Calendar.getInstance();
		
		temp.set(2011, 0, 1);
		
		Date dataUm = Utilitarios.stringToDate(diaUm);
		dataTeste.setTime(dataUm);
		
		assertTrue("Problema em passar String para Date", datasIguais(dataTeste, temp) );
		
	}
	
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.runClasses(TesteUtilitarios.class);
	}
}
