package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LOCALIDADES database table.
 * 
 */
@Entity
@Table(name="LOCALIDADES")
@NamedQuery(name="Localidade.findAll", query="SELECT l FROM Localidad l")
public class Localidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_LOCALIDAD")
	@SequenceGenerator(name = "SEQ_ID_LOCALIDAD", sequenceName = "SEQ_ID_LOCALIDAD", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_LOCALIDAD")
	private Integer idLocalidad;

	private String nombre;
	
	@OneToMany(mappedBy="localidad",fetch = FetchType.EAGER)
	private List<Zona> zonas;
	
	@ManyToOne
	@JoinColumn(name = "ID_DEPARTAMENTO")
	private Departamento departamento;

	public Localidad() {
	}

	public Integer getIdLocalidad() {
		return this.idLocalidad;
	}

	public void setIdLocalidad(Integer idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Zona> getZonas() {
		return zonas;
	}

	public void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return  nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLocalidad == null) ? 0 : idLocalidad.hashCode());
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
		Localidad other = (Localidad) obj;
		if (idLocalidad == null) {
			if (other.idLocalidad != null)
				return false;
		} else if (!idLocalidad.equals(other.idLocalidad))
			return false;
		return true;
	}
}