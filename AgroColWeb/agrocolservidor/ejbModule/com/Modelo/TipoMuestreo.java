package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIPO_MUESTREO database table.
 * 
 */
@Entity
@Table(name="TIPO_MUESTREO")
@NamedQuery(name="TipoMuestreo.findAll", query="SELECT t FROM TipoMuestreo t")
public class TipoMuestreo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TIPO_MUESTREO")
	@SequenceGenerator(name = "SEQ_ID_TIPO_MUESTREO", sequenceName = "SEQ_ID_TIPO_MUESTREO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_TIPO_MUESTREO")
	private Integer idTipoMuestreo;

	private String nombre;

	//bi-directional many-to-one association to Formulario
	@OneToMany(mappedBy="tipoMuestreo")
	private List<Formulario> formularios;

	public TipoMuestreo() {
	}

	public Integer getIdTipoMuestreo() {
		return this.idTipoMuestreo;
	}

	public void setIdTipoMuestreo(Integer idTipoMuestreo) {
		this.idTipoMuestreo = idTipoMuestreo;
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
		formulario.setTipoMuestreo(this);

		return formulario;
	}

	public Formulario removeFormulario(Formulario formulario) {
		getFormularios().remove(formulario);
		formulario.setTipoMuestreo(null);

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
		result = prime * result + ((idTipoMuestreo == null) ? 0 : idTipoMuestreo.hashCode());
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
		TipoMuestreo other = (TipoMuestreo) obj;
		if (idTipoMuestreo == null) {
			if (other.idTipoMuestreo != null)
				return false;
		} else if (!idTipoMuestreo.equals(other.idTipoMuestreo))
			return false;
		return true;
	}
	
	
	

}