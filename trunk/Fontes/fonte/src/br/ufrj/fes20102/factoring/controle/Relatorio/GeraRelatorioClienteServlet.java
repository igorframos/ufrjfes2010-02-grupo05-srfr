package br.ufrj.fes20102.factoring.controle.Relatorio;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.fes20102.factoring.controle.Utilitarios.Utilitarios;
import br.ufrj.fes20102.factoring.modelo.Persistencia.ChequeDAO;



/**
 * Servlet responsável por gerar relatórios por Cliente
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

	/**
	 * Chama o modelo para pegar um List com todos os cheques,
	 * dependendo de "devolvidos", retorna todos os cheques do
	 * cliente ou somente os devolvidos.
	 * @param cnpj
	 * @param devolvidos
	 * @return <b>List</b> com todos os cheques associados ao cliente
	 * e que satisfaçam as restrições da entrada.
	 * @throws Exception
	 */
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
