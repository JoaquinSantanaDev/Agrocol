package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the EQUIPAMIENTO database table.
 * 
 */
@Entity
@NamedQuery(name="Equipamiento.findAll", query="SELECT e FROM Equipamiento e")
public class Equipamiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_EQUIPAMIENTO")
	@SequenceGenerator(name = "SEQ_ID_EQUIPAMIENTO", sequenceName = "SEQ_ID_EQUIPAMIENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_EQUIPAMIENTO")
	private Integer idEquipamiento;
	
	@Column(length = 100)
	private String nombre;

	//bi-directional many-to-one association to Formulario
	@OneToMany(mappedBy="equipamiento")
	private List<Formulario> formularios;

	public Equipamiento() {
	}

	public Integer getIdEquipamiento() {
		return this.idEquipamiento;
	}

	public void setIdEquipamiento(Integer idEquipamiento) {
		this.idEquipamiento = idEquipamiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Formulario> getFormularios() {
		return this.formularios;
	}

	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}

	public Formulario addFormulario(Formulario formulario) {
		getFormularios().add(formulario);
		formulario.setEquipamiento(this);

		return formulario;
	}

	public Formulario removeFormulario(Formulario formulario) {
		getFormularios().remove(formulario);
		formulario.setEquipamiento(null);

		return formulario;
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEquipamiento == null) ? 0 : idEquipamiento.hashCode());
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
		Equipamiento other = (Equipamiento) obj;
		if (idEquipamiento == null) {
			if (other.idEquipamiento != null)
				return false;
		} else if (!idEquipamiento.equals(other.idEquipamiento))
			return false;
		return true;
	}
	
	
	
	

}