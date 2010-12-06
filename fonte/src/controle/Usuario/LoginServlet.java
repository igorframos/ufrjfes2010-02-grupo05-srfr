package controle.Usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Dominio.Usuario;
import modelo.Persistencia.UsuarioDAO;

/**
 * Implementação do Sistema de Login
 * Faz a validação do Usuário e o redirecionamento para o menu
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * Dados o login e a senha vindos do form, realiza uma consulta
	 * ao Banco de Dados para ver se os mesmos conferem com os dados
	 * armazenados. Caso confiram, retorna true, caso contrário, false.
	 * 
	 * @param login
	 * @param senha 
	 * 
	 * @return true, se o usuário existir no banco.
	 * 		   false, caso contrário
	 * 
	 * 
	 */
	public boolean validaUsuario(String login, String senha) {
		try {
			UsuarioDAO dao = new UsuarioDAO();

			Usuario usuario = dao.filtraLogin(login);

			if (!senha.equals(usuario.getSenha())) {
				return false;
			}

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String login, senha;

		login = request.getParameter("login");
		senha = request.getParameter("senha");

		if (validaUsuario(login, senha)) {

			Cookie cookie = new Cookie("nomeUsuario", login);
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			response.sendRedirect("visao/menuPrincipal.jsp");
		} else {
			response.sendRedirect("");
		}
	}

}
