package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ESTACION_MUESTREO database table.
 * 
 */
@Entity
@Table(name="ESTACION_MUESTREO")
@NamedQuery(name="EstacionMuestreo.findAll", query="SELECT e FROM EstacionMuestreo e")
public class EstacionMuestreo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ESTACION_MUESTREO")
	@SequenceGenerator(name = "SEQ_ID_ESTACION_MUESTREO", sequenceName = "SEQ_ID_ESTACION_MUESTREO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_ESTACION_MUESTREO")
	private Integer idEstacionMuestreo;

	private String nombre;

	//bi-directional many-to-one association to Formulario
	@OneToMany(mappedBy="estacionMuestreo")
	private List<Formulario> formularios;

	public EstacionMuestreo() {
	}

	public Integer getIdEstacionMuestreo() {
		return this.idEstacionMuestreo;
	}

	public void setIdEstacionMuestreo(Integer idEstacionMuestreo) {
		this.idEstacionMuestreo = idEstacionMuestreo;
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
		formulario.setEstacionMuestreo(this);

		return formulario;
	}

	public Formulario removeFormulario(Formulario formulario) {
		getFormularios().remove(formulario);
		formulario.setEstacionMuestreo(null);

		return formulario;
	}

	@Override
	public String toString() {
		return  nombre ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEstacionMuestreo == null) ? 0 : idEstacionMuestreo.hashCode());
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
		EstacionMuestreo other = (EstacionMuestreo) obj;
		if (idEstacionMuestreo == null) {
			if (other.idEstacionMuestreo != null)
				return false;
		} else if (!idEstacionMuestreo.equals(other.idEstacionMuestreo))
			return false;
		return true;
	}
	
	
	

}