package Controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import com.Dao.IFormularioDao;
import com.Modelo.Casilla;
import com.Modelo.Departamento;
import com.Modelo.Equipamiento;
import com.Modelo.EstacionMuestreo;
import com.Modelo.Formulario;
import com.Modelo.Localidad;
import com.Modelo.MetodoMuestreo;
import com.Modelo.PlantillaCasilla;
import com.Modelo.PlantillaFormulario;
import com.Modelo.TipoMuestreo;
import com.Modelo.Zona;

public class FormularioController {

	public static Boolean  altaPlantillaFormulario(PlantillaFormulario plantillaFormulario) {
		try {
			IFormularioDao plantilla = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return plantilla.altaPlantillaFormulario(plantillaFormulario) > -1 ? true : false	;
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al altaPlantillaFormulario","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}			
	}
	
	
	public static List<PlantillaFormulario> listadoPlantillasFormulario() {
		try {
			IFormularioDao plantilla = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return plantilla.listadoPlantillasFormulario()	;
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al listadoPlantillasFormulario","Error",JOptionPane.ERROR_MESSAGE);
			return new LinkedList<PlantillaFormulario>();
		}			
	}
	
	public static List<Departamento> listadoDepartamentos() {
		try {
			IFormularioDao plantilla = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return plantilla.listadoDepartamentos();
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al listadoDepartamentos","Error",JOptionPane.ERROR_MESSAGE);
			return new LinkedList<Departamento>();
		}			
	}
	
	public static List<Localidad> listadDeLocalidades(Integer xIdDepartamento) {
		try {
			IFormularioDao plantilla = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return plantilla.listadDeLocalidades(xIdDepartamento);
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al listadDeLocalidades","Error",JOptionPane.ERROR_MESSAGE);
			return new LinkedList<Localidad>();
		}			
	}
	
	public static List<Zona> listadDeZonas(Integer xIdLocalidades) {
		try {
			IFormularioDao plantilla = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return plantilla.listadDeZonas(xIdLocalidades);
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al listadDeZonas","Error",JOptionPane.ERROR_MESSAGE);
			return new LinkedList<Zona>();
		}			
	}


	public static  List<MetodoMuestreo>  listadoMetodoMuestreo() {
		try {
			IFormularioDao plantilla = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return plantilla.listadoMetodoMuestreo();
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al listadoMetodoMuestreo","Error",JOptionPane.ERROR_MESSAGE);
			return new LinkedList<MetodoMuestreo>();
		}
	}


	public static List<TipoMuestreo>  listadoTipoMuestreo() {
		try {
			IFormularioDao plantilla = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return plantilla.listadoTipoMuestreo();
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al listadoTipoMuestreo","Error",JOptionPane.ERROR_MESSAGE);
			return new LinkedList<TipoMuestreo>();
		}
	}


	public static List<EstacionMuestreo>  listadoEstacionMuestreo() {
		try {
			IFormularioDao plantilla = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return plantilla.listadoEstacionMuestreo();
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al listadoEstacionMuestreo","Error",JOptionPane.ERROR_MESSAGE);
			return new LinkedList<EstacionMuestreo>();
		}
	}
	
	public static List<Equipamiento>  listadoEquipamientos() {
		try {
			IFormularioDao plantilla = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return plantilla.listadoEquipamientos();
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al listadoEstacionMuestreo","Error",JOptionPane.ERROR_MESSAGE);
			return new LinkedList<Equipamiento>();
		}
	}


	public static boolean altaActividadCampo(Formulario formulario) {
		try {
			IFormularioDao plantilla = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return plantilla.altaActividadCampo(formulario);
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al altaActividadCampo","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}


	public static List<Formulario> listadoActividadeCapoSegunEstacionDeMuestreo(Integer idEstacion) {
		try {
			IFormularioDao formularioDao = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return formularioDao.listadoActividadeCapoSegunEstacionDeMuestreo(idEstacion);
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al listadoActividadeCapoSegunEstacionDeMuestreo","Error",JOptionPane.ERROR_MESSAGE);
			return new ArrayList<Formulario>();
		}
	}


	public static List<Formulario> cargarListadoActividadesCampo(Date fechaDesde, Date fechaHasta) {
		try {
			IFormularioDao formularioDao = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return formularioDao.listadoActividadeRangoFechaOrdenadaDepto(fechaDesde,fechaHasta);
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al cargarListadoActividadesCampo","Error",JOptionPane.ERROR_MESSAGE);
			return new ArrayList<Formulario>();
		}
	}


	public static List<Formulario> ListadoDeUsuarioExperto() {
		try {
			IFormularioDao formularioDao = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return formularioDao.listadoActividadedeUsuarioExperto();
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al cargarListadoActividadesCampo","Error",JOptionPane.ERROR_MESSAGE);
			return new ArrayList<Formulario>();
		}
	}
	
	public static List<Formulario> ListadoActividadesCampo() {
		try {
			IFormularioDao formularioDao = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return formularioDao.ListadoActividadesCampo();
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al cargarListadoActividadesCampo","Error",JOptionPane.ERROR_MESSAGE);
			return new ArrayList<Formulario>();
		}
	}


	public static Formulario ObtenerPorId(Integer idForm) {
		try {
			IFormularioDao formularioDao = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return formularioDao.obtenerFormularioPorId(idForm);
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			JOptionPane.showMessageDialog(null, "Erro al CargarListado Actividades Campo","Error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}


	public static boolean eliminarFormulario(Integer idFormulario) {
		try {
			IFormularioDao formularioDao = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return formularioDao.eliminarFormulario(idFormulario);
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			return false;
		}
	}


	public static PlantillaFormulario obtenerPlantillaFormulario(Integer idPlantilla) {
		try {
			IFormularioDao formularioDao = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return formularioDao.obtenerPlantillaFormulario(idPlantilla);
		} catch (NamingException e) {
			JOptionPane.showMessageDialog(null, "Erro al obtener Plantilla Formulario","Error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}


	public static boolean eliminarActividadCampo(Integer idForm) {
		try {
			IFormularioDao formularioDao = (IFormularioDao) InitialContext.doLookup("AgroColServidor/FormularioDaoImpl!com.Dao.IFormularioDao");
			return formularioDao.eliminarActividadCampo(idForm);
		} catch (NamingException e) {
			System.out.println("ERROR  => " + e);
			return false;
		}
	}
}
