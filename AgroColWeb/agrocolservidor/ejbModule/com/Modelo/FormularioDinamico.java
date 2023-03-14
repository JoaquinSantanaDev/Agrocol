package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FORMULARIO_DINAMICO database table.
 * 
 */
@Entity
@Table(name="FORMULARIO_DINAMICO")
@NamedQuery(name="FormularioDinamico.findAll", query="SELECT f FROM FormularioDinamico f")
public class FormularioDinamico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_FORMULARIO_DINAMICO")
	@SequenceGenerator(name = "SEQ_ID_FORMULARIO_DINAMICO", sequenceName = "SEQ_ID_FORMULARIO_DINAMICO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_FORMULARIO_DINAMICO")
	private Integer idFormularioDinamico;

	@Column(name="PARAMETRO",nullable = false)
	private String parametro;

	//bi-directional one-to-one association to Formulario
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_FORM")
	private Formulario formulario;

	//bi-directional many-to-one association to PlantillaCasilla
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_PLANTILLA_CASILLA")
	private PlantillaCasilla plantillaCasilla;
		
	public FormularioDinamico() {
		
	}

	public long getIdFormularioDinamico() {
		return this.idFormularioDinamico;
	}

	public void setIdFormularioDinamico(Integer idFormularioDinamico) {
		this.idFormularioDinamico = idFormularioDinamico;
	}

		public String getParametro() {
		return this.parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public Formulario getFormulario() {
		return this.formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public PlantillaCasilla getPlantillaCasilla() {
		return this.plantillaCasilla;
	}

	public void setPlantillaCasilla(PlantillaCasilla plantillaCasilla) {
		this.plantillaCasilla = plantillaCasilla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFormularioDinamico == null) ? 0 : idFormularioDinamico.hashCode());
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
		FormularioDinamico other = (FormularioDinamico) obj;
		if (idFormularioDinamico == null) {
			if (other.idFormularioDinamico != null)
				return false;
		} else if (!idFormularioDinamico.equals(other.idFormularioDinamico))
			return false;
		return true;
	}
	
	

}