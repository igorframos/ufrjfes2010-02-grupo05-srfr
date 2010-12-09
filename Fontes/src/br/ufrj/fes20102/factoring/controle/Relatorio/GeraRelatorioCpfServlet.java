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
 * Servlet responsável por gerar relatórios por CPF
 */
@WebServlet("/GeraRelatorioCpfServlet")
public class GeraRelatorioCpfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GeraRelatorioCpfServlet() {
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
	 * Lista os cheques do cpf
	 * 
	 * @param cpf
	 * @param devolvido
	 * @return <b>List</b> de Cheques que satisfazem a entrada.
	 * @throws Exception
	 */
	private List pegaCheques(String cpf, boolean devolvido) throws Exception {

		ChequeDAO dao = new ChequeDAO();

		List cheques = dao.filtraCPF(cpf, devolvido);

		return cheques;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String cpf = request.getParameter("cpf");
		String devolvidos[] = request.getParameterValues("devolvido");
		boolean devolvido = false;

		if (devolvidos != null) {
			devolvido = true;
		}

		if (Utilitarios.usuarioLogado(request)) {
			List lista;
			try {
				lista = pegaCheques(cpf, devolvido);
				request.setAttribute("cheques", lista);
				request.getRequestDispatcher(
						"visao/gerarRelatorioCpf/exibeRelatorioCpf.jsp")
						.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.getRequestDispatcher(
						"visao/gerarRelatorioCpf/geraRelatorioCpfForm.jsp")
						.forward(request, response);
			}

		} else {
			response.sendRedirect("");
		}
	}

}
