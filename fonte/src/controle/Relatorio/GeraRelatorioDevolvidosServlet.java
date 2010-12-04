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
 * Servlet implementation class GeraRelatorioDevolvidosServlet
 */
@WebServlet("/GeraRelatorioDevolvidosServlet")
public class GeraRelatorioDevolvidosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GeraRelatorioDevolvidosServlet() {
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
		
		if (Utilitarios.usuarioLogado(request)) {
			List cheques;

			try {
				ChequeDAO dao = new ChequeDAO();
				cheques = dao.listar(true);
				
				request.setAttribute("cheques", cheques);
				request.getRequestDispatcher(
						"visao/gerarRelatorioDevolvidos/exibeRelatorioDevolvidos.jsp")
						.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
