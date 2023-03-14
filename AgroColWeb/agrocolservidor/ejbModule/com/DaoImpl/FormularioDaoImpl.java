package com.DaoImpl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.Dao.ICasillaDao;
import com.Dao.IFormularioDao;
import com.Modelo.Departamento;
import com.Modelo.Equipamiento;
import com.Modelo.EstacionMuestreo;
import com.Modelo.Formulario;
import com.Modelo.Localidad;
import com.Modelo.MetodoMuestreo;
import com.Modelo.PlantillaCasilla;
import com.Modelo.PlantillaFormulario;
import com.Modelo.TipoMuestreo;
import com.Modelo.Usuario;
import com.Modelo.Zona;

@Stateless
@LocalBean
public class FormularioDaoImpl implements IFormularioDao {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private ICasillaDao casillaDao;

	@Override
	public Integer altaPlantillaFormulario(PlantillaFormulario plantillaFormulario) {
		try {
			if (plantillaFormulario.getIdPlantilla() == null) {
				em.persist(plantillaFormulario);
			} else {
				PlantillaFormulario vieja = em.find(PlantillaFormulario.class, plantillaFormulario.getIdPlantilla());
				boolean bandera = false;

				if (vieja.getPlantillaCasillas().size() > 0) {
					if (vieja.getPlantillaCasillas().get(0).getFormularioDinamicos().size() > 0) {
						bandera = true;
						em.flush();
						return -1;
					} else {
						em.remove(vieja);
					}
				}

				if (!bandera) {
					em.merge(plantillaFormulario);
				}
			}
			em.flush();
			return plantillaFormulario.getIdPlantilla();
		} catch (Exception e) {
			System.out.println("Error al alta Plantilla Formulario => " + e);
			return -1;
		}
	}

	@Override
	public List<PlantillaFormulario> listadoPlantillasFormulario() {
		try {
			return em.createQuery("SELECT e FROM PlantillaFormulario e where e.activo=true").getResultList();
		} catch (Exception e) {
			System.out.println("Error al altaTipoInput Usuario => " + e.getMessage());
			return new LinkedList<PlantillaFormulario>();
		}
	}

	@Override
	public List<Departamento> listadoDepartamentos() {
		try {
			return em.createQuery("SELECT e FROM Departamento e").getResultList();
		} catch (Exception e) {
			System.out.println("Error al altaTipoInput Usuario => " + e.getMessage());
			return new LinkedList<Departamento>();
		}
	}

	@Override
	public List<Localidad> listadDeLocalidades(Integer xIdDepartamento) {
		try {
			return em.createQuery("SELECT e FROM Localidad e WHERE e.departamento.idDepartamento=:id")
					.setParameter("id", xIdDepartamento).getResultList();
		} catch (Exception e) {
			System.out.println("Error al altaTipoInput Usuario => " + e.getMessage());
			return new LinkedList<Localidad>();
		}
	}

	@Override
	public List<Zona> listadDeZonas(Integer xIdLocalidades) {
		try {
			return em.createQuery("SELECT e FROM Zona e WHERE e.localidad.idLocalidad=:id")
					.setParameter("id", xIdLocalidades).getResultList();
		} catch (Exception e) {
			System.out.println("Error al altaTipoInput Usuario => " + e.getMessage());
			return new LinkedList<Zona>();
		}
	}

	@Override
	public List<MetodoMuestreo> listadoMetodoMuestreo() {
		try {
			return em.createQuery("SELECT e FROM MetodoMuestreo e").getResultList();
		} catch (Exception e) {
			System.out.println("Error al altaTipoInput Usuario => " + e.getMessage());
			return new LinkedList<MetodoMuestreo>();
		}
	}

	@Override
	public List<TipoMuestreo> listadoTipoMuestreo() {
		try {
			return em.createQuery("SELECT e FROM TipoMuestreo e").getResultList();
		} catch (Exception e) {
			System.out.println("Error al altaTipoInput Usuario => " + e.getMessage());
			return new LinkedList<TipoMuestreo>();
		}
	}

	@Override
	public List<EstacionMuestreo> listadoEstacionMuestreo() {
		try {
			return em.createQuery("SELECT e FROM EstacionMuestreo e").getResultList();
		} catch (Exception e) {
			System.out.println("Error al altaTipoInput Usuario => " + e.getMessage());
			return new LinkedList<EstacionMuestreo>();
		}
	}

