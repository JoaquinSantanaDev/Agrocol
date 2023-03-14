package Controllers;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import com.Dao.IRolDao;
import com.Modelo.Rol;


public class RolController {
	

	public static List<Rol> listarTodosRoles () {		
		try {
			IRolDao userDaoImpl = (IRolDao) InitialContext.doLookup("AgroColServidor/RolDaoImpl!com.Dao.IRolDao");
			return userDaoImpl.listarTodos();				
		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
		}	
		return null;
	}

	public static void altaRol(Rol rol) {
		try {
			IRolDao userDaoImpl = (IRolDao) InitialContext.doLookup("AgroColServidor/RolDaoImpl!com.Dao.IRolDao");
			userDaoImpl.altaRol(rol);				
		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
			JOptionPane.showMessageDialog(null, "Erro al Ingresar rol","Error",JOptionPane.ERROR_MESSAGE);
		}			
	}
	

}
