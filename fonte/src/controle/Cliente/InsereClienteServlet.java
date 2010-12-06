package controle.Cliente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.Utilitarios.Utilitarios;

import modelo.Dominio.Cliente;
import modelo.Persistencia.ClienteDAO;

/**
 * Servlet responsável por inserir um cliente.
 */
@WebServlet("/InsereClienteServlet")
public class InsereClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsereClienteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	String msgErro = "";

	/**
	 * Valida o form, cria uma instância de Cliente e a insere no Banco.
	 * @param nome
	 * @param cnpj
	 * @param endereco
	 * @param contato
	 * @return
	 */
	public boolean valida(String nome, String cnpj, String endereco, String contato) {

		if (nome.equals("") || cnpj.equals("") || endereco.equals("")
				|| contato.equals("")) {
			msgErro = "Preencha todos os campos!";
			return false;
		}

		Cliente cliente = new Cliente();

		cliente.setNome(nome);
		cliente.setCnpj(cnpj);
		cliente.setEndereco(endereco);
		cliente.setContato(contato);

		try {
			ClienteDAO dao = new ClienteDAO();
			dao.insere(cliente);

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		msgErro = "Cliente não pôde ser inserido no Banco de Dados!";
		return false;
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String nome = request.getParameter("nome");
		String cnpj = request.getParameter("cnpj");
		String endereco = request.getParameter("endereco");
		String contato = request.getParameter("contato");

		// Se o cara tá logado
		if(Utilitarios.usuarioLogado(request)) {
			if (valida(nome, cnpj, endereco, contato)) {
				request.getRequestDispatcher(
						"visao/inserirCliente/insereClienteSucesso.jsp").forward(
						request, response);
			} else {
				if (!msgErro.equals("")) {
					Cookie cookie = new Cookie("mensagemErroInsereCliente", msgErro);
					cookie.setMaxAge(10);
					response.addCookie(cookie);
				}
				response.sendRedirect("visao/inserirCliente/insereClienteForm.jsp");
			}
		} else {
			response.sendRedirect("");
		}
		

	}

}
