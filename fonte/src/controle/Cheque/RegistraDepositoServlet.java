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
	
	private void atualizaCpfInvalido(String cpf) throws Exception {
		CpfInvalidoDAO dao = new CpfInvalidoDAO();
		
		CpfInvalido cpfI = dao.filtraCPF(cpf);
		
		if(cpfI == null) {
			CpfInvalido cpfNovo = new CpfInvalido();
			cpfNovo.setCpf(cpf);
			dao.insere(cpfNovo);
		}
	}
	
	private void atualizaCheque(Cheque cheque) throws Exception {
		
		atualizaCliente(cheque.getCnpj());
		
		ChequeDAO dao = new ChequeDAO();
		
		dao.atualiza(cheque);
		dao.encerra();
		
		if(cheque.getDevolvido() == 1) {
			atualizaCpfInvalido(cheque.getCpf());
		}
	}
	
	private boolean validaForm(String numero, String dataDesconto, int devolvido) {
		
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
		cheque.setDevolvido(devolvido);
		
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
		String devolvidoMarcado[] = request.getParameterValues("devolvido");
		int devolvido = 0;
		
		if(devolvidoMarcado != null) {
			devolvido = 1;
		}
		
		if(Utilitarios.usuarioLogado(request)) {
			if(validaForm(numero, dataDesconto, devolvido)) {
				request.getRequestDispatcher("/visao/registrarDeposito/registraDepositoSucesso.jsp").forward(request, response);
			} else {
				response.sendRedirect("/visao/registrarDeposito/registraDepositoForm.jsp");
			}
		} else {
			response.sendRedirect("");
		}
		
	}

}
