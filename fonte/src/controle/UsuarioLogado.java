package controle;

public class UsuarioLogado {

	private static UsuarioLogado usuario;
	
	private String login;
	private boolean admin;
	
	private UsuarioLogado() {
		
	}
	
	public static UsuarioLogado pegaUsuario() {
		if(usuario == null) {
			usuario = new UsuarioLogado();
		}
		
		return usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
