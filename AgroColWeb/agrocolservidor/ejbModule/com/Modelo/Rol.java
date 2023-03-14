package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the ROLES database table.
 * 
 */
@Entity
@Table(name = "ROLES")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_ROL")
	@SequenceGenerator(name = "SEQ_ID_ROL", sequenceName = "SEQ_ID_ROL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_ROL")
	private Integer idRol;

	private String descripcion;

	private String nombre;

	// bi-directional many-to-many association to Funcionalidade
	@ManyToMany
	@JoinTable(name = "ROL_FUNCION", joinColumns = { @JoinColumn(name = "ID_ROL") }, inverseJoinColumns = {@JoinColumn(name = "ID_FUNCION")})
	private List<Funcionalidad> funcionalidades;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "rol")
	private List<Usuario> usuarios;

	public Rol() {
		this.funcionalidades = new ArrayList<Funcionalidad>();
	}

	public Integer getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Funcionalidad> getFuncionalidades() {
		return this.funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setRol(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setRol(null);

		return usuario;
	}

	@Override
	public String toString() {
		return String.valueOf(idRol);
	}

}