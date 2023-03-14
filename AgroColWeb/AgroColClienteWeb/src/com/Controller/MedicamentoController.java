package com.Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.Dao.IMedicamento;

import com.Modelo.Medicamento;

import com.Validator.UsuarioValidator;
import com.exception.ServiciosException;

@Named(value = "medicamento") // JEE8
@SessionScoped // JEE8
public class MedicamentoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private IMedicamento medicamentoDao;
	
	private Medicamento usuarioSeleccionado = new Medicamento();
	
	private boolean usuarioInvalido = false;

	private Integer id;

	private String modalidad = "";

	public MedicamentoController() {
		super();
	}

	// se ejecuta antes de desplegar la vista
	public void preRenderViewListener() {

		if (id != null) {
			usuarioSeleccionado = medicamentoDao.buscarPorId(id);
		} else if(!modalidad.contentEquals("insert")){
			usuarioSeleccionado = new Medicamento();
		}

		
	
		}
		

		
	

	@PostConstruct
	public void init() {
		usuarioSeleccionado = new Medicamento();
	}

	public String guardarUsuario() {

		if (UsuarioValidator.validarUsuario(usuarioSeleccionado, FacesContext.getCurrentInstance(),medicamentoDao)) {
			usuarioInvalido=false;
			try {
				Medicamento usuarioNuevo = medicamentoDao.buscarPorId(usuarioSeleccionado.getIdMedicamento());
				

				if (usuarioNuevo == null) {
					medicamentoDao.altaMedicamento(usuarioSeleccionado);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado Usuario.", ""));

				} else {
					medicamentoDao.modificarMedicamento(usuarioSeleccionado);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado Usuario.", ""));
				}

				/*ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/usuario/listadoUsuario.xhtml");*/
				usuarioSeleccionado = usuarioNuevo;
			} catch (ServiciosException e) {
				FacesMessage facesMsg = new FacesMessage(null, "Error al agregar un usuario",
						e.toString());
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				
			}
			catch (Exception e) {
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error 500",
						"Internal Server Error");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				
			}
		}else {
			id=null;
			usuarioInvalido=true;		
		}

		return "";
	}

	public List<Medicamento> getListadoUsuarios() {
		try {
			return medicamentoDao.listarTodos();
		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error 500", "Internal Server Error");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}

		return new ArrayList<Medicamento>();
	}

	public String eliminarUsuario() {

		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
			Integer id = Integer.parseInt(params.get("id"));
				medicamentoDao.bajaMedicamento(id);
				System.out.print(id);
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/usuario/listadoUsuario.xhtml");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	

	public String modificarMedicamento() {

		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

		Integer id = Integer.parseInt(params.get("id"));

		this.id = id;
		this.modalidad = "update";

		return "/usuario/formUsuario.xhtml?modalidad=update&id=" + id;

	}

	public Medicamento getUsuarioSeleccionado() {
		if (usuarioSeleccionado == null)
			usuarioSeleccionado = new Medicamento();
		return usuarioSeleccionado;
	}

//
	public void setUsuarioSeleccionado(Medicamento usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}


	public boolean isUsuarioInvalido() {
		return usuarioInvalido;
	}

	public void setUsuarioInvalido(boolean usuarioInvalido) {
		this.usuarioInvalido = usuarioInvalido;
	}
	
	

}
