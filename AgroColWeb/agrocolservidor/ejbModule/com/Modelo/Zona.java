package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ZONAS database table.
 * 
 */
@Entity
@Table(name="ZONAS")
@NamedQuery(name="Zona.findAll", query="SELECT z FROM Zona z")
public class Zona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ZONA")
	@SequenceGenerator(name = "SEQ_ID_ZONA", sequenceName = "SEQ_ID_ZONA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_ZONA")
	private Integer idZona;

	private String nombre;

	//bi-directional many-to-one association to Formulario
	@OneToMany(mappedBy="zona")
	private List<Formulario> formularios;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_LOCALIDAD")
	private Localidad localidad;

	public Zona() {
	}

	public Integer getIdZona() {
		return this.idZona;
	}

	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Formulario> getFormularios() {
		return formularios;
	}

	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	@Override
	public String toString() {
		return  nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idZona == null) ? 0 : idZona.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zona other = (Zona) obj;
		if (idZona == null) {
			if (other.idZona != null)
				return false;
		} else if (!idZona.equals(other.idZona))
			return false;
		return true;
	}
}