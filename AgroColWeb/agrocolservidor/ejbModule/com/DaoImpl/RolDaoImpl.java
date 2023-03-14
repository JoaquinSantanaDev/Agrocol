package com.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.Modelo.Rol;
import com.Dao.IRolDao;


@Stateless
@LocalBean
public class RolDaoImpl implements IRolDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public int altaRol(Rol rol) {
		try {
			rol.getFuncionalidades().forEach(fun->em.persist(fun));
			em.persist(rol);
			em.flush();
			return rol.getIdRol();
		} catch (Exception e) {
			System.out.println("Error al Crear Rol => " + e.getMessage());
			return -1;
		}
	}

	@Override
	public boolean bajaRol(Integer xIdRol) {
		try {			
			Rol rol = em.find(Rol.class,xIdRol);
			em.remove(rol);
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Error al Crear Usuario => " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean modificarRol(Rol rol) {
		try {			
			em.merge(rol);
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Error al Crear Usuario => " + e.getMessage());
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rol> listarTodos() {
		try {
			List<Rol> lista = em.createQuery("SELECT e FROM Rol e").getResultList();
			return lista;
		} catch (Exception e) {
			System.out.println("Error al Modificar Usuario => " + e.getMessage());
			return new ArrayList<Rol>();
		}
	}

	@Override
	public Rol buscarPorId(Integer xIdRol) {
		try {			
			Rol rol = em.find(Rol.class,xIdRol);
			em.flush();
			return rol;
		} catch (Exception e) {
			System.out.println("Error al Crear Usuario => " + e.getMessage());
			return null;
		}
	}	
}
