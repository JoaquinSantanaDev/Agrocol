package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.Modelo.Rol;

import Controllers.RolController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaRol {

	private JFrame AsignarRol;
	private JTextField txtRol;
	private JButton btnAltaRol;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaRol window = new AltaRol();
					window.AsignarRol.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AltaRol() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		AsignarRol = new JFrame();
		AsignarRol.setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		AsignarRol.setForeground(Color.WHITE);
		AsignarRol.getContentPane().setBackground(SystemColor.textHighlight);
		AsignarRol.setBounds(100, 100, 375, 173);
		AsignarRol.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("Rol:");
		lblNewLabel.setFocusTraversalKeysEnabled(false);
		lblNewLabel.setFocusable(false);
		lblNewLabel.setDisplayedMnemonic(KeyEvent.VK_ENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);

		txtRol = new JTextField();
		txtRol.setBorder(new LineBorder(Color.CYAN));
		txtRol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRol.setColumns(10);

		btnAltaRol = new JButton("Crear");
		btnAltaRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtRol.getText().length() > 30 && txtRol.getText().length() < 1) {
					JOptionPane.showMessageDialog(AsignarRol, "Nombre de Rol debe contener entre 1 y caracteres","Atención",JOptionPane.WARNING_MESSAGE);
				}else {
					Rol rol = new Rol();
					rol.setNombre(txtRol.getText());
					rol.setDescripcion(txtRol.getText());
					RolController.altaRol(rol);
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(AsignarRol.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtRol, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
								.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(137)
							.addComponent(btnAltaRol, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtRol, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAltaRol)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		AsignarRol.getContentPane().setLayout(groupLayout);
	}
}
