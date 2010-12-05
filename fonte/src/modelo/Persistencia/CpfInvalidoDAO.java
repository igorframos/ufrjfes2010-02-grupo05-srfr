package modelo.Persistencia;

import java.util.ArrayList;
import java.util.List;

import modelo.Apresentacao.ChequeVO;
import modelo.Dominio.Cheque;
import modelo.Dominio.CpfInvalido;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * 
 * Classe responsável pela interação entre a classe
 * <b>CpfInvalido</b> do Domínio
 * e o Banco de Dados
 *
 */
public class CpfInvalidoDAO {

	private SessionFactory sessaoFactory;
	
	/**
	 * Construtor, inicia a Sessão com o Hibernate,
	 * carregando as configurações estabelecidas.
	 * @throws Exception
	 */
	public CpfInvalidoDAO() throws Exception {
		sessaoFactory = new Configuration().configure().buildSessionFactory();  
    }
	
	/**
	 * Encerra a Sessão criada.
	 */
	public void encerra() {
		sessaoFactory.close();
	}
	
	/**
	 * Insere uma instância de CpfInvalido no Banco de Dados
	 * @param cpf
	 * @throws Exception
	 */
	public void insere(CpfInvalido cpf) throws Exception {
		Session sessao = sessaoFactory.openSession();		
		sessao.beginTransaction();
		
		sessao.save(cpf);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
	/**
	 * Lista todos os cpfs inválidos cadastrados.
	 * @return <b>List</b>
	 */
	public List listar() {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(CpfInvalido.class);
		
		List lista = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return lista;
	}
	
	/**
	 * Checa se o cpf dado consta na tabela
	 * @param cpf
	 * @return Instância de <b>CpfInvalido</b> se o CPF constar na tabela, <br>
	 * <b>null</b> senão.
	 */
	public CpfInvalido filtraCPF(String cpf) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(CpfInvalido.class);
		filtro.add(Restrictions.like("cpf", cpf));
		
		List filtrada = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		if(filtrada.size() == 0) {
			return null;
		}
		
		return (CpfInvalido)filtrada.get(0);
	}
	
	/**
	 * Deleta cpf da tabela
	 * @param cpf
	 */
	public void deleta(String cpf) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		CpfInvalido cpfI = filtraCPF(cpf);
		
		sessao.delete(cpfI);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
}
