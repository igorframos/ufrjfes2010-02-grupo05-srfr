package controle.Utilitarios;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe que implementa m�todos �teis a v�rias outras, para evitar
 * repeti��o de trabalho ou imports incorretos, essa Classe agrega
 * fun��es variadas e �teis.
 * 
 * @author Gabriel
 * 
 */
public class Utilitarios {

	/**
	 * Verifica se existe um usu�rio logado no sistema
	 * @param request
	 * @return <b>true</b> se existe um usu�rio logado. <br>
	 * <b>false</b> sen�o.
	 */
	public static boolean usuarioLogado(HttpServletRequest request) {
		
		Cookie biscoitos[] = request.getCookies();
		for(Cookie c : biscoitos) {
			
			if( c.getName().equalsIgnoreCase("nomeUsuario")) {
				return true;
			}
			
		}
		
		return false;
	}
	
	/**
	 * Transforma uma String da forma "dd/mm/aaaa" em uma
	 * inst�ncia de Date
	 * @param dataString
	 * @return <b>Date</b> correspondente � String dada
	 */
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
	
	/**
	 * Dado uma inst�ncia de Date, cria uma String da forma
	 * "dd/mm/aaaa"
	 * @param data
	 * @return <b>String</b> associada ao <b>Date</b> de entrada
	 */
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
