package com.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the FORMULARIOS database table.
 * 
 */
@Entity
@Table(name = "FORMULARIOS")
@NamedQuery(name = "Formulario.findAll", query = "SELECT f FROM Formulario f")
public class Formulario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_FORM")
	@SequenceGenerator(name = "SEQ_ID_FORM", sequenceName = "SEQ_ID_FORM", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_FORM")
	private Integer idForm;

	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private String geopunto;
	
	private Boolean activo;
	
	private String resumen;
	
	@Column(name = "NOMBRE_FORMULARIO",length = 100)
	private String nombre;

	// bi-directional many-to-one association to Equipamiento
	@ManyToOne
	@JoinColumn(name = "ID_EQUIPAMIENTO")
	private Equipamiento equipamiento;

	// bi-directional many-to-one association to EstacionMuestreo
	@ManyToOne
	@JoinColumn(name = "ID_ESTACION_MUESTREO")
	private EstacionMuestreo estacionMuestreo;

	// bi-directional many-to-one association to Localidade
	@ManyToOne
	@JoinColumn(name = "ID_ZONA")
	private Zona zona;

	// bi-directional many-to-one association to MetodoMuestreo
	@ManyToOne
	@JoinColumn(name = "ID_METODO_MUESTREO")
	private MetodoMuestreo metodoMuestreo;

	// bi-directional many-to-one association to FormularioDinamico
	@OneToMany(mappedBy = "formulario",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<FormularioDinamico> formularioDinamicos;

	// bi-directional many-to-one association to TipoMuestreo
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_MUESTREO")
	private TipoMuestreo tipoMuestreo;

	@JoinColumn(name = "ID_USUARIO")
	@ManyToOne
	private Usuario usuario;

	public Formulario() {
		formularioDinamicos = new ArrayList<FormularioDinamico>();
	}

	public Integer getIdForm() {
		return this.idForm;
	}

	public void setIdForm(Integer idForm) {
		this.idForm = idForm;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getGeopunto() {
		return this.geopunto;
	}

	public void setGeopunto(String geopunto) {
		this.geopunto = geopunto;
	}

	public String getResumen() {
		return this.resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public Equipamiento getEquipamiento() {
		return this.equipamiento;
	}

	public void setEquipamiento(Equipamiento equipamiento) {
		this.equipamiento = equipamiento;
	}

	public EstacionMuestreo getEstacionMuestreo() {
		return this.estacionMuestreo;
	}

	public void setEstacionMuestreo(EstacionMuestreo estacionMuestreo) {
		this.estacionMuestreo = estacionMuestreo;
	}

	public MetodoMuestreo getMetodoMuestreo() {
		return this.metodoMuestreo;
	}

	public void setMetodoMuestreo(MetodoMuestreo metodoMuestreo) {
		this.metodoMuestreo = metodoMuestreo;
	}

	public TipoMuestreo getTipoMuestreo() {
		return this.tipoMuestreo;
	}

	public void setTipoMuestreo(TipoMuestreo tipoMuestreo) {
		this.tipoMuestreo = tipoMuestreo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public List<FormularioDinamico> getFormularioDinamicos() {
		return formularioDinamicos;
	}

	public void setFormularioDinamicos(List<FormularioDinamico> formularioDinamicos) {
		this.formularioDinamicos = formularioDinamicos;
	}
	
	public void addFormularioDinamicos(FormularioDinamico form) {
		formularioDinamicos.add(form);
	}
	
	public void removeFormularioDinamicos(FormularioDinamico form) {
		formularioDinamicos.remove(form);
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}