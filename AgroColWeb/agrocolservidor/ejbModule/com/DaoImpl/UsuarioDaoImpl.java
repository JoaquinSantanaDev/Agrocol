package com.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.Dao.IUsuario;
import com.Modelo.EnumEstado;
import com.Modelo.EnumTipoDoc;
import com.Modelo.Usuario;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class UsuarioDaoImpl implements IUsuario {

	@PersistenceContext
	private EntityManager em;

	public UsuarioDaoImpl() {
	}

	@Override
	public void altaUsuario(Usuario Usuario) throws ServiciosException {
		try {
			em.persist(Usuario);
			em.flush();
			//Usuario.getIdUsuario();
			
		} catch (Exception e) {
			throw new ServiciosException(
					"Error al Crear Usuario => " + e.getMessage());
			
		}
	}

	@Override
	public boolean bajaUsuario(Integer xIdUsuario) {
		try {
			Usuario xUsuario = em.find(Usuario.class, xIdUsuario);
			em.remove(xUsuario);
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Error al Eliminar Usuario => " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean modificarUsuario(Usuario usuario) {
		try {
			em.merge(usuario);
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Error al Modificar Usuario => " + e.getMessage());
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarTodos() {
		try {
			return em.createQuery("SELECT e FROM Usuario e").getResultList();
		} catch (Exception e) {
			System.out.println("Error al listarTodos Usuario => " + e.getMessage());
			return new ArrayList<Usuario>();
		}
	}

	@Override
	public Usuario buscarPorId(Integer xIdUsuario) {
		try {
			return em.find(Usuario.class, xIdUsuario);
		} catch (Exception e) {
			System.out.println("Error al buscarPorId Usuario => " + e.getMessage());
			return null;
		}
	}

	@Override
	public Usuario buscarPorDocTipoDoc(EnumTipoDoc tipoDocumento, String Documento) {
		try {
			return em.createQuery("SELECT e FROM Usuario e WHERE e.documento = ?1 AND e.tipoDoc=?2", Usuario.class)
					.setParameter(1, Documento).setParameter(2, tipoDocumento).getSingleResult();
		} catch (Exception e) {
			System.out.println("Error al buscarPorDocTipoDoc Usuario => " + e.getMessage());
			return null;
		}
	}

	@Override
	public Usuario autenticar(String nombreUsuario, String clave) {
		try {
			System.out.println(clave);
			System.out.println(nombreUsuario);
			Usuario user = em
					.createQuery("SELECT e FROM Usuario e WHERE e.nombreUsuario = :nom AND e.clave=:clav",
							Usuario.class)
					.setParameter("nom", nombreUsuario).setParameter("clav", clave).getSingleResult();
			return user;
		} catch (Exception e) {
			System.out.println("Error al autenticar Usuario => " + e);
			return null;
		}
	}

	@Override
	public boolean actualizarEstado(Integer xIdUsuario, EnumEstado estado) {
		try {
			Usuario usuarioViejo = em.find(Usuario.class, xIdUsuario);
			usuarioViejo.setEstado(estado);
			em.merge(usuarioViejo);
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Error al Modificar Usuario => " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Usuario> listarExpertos() {
		try {
			return em.createQuery("SELECT e FROM Usuario e WHERE e.rol.idRol=2").getResultList();
		} catch (Exception e) {
			System.out.println("Error al listarTodos listarExpertos => " + e.getMessage());
			return new ArrayList<Usuario>();
		}
	}

	@Override
	public boolean existeNombreUsuario(String nombreUsuario) {
		try {
			Usuario user = em
					.createQuery("SELECT e FROM Usuario e WHERE e.nombreUsuario = :nom",
							Usuario.class)
					.setParameter("nom", nombreUsuario).getSingleResult();
			return user != null;
		} catch (Exception e) {
			System.out.println("Error al listarTodos listarExpertos => " + e.getMessage());
			return false;
		}
	}
	

}
