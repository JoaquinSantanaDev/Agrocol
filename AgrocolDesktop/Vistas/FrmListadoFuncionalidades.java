package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.Modelo.Funcionalidad;
import com.Modelo.Usuario;

import Controllers.FuncionalidadController;
import Controllers.UsuarioController;
import Genericos.UsuarioLogeadoSingleton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JComboBox;

public class FrmListadoFuncionalidades {

	public JFrame frmFuncionalidades;
	private JTable tabla;

	/**
	 * Create the application.
	 */
	public FrmListadoFuncionalidades() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFuncionalidades = new JFrame();
		frmFuncionalidades.setBackground(new Color(0, 153, 204));
		frmFuncionalidades.getContentPane().setBackground(Color.WHITE);
		frmFuncionalidades.setTitle("Funcionalidades");
		frmFuncionalidades.setResizable(false);
		frmFuncionalidades.setBounds(100, 100, 579, 371);
		frmFuncionalidades.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFuncionalidades.setLocationRelativeTo(null);
		frmFuncionalidades.getContentPane().setLayout(null);
		frmFuncionalidades.setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 87, 573, 185);
		frmFuncionalidades.getContentPane().add(scrollPane);
		
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(FuncionalidadController.tieneFuncionalidadUsuario(UsuarioLogeadoSingleton.getInstancia().getUsuario(), (Integer) tabla.getValueAt(tabla.getSelectedRow(),0))){
					JOptionPane.showMessageDialog(frmFuncionalidades, "Usuario Logeado cuenta con Funcionalidad","Aviso",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(frmFuncionalidades, "Usuario Logeado NO cuenta con Funcionalidad","Aviso",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		scrollPane.setColumnHeaderView(tabla);
		
		JLabel lblNewLabel = new JLabel("Lista de Funcionalidades");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(191, 11, 217, 31);
		frmFuncionalidades.getContentPane().add(lblNewLabel);
		ListadoFuncionalidades();		
	}

	private void ListadoFuncionalidades() {
		DefaultTableModel modelo = new DefaultTableModel();

		final String[] columnNames = { "id Funcionalidad ", "Nombre", "Descripción"};

		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] fila = new Object[columnNames.length];

		for (Funcionalidad objfun : FuncionalidadController.listarTodas()) {
			fila[0] = objfun.getIdFuncionalidad();
			fila[1] = objfun.getDescripcion();
			fila[2] = objfun.getNombre();
			modelo.addRow(fila);
		}
		tabla.setModel(modelo);
	}
}
