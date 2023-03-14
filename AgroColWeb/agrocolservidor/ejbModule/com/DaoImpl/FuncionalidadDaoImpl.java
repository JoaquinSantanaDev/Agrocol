/**
 * 
 */
package com.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.Dao.IFuncionalidadDao;
import com.Modelo.Funcionalidad;
import com.Modelo.Rol;
import com.Modelo.Usuario;

@Stateless
@LocalBean
public class FuncionalidadDaoImpl implements IFuncionalidadDao {

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public int altaFuncionalida(Funcionalidad funcionalidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean bajaFuncionalida(Integer xIdFuncionalidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificarFuncionalida(Funcionalidad funcionalidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionalidad> listarTodos() {
		try {
			return em.createQuery("SELECT e FROM Funcionalidad e").getResultList();
		} catch (Exception e) {
			System.out.println("Error al Modificar Usuario => " + e.getMessage());
			return new ArrayList<Funcionalidad>();
		}
	}

	@Override
	public List<Funcionalidad> listarFuncionalidadesUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionalidad buscarPorId(Integer xIdFuncionalidad) {
		try {			
			Funcionalidad funcionalidad = em.find(Funcionalidad.class,xIdFuncionalidad);
			em.flush();
			return funcionalidad;
		} catch (Exception e) {
			System.out.println("Error al Crear Usuario => " + e.getMessage());
			return null;
		}
	}

	@Override
	public boolean tieneFuncionalidadUsuario(Usuario usuario, Integer idFuncionalidad) {
		try {			
			Funcionalidad funcionalidad = em.find(Funcionalidad.class,idFuncionalidad);
			em.flush();
			for (Rol rol : funcionalidad.getRoles()) {
				for(Usuario user : rol.getUsuarios()) {
					if(user.getIdUsuario().equals(usuario.getIdUsuario())) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			System.out.println("Error al Crear Usuario => " + e.getMessage());
			return false;
		}
	}

}
