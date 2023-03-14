package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.UsuarioController;
import Genericos.UsuarioLogeadoSingleton;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Toolkit;

public class FrmLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel JFrame_Login;
	private JTextField txtNomUsuario;
	private JPasswordField txtClave;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/Img/Logo_SL.png")));
		setBounds(100, 100, 700, 450);
		setUndecorated(true);
		JFrame_Login = new JPanel();
		setLocationRelativeTo(null);
		JFrame_Login.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(JFrame_Login);
		JFrame_Login.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(25, 25, 112));
		separator.setBounds(420, 201, 206, 3);
		JFrame_Login.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(25, 25, 112));
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(420, 273, 206, 3);
		JFrame_Login.add(separator_1);

		JLabel lbl1_Usuario = new JLabel("Usuario");
		lbl1_Usuario.setForeground(new Color(25, 25, 112));
		lbl1_Usuario.setFont(new Font("SansSerif", Font.BOLD, 15));
		lbl1_Usuario.setBounds(420, 148, 74, 20);
		JFrame_Login.add(lbl1_Usuario);

		JLabel lbl2_Contraseña = new JLabel("Contraseña");
		lbl2_Contraseña.setForeground(new Color(25, 25, 112));
		lbl2_Contraseña.setFont(new Font("SansSerif", Font.BOLD, 15));
		lbl2_Contraseña.setBounds(420, 224, 95, 20);
		JFrame_Login.add(lbl2_Contraseña);

		txtNomUsuario = new JTextField();
		txtNomUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNomUsuario.setBorder(null);
		txtNomUsuario.setBounds(420, 181, 206, 23);
		JFrame_Login.add(txtNomUsuario);
		txtNomUsuario.setColumns(10);

		txtClave = new JPasswordField();
		txtClave.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtClave.setBorder(null);
		txtClave.setBounds(420, 256, 206, 20);
		JFrame_Login.add(txtClave);

		JLabel lbl_MensajeError = new JLabel("Usuario y/o contraseña incorrecta");
		lbl_MensajeError.setForeground(Color.RED);
		lbl_MensajeError.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbl_MensajeError.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_MensajeError.setBounds(420, 287, 206, 14);
		JFrame_Login.add(lbl_MensajeError);

		JButton btn_Ingresar = new JButton("");
		btn_Ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomusuario = txtNomUsuario.getText();
				String clave = txtClave.getText();
				if (UsuarioController.logIn(nomusuario, clave)) {
					FrmMenuPrincipal frame = new FrmMenuPrincipal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					hide();
				} else {
					lbl_MensajeError.show();
				}

			}
		});
		
		btn_Ingresar.setSelectedIcon(new ImageIcon(FrmLogin.class.getResource("/Img/Boton_Ingreso.png")));
		btn_Ingresar.setBorder(null);
		btn_Ingresar.setBackground(new Color(0, 0, 0, 0));
		btn_Ingresar.setIcon(new ImageIcon(FrmLogin.class.getResource("/Img/Boton_Ingreso.png")));
		btn_Ingresar.setBounds(427, 324, 199, 31);
		JFrame_Login.add(btn_Ingresar);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(FrmLogin.class.getResource("/Img/PantallaLoginFinal.jpg")));
		lblNewLabel.setBounds(0, 0, 700, 450);
		JFrame_Login.add(lblNewLabel);
		lbl_MensajeError.hide();

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
	

	
	
}
