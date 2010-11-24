package controle;

import java.util.Calendar;
import java.util.Date;

public class Utilitarios {

	public static Date stringToDate(String dataString) {
		
		String valores[] = dataString.split("/");
		
		int dia = Integer.parseInt(valores[0]);
		int mes = Integer.parseInt(valores[1]);
		int ano = Integer.parseInt(valores[2]);
		
		Calendar hoje = Calendar.getInstance();
		
		hoje.set(ano, mes, dia);
		
		Date data = hoje.getTime();
		
		return data;
		
	}
	
}
