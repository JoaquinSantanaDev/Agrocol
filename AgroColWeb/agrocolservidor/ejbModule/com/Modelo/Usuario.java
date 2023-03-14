package com.Modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.validation.constraints.NotEmpty;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name="USUARIOS")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_USUARIO")
	@SequenceGenerator(name = "SEQ_ID_USUARIO", sequenceName = "SEQ_ID_USUARIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_USUARIO")
	private Integer idUsuario;
	
	@NotEmpty(message = "El apellido no puede ser Vacio")
	private String apellido;

	@NotEmpty(message = "Contraseña no puede ser vacia")
	private String clave;

	@NotEmpty(message = "Debe ingresar Dirección")
	private String direccion;
	
	@NotEmpty(message = "Debe ingresar Documento")
	private String documento;

	@Enumerated(EnumType.STRING)
	private EnumEstado estado;

	@NotEmpty(message = "Debe ingresar Mail")
	private String mail;

	@NotEmpty(message = "Debe ingresar Nombre")
	private String nombre;
	
	@NotEmpty(message = "Debe ingresar Profesion")
	private String profesion;
	
	@NotEmpty(message = "Debe ingresar Institucion")
	private String instituto;
	
	@Column(name="NOMBRE_USUARIO",unique=true)	
	@NotEmpty(message = "Debe ingresar Nombre de usuario")
	private String nombreUsuario;

	@Column(name="TIPO_DOC")
	@Enumerated(EnumType.STRING)
	private EnumTipoDoc tipoDoc;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_ROL")
	private Rol rol;
	
	@OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
	private List<Formulario> formularios;
	

	public Usuario() {
		this.rol=new Rol();
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public EnumEstado getEstado() {
		return this.estado;
	}

	public void setEstado(EnumEstado estado) {
		this.estado = estado;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public EnumTipoDoc getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(EnumTipoDoc tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol role) {
		this.rol = role;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getInstituto() {
		return instituto;
	}

	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}

	public List<Formulario> getFormularios() {
		return formularios;
	}

	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}

	@Override
	public String toString() {
		return  nombre + " - "+   apellido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}
}