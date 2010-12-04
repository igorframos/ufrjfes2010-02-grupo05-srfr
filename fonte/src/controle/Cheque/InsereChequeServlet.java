package controle.Cheque;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Dominio.Cheque;
import modelo.Dominio.Cliente;
import modelo.Dominio.CpfInvalido;
import modelo.Persistencia.ChequeDAO;
import modelo.Persistencia.ClienteDAO;
import modelo.Persistencia.CpfInvalidoDAO;
import controle.Utilitarios.Utilitarios;

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
	
	private Cliente pegaCliente(String CNPJ) {
		
		try {
			ClienteDAO dao = new ClienteDAO();
			Cliente cliente = dao.filtraCNPJ(CNPJ);
			dao.encerra();
			
			// Se o cliente não existe no BD, não posso inserir
			// um cheque relacionado a ele!
			if(cliente == null) {
				return null;
			}
			
			return cliente;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
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
	
	private void insereCheque(Cheque cheque) throws Exception {
		ChequeDAO dao= new ChequeDAO();
		dao.insere(cheque);
		dao.encerra();
	}
	
	private void atualizaCliente(Cliente cliente) throws Exception {
		
		ClienteDAO clienteDAO = new ClienteDAO();

		int operacoesTotais = cliente.getNum_operacoes_realizadas();
		int operacoesAtuais = cliente.getNum_operacoes_atuais();
		
		cliente.setNum_operacoes_atuais(operacoesAtuais+1);
		cliente.setNum_operacoes_realizadas(operacoesTotais+1);

		clienteDAO.atualiza(cliente);
		clienteDAO.encerra();

	}
	
	private boolean validaCpf(String cpf) {
		// Se o cpf não é confiável
		CpfInvalidoDAO cpfDao;
		try {
			cpfDao = new CpfInvalidoDAO();
			CpfInvalido cpfI = cpfDao.filtraCPF(cpf);
			cpfDao.encerra();
			
			if(cpfI != null) {
				// Significa que ele tá na "lista negra" de CPFs
				return false;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private boolean validaFormulario(String numero, String cpf, String cnpj, String valorBruto, String valorDescontado, String dataVencimento) {
		
		// Se algum campo estiver vazio
		if( numero.equals("") || cpf.equals("") || cnpj.equals("") || valorBruto.equals("") || valorDescontado.equals("") || dataVencimento.equals("") ) {
			return false;
		}
		
		// Se o cliente não existir no BD
		Cliente cliente = pegaCliente(cnpj);
		if( cliente == null ) {
			return false;
		}
		
		// Se o cheque já estiver no bd
		if(!validaNumero(numero)) {
			return false;
		}
		
		// Se o valorDescontado > valorBruto
		Double valorB = Double.parseDouble(valorBruto);
		Double valorD = Double.parseDouble(valorDescontado);
		
		if( valorD > valorB ) {
			return false;
		}
		
		if(!validaCpf(cpf)) {
			return false;
		}
		
		Cheque cheque = new Cheque();
		
		cheque.setNumero(numero);
		cheque.setCpf(cpf);
		cheque.setCnpj(cnpj);
		
		Date data = Utilitarios.stringToDate(dataVencimento);
		
		cheque.setData_vencimento(data);
		
		cheque.setValor_bruto(valorB);
		cheque.setValor_descontado(valorD);
		
		try {
			// Inserindo cheque no BD
			insereCheque(cheque);			
			
			// Alterando o número de operações do cliente no BD
			atualizaCliente(cliente);
						
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
		String valorDescontado = request.getParameter("valorDescontado");
		String dataVencimento = request.getParameter("dataVencimento");
		
		
		if(Utilitarios.usuarioLogado(request)) {
			if( validaFormulario(numero, cpf, cnpj, valorBruto, valorDescontado, dataVencimento) ) {
				//request.setAttribute("cnpjEmpresa", cnpj);
				request.getRequestDispatcher("visao/inserirCheque/insereChequeSucesso.jsp").forward(request, response);
			} else {
				//request.getRequestDispatcher("visao/inserirCheque/insereChequeFalha.jsp").forward(request, response);
				response.sendRedirect("visao/inserirCheque/insereChequeForm.jsp");
			}
		} else {
			response.sendRedirect("");
		}

	}

}
