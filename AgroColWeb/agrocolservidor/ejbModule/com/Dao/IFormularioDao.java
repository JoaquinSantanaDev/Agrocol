package com.Dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.Modelo.Departamento;
import com.Modelo.Equipamiento;
import com.Modelo.EstacionMuestreo;
import com.Modelo.Formulario;
import com.Modelo.Localidad;
import com.Modelo.MetodoMuestreo;
import com.Modelo.PlantillaFormulario;
import com.Modelo.TipoMuestreo;
import com.Modelo.Usuario;
import com.Modelo.Zona;

@Remote
public interface IFormularioDao {
	
	public Integer  altaPlantillaFormulario(PlantillaFormulario plantillaFormulario);

	public List<PlantillaFormulario> listadoPlantillasFormulario();
	
	public List<Departamento> listadoDepartamentos();
	
	public List<Localidad> listadDeLocalidades(Integer xIdDepartamento);
	
	public List<Zona> listadDeZonas(Integer xIdLocalidades);

	public List<MetodoMuestreo> listadoMetodoMuestreo();

	public List<TipoMuestreo> listadoTipoMuestreo();

	public List<EstacionMuestreo> listadoEstacionMuestreo();

	public boolean altaActividadCampo(Formulario formulario);

	public List<Formulario> listadoActividadeCapoSegunEstacionDeMuestreo(Integer idEstacionMuestreo);

	public List<Equipamiento> listadoEquipamientos();
	
	public List<Formulario> listadoActividadeRangoFechaOrdenadaDepto(Date fechaDesde ,Date fechaHasta);

	public List<Formulario> listadoActividadedeUsuarioExperto(Usuario usuario);
	
	public Boolean eliminarFormulario(Integer idForm);

	public Formulario obtenerFormularioPorId(Integer idForm);

	public PlantillaFormulario obtenerPlantillaFormulario(Integer idPlantilla);

	public List<Formulario> ListadoActividadesCampo();

	public boolean eliminarActividadCampo(Integer idForm);
	
	
}
