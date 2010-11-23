package modelo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ChequeDAO {

	private SessionFactory sessaoFactory;
	
	public ChequeDAO() throws Exception {
		sessaoFactory = new Configuration().configure().buildSessionFactory();  
    }
	
	public void encerra() {
		sessaoFactory.close();
	}
	
	public void insere(Cheque cheque) throws Exception {
		Session sessao = sessaoFactory.openSession();		
		sessao.beginTransaction();
		
		sessao.save(cheque);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
	public List listar() {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		List lista = sessao.createQuery("from Cheque").list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return lista;
	}
	
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
	
	public List filtraCPF(String cpf) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Cheque.class);
		filtro.add(Restrictions.like("cpf", cpf));
		filtro.addOrder(Order.desc("dataVencimento"));
		
		List filtrada = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return filtrada;
	}
	
	public void deleta(String numero) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Cheque cheque = filtraNumero(numero);
		
		sessao.delete(cheque);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
}
