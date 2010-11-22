package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("passou aqui!");
		
		String numero = request.getParameter("numero");
		String cnpj = request.getParameter("cnpj");
		
		System.out.println(numero);
		System.out.println(cnpj);
		
		request.setAttribute("cnpjEmpresa", cnpj);
		request.getRequestDispatcher("/insereChequeSucesso.jsp").forward(request, response);
		
	}

}
