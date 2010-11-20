package modelo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ClienteDAO {
	
	private SessionFactory sessaoFactory;

	public ClienteDAO() throws Exception {
		sessaoFactory = new Configuration().configure().buildSessionFactory();  
    }
	
	public void encerra() {
		sessaoFactory.close();
	}
	
	public void insere(Cliente cliente) throws Exception {
		Session sessao = sessaoFactory.openSession();		
		sessao.beginTransaction();
		
		sessao.save(cliente);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
	public List listar() {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		List lista = sessao.createQuery("from Cliente").list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return lista;
	}
	
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
	
	public List filtraCNPJ(String cnpj) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Cliente.class);
		//filtro.add(Restrictions.like("cnpj", cnpj));
		filtro.add(Restrictions.idEq(cnpj));
		filtro.addOrder(Order.desc("dataVencimento"));
		
		List filtrada = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return filtrada;
	}
	
	public Cliente encontraCNPJ(String cnpj) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		List lista = filtraCNPJ(cnpj);
		
		Cliente cliente = new Cliente();
		
		if(lista != null && lista.size() > 0) {
			cliente = (Cliente) lista.get(0);
		}
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return cliente;
	}
	
	public void deleta(String cnpj) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Cliente cliente = encontraCNPJ(cnpj);
		
		sessao.delete(cliente);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
}
