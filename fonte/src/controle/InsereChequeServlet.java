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
		
		try {
			ClienteDAO dao = new ClienteDAO();
			Cliente cliente = dao.filtraCNPJ(CNPJ);
			dao.encerra();
			
			System.out.println(cliente.getCnpj());
			System.out.println(cliente.getNome());
			System.out.println(cliente.getConfiavel());
			
			// Se o cliente não existe no BD, não posso inserir
			// um cheque relacionado a ele!
			if(cliente == null) {
				return false;
			}
			
			// Caso contrário, devo checar se ele é confiável
			if(cliente.getConfiavel() == 1) {
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	private boolean validaNumero(String numero) {
		try {
			ChequeDAO dao = new ChequeDAO();
			Cheque cheque = dao.filtraNumero(numero);
			dao.encerra();
			
			// Se for null é porque não existe o cheque no BD,
			// portanto, posso inserir!
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
	
	private boolean validaFormulario(String numero, String cpf, String cnpj, String valorBruto, String dataVencimento) {
		
		// Se algum campo estiver vazio
		if( numero.equals("") || cpf.equals("") || cnpj.equals("") || valorBruto.equals("") || dataVencimento.equals("") ) {
			return false;
		}
		
		// Se o cliente não for confiável
		if(!validaCliente(cnpj)) {
			return false;
		}
		
		// Se o cheque já estiver no bd
		if(!validaNumero(numero)) {
			return false;
		}
		
		Cheque cheque = new Cheque();
		
		cheque.setNumero(numero);
		cheque.setCPF(cpf);
		cheque.setCNPJ(cnpj);
		
		Date data = Utilitarios.stringToDate(dataVencimento);
		
		cheque.setData_vencimento(data);
		
		Double valor = Double.parseDouble(valorBruto);
		
		cheque.setValor_bruto(valor);
		
		try {
			ChequeDAO dao = new ChequeDAO();
			dao.insere(cheque);
			dao.encerra();
			
			return true;
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
		String cpf = request.getParameter("cpf");
		String valorBruto = request.getParameter("valorBruto");
		String dataVencimento = request.getParameter("dataVencimento");
		
		if( validaFormulario(numero, cpf, cnpj, valorBruto, dataVencimento) ) {
			//request.setAttribute("cnpjEmpresa", cnpj);
			request.getRequestDispatcher("/insereChequeSucesso.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/insereChequeFalha.jsp").forward(request, response);
		}		
	}

}
