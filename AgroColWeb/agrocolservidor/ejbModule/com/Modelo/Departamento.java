package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DEPARTAMENTOS database table.
 * 
 */
@Entity
@Table(name="DEPARTAMENTOS")
@NamedQuery(name="Departamento.findAll", query="SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_DEPARTAMENTO")
	@SequenceGenerator(name = "SEQ_ID_DEPARTAMENTO", sequenceName = "SEQ_ID_DEPARTAMENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_DEPARTAMENTO")
	private Integer idDepartamento;

	@Column(name="nombre",length = 100)
	private String nombre;
	
	@OneToMany(mappedBy = "departamento",fetch = FetchType.EAGER)
	private List<Localidad> localidades;

	public Departamento() {
	}

	public Integer getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Localidad> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
	}
	
	@Override
	public String toString() {
		return  nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDepartamento == null) ? 0 : idDepartamento.hashCode());
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
		Departamento other = (Departamento) obj;
		if (idDepartamento == null) {
			if (other.idDepartamento != null)
				return false;
		} else if (!idDepartamento.equals(other.idDepartamento))
			return false;
		return true;
	}
	
	
	
	

}