package br.ufrj.fes20102.factoring.modelo.Persistencia;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufrj.fes20102.factoring.modelo.Dominio.Cliente;

/**
 * 
 * Classe responsável pela interação entre a classe
 * <b>Cliente</b> do Domínio
 * e o Banco de Dados
 *
 */
public class ClienteDAO {
	
	private SessionFactory sessaoFactory;

	/**
	 * Construtor, inicia a Sessão com o Hibernate,
	 * carregando as configurações estabelecidas.
	 * @throws Exception
	 */
	public ClienteDAO() throws Exception {
		sessaoFactory = new Configuration().configure().buildSessionFactory();  
    }
	
	/**
	 * Encerra a Sessão criada.
	 */
	public void encerra() {
		sessaoFactory.close();
	}
	
	/**
	 * Insere uma instância de Cliente no Banco de Dados
	 * @param cheque
	 * @throws Exception
	 */
	public void insere(Cliente cliente) throws Exception {
		Session sessao = sessaoFactory.openSession();		
		sessao.beginTransaction();
		
		sessao.save(cliente);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
	/**
	 * Lista todos os clientes do Banco de Dados
	 * @return Um <b>List</b> de <b>Cliente</b> com todos 
	 * os clientes do sistema
	 */
	public List listar() {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		List lista = sessao.createQuery("from Cliente").list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return lista;
	}
	
	/**
	 * Lista os Clientes do Banco de Dados com nome que contenha o dado
	 * @param nome
	 * @return <b>List</b>
	 */
	public List filtraNome(String nome) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Cliente.class);
		
		String nomeFiltro = "%" + nome + "%";
		filtro.add(Restrictions.like("nome", nomeFiltro));
		
		List filtrada = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return filtrada;
	}
	
	/**
	 * Pega o Cliente com o CNPJ correspondente.
	 * @param cnpj
	 * @return Instância de <b>Cliente</b> se o CNPJ existir, <br>
	 * <b>null</b> senão.
	 */
	public Cliente filtraCNPJ(String cnpj) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Cliente.class);
		filtro.add(Restrictions.idEq(cnpj));
		
		List filtrada = filtro.list();
		
		if(filtrada.size() == 0) {
			return null;
		}
		
		Cliente cliente = (Cliente) filtrada.get(0);
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return cliente;
	}
	
	/**
	 * Retira do BD o cliente correspondente ao CNPJ dado
	 * @param cnpj
	 */
	public void deleta(String cnpj) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Cliente cliente = filtraCNPJ(cnpj);
		
		sessao.delete(cliente);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
	/**
	 * Atualiza um cliente
	 * @param cliente
	 */
	public void atualiza(Cliente cliente) {
		
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		sessao.update(cliente);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
}
