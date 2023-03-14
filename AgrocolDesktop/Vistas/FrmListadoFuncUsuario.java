package Vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.Modelo.Funcionalidad;
import com.Modelo.Usuario;

import Controllers.FuncionalidadController;
import Controllers.UsuarioController;
import Genericos.UsuarioLogeadoSingleton;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmListadoFuncUsuario extends JFrame {

	public JPanel frmFuncionalidades;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmListadoFuncUsuario frame = new FrmListadoFuncUsuario();
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
	public FrmListadoFuncUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 553);
		frmFuncionalidades = new JPanel();
		frmFuncionalidades.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmFuncionalidades);
		frmFuncionalidades.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 206, 645, 297);
		frmFuncionalidades.add(scrollPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario user = UsuarioLogeadoSingleton.getInstancia().getUsuario();
				Integer codigo = (Integer) table.getValueAt(table.getSelectedRow(),0);
				
				if(FuncionalidadController.tieneFuncionalidadUsuario(user, codigo)) {
					JOptionPane.showMessageDialog(frmFuncionalidades,"Usuario Cuenta con funcionalidad","Error",JOptionPane.INFORMATION_MESSAGE);	
				}else {
					JOptionPane.showMessageDialog(frmFuncionalidades,"Usuario No cuenta con funcionalidad","Error",JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
		
		
		
		
		scrollPane.setViewportView(table);
		
		JLabel lblUsuario = new JLabel("USUARIO : ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblUsuario.setBounds(164, 66, 133, 41);
		frmFuncionalidades.add(lblUsuario);
		
		JLabel lblUsuarioLogueado = new JLabel("");
		lblUsuarioLogueado.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblUsuarioLogueado.setBounds(284, 66, 133, 41);
		frmFuncionalidades.add(lblUsuarioLogueado);
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
		
		table.setModel(modelo);
	}
	
	
	
	
	
	
	
	
	
	
	
}
