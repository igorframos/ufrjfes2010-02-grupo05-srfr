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

public class CpfInvalidoDAO {

	private SessionFactory sessaoFactory;
	
	public CpfInvalidoDAO() throws Exception {
		sessaoFactory = new Configuration().configure().buildSessionFactory();  
    }
	
	public void encerra() {
		sessaoFactory.close();
	}
	
	public void insere(CpfInvalido cpf) throws Exception {
		Session sessao = sessaoFactory.openSession();		
		sessao.beginTransaction();
		
		sessao.save(cpf);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
	public List listar() {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(CpfInvalido.class);
		
		List lista = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return lista;
	}
	
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
	
	public void deleta(String cpf) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		CpfInvalido cpfI = filtraCPF(cpf);
		
		sessao.delete(cpfI);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
}
