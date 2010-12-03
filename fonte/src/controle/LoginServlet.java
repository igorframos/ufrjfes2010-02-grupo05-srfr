package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 * Servlet implementation class LoginServlet
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

	boolean validaUsuario(String login, String senha) {
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
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			response.sendRedirect("visao/menuPrincipal.jsp");
		} else {
			response.sendRedirect("");
		}
	}

}
