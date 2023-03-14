package Genericos;

import com.Modelo.Usuario;

public class UsuarioLogeadoSingleton {
	
	private Usuario usuario;
	
	
	private static UsuarioLogeadoSingleton userLog = null;
	
	private UsuarioLogeadoSingleton() {	
		usuario=new Usuario();
		
	}
	
	public static UsuarioLogeadoSingleton getInstancia() {
		return userLog==null ? userLog = new UsuarioLogeadoSingleton() : userLog;
	}
	
	public void setUsuario(Usuario user) {
		this.usuario = user;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

}
