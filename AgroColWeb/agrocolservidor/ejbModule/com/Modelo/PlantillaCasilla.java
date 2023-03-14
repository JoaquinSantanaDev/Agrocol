package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the PLANTILLA_CASILLA database table.
 * 
 */
@Entity
@Table(name="PLANTILLA_CASILLA")
@NamedQuery(name="PlantillaCasilla.findAll", query="SELECT p FROM PlantillaCasilla p")
public class PlantillaCasilla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PLANTILLA_CASILLA")
	@SequenceGenerator(name = "SEQ_ID_PLANTILLA_CASILLA", sequenceName = "SEQ_ID_PLANTILLA_CASILLA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_PLANTILLA_CASILLA")
	private Integer idPlantillaCasilla;

	//bi-directional many-to-one association to FormularioDinamico
	@OneToMany(mappedBy="plantillaCasilla")
	private List<FormularioDinamico> formularioDinamicos;

	//bi-directional many-to-one association to Casilla
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_CASILLA")
	private Casilla casilla;

	//bi-directional many-to-one association to PlantillaFormulario
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_PLANTILLA_FORMULARIO")
	private PlantillaFormulario plantillaFormulario;
	
	@Transient
	private String parametro;

	public PlantillaCasilla() {
	}

	public Integer getIdPlantillaCasilla() {
		return this.idPlantillaCasilla;
	}

	public void setIdPlantillaCasilla(Integer idPlantillaCasilla) {
		this.idPlantillaCasilla = idPlantillaCasilla;
	}

	public List<FormularioDinamico> getFormularioDinamicos() {
		return this.formularioDinamicos;
	}

	public void setFormularioDinamicos(List<FormularioDinamico> formularioDinamicos) {
		this.formularioDinamicos = formularioDinamicos;
	}

	public FormularioDinamico addFormularioDinamico(FormularioDinamico formularioDinamico) {
		getFormularioDinamicos().add(formularioDinamico);
		formularioDinamico.setPlantillaCasilla(this);

		return formularioDinamico;
	}

	public FormularioDinamico removeFormularioDinamico(FormularioDinamico formularioDinamico) {
		getFormularioDinamicos().remove(formularioDinamico);
		formularioDinamico.setPlantillaCasilla(null);

		return formularioDinamico;
	}

	public Casilla getCasilla() {
		return this.casilla;
	}

	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}

	public PlantillaFormulario getPlantillaFormulario() {
		return this.plantillaFormulario;
	}

	public void setPlantillaFormulario(PlantillaFormulario plantillaFormulario) {
		this.plantillaFormulario = plantillaFormulario;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
}