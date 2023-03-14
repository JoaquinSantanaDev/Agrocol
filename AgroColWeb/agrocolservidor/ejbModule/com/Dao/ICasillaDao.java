package com.Dao;

import java.util.List;

import javax.ejb.Remote;

import com.Modelo.Casilla;
import com.Modelo.PlantillaCasilla;
import com.Modelo.TipoInput;
import com.Modelo.UnidadesMedida;

@Remote
public interface ICasillaDao {

	public int altaCasilla(Casilla Casilla);
	
	public boolean bajaCasilla(Integer xIdCasilla);

	public boolean modificarCasilla(Casilla Casilla);
	
	public List<Casilla> listarTodos();
	
	public Casilla buscarPorId(Integer xIdCasilla);

	public List<TipoInput> listarTiposInput();

	public List<UnidadesMedida> listarUnidadesMedidas();

	public void altaTipoInput(TipoInput tipoInput);

	public void altaUnidadMedida(UnidadesMedida unidad);
	
	public List<PlantillaCasilla> ObtenerCasillaDePlantilla(Integer xIdPlantilla);
	
	


}
