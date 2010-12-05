package modelo.Persistencia;

import java.util.ArrayList;
import java.util.List;

import modelo.Apresentacao.ChequeVO;
import modelo.Dominio.Cheque;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * 
 * Classe responsável pela interação entre a classe
 * <b>Cheque</b> do Domínio
 * e o Banco de Dados
 *
 */
public class ChequeDAO {

	private SessionFactory sessaoFactory;
	
	/**
	 * Construtor, inicia a Sessão com o Hibernate,
	 * carregando as configurações estabelecidas.
	 * @throws Exception
	 */
	public ChequeDAO() throws Exception {
		sessaoFactory = new Configuration().configure().buildSessionFactory();  
    }
	
	/**
	 * Encerra a Sessão criada.
	 */
	public void encerra() {
		sessaoFactory.close();
	}
	
	/**
	 * Insere uma instância de Cheque no Banco de Dados
	 * @param cheque
	 * @throws Exception
	 */
	public void insere(Cheque cheque) throws Exception {
		Session sessao = sessaoFactory.openSession();		
		sessao.beginTransaction();
		
		sessao.save(cheque);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
	/**
	 * Lista todos os cheques do Banco de Dados
	 * @return Um <b>List</b> de <b>ChequeVO</b> com todos 
	 * os cheques do sistema
	 */
	public List<ChequeVO> listar() {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Cheque.class);
		filtro.addOrder(Order.asc("data_vencimento"));
		
		List<Cheque> lista = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		List<ChequeVO> retorno = new ArrayList<ChequeVO>();
		
		for (Cheque cheque : lista) {
			retorno.add(new ChequeVO(cheque));
		}
		
		return retorno;
	}
	
	/**
	 * Lista os cheques do Banco de Dados de acordo com "devolvido"
	 * @param devolvido
	 * @return Um <b>List</b> de <b>ChequeVO</b> que contém todos
	 * os cheques do BD ou somente os cheques devolvidos.
	 */
	public List<ChequeVO> listar(boolean devolvido) {
		
		if(!devolvido) {
			return listar();
		}
		
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Cheque.class);
		filtro.add(Restrictions.like("devolvido", 1));
		
		List<Cheque> lista = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		List<ChequeVO> retorno = new ArrayList<ChequeVO>();
		
		for (Cheque cheque : lista) {
			retorno.add(new ChequeVO(cheque));
		}
		
		return retorno;
	}
	
	/**
	 * Pega um Cheque do BD pelo número
	 * @param numero
	 * @return <b>Cheque</b> correspondente ao numero da entrada, caso exista. <br>
	 * <b>null</b> caso contrário.
	 */
	public Cheque filtraNumero(String numero) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Cheque.class);
		filtro.add(Restrictions.idEq(numero));
		
		List filtrada = filtro.list();
		
		if(filtrada.size() == 0) {
			return null;
		}
		
		Cheque cheque = (Cheque) filtrada.get(0);
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return cheque;
	}
	
	/**
	 * Lista os cheques correspondentes a um determinado cpf
	 * @param cpf
	 * @return <b>List</b> de cheques contendo todos os cheques
	 * associados à entrada.
	 */
	public List filtraCPF(String cpf) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Cheque.class);
		filtro.add(Restrictions.like("cpf", cpf));
		
		List filtrada = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		List<ChequeVO> retorno = new ArrayList<ChequeVO>();
		
		for (Object cheque : filtrada) {
			retorno.add(new ChequeVO((Cheque)cheque));
		}
		
		return retorno;
	}
	
	/**
	 * Lista todos os cheques correspondentes ao CPF ou
	 * só os devolvidos
	 * @param cpf
	 * @param devolvido
	 * @return <b>List</b> contendo todos os cheques do CPF ou só
	 * os cheques do CPF que foram devolvidos.
	 */
	public List filtraCPF(String cpf, boolean devolvido) {
		
		if(!devolvido) {
			return filtraCPF(cpf);
		}
		
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Cheque.class);
		filtro.add(Restrictions.like("cpf", cpf));
		filtro.add(Restrictions.like("devolvido", 1));
		
		List filtrada = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		List<ChequeVO> retorno = new ArrayList<ChequeVO>();
		
		for (Object cheque : filtrada) {
			retorno.add(new ChequeVO((Cheque)cheque));
		}
		
		return retorno;
	}
	
	/**
	 * Lista os cheques correspondentes ao CNPJ dado
	 * @param cnpj
	 * @return <b>List</b>
	 */
	public List filtraCNPJ(String cnpj) {
		
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Cheque.class);
		filtro.add(Restrictions.like("cnpj", cnpj));
		
		List filtrada = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		List<ChequeVO> retorno = new ArrayList<ChequeVO>();
		
		for (Object cheque : filtrada) {
			retorno.add(new ChequeVO( (Cheque)cheque));
		}
		
		return retorno;
	}
	
	/**
	 * Lista os cheques correspondentes à entrada
	 * @param cnpj
	 * @param devolvidos
	 * @return <b>List</b>
	 */
	public List filtraCNPJ(String cnpj, boolean devolvidos) {
		
		if(!devolvidos) {
			return filtraCNPJ(cnpj);
		}
		
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Cheque.class);
		filtro.add(Restrictions.like("cnpj", cnpj));
		filtro.add(Restrictions.like("devolvido", 1));
		
		List filtrada = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		List<ChequeVO> retorno = new ArrayList<ChequeVO>();
		
		for (Object cheque : filtrada) {
			retorno.add(new ChequeVO( (Cheque)cheque));
		}
		
		return retorno;	
	}
	
	/**
	 * Retira do BD o cheque correspondente ao número dado
	 * @param numero
	 */
	public void deleta(String numero) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Cheque cheque = filtraNumero(numero);
		
		sessao.delete(cheque);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
	/**
	 * Atualiza um cheque
	 * @param cheque
	 */
	public void atualiza(Cheque cheque) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		sessao.update(cheque);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
}
