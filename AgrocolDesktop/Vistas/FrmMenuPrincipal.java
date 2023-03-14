package Vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Modelo.Casilla;
import com.Modelo.Funcionalidad;
import com.Modelo.Rol;
import com.Modelo.TipoInput;
import com.Modelo.UnidadesMedida;
import com.Modelo.Usuario;

import Controllers.CasillaController;
import Controllers.FuncionalidadController;
import Controllers.RolController;
import Controllers.UsuarioController;
import Genericos.UsuarioLogeadoSingleton;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmMenuPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public FrmMenuPrincipal() {

		// altasDinamicas();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1032, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		JLabel LblCerrarSesion = new JLabel("");
		setResizable(false);
		LblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hide();
				FrmLogin frame = new FrmLogin();
				frame.setVisible(true);
				UsuarioLogeadoSingleton.getInstancia().setUsuario(null);
			}
		});

		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario Usuario = UsuarioLogeadoSingleton.getInstancia().getUsuario();
				if (FuncionalidadController.tieneFuncionalidadUsuario(Usuario, 9)) {
					FrmCasillas frame = new FrmCasillas();
				} else {
					JOptionPane.showMessageDialog(contentPane, "No tiene permiso para esta funcionalidad", "Atención",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario Usuario = UsuarioLogeadoSingleton.getInstancia().getUsuario();
				if (FuncionalidadController.tieneFuncionalidadUsuario(Usuario, 1)) {
					FrmAltaUsuario window = new FrmAltaUsuario();
					window.frmAgrocol.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(contentPane, "No tiene permiso para esta funcionalidad", "Atención",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		lblNewLabel_1.setBounds(90, 331, 144, 22);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1_1_1.setBorder(null);
		lblNewLabel_1_1_1.setBounds(567, 331, 144, 22);
		contentPane.add(lblNewLabel_1_1_1);
		LblCerrarSesion.setBounds(962, 0, 55, 51);
		contentPane.add(LblCerrarSesion);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario Usuario = UsuarioLogeadoSingleton.getInstancia().getUsuario();
				if (FuncionalidadController.tieneFuncionalidadUsuario(Usuario, 6)) {
					FrmAltaPlantillaFormulario frm = new FrmAltaPlantillaFormulario(null);
				} else {
					JOptionPane.showMessageDialog(contentPane, "No tiene permiso para esta funcionalidad", "Atención",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		lblNewLabel_1_1.setBorder(null);
		lblNewLabel_1_1.setBounds(328, 331, 144, 22);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmListados frm = new FrmListados();
			}
		});
		lblNewLabel_1_1_1_1.setBorder(null);
		lblNewLabel_1_1_1_1.setBounds(803, 331, 144, 22);
		contentPane.add(lblNewLabel_1_1_1_1);

		JButton btn_Ingresar = new JButton("");
		btn_Ingresar.setBorder(null);
		btn_Ingresar.setBackground(new Color(0, 0, 0, 0));
		btn_Ingresar.setBounds(63, 415, 199, 31);
		contentPane.add(btn_Ingresar);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		lblNewLabel.setIcon(new ImageIcon(FrmMenuPrincipal.class.getResource("/Img/MenuFondo - copia.png")));
		lblNewLabel.setBounds(0, 0, 1027, 390);
		contentPane.add(lblNewLabel);
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
