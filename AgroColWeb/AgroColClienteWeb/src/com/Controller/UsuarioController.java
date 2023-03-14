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

import org.apache.commons.codec.digest.DigestUtils;

import com.Dao.IRolDao;
import com.Dao.IUsuario;
import com.Modelo.EnumEstado;
import com.Modelo.EnumTipoDoc;
import com.Modelo.Rol;
import com.Modelo.Usuario;
import com.Validator.UsuarioValidator;
import com.exception.ServiciosException;

@Named(value = "usuario") // JEE8
@SessionScoped // JEE8
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private IUsuario usuarioDao;
	
	@EJB
	private IRolDao rolDao;

	private Usuario usuarioAutenticado;

	private Usuario usuarioSeleccionado = new Usuario();

	private boolean modoEdicion = false;
	
	private boolean usuarioInvalido = false;

	private Integer id;

	private String modalidad = "";

	public UsuarioController() {
		super();
	}

	// se ejecuta antes de desplegar la vista
	public void preRenderViewListener() {

		if (id != null) {
			usuarioSeleccionado = usuarioDao.buscarPorId(id);
		} else if(!modalidad.contentEquals("insert")){
			usuarioSeleccionado = new Usuario();
		}

		if (modalidad != null) {
			if (modalidad.contentEquals("view")) {
				usuarioInvalido=false;
				modoEdicion = false;
			} else if (modalidad.contentEquals("update")) {
				usuarioInvalido=false;
				modoEdicion = true;
			} else if (modalidad.contentEquals("insert") && !usuarioInvalido) {
				usuarioSeleccionado = new Usuario();
				modoEdicion = false;
			} else {
				modoEdicion = false;
				modalidad = "view";

			}
		} else {
			modoEdicion = false;
			modalidad = "view";

		}	
		
		if(usuarioAutenticado == null || usuarioAutenticado.getNombreUsuario() == null || usuarioAutenticado.getNombreUsuario().trim().equals("")) {
			System.out.print("Usuario autenticado Nulo");
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
				ec.redirect(ec.getRequestContextPath() + "/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe Iniciar Sesion", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}else {
			System.out.print("Usuario autenticado ROL: "+usuarioAutenticado.getRol().getDescripcion().toString());
			if(!usuarioAutenticado.getRol().getDescripcion().equals("ADMIN")) {
				System.out.print("ROL: "+ usuarioAutenticado.getRol().getDescripcion().toString());
				System.out.print("El Usuario no es administrador");
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				try {
					ec.redirect(ec.getRequestContextPath() + "/inicio/index.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No posee permisos para ver esta página", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}

/*			
			boolean puede = false;
			//verifico si el rol que tiene asignado el usuario puede acceder a la funcionalidad
			for (int i = 0; i < usuarioAutenticado.getRol().getFuncionalidades().size(); i++) {
				if (usuarioAutenticado.getRol().getFuncionalidades().get(i).getIdFuncionalidad()==3) {
					puede = true;
				}
			}
			if (!puede) {
				System.out.print("ROL: "+ usuarioAutenticado.getRol().getDescripcion().toString());
				System.out.print("El Usuario no es administrador");
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				try {
					ec.redirect(ec.getRequestContextPath() + "/inicio/index.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No posee permisos para ver esta página", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
*/		
		}
		

		
	}

	@PostConstruct
	public void init() {
		usuarioSeleccionado = new Usuario();
		usuarioAutenticado = new Usuario();
	}

	public String autenticarUsuario() {
		try {
			Usuario usuarioNuevo = usuarioDao.autenticar(usuarioAutenticado.getNombreUsuario(),DigestUtils.md5Hex(usuarioAutenticado.getClave()));

			if (usuarioNuevo != null) {
				if (usuarioNuevo.getEstado() == EnumEstado.ACTIVO) {
					// mensaje de actualizacion correcta
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Existe", "");
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
					ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

					ec.redirect(ec.getRequestContextPath() + "/inicio/index.xhtml");
					usuarioAutenticado = usuarioNuevo;
				}else {
					usuarioAutenticado = new Usuario();
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario Inactivo", "");
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				}
				

			} else {
				usuarioAutenticado = new Usuario();
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario No Existe", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}

		} catch (Exception e) {
			usuarioAutenticado = new Usuario();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error 500", "Internal Server Error");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		}

		return "";
	}

	public String logOut() {
		try {
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Existe", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			usuarioAutenticado = new Usuario();

			ec.redirect(ec.getRequestContextPath() + "/Autenticacion.xhtml");
		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error 500", "Internal Server Error");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}

		return "";
	}

	public String guardarUsuario() {

		if (UsuarioValidator.validarUsuario(usuarioSeleccionado, FacesContext.getCurrentInstance(),usuarioDao)) {
			usuarioInvalido=false;
			try {
				Usuario usuarioNuevo = usuarioDao.buscarPorId(usuarioSeleccionado.getIdUsuario());
				usuarioSeleccionado.setClave(DigestUtils.md5Hex(usuarioSeleccionado.getClave()));
				

				if (usuarioNuevo == null) {
					usuarioSeleccionado.setEstado(EnumEstado.ACTIVO);
					usuarioDao.altaUsuario(usuarioSeleccionado);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado Usuario.", ""));

				} else {
					usuarioDao.modificarUsuario(usuarioSeleccionado);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado Usuario.", ""));
				}

				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/usuario/listadoUsuario.xhtml");
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

	public List<Usuario> getListadoUsuarios() {
		try {
			return usuarioDao.listarTodos();

		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error 500", "Internal Server Error");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}

		return new ArrayList<Usuario>();
	}

	public String eliminarUsuario() {

		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
			Integer id = Integer.parseInt(params.get("id"));

			if (!id.equals(usuarioAutenticado.getIdUsuario())) {
				usuarioDao.actualizarEstado(id, EnumEstado.INACTIVO);
				System.out.print(id);
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/usuario/listadoUsuario.xhtml");
			}else {				
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se Puede eliminar Usuario Logueado", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public List<Rol> getListadoRoles() {
		return rolDao.listarTodos();
	}

	public String modificarUsuario() {

		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

		Integer id = Integer.parseInt(params.get("id"));

		this.id = id;
		this.modalidad = "update";

		return "/usuario/formUsuario.xhtml?modalidad=update&id=" + id;

	}

	public Usuario getUsuarioSeleccionado() {
		if (usuarioSeleccionado == null)
			usuarioSeleccionado = new Usuario();
		return usuarioSeleccionado;
	}

//
	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public boolean getModoEdicion() {
		return modoEdicion;
	}

	public boolean isModoEdicion() {
		return modoEdicion;
	}

	public void setModoEdicion(boolean modoEdicion) {
		this.modoEdicion = modoEdicion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EnumTipoDoc[] getListDocumentos() {
		return EnumTipoDoc.values();
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

	public boolean isUsuarioInvalido() {
		return usuarioInvalido;
	}

	public void setUsuarioInvalido(boolean usuarioInvalido) {
		this.usuarioInvalido = usuarioInvalido;
	}
	
	

}
