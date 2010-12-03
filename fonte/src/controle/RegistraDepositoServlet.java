package controle;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Cheque;
import modelo.ChequeDAO;
import modelo.Cliente;
import modelo.ClienteDAO;

/**
 * Servlet implementation class RegistraDepositoServlet
 */
@WebServlet("/RegistraDepositoServlet")
public class RegistraDepositoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistraDepositoServlet() {
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
	 * @throws Exception 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	private Cheque pegaCheque(String numero) throws Exception {
		
		ChequeDAO dao = new ChequeDAO();
		
		Cheque cheque = dao.filtraNumero(numero);
		
		return cheque;		
	}
	
	private void atualizaCliente(String cnpj) throws Exception {
		
		ClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = dao.filtraCNPJ(cnpj);
		
		int numOpAtuais = cliente.getNum_operacoes_atuais();
		numOpAtuais--;
		
		cliente.setNum_operacoes_atuais(numOpAtuais);
		
		dao.atualiza(cliente);
	}
	
	private void atualizaCheque(Cheque cheque) throws Exception {
		
		atualizaCliente(cheque.getCnpj());
		
		ChequeDAO dao = new ChequeDAO();
		
		dao.atualiza(cheque);
	}
	
	private boolean validaForm(String numero, String dataDesconto) {
		
		if(numero.equals("") || dataDesconto.equals("") ) {
			return false;
		}
		
		Cheque cheque = new Cheque();
		
		// Pego o cheque no Banco de Dados
		try {
			cheque = pegaCheque(numero);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(cheque == null) {
			return false;
		}
		
		// Testa se a data de desconto é válida
		Date data = Utilitarios.stringToDate(dataDesconto);
		
		if( data.compareTo(cheque.getData_vencimento()) == -1 ) {
			return false;
		}
		
		cheque.setData_desconto(data);
		
		// Atualizo o Cheque e o Cliente no Banco de Dados
		try {
			atualizaCheque(cheque);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String numero = request.getParameter("numero");
		String dataDesconto = request.getParameter("data");
		
		if(Utilitarios.usuarioLogado(request)) {
			if(validaForm(numero, dataDesconto)) {
				request.getRequestDispatcher("/visao/registrarDeposito/registraDepositoSucesso.jsp").forward(request, response);
			} else {
				response.sendRedirect("/visao/registrarDeposito/registraDepositoForm.jsp");
			}
		} else {
			response.sendRedirect("");
		}
		
	}

}
