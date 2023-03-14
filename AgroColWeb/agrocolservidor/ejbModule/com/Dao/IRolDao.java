package com.Dao;

import java.util.List;

import javax.ejb.Remote;

import com.Modelo.Rol;

@Remote
public interface IRolDao {
	
	public int altaRol(Rol rol);
	
	public boolean bajaRol(Integer xIdRol);

	public boolean modificarRol(Rol rol);
	
	public List<Rol> listarTodos();
	
	public Rol buscarPorId(Integer xIdRol);
	
}