	@Override
	public boolean altaActividadCampo(Formulario formulario) {
		try {
			
			
			formulario.getFormularioDinamicos().forEach(formDinamic -> {
				formDinamic.setFormulario(formulario);
				formDinamic.setParametro(formDinamic.getParametro());
				formDinamic.setPlantillaCasilla(em.find(PlantillaCasilla.class, formDinamic.getPlantillaCasilla().getIdPlantillaCasilla()));
			});
			
			if (formulario.getIdForm() != null) {				
				em.createQuery("DELETE FROM FormularioDinamico e WHERE formulario.idForm=:idForm").setParameter("idForm", formulario.getIdForm()).executeUpdate();
				em.merge(formulario);
			}else {
				em.persist(formulario);
			}
			
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Error al alta Alta Actividad Campo => " + e);
			return false;
		}
	}

	@Override
	public List<Formulario> listadoActividadeCapoSegunEstacionDeMuestreo(Integer idEstacionMuestreo) {
		try {
			return em.createQuery(
					"SELECT e FROM Formulario e WHERE e.estacionMuestreo.idEstacionMuestreo=:idEstacion and e.activo=TRUE")
					.setParameter("idEstacion", idEstacionMuestreo).getResultList();
		} catch (Exception e) {
			System.out.println("Error al listadoActividadeCapoSegunEstacionDeMuestreo Usuario => " + e.getMessage());
			return new LinkedList<Formulario>();
		}
	}

	@Override
	public List<Equipamiento> listadoEquipamientos() {
		try {
			return em.createQuery("SELECT e FROM Equipamiento e").getResultList();
		} catch (Exception e) {
			System.out.println("Error al altaTipoInput Usuario => " + e.getMessage());
			return new LinkedList<Equipamiento>();
		}
	}

	@Override
	public List<Formulario> listadoActividadeRangoFechaOrdenadaDepto(Date fechaDesde, Date fechaHasta) {
		try {
			return em.createQuery(
					"SELECT e FROM Formulario e WHERE e.activo=TRUE and  e.fecha BETWEEN :startDate AND :endDate")
					.setParameter("startDate", fechaDesde).setParameter("endDate", fechaHasta).getResultList();
		} catch (Exception e) {
			System.out.println("Error al listadoActividadeCapoSegunEstacionDeMuestreo Usuario => " + e.getMessage());
			return new LinkedList<Formulario>();
		}
	}

	@Override
	public List<Formulario> listadoActividadedeUsuarioExperto(Usuario usuario) {
		try {
			return em.createQuery(
					"SELECT e FROM Formulario e WHERE e.activo=TRUE and  e.usuario.idUsuario=:idUser ORDER BY e.zona.localidad.departamento.nombre")
					.setParameter("idUser", usuario.getIdUsuario())
					.getResultList();
		} catch (Exception e) {
			System.out.println("Error al listadoActividadeCapoSegunEstacionDeMuestreo Usuario => " + e.getMessage());
			return new LinkedList<Formulario>();
		}
	}

	@Override
	public Boolean eliminarFormulario(Integer idForm) {
		try {
			PlantillaFormulario form = em.find(PlantillaFormulario.class, idForm);
			form.setActivo(false);
			em.merge(form);
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Error al eliminarFormulario Usuario => " + e.getMessage());
			return false;
		}
	}

	@Override
	public Formulario obtenerFormularioPorId(Integer idForm) {
		try {
			Formulario form = em.find(Formulario.class, idForm);
			em.flush();
			return form;
		} catch (Exception e) {
			System.out.println("Error al obtenerFormularioPorId Usuario => " + e.getMessage());
			return null;
		}
	}

	@Override
	public PlantillaFormulario obtenerPlantillaFormulario(Integer idPlantilla) {
		try {
			return em.find(PlantillaFormulario.class, idPlantilla);
		} catch (Exception e) {
			System.out.println("Error al listadoActividadeCapoSegunEstacionDeMuestreo Usuario => " + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Formulario> ListadoActividadesCampo() {
		try {
			return em.createQuery("SELECT e FROM Formulario e WHERE e.activo=TRUE").getResultList();
		} catch (Exception e) {
			System.out.println("Error al listadoActividadeCapoSegunEstacionDeMuestreo Usuario => " + e.getMessage());
			return new LinkedList<Formulario>();
		}
	}

	@Override
	public boolean eliminarActividadCampo(Integer idForm) {
		try {
			Formulario form = em.find(Formulario.class, idForm);
			form.setActivo(false);
			em.merge(form);
			em.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Error al eliminarFormulario Usuario => " + e.getMessage());
			return false;
		}
	}

}
