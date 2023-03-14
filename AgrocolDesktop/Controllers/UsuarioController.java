package Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.codec.digest.DigestUtils;

import com.Dao.IUsuario;
import com.Modelo.Usuario;

import Genericos.UsuarioLogeadoSingleton;

public class UsuarioController {

	public static boolean logIn(String nomUsuario, String contraseña) {
		try {
			IUsuario userDaoImpl = (IUsuario) InitialContext
					.doLookup("AgroColServidor/UsuarioDaoImpl!com.Dao.IUsuario");
			Usuario user = userDaoImpl.autenticar(nomUsuario, DigestUtils.md5Hex(contraseña));
			if (user != null) {
				UsuarioLogeadoSingleton.getInstancia().setUsuario(user);
			}
			return user != null;
		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
		}
		return false;
	}

	public static boolean altaUsuario(Usuario usuario) {
		Integer idUsuario = -1;

		try {
			IUsuario userDaoImpl = (IUsuario) InitialContext
					.doLookup("AgroColServidor/UsuarioDaoImpl!com.Dao.IUsuario");
			if (usuario.getIdUsuario() != null) {
				userDaoImpl.modificarUsuario(usuario);
				idUsuario = usuario.getIdUsuario();
			} else {				
				usuario.setClave(DigestUtils.md5Hex(usuario.getClave()));
				idUsuario = userDaoImpl.altaUsuario(usuario);
			}

			return idUsuario != -1 ? true : false;

		} catch (Exception e) {
			System.out.println("ERROR => " + e);
		}
		return false;
	}

	public static List<Usuario> ObtenerUsuarios() {
		try {
			IUsuario userDaoImpl = (IUsuario) InitialContext
					.doLookup("AgroColServidor/UsuarioDaoImpl!com.Dao.IUsuario");
			return userDaoImpl.listarTodos();

		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
		}

		return new ArrayList<Usuario>();
	}

	public static Usuario ObtenerUsuariosPorId(Integer codigo) {
		try {
			IUsuario userDaoImpl = (IUsuario) InitialContext
					.doLookup("AgroColServidor/UsuarioDaoImpl!com.Dao.IUsuario");
			return userDaoImpl.buscarPorId(codigo);

		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
		}

		return new Usuario();
	}

	public static boolean eliminarUsuario(Integer codigo) {
		try {
			IUsuario userDaoImpl = (IUsuario) InitialContext
					.doLookup("AgroColServidor/UsuarioDaoImpl!com.Dao.IUsuario");
			return userDaoImpl.bajaUsuario(codigo);

		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
		}
		return false;
	}

}
