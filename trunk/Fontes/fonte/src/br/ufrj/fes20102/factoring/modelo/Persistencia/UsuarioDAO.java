package br.ufrj.fes20102.factoring.modelo.Persistencia;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.ufrj.fes20102.factoring.modelo.Dominio.Usuario;

/**
 * 
 * Classe respons�vel pela intera��o entre a classe
 * <b>Usuario</b> do Dom�nio
 * e o Banco de Dados
 *
 */
public class UsuarioDAO {

	private SessionFactory sessaoFactory;
	
	/**
	 * Construtor, inicia a Sess�o com o Hibernate,
	 * carregando as configura��es estabelecidas.
	 * @throws Exception
	 */
	public UsuarioDAO() throws Exception {
		sessaoFactory = new Configuration().configure().buildSessionFactory();  
    }
	
	/**
	 * Encerra a Sess�o criada.
	 */
	public void encerra() {
		sessaoFactory.close();
	}
	
	/**
	 * Insere uma inst�ncia de Usu�rio no Banco de Dados
	 * @param usuario
	 * @throws Exception
	 */
	public void insere(Usuario usuario) throws Exception {
		Session sessao = sessaoFactory.openSession();		
		sessao.beginTransaction();
		
		sessao.save(usuario);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
	/**
	 * Lista todos os usu�rios do Banco de Dados
	 * @return <b>List</b>
	 */
	public List listar() {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Criteria filtro = sessao.createCriteria(Usuario.class);
		
		List lista = filtro.list();
		
		sessao.getTransaction().commit();
		sessao.close();
		
		return lista;	
	}
	
	/**
	 * Retorna o usu�rio associado ao login dado.
	 * @param login
	 * @return inst�ncia de <b>Usuario</b>, ou <br>
	 * <b>null</b>.
	 */
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
	
	/**
	 * Checa se o login e a senha digitados correspondem de fato
	 * a algu�m cadastrado
	 * @param login
	 * @param senha
	 * @return <b>true</b> se correspondem, <br>
	 * <b>false</b> sen�o.
	 */
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
	
	/**
	 * Deleta usu�rio do banco.
	 * @param login
	 */
	public void deleta(String login) {
		Session sessao = sessaoFactory.openSession();
		sessao.beginTransaction();
		
		Usuario usuario = filtraLogin(login);
		
		sessao.delete(usuario);
		
		sessao.getTransaction().commit();
		sessao.close();
	}
	
}
