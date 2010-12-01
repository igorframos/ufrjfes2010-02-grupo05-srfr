package modelo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class UsuarioDAO {

	private SessionFactory sessaoFactory;
	
	public UsuarioDAO() throws Exception {
		sessaoFactory = new Configuration().configure().buildSessionFactory();  
    }
	
	public void encerra() {
		sessaoFactory.close();
	}
	
	public void insere(Usuario usuario) throws Exception {
		Session sessao = sessaoFactory.openSession();		
		sessao.beginTransaction();
		
		sessao.save(usuario);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
	public List listar() {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Usuario.class);
		
		List lista = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return lista;	
	}
	
	public Usuario filtraLogin(String login) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Usuario.class);
		filtro.add(Restrictions.like("login", login));
		
		List filtrada = filtro.list();
		
		if(filtrada.size() == 0) {
			return null;
		}
		
		Usuario usuario = (Usuario) filtrada.get(0);
		
		return usuario;
	}
	
	public boolean checaUsuario(String login, String senha) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Usuario.class);
		filtro.add(Restrictions.like("login", login));
		
		List filtrada = filtro.list();
		
		if(filtrada.size() == 0) {
			return false;
		}
		
		Usuario usuario = (Usuario) filtrada.get(0);
		
		if( !usuario.getSenha().equals(senha) ) {
			return false;
		}
		
		return true;
	}
	
	public void deleta(String login) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Usuario usuario = filtraLogin(login);
		
		sessao.delete(usuario);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
}
