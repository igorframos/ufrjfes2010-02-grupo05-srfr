package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Cliente;
import modelo.ClienteDAO;

/**
 * Servlet implementation class InsereClienteServlet
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
    
    boolean valida(String nome, String cnpj, String endereco, String contato) {
    	
    	if( nome.equals("") || cnpj.equals("") || endereco.equals("") || contato.equals("")) {
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
    	
    	return false;
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
		// TODO Auto-generated method stub
		
		String nome = request.getParameter("nome");
		String cnpj = request.getParameter("cnpj");
		String endereco = request.getParameter("endereco");
		String contato = request.getParameter("contato");
		
		if( valida(nome, cnpj, endereco, contato) ) {
			request.getRequestDispatcher("visao/inserirCliente/insereClienteSucesso.jsp").forward(request, response);
		} else {
			response.sendRedirect("visao/inserirCliente/insereClienteForm.jsp");
		}
		
	}

}
