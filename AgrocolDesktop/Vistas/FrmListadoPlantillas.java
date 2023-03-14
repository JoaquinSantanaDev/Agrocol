package Vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.Modelo.PlantillaFormulario;
import com.Modelo.Usuario;

import Controllers.FormularioController;
import Controllers.FuncionalidadController;
import Genericos.UsuarioLogeadoSingleton;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

public class FrmListadoPlantillas extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public FrmListadoPlantillas() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 967, 652);
		setLocationRelativeTo(null);
		setTitle("Listado de Formularios");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel_1_1 = new JLabel(" ?");
		lblNewLabel_1_1.setToolTipText("Haga click en una fila de la tabla para desplegar mas opcione.");
		lblNewLabel_1_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1_1.setBackground(new Color(0, 0, 139));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel_1_1.setBounds(868, 11, 29, 38);
		contentPane.add(lblNewLabel_1_1);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		JLabel lblNewLabel_1 = new JLabel("Listado de Formularios");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_1.setBounds(327, 11, 337, 38);
		contentPane.add(lblNewLabel_1);
		setResizable(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 115, 931, 471);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario Usuario = UsuarioLogeadoSingleton.getInstancia().getUsuario();
				if (FuncionalidadController.tieneFuncionalidadUsuario(Usuario,8)) {
					Integer codigo = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					FrmOpcionListadoPlantilla frm = new FrmOpcionListadoPlantilla(codigo, null);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "No tiene permiso para esta funcionalidad", "Atención",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmListadoPlantillas.class.getResource("/Img/FondoGenerico.png")));
		lblNewLabel.setBounds(0, 0, 959, 620);
		contentPane.add(lblNewLabel);
		ListadoFormularios();
	}

	public void ListadoFormularios() {
		DefaultTableModel modelo = new DefaultTableModel();
		final String[] columnNames = { "id Funcionalidad ", "Nombre", "Descripción" };

		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] fila = new Object[columnNames.length];

		for (PlantillaFormulario plantilla : FormularioController.listadoPlantillasFormulario()) {

			fila[0] = plantilla.getIdPlantilla();
			fila[1] = plantilla.getNombrePlantilla();
			fila[2] = plantilla.getDescripcionPlantilla();

			modelo.addRow(fila);
		}
		table.setModel(modelo);
	}
}
