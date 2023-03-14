package Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.Dao.IFuncionalidadDao;
import com.Modelo.Funcionalidad;
import com.Modelo.Usuario;


public class FuncionalidadController {
	

	public static List<Funcionalidad> listarTodas () {		
		try {
			IFuncionalidadDao funcionalidadDao = (IFuncionalidadDao) InitialContext.doLookup("AgroColServidor/FuncionalidadDaoImpl!com.Dao.IFuncionalidadDao");
			return funcionalidadDao.listarTodos();				
		} catch (NamingException e) {
			System.out.println("ERROR listarTodas Funcionalidade=> " + e);
		}	
		return new ArrayList<Funcionalidad>();
	}

	public static boolean tieneFuncionalidadUsuario(Usuario usuario, Integer idFuncionalidad) {
		try {
			IFuncionalidadDao funcionalidadDao = (IFuncionalidadDao) InitialContext.doLookup("AgroColServidor/FuncionalidadDaoImpl!com.Dao.IFuncionalidadDao");
			return funcionalidadDao.tieneFuncionalidadUsuario(usuario,idFuncionalidad);				
		} catch (NamingException e) {
			System.out.println("ERROR => " + e);
		}	
		return false;
	}

}
