package controle.Utilitarios;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe que implementa métodos úteis a várias outras, para evitar
 * repetição de trabalho ou imports incorretos, essa Classe agrega
 * funções variadas e úteis.
 * 
 * @author Gabriel
 * 
 */
public class Utilitarios {

	/**
	 * Verifica se existe um usuário logado no sistema
	 * @param request
	 * @return <b>true</b> se existe um usuário logado. <br>
	 * <b>false</b> senão.
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
	 * instância de Date
	 * @param dataString
	 * @return <b>Date</b> correspondente à String dada
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
	 * Dado uma instância de Date, cria uma String da forma
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
