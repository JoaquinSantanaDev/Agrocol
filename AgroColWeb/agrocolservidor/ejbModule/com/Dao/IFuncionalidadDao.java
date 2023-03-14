package com.Dao;

import java.util.List;

import javax.ejb.Remote;

import com.Modelo.Funcionalidad;
import com.Modelo.Usuario;

@Remote
public interface IFuncionalidadDao {
	
	public int altaFuncionalida(Funcionalidad funcionalidad);
	
	public boolean bajaFuncionalida(Integer xIdFuncionalidad);

	public boolean modificarFuncionalida(Funcionalidad funcionalidad);
	
	public List<Funcionalidad> listarTodos();
	
	public List<Funcionalidad> listarFuncionalidadesUsuario(Usuario usuario);
	
	public Funcionalidad buscarPorId(Integer xIdFuncionalidad);

	public boolean tieneFuncionalidadUsuario(Usuario usuario, Integer idFuncionalidad);

}
