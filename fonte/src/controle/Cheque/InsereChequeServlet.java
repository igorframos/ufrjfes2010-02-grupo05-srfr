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
 * Servlet respons�vel por inserir um cheque no Banco de Dados
 * Verifica se o cliente � confi�vel, se o cheque possui um formato v�lido,
 * e se o cheque j� n�o est� no Banco.
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
	 * Para inserir um cheque no Banco, � preciso que haja um Cliente associado ao Cheque.
	 * Caso o cliente n�o conste no Banco de Dados, n�o � poss�vel
	 * inserir um cheque associado a ele.
	 * 
	 * @param CNPJ
	 * @return Cliente existente no BD ou <b>null</b>
	 */
	private Cliente pegaCliente(String CNPJ) {
		
		try {
			ClienteDAO dao = new ClienteDAO();
			Cliente cliente = dao.filtraCNPJ(CNPJ);
			dao.encerra();
			
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
	
	/**
	 * O n�mero de um cheque � �nico. Portanto, se j� existir
	 * um cheque com o n�mero digitado na entrada pelo usu�rio,
	 * n�o posso inser�-lo no Banco.
	 * 
	 * @param numero
	 * @return <b>true</b> se o n�mero n�o for associado a nenhum cheque do banco de dados,
	 * <br><b>false</b> sen�o.
	 */
	private boolean validaNumero(String numero) {
		try {
			ChequeDAO dao = new ChequeDAO();
			Cheque cheque = dao.filtraNumero(numero);
			dao.encerra();
			
			// Se for null � porque n�o existe o cheque no BD,
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
	
	/**
	 * Chama o modelo para inserir uma inst�ncia de Cheque no BD.
	 * @param cheque
	 * @throws Exception
	 */
	private void insereCheque(Cheque cheque) throws Exception {
		ChequeDAO dao= new ChequeDAO();
		dao.insere(cheque);
		dao.encerra();
	}
	
	/**
	 * Se o cheque pode ser inserido, ent�o vai aumentar o n�mero
	 * de opera��es realizadas com o Cliente associado, portanto,
	 * tenho que atualizar o Banco de Dados com essas informa��es.
	 * 
	 * @param cliente
	 * @throws Exception
	 */
	private void atualizaCliente(Cliente cliente) throws Exception {
		
		ClienteDAO clienteDAO = new ClienteDAO();

		int operacoesTotais = cliente.getNum_operacoes_realizadas();
		int operacoesAtuais = cliente.getNum_operacoes_atuais();
		
		cliente.setNum_operacoes_atuais(operacoesAtuais+1);
		cliente.setNum_operacoes_realizadas(operacoesTotais+1);

		clienteDAO.atualiza(cliente);
		clienteDAO.encerra();

	}
	
	/**
	 * Testa se o CPF n�o est� na lista dos n�o confi�veis
	 * (maus pagadores)
	 * @param cpf
	 * @return <b>true</b> se o cpf � confi�vel, <br>
	 * <b>false</b> sen�o.
	 */
	private boolean validaCpf(String cpf) {
		// Se o cpf n�o � confi�vel
		CpfInvalidoDAO cpfDao;
		try {
			cpfDao = new CpfInvalidoDAO();
			CpfInvalido cpfI = cpfDao.filtraCPF(cpf);
			cpfDao.encerra();
			
			if(cpfI != null) {
				// Significa que ele t� na "lista negra" de CPFs
				return false;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Valido o formul�rio de acordo com as regras de neg�cio.
	 * 
	 * @param numero
	 * @param cpf
	 * @param cnpj
	 * @param valorBruto
	 * @param valorDescontado
	 * @param dataVencimento
	 * @return <b> true </b> se a entrada � v�lida, <br>
	 * <b>false</b> sen�o.
	 * 
	 */
	private boolean validaFormulario(String numero, String cpf, String cnpj, String valorBruto, String valorDescontado, String dataVencimento) {
		
		// Se algum campo estiver vazio
		if( numero.equals("") || cpf.equals("") || cnpj.equals("") || valorBruto.equals("") || valorDescontado.equals("") || dataVencimento.equals("") ) {
			return false;
		}
		
		// Se o cliente n�o existir no BD
		Cliente cliente = pegaCliente(cnpj);
		if( cliente == null ) {
			return false;
		}
		
		// Se o cheque j� estiver no bd
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
			
			// Alterando o n�mero de opera��es do cliente no BD
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
