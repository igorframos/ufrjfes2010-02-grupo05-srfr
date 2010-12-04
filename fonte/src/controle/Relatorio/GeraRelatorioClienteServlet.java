package controle.Relatorio;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.Utilitarios.Utilitarios;

import modelo.Persistencia.ChequeDAO;

/**
 * Servlet implementation class GeraRelatorioClienteServlet
 */
@WebServlet("/GeraRelatorioClienteServlet")
public class GeraRelatorioClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GeraRelatorioClienteServlet() {
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

	private List pegaChequesCliente(String cnpj, boolean devolvidos)
			throws Exception {

		ChequeDAO dao = new ChequeDAO();

		List cheques = dao.filtraCNPJ(cnpj, devolvidos);

		return cheques;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String cnpj = request.getParameter("cnpj");
		String devolvido[] = request.getParameterValues("devolvidos");
		boolean devolvidos = false;

		if (devolvido != null) {
			devolvidos = true;
		}

		if (Utilitarios.usuarioLogado(request)) {
			List cheques;
			try {
				cheques = pegaChequesCliente(cnpj, devolvidos);
				request.setAttribute("cheques", cheques);
				request.getRequestDispatcher(
						"visao/gerarRelatorioCliente/exibeRelatorioCliente.jsp")
						.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.getRequestDispatcher(
				"visao/gerarRelatorioCliente/geraRelatorioClienteForm.jsp")
				.forward(request, response);
			}
		} else {
			response.sendRedirect("");
		}
	}

}
