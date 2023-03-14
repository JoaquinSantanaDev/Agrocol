package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the UNIDADES_MEDIDA database table.
 * 
 */
@Entity
@Table(name="UNIDADES_MEDIDA")
@NamedQuery(name="UnidadesMedida.findAll", query="SELECT u FROM UnidadesMedida u")
public class UnidadesMedida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_UNIDADES_MEDIDA")
	@SequenceGenerator(name = "SEQ_ID_UNIDADES_MEDIDA", sequenceName = "SEQ_ID_UNIDADES_MEDIDA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_UNIDADES_MEDIDA")
	private Integer idUnidadesMedida;

	private String nombre;

	//bi-directional many-to-one association to Casilla
	@OneToMany(mappedBy="unidadesMedida")
	private List<Casilla> casillas;

	public UnidadesMedida() {
	}

	public Integer getIdUnidadesMedida() {
		return this.idUnidadesMedida;
	}

	public void setIdUnidadesMedida(Integer idUnidadesMedida) {
		this.idUnidadesMedida = idUnidadesMedida;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Casilla> getCasillas() {
		return this.casillas;
	}

	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}

	public Casilla addCasilla(Casilla casilla) {
		getCasillas().add(casilla);
		casilla.setUnidadesMedida(this);

		return casilla;
	}

	public Casilla removeCasilla(Casilla casilla) {
		getCasillas().remove(casilla);
		casilla.setUnidadesMedida(null);

		return casilla;
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUnidadesMedida == null) ? 0 : idUnidadesMedida.hashCode());
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
		UnidadesMedida other = (UnidadesMedida) obj;
		if (idUnidadesMedida == null) {
			if (other.idUnidadesMedida != null)
				return false;
		} else if (!idUnidadesMedida.equals(other.idUnidadesMedida))
			return false;
		return true;
	}

	
	
	
	
}