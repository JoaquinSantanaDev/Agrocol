package Vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Modelo.Casilla;
import com.Modelo.Funcionalidad;
import com.Modelo.Rol;
import com.Modelo.TipoInput;
import com.Modelo.UnidadesMedida;

import Controllers.CasillaController;
import Controllers.RolController;
import Genericos.UsuarioLogeadoSingleton;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FrmMenuPrincipalOld extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public FrmMenuPrincipalOld() {

		//altasDinamicas();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAltaUsuario = new JButton("Alta Usuario");
		btnAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmAltaUsuario window = new FrmAltaUsuario();
				window.frmAgrocol.setVisible(true);
			}
		});

		btnAltaUsuario.setBounds(169, 163, 136, 73);
		contentPane.add(btnAltaUsuario);

		JButton btnFuncionalidades = new JButton("Permisos Usuario");
		btnFuncionalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoFuncUsuario window = new FrmListadoFuncUsuario();
				window.frmFuncionalidades.setVisible(true);
			}
		});
		btnFuncionalidades.setBounds(169, 11, 146, 73);
		contentPane.add(btnFuncionalidades);

		JButton btnCerrarSesion = new JButton("cerrarSession");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hide();
				FrmLogin frame = new FrmLogin();
				frame.setVisible(true);
				UsuarioLogeadoSingleton.getInstancia().setUsuario(null);
			}
		});
		btnCerrarSesion.setBounds(517, 414, 136, 35);
		contentPane.add(btnCerrarSesion);

		JButton btnListadoFuncionalidades = new JButton("Listado Funcionalidades");
		btnListadoFuncionalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoFuncionalidades frame = new FrmListadoFuncionalidades();
				frame.frmFuncionalidades.setVisible(true);
			}
		});
		btnListadoFuncionalidades.setBounds(325, 11, 182, 73);
		contentPane.add(btnListadoFuncionalidades);

		JButton btnCrearFormulario = new JButton("CrearFormulario");
		btnCrearFormulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmAltaPlantillaFormulario frm = new FrmAltaPlantillaFormulario(null);
			}
		});
		btnCrearFormulario.setBounds(315, 163, 136, 73);
		contentPane.add(btnCrearFormulario);

		JButton btnAltaCasilla = new JButton("Alta Casilla");
		btnAltaCasilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCasillas frame = new FrmCasillas();
			}
		});
		btnAltaCasilla.setBounds(23, 163, 136, 73);
		contentPane.add(btnAltaCasilla);
		
		JButton btnListaDeFormularios = new JButton("Lista de Formularios");
		btnListaDeFormularios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoPlantillas frm = new FrmListadoPlantillas();
			}
		});
		btnListaDeFormularios.setBounds(13, 376, 146, 73);
		contentPane.add(btnListaDeFormularios);
		
		JButton btnAcvcampoSegunEstacin = new JButton("Actividad de Campo Segun Estaci\u00F3n de Muestreo");
		btnAcvcampoSegunEstacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoFormPorEstacionMuestreo frm = new  FrmListadoFormPorEstacionMuestreo();
			}
		});
		btnAcvcampoSegunEstacin.setBounds(350, 292, 277, 73);
		contentPane.add(btnAcvcampoSegunEstacin);
		
		JButton btnPorFechas = new JButton("Por Fechas");
		btnPorFechas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoActividadesPorFecha frm = new FrmListadoActividadesPorFecha();
			}
		});
		btnPorFechas.setBounds(204, 292, 136, 73);
		contentPane.add(btnPorFechas);
		
		JButton btnDeExperto = new JButton("De Experto");
		btnDeExperto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoFormDeExperto frm = new FrmListadoFormDeExperto();
			}
		});
		btnDeExperto.setBounds(169, 376, 182, 73);
		contentPane.add(btnDeExperto);
		
		JButton btnListadoActividadeCampo = new JButton("Listado Actividades de Campo");
		btnListadoActividadeCampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmListadoActividadesDeCampo frm = new FrmListadoActividadesDeCampo();
			}
		});
		btnListadoActividadeCampo.setBounds(383, 376, 182, 73);
		contentPane.add(btnListadoActividadeCampo);
	}

	private void altasDinamicas() {
		ArrayList<Funcionalidad> colFun = new ArrayList<Funcionalidad>();
		Rol rol = new Rol();
		rol.setDescripcion("Administrador");
		rol.setNombre("Admin");
		rol.setUsuarios(null);

		Funcionalidad func = new Funcionalidad();
		func.setDescripcion("Alta Usuario, Baja, Modificacion de Usuario");
		func.setNombre("ABM Usuario");
		colFun.add(func);

		func = new Funcionalidad();
		func.setDescripcion("Listar Funcionalidades");
		func.setNombre("Listado de Todas la Funcionalidades");
		colFun.add(func);
		rol.setFuncionalidades(colFun);
		RolController.altaRol(rol);

		TipoInput tipo = new TipoInput();
		tipo.setNombre("Texto");
		CasillaController.altaTipoInput(tipo);

		tipo = new TipoInput();
		tipo.setNombre("Numerico");
		CasillaController.altaTipoInput(tipo);

		tipo = new TipoInput();
		tipo.setNombre("Fecha");
		CasillaController.altaTipoInput(tipo);

		tipo = new TipoInput();
		tipo.setNombre("Entero");
		CasillaController.altaTipoInput(tipo);

		UnidadesMedida unidad = new UnidadesMedida();
		unidad.setNombre("KiloGramos");
		CasillaController.altaUnidadMedida(unidad);

		unidad = new UnidadesMedida();
		unidad.setNombre("Litros");
		CasillaController.altaUnidadMedida(unidad);

		unidad = new UnidadesMedida();
		unidad.setNombre("Metros");
		CasillaController.altaUnidadMedida(unidad);

		unidad = new UnidadesMedida();
		unidad.setNombre("Centimetros");
		CasillaController.altaUnidadMedida(unidad);

		unidad = new UnidadesMedida();
		unidad.setNombre("Metros Cuatrados");
		CasillaController.altaUnidadMedida(unidad);

	}
}
