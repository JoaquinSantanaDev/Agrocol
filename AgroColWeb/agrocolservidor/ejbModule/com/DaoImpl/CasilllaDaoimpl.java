package com.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.Dao.ICasillaDao;
import com.Modelo.Casilla;
import com.Modelo.PlantillaCasilla;
import com.Modelo.TipoInput;
import com.Modelo.UnidadesMedida;

@Stateless
@LocalBean
public class CasilllaDaoimpl implements ICasillaDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public int altaCasilla(Casilla casilla) {
		try {
			if (casilla.getIdCasilla() != null) {
				modificarCasilla(casilla);
			} else {
				casilla.setTipoInput(em.find(TipoInput.class, casilla.getTipoInput().getIdTipoInput()));
				casilla.setUnidadesMedida(em.find(UnidadesMedida.class, casilla.getUnidadesMedida().getIdUnidadesMedida()));
				em.persist(casilla);
				em.flush();
			}
			return casilla.getIdCasilla();
		} catch (Exception e) {
			System.out.println("Error al Crear Casilla => " + e.getMessage());
			return -1;
		}

	}

	@Override
	public boolean bajaCasilla(Integer xIdCasilla) {
		try {
			Casilla casilla = em.find(Casilla.class, xIdCasilla);
			em.remove(casilla);
			em.flush();
			return true;

		} catch (Exception e) {
			System.out.println("Error al bajaCasilla => " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean modificarCasilla(Casilla Casilla) {
		try {
			Casilla casila2 = em.find(Casilla.class, Casilla.getIdCasilla());
			casila2.setTipoInput(em.find(TipoInput.class, Casilla.getTipoInput().getIdTipoInput()));
			casila2.setUnidadesMedida(em.find(UnidadesMedida.class, Casilla.getUnidadesMedida().getIdUnidadesMedida()));
			casila2.setNombre(Casilla.getNombre());
			casila2.setDescripcion(Casilla.getDescripcion());
			em.merge(casila2);
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Error al modificarCasilla => " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Casilla> listarTodos() {
		try {
			return em.createQuery("SELECT e FROM Casilla e").getResultList();
		} catch (Exception e) {
			System.out.println("Error al listarTodos Usuario => " + e.getMessage());
			return new ArrayList();
		}
	}

	@Override
	public Casilla buscarPorId(Integer xIdCasilla) {
		try {
			return em.find(Casilla.class, xIdCasilla);
		} catch (Exception e) {
			System.out.println("Error al listarTodos Usuario => " + e.getMessage());
			return null;
		}
	}

	@Override
	public List<TipoInput> listarTiposInput() {
		try {
			return em.createQuery("SELECT e FROM TipoInput e").getResultList();
		} catch (Exception e) {
			System.out.println("Error al listarTodos Usuario => " + e.getMessage());
			return new ArrayList();
		}
	}

	@Override
	public List<UnidadesMedida> listarUnidadesMedidas() {
		try {
			return em.createQuery("SELECT e FROM UnidadesMedida e").getResultList();
		} catch (Exception e) {
			System.out.println("Error al listarTodos Usuario => " + e.getMessage());
			return new ArrayList();
		}
	}

	@Override
	public void altaTipoInput(TipoInput tipoInput) {
		try {
			em.persist(tipoInput);
			em.flush();
		} catch (Exception e) {
			System.out.println("Error al altaTipoInput Usuario => " + e.getMessage());
		}

	}

	@Override
	public void altaUnidadMedida(UnidadesMedida unidad) {
		try {
			em.persist(unidad);
			em.flush();
		} catch (Exception e) {
			System.out.println("Error al altaUnidadMedida => " + e.getMessage());
		}

	}

	@Override
	public List<PlantillaCasilla> ObtenerCasillaDePlantilla(Integer xIdPlantilla) {
		try {
			return em.createQuery("SELECT c FROM PlantillaCasilla c WHERE c.plantillaFormulario.idPlantilla=:id",PlantillaCasilla.class)
					.setParameter("id", xIdPlantilla).getResultList();
		} catch (Exception e) {
			System.out.println("Error al ObtenerCasillaDePlantilla => " + e.getMessage());
			return new ArrayList();
		}
	}
	
	

}
