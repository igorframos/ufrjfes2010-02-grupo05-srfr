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
	
	public static String dateToString(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		
		String dia, mes, ano;
		
		if(cal.get(Calendar.DAY_OF_MONTH) < 10) {
			dia = "0" + cal.get(Calendar.DAY_OF_MONTH);
		} else {
			dia = "" + cal.get(Calendar.DAY_OF_MONTH);
		}
		
		if(cal.get(Calendar.MONTH) < 9) {
			mes = "0" + (cal.get(Calendar.MONTH) + 1);
		} else {
			mes = "" + (cal.get(Calendar.MONTH) + 1);
		}
		
		ano = "" + cal.get(Calendar.YEAR);
		
		return dia + "/" + mes + "/" + ano;
	}
	
}
