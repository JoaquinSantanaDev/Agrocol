package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the CASILLAS database table.
 * 
 */
@Entity
@Table(name = "CASILLAS")
@NamedQuery(name = "Casilla.findAll", query = "SELECT c FROM Casilla c")
public class Casilla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_CASILLA")
	@SequenceGenerator(name = "SEQ_ID_CASILLA", sequenceName = "SEQ_ID_CASILLA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_CASILLA")
	private Integer idCasilla;

	private String descripcion;

	// bi-directional many-to-one association to TipoInput
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_INPUT")
	private TipoInput tipoInput;

	// bi-directional many-to-one association to UnidadesMedida
	@ManyToOne
	@JoinColumn(name = "ID_UNIDADES_MEDIDA")
	private UnidadesMedida unidadesMedida;

	private String nombre;

	// bi-directional many-to-one association to PlantillaCasilla
	@OneToMany(mappedBy = "casilla")
	private List<PlantillaCasilla> plantillaCasillas;

	public Casilla(int i) {
		
		idCasilla=i;
		
	}
	public Casilla() {
	}

	public Integer getIdCasilla() {
		return this.idCasilla;
	}

	public void setIdCasilla(Integer idCasilla) {
		this.idCasilla = idCasilla;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoInput getTipoInput() {
		return tipoInput;
	}

	public void setTipoInput(TipoInput tipoInput) {
		this.tipoInput = tipoInput;
	}

	public UnidadesMedida getUnidadesMedida() {
		return unidadesMedida;
	}

	public void setUnidadesMedida(UnidadesMedida unidadesMedida) {
		this.unidadesMedida = unidadesMedida;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PlantillaCasilla> getPlantillaCasillas() {
		return this.plantillaCasillas;
	}

	public void setPlantillaCasillas(List<PlantillaCasilla> plantillaCasillas) {
		this.plantillaCasillas = plantillaCasillas;
	}

	public PlantillaCasilla addPlantillaCasilla(PlantillaCasilla plantillaCasilla) {
		getPlantillaCasillas().add(plantillaCasilla);
		plantillaCasilla.setCasilla(this);

		return plantillaCasilla;
	}

	public PlantillaCasilla removePlantillaCasilla(PlantillaCasilla plantillaCasilla) {
		getPlantillaCasillas().remove(plantillaCasilla);
		plantillaCasilla.setCasilla(null);

		return plantillaCasilla;
	}

	@Override
	public String toString() {
		return  nombre ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCasilla == null) ? 0 : idCasilla.hashCode());
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
		Casilla other = (Casilla) obj;
		if (idCasilla == null) {
			if (other.idCasilla != null)
				return false;
		} else if (!idCasilla.equals(other.idCasilla))
			return false;
		return true;
	}

	
	
	
}