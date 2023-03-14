package com.Modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIPO_INPUT database table.
 * 
 */
@Entity
@Table(name="TIPO_INPUT")
@NamedQuery(name="TipoInput.findAll", query="SELECT t FROM TipoInput t")
public class TipoInput implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TIPO_INPUT")
	@SequenceGenerator(name = "SEQ_ID_TIPO_INPUT", sequenceName = "SEQ_ID_TIPO_INPUT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_TIPO_INPUT")
	private Integer idTipoInput;

	private String nombre;

	//bi-directional many-to-one association to Casilla
	@OneToMany(mappedBy="tipoInput")
	private List<Casilla> casillas;

	public TipoInput() {
	}

	public Integer getIdTipoInput() {
		return this.idTipoInput;
	}

	public void setIdTipoInput(Integer idTipoInput) {
		this.idTipoInput = idTipoInput;
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
		casilla.setTipoInput(this);

		return casilla;
	}

	public Casilla removeCasilla(Casilla casilla) {
		getCasillas().remove(casilla);
		casilla.setTipoInput(null);

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
		result = prime * result + (int) (idTipoInput ^ (idTipoInput >>> 32));
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
		TipoInput other = (TipoInput) obj;
		if (idTipoInput != other.idTipoInput)
			return false;
		return true;
	}

}