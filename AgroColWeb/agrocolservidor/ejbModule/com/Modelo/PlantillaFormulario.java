package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the PLANTILLA_FORMULARIO database table.
 * 
 */
@Entity
@Table(name="PLANTILLA_FORMULARIO")
@NamedQuery(name="PlantillaFormulario.findAll", query="SELECT p FROM PlantillaFormulario p")
public class PlantillaFormulario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PLANTILLA")
	@SequenceGenerator(name = "SEQ_ID_PLANTILLA", sequenceName = "SEQ_ID_PLANTILLA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_PLANTILLA")
	private Integer idPlantilla;

	@Column(name="DESCRIPCION_PLANTILLA")
	private String descripcionPlantilla;

	@Column(name="NOMBRE_PLANTILLA")
	private String nombrePlantilla;
	
	@Column(name="activo")
	private boolean activo;

	//bi-directional many-to-one association to PlantillaCasilla
	@OneToMany(mappedBy="plantillaFormulario",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<PlantillaCasilla> plantillaCasillas;

	public PlantillaFormulario() {
		plantillaCasillas = new ArrayList<PlantillaCasilla>();
	}

	public PlantillaFormulario(Integer idPlantilla2) {
		idPlantilla=idPlantilla2;
	}

	public Integer getIdPlantilla() {
		return this.idPlantilla;
	}

	public void setIdPlantilla(Integer idPlantilla) {
		this.idPlantilla = idPlantilla;
	}

	public String getDescripcionPlantilla() {
		return this.descripcionPlantilla;
	}

	public void setDescripcionPlantilla(String descripcionPlantilla) {
		this.descripcionPlantilla = descripcionPlantilla;
	}

	public String getNombrePlantilla() {
		return this.nombrePlantilla;
	}

	public void setNombrePlantilla(String nombrePlantilla) {
		this.nombrePlantilla = nombrePlantilla;
	}

	public List<PlantillaCasilla> getPlantillaCasillas() {
		return this.plantillaCasillas;
	}

	public void setPlantillaCasillas(List<PlantillaCasilla> plantillaCasillas) {
		this.plantillaCasillas = plantillaCasillas;
	}

	public PlantillaCasilla addPlantillaCasilla(PlantillaCasilla plantillaCasilla) {
		getPlantillaCasillas().add(plantillaCasilla);
		plantillaCasilla.setPlantillaFormulario(this);

		return plantillaCasilla;
	}

	public PlantillaCasilla removePlantillaCasilla(PlantillaCasilla plantillaCasilla) {
		getPlantillaCasillas().remove(plantillaCasilla);
		plantillaCasilla.setPlantillaFormulario(null);

		return plantillaCasilla;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	
	

}