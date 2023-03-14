package com.Dao;

import java.util.List;

import javax.ejb.Remote;

import com.Modelo.PlantillaFormulario;

@Remote
public interface IPlantillaFormulario {
	
	public int altaFuncionalida(PlantillaFormulario PlantillaFormulario);
	
	public boolean bajaFuncionalida(Integer xIdPlantillaFormulario);

	public boolean modificarFuncionalida(PlantillaFormulario PlantillaFormulario);
	
	public List<PlantillaFormulario> listarTodos();
	
	public PlantillaFormulario buscarPorId(Integer xIdPlantillaFormulario);


}
