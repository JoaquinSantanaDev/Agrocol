package Vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.desktop.AppHiddenEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.Modelo.EnumEstado;
import com.Modelo.EnumTipoDoc;
import com.Modelo.Funcionalidad;
import com.Modelo.Rol;
import com.Modelo.Usuario;

import Controllers.RolController;
import Controllers.UsuarioController;
import Genericos.FuncionesGenericas;

import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;

public class FrmAltaUsuario {

	public JFrame frmAgrocol;
	private JTextField txtNombre;
	private JTextField txtMail;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtContraseña;
	private JComboBox listaRoles;
	private JTable tablaUsuarios;
	private JTextField txtDocumento;
	private JComboBox comboTipoDoc;
	private JTextField txtDireccion;
	private JPasswordField txtConfirmPass;
	private JLabel lblIdUsuario;
	private JLabel LblFondo;
	private JLabel lblRegistrar;
	private JLabel lblModificar;
	private JLabel lblEliminar;
	private JLabel lblLimpiar;

	/**
	 * Create the application.
	 */
	public FrmAltaUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAgrocol = new JFrame();
		
		frmAgrocol.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				frmAgrocol.hide();
			}
		});
		frmAgrocol.setResizable(false);
		frmAgrocol.setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		frmAgrocol.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		frmAgrocol.getContentPane().setForeground(Color.BLACK);
		frmAgrocol.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmAgrocol.setBackground(SystemColor.window);
		frmAgrocol.setForeground(new Color(255, 255, 255));
		frmAgrocol.getContentPane().setBackground(SystemColor.control);
		frmAgrocol.setBounds(100, 100, 906, 631);
		frmAgrocol.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAgrocol.setResizable(false);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(55, 65, 155, 18);
		txtNombre.setBorder(null);
		txtNombre.setForeground(Color.BLACK);
		txtNombre.setBackground(new Color(255, 255, 255));
		txtNombre.setColumns(10);

		txtMail = new JTextField();
		txtMail.setBounds(654, 65, 190, 18);
		txtMail.setBorder(null);
		txtMail.setForeground(Color.BLACK);
		txtMail.setColumns(10);
		txtMail.setBackground(Color.WHITE);

		txtApellido = new JTextField();
		txtApellido.setBounds(256, 65, 161, 18);
		txtApellido.setBorder(null);
		txtApellido.setForeground(Color.BLACK);
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(63, 231, 147, 18);
		txtUsuario.setBorder(null);
		txtUsuario.setForeground(Color.BLACK);
		txtUsuario.setColumns(10);
		txtUsuario.setBackground(Color.WHITE);

		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(256, 231, 165, 18);
		txtContraseña.setBorder(null);
		txtContraseña.setForeground(Color.BLACK);
		txtContraseña.setColumns(10);
		txtContraseña.setBackground(Color.WHITE);

		lblModificar = new JLabel("");
		lblModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario usuario = new Usuario();
				if (validarCampos(usuario)) {
					if (usuario.getIdUsuario() != null) {
						if (UsuarioController.altaUsuario(usuario)) {
							JOptionPane.showMessageDialog(frmAgrocol, "Usuario Agregado", "Exito",
									JOptionPane.INFORMATION_MESSAGE);
							ListadoUsuarios();
						} else {
							JOptionPane.showMessageDialog(frmAgrocol, "Usuario No Agregado", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						;
					}else {
						JOptionPane.showMessageDialog(frmAgrocol, "No es posible modificar usuario ya que no existe", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		lblRegistrar = new JLabel("");
		lblRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario usuario = new Usuario();
				if (validarCampos(usuario)) {
					if (usuario.getIdUsuario() == null) {
						if (UsuarioController.altaUsuario(usuario)) {
							JOptionPane.showMessageDialog(frmAgrocol, "Usuario Agregado", "Exito",
									JOptionPane.INFORMATION_MESSAGE);
							ListadoUsuarios();
						} else {
							JOptionPane.showMessageDialog(frmAgrocol, "Usuario No Agregado Verifique nombre de Usuario", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						;
					}else {
						JOptionPane.showMessageDialog(frmAgrocol, "Usuario ya Existe", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		lblLimpiar = new JLabel("");
		lblLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarCampos();
			}
		});
		frmAgrocol.getContentPane().setLayout(null);
		lblLimpiar.setBounds(798, 144, 70, 63);
		frmAgrocol.getContentPane().add(lblLimpiar);
		lblRegistrar.setBounds(637, 233, 70, 63);
		frmAgrocol.getContentPane().add(lblRegistrar);
		
		lblEliminar = new JLabel("");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (UsuarioController.eliminarUsuario(Integer.parseInt(lblIdUsuario.getText()))) {
					JOptionPane.showMessageDialog(frmAgrocol, "Usuario Eliminado", "Exito",
							JOptionPane.INFORMATION_MESSAGE);
					limpiarCampos();
				} else {
					JOptionPane.showMessageDialog(frmAgrocol, "Error al eliminar Usuario", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		lblEliminar.setBounds(798, 231, 70, 63);
		frmAgrocol.getContentPane().add(lblEliminar);
		lblModificar.setBounds(717, 233, 70, 63);
		frmAgrocol.getContentPane().add(lblModificar);

		listaRoles = new JComboBox();
		listaRoles.setBounds(442, 69, 177, 22);
		frmAgrocol.getContentPane().add(listaRoles);
		frmAgrocol.getContentPane().add(txtNombre);
		frmAgrocol.getContentPane().add(txtMail);
		frmAgrocol.getContentPane().add(txtUsuario);
		frmAgrocol.getContentPane().add(txtApellido);
		frmAgrocol.getContentPane().add(txtContraseña);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 307, 857, 249);
		frmAgrocol.getContentPane().add(scrollPane);

		tablaUsuarios = new JTable();
		tablaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer codigo = (Integer) tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 8);
				if (codigo > 0) {
					buscarUsuario(codigo);
					lblEliminar.show();
				} else {
					JOptionPane.showMessageDialog(frmAgrocol, "Usuario No Existe", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		scrollPane.setViewportView(tablaUsuarios);

		txtDocumento = new JTextField();
		txtDocumento.setForeground(Color.BLACK);
		txtDocumento.setColumns(10);
		txtDocumento.setBorder(null);
		txtDocumento.setBackground(Color.WHITE);
		txtDocumento.setBounds(256, 144, 165, 22);
		frmAgrocol.getContentPane().add(txtDocumento);

		comboTipoDoc = new JComboBox();
		comboTipoDoc.setBounds(53, 144, 177, 22);
		frmAgrocol.getContentPane().add(comboTipoDoc);

		txtDireccion = new JTextField();
		txtDireccion.setForeground(Color.BLACK);
		txtDireccion.setColumns(10);
		txtDireccion.setBorder(null);
		txtDireccion.setBackground(Color.WHITE);
		txtDireccion.setBounds(453, 144, 262, 18);
		frmAgrocol.getContentPane().add(txtDireccion);

		txtConfirmPass = new JPasswordField();
		txtConfirmPass.setForeground(Color.BLACK);
		txtConfirmPass.setColumns(10);
		txtConfirmPass.setBorder(null);
		txtConfirmPass.setBackground(Color.WHITE);
		txtConfirmPass.setBounds(453, 231, 155, 18);
		frmAgrocol.getContentPane().add(txtConfirmPass);

	

		lblIdUsuario = new JLabel("");
		lblIdUsuario.setName("Nombre");
		lblIdUsuario.setForeground(Color.BLACK);
		lblIdUsuario.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblIdUsuario.setBounds(30, 21, 102, 19);
		frmAgrocol.getContentPane().add(lblIdUsuario);

		LblFondo = new JLabel("");
		LblFondo.setHorizontalAlignment(SwingConstants.CENTER);
		LblFondo.setBackground(Color.WHITE);
		LblFondo.setBounds(0, 0, 900, 602);
		ImageIcon iconoEscalafondo = new ImageIcon(
				new ImageIcon(FrmAltaUsuario.class.getResource("/Img/UsuarioFondo.png")).getImage()
						.getScaledInstance(LblFondo.getWidth(), LblFondo.getHeight(), java.awt.Image.SCALE_DEFAULT));
		LblFondo.setIcon(iconoEscalafondo);
		LblFondo.setBorder(null);
		frmAgrocol.getContentPane().add(LblFondo);
		RolController.listarTodosRoles().forEach(rol -> {
			listaRoles.addItem(rol);
		});

		for (int x = 0; x < EnumTipoDoc.values().length; x++) {
			comboTipoDoc.addItem(EnumTipoDoc.values()[x]);
		}

		ListadoUsuarios();
		lblEliminar.hide();
	}

	protected void buscarUsuario(Integer codigo) {
		try {
			Usuario usuario = UsuarioController.ObtenerUsuariosPorId(codigo);
			txtApellido.setText(usuario.getApellido());
			txtNombre.setText(usuario.getNombre());
			txtMail.setText(usuario.getMail());
			txtUsuario.setText(usuario.getNombreUsuario());
			txtDocumento.setText(usuario.getDocumento());
			txtDireccion.setText(usuario.getDireccion());
			txtConfirmPass.setText(usuario.getClave());
			txtContraseña.setText(usuario.getClave());
			lblIdUsuario.setText(String.valueOf(usuario.getIdUsuario()));
			txtUsuario.disable();
			txtConfirmPass.disable();
			txtContraseña.disable();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAgrocol, "Error al Obtener Usuario", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCampos() {
		txtApellido.setText("");
		txtNombre.setText("");
		txtContraseña.setText("");
		txtMail.setText("");
		txtUsuario.setText("");
		txtDocumento.setText("");
		txtConfirmPass.setText("");
		txtDireccion.setText("");
		lblEliminar.hide();
		lblIdUsuario.setText("");
		txtUsuario.enable();
		txtConfirmPass.enable();
		txtContraseña.enable();
		ListadoUsuarios();
	}

	private void ListadoUsuarios() {

		DefaultTableModel modelo = new DefaultTableModel();

		final String[] columnNames = { "Documento ", "Tipo Documento ", "Nombre", "Apellido", "Mail", "Rol",
				"Nombre Usuario", "Estado Activo", "id Usuario" };

		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] fila = new Object[columnNames.length];
		List<Usuario> colUsuario = UsuarioController.ObtenerUsuarios();

		for (Usuario objPer : colUsuario) {
			fila[0] = objPer.getDocumento();
			fila[1] = objPer.getTipoDoc().getDescripcion();
			fila[2] = objPer.getNombre();
			fila[3] = objPer.getApellido();
			fila[4] = objPer.getMail();
			fila[5] = objPer.getRol().getDescripcion();
			fila[6] = objPer.getNombreUsuario();
			fila[7] = objPer.getEstado();
			fila[8] = objPer.getIdUsuario();
			modelo.addRow(fila);
		}

		tablaUsuarios.setModel(modelo);
	}

	private boolean validarCampos(Usuario usuario) {

		if (txtNombre.getText().length() > 50 || txtNombre.getText().length() < 1) {
			JOptionPane.showMessageDialog(frmAgrocol, "Nombre debe contener entre 1 y 50 caracteres", "Validación",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (txtApellido.getText().length() > 50 || txtNombre.getText().length() < 1) {
			JOptionPane.showMessageDialog(frmAgrocol, "Apellido debe contener entre 1 y 50 caracteres", "Validación",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (txtContraseña.getText().length() < 1) {
			JOptionPane.showMessageDialog(frmAgrocol, "Contraseña no debe ser vacía", "Validación",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (txtMail.getText().length() > 100 || txtMail.getText().length() < 1) {
			JOptionPane.showMessageDialog(frmAgrocol, "Mail debe contener entre 1 y 100 caracteres", "Validación",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (txtUsuario.getText().length() > 50 || txtUsuario.getText().length() < 1) {
			JOptionPane.showMessageDialog(frmAgrocol, "Usuario debe contener entre 1 y 50 caracteres", "Validación",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (txtDocumento.getText().length() > 30 || txtUsuario.getText().length() < 1) {
			JOptionPane.showMessageDialog(frmAgrocol, "Documento debe contener entre 1 y 30 caracteres", "Validación",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (txtDireccion.getText().length() > 100 || txtUsuario.getText().length() < 1) {
			JOptionPane.showMessageDialog(frmAgrocol, "Direccion debe contener entre 1 y 100 caracteres", "Validación",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (txtApellido.getText().length() > 100 || txtApellido.getText().length() < 1) {
			JOptionPane.showMessageDialog(frmAgrocol, "Apellido debe contener entre 1 y 100 caracteres", "Validación",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (!txtContraseña.getText().equals(txtConfirmPass.getText())) {
			JOptionPane.showMessageDialog(frmAgrocol, "Contraseñas son diferentes", "Validación",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (lblIdUsuario != null && lblIdUsuario.getText() != null && !lblIdUsuario.getText().equals("")) {
			usuario.setIdUsuario(Integer.parseInt(lblIdUsuario.getText()));
		}
		
		if (((EnumTipoDoc)comboTipoDoc.getSelectedItem()).equals(EnumTipoDoc.CI)) {
			if(!FuncionesGenericas.verificadorCi(txtDocumento.getText())) {
				JOptionPane.showMessageDialog(frmAgrocol, "Cedula Invalida", "Validación",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		
		if (!FuncionesGenericas.validarMail(txtMail.getText())) {
				JOptionPane.showMessageDialog(frmAgrocol, "Correo Electronico invalido", "Validación",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
		}

		usuario.setTipoDoc((EnumTipoDoc) comboTipoDoc.getSelectedItem());
		usuario.setApellido(txtApellido.getText());
		usuario.setNombre(txtNombre.getText());
		usuario.setDocumento(txtDocumento.getText());
		usuario.setClave(txtContraseña.getText());
		usuario.setMail(txtMail.getText());
		usuario.setNombreUsuario(txtUsuario.getText());
		usuario.setDireccion(txtDireccion.getText());
		usuario.setRol((Rol) listaRoles.getSelectedItem());
		usuario.setEstado(EnumEstado.ACTIVO);
		limpiarCampos();
		return true;
	}
}
