package controle.Testes;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import controle.Utilitarios.Utilitarios;

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
		
		hoje = cal.getTime();
		
		cal.set(2010, 11, 04);
		ontem = cal.getTime();
		
		cal.set(2010, 10, 05);
		mesPassado = cal.getTime();
		
		diaUm = "01/01/2011";
	}
	
	public boolean datasIguais(Calendar cal1, Calendar cal2) {
		if(cal1.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)) {
			System.out.println("Dia1: " + cal1.get(Calendar.DAY_OF_MONTH) );
			System.out.println("Dia2: " + cal2.get(Calendar.DAY_OF_MONTH) );
			return false;
		}
		
		if(cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
			System.out.println("Mes1: " + cal1.get(Calendar.MONTH));
			System.out.println("Mes2: " + cal2.get(Calendar.MONTH));
			
			return false;
		}
		
		if(cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
			System.out.println("Ano1: " + cal1.get(Calendar.YEAR));
			System.out.println("Ano1: " + cal2.get(Calendar.YEAR));
			return false;
		}
		
		return true;
	}
	
	@Test
	public void testaDataString() {
		assertTrue("Problema em passar Data para String", Utilitarios.dateToString(hoje).equals("05/12/2010"));
		
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
		
		System.out.println(temp.toString());
		System.out.println(dataTeste.toString());
		
		assertTrue("Problema em passar String para Date", datasIguais(dataTeste, temp) );
		
	}
	
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.runClasses(TesteUtilitarios.class);
	}
}
