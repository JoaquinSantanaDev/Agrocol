package com.Validator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.Dao.IUsuario;
import com.FuncionesGenericas.FuncionesGenericas;
import com.Modelo.EnumTipoDoc;
import com.Modelo.Usuario;

public class UsuarioValidator {

	public static boolean validarUsuario(Usuario usuarioSeleccionado, FacesContext currentInstance,
			IUsuario usuarioDao) {

		boolean valido = true;

		if (usuarioSeleccionado.getTipoDoc() != null && usuarioSeleccionado.getTipoDoc().equals(EnumTipoDoc.CI)) {
			if (!FuncionesGenericas.documentoPattern_CI(usuarioSeleccionado.getDocumento())) {
				currentInstance.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Formato Cedula Invalida", ""));
				valido = false;
			} else {
				if (!FuncionesGenericas.verificadorCi((usuarioSeleccionado.getDocumento()))) {
					currentInstance.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Cedula Invalida", ""));
					valido = false;
				}
			}
		}

		if (usuarioSeleccionado.getTipoDoc() == null) {
			currentInstance.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar dosumento", ""));
			valido = false;
		}

		if (usuarioSeleccionado.getClave() != null && usuarioSeleccionado.getClave().length() < 6 || usuarioSeleccionado.getClave().length() > 16) {
			currentInstance.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Clave debe contener entre 6 y 16 caracteres", ""));
			valido = false;
		}

		if (usuarioSeleccionado.getIdUsuario() == null) {

			if (usuarioDao.buscarPorDocTipoDoc(usuarioSeleccionado.getTipoDoc(),
					usuarioSeleccionado.getDocumento()) != null) {
				currentInstance.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Documento ya Existe", ""));
				valido = false;
			}

			if (usuarioDao.existeNombreUsuario(usuarioSeleccionado.getNombreUsuario())) {
				currentInstance.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Nombre de usuario ya existe", ""));
				valido = false;
			}

		}

		return valido;
	}

}
