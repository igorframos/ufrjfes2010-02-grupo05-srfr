package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Cheque;
import modelo.ChequeDAO;

/**
 * Servlet implementation class InsereChequeServlet
 */
@WebServlet("/InsereChequeServlet")
public class InsereChequeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsereChequeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private boolean validaCliente(String CNPJ) {
		
		return true;
	}
	
	private boolean validaNumero(String numero) {
		
		if(numero.equals("")) {
			return false;
		}
		
		try {
			ChequeDAO dao = new ChequeDAO();
			Cheque cheque = dao.filtraNumero(numero);
			dao.encerra();
			if(cheque == null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String numero = request.getParameter("numero");
		String cnpj = request.getParameter("cnpj");
		
		if( validaCliente(cnpj) && validaNumero(numero) ) {
			// chamar inserirCheque...
			
			request.setAttribute("cnpjEmpresa", cnpj);
			request.getRequestDispatcher("/insereChequeSucesso.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/insereChequeFalha.jsp").forward(request, response);
		}		
	}

}
