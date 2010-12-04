package controle.Relatorio;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.Utilitarios.Utilitarios;

import modelo.Dominio.Cheque;
import modelo.Persistencia.ChequeDAO;

/**
 * Servlet implementation class ListaChequesServlet
 */
@WebServlet("/ListaChequesServlet")
public class ListaChequesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaChequesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(Utilitarios.usuarioLogado(request)) {
			List cheques;
			
			try {
				ChequeDAO dao = new ChequeDAO();
				cheques = dao.listar();
				
				request.setAttribute("tabela", cheques);
				request.getRequestDispatcher("visao/listarCheques/listarCheques.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("");
		}		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
