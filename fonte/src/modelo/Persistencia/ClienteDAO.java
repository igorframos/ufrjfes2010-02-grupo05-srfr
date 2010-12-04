package modelo.Persistencia;

import java.util.List;

import modelo.Dominio.Cliente;

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
	
	public void deleta(String cnpj) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Cliente cliente = filtraCNPJ(cnpj);
		
		sessao.delete(cliente);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
	public void atualiza(Cliente cliente) {
		
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		sessao.update(cliente);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
}
