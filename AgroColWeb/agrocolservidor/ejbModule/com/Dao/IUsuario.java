package com.Dao;

import java.util.List;

import javax.ejb.Remote;

import com.Modelo.EnumEstado;
import com.Modelo.EnumTipoDoc;
import com.Modelo.Usuario;
import com.exception.ServiciosException;

@Remote
public interface IUsuario {
	
	public void altaUsuario(Usuario usuario) throws ServiciosException;
	
	public boolean bajaUsuario(Integer xIdUsuario);

	public boolean modificarUsuario(Usuario usuario);
	
	public boolean actualizarEstado(Integer xIdUsuario,EnumEstado estado);
	
	public List<Usuario> listarTodos();
	
	public Usuario buscarPorId(Integer xIdUsuario);
	
	public Usuario buscarPorDocTipoDoc(EnumTipoDoc tipoDocumento,String Documento);
	
	public Usuario autenticar(String nombreUsuari,String clave);
	
	public List<Usuario> listarExpertos();

	public boolean existeNombreUsuario(String nombreUsuario);
	
	
}
