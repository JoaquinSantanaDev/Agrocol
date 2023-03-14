package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the METODO_MUESTREO database table.
 * 
 */
@Entity
@Table(name="METODO_MUESTREO")
@NamedQuery(name="MetodoMuestreo.findAll", query="SELECT m FROM MetodoMuestreo m")
public class MetodoMuestreo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_METODO_MUESTREO")
	@SequenceGenerator(name = "SEQ_ID_METODO_MUESTREO", sequenceName = "SEQ_ID_METODO_MUESTREO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_METODO_MUESTREO")
	private Integer idMetodoMuestreo;

	private String nombre;

	//bi-directional many-to-one association to Formulario
	@OneToMany(mappedBy="metodoMuestreo")
	private List<Formulario> formularios;

	public MetodoMuestreo() {
	}

	public Integer getIdMetodoMuestreo() {
		return this.idMetodoMuestreo;
	}

	public void setIdMetodoMuestreo(Integer idMetodoMuestreo) {
		this.idMetodoMuestreo = idMetodoMuestreo;
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
		formulario.setMetodoMuestreo(this);

		return formulario;
	}

	public Formulario removeFormulario(Formulario formulario) {
		getFormularios().remove(formulario);
		formulario.setMetodoMuestreo(null);

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
		result = prime * result + ((idMetodoMuestreo == null) ? 0 : idMetodoMuestreo.hashCode());
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
		MetodoMuestreo other = (MetodoMuestreo) obj;
		if (idMetodoMuestreo == null) {
			if (other.idMetodoMuestreo != null)
				return false;
		} else if (!idMetodoMuestreo.equals(other.idMetodoMuestreo))
			return false;
		return true;
	}

	
	
	
	
	
}