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

import com.Modelo.Formulario;
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

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Color;

public class FrmListadoActividadesDeCampo extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public FrmListadoActividadesDeCampo() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1045, 645);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		JLabel lblModificar = new JLabel("");
		lblModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario Usuario = UsuarioLogeadoSingleton.getInstancia().getUsuario();
				if (FuncionalidadController.tieneFuncionalidadUsuario(Usuario, 1)) {
					Integer idForm = -1;
					try {
						idForm = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					} catch (Exception ex) {
						// TODO: handle exception
					}
					if (idForm > -1) {
						Formulario formulario = FormularioController.ObtenerPorId(idForm);
						FrmActividadCampo frm = new FrmActividadCampo(null, formulario);
					} else {
						JOptionPane.showMessageDialog(contentPane, "Elija una actividad", "Atención",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "No tiene permiso para esta funcionalidad", "Atención",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		JLabel lblNewLabel_1_1 = new JLabel(" ?");
		lblNewLabel_1_1.setToolTipText(
				"Esta pantalla muestra todas las actividades de campo, seleccionando una de la tabla y precionando uno de los botones podra eliminar o modificar toda la actividad");
		lblNewLabel_1_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel_1_1.setBackground(new Color(0, 0, 139));
		lblNewLabel_1_1.setBounds(893, 11, 29, 38);
		contentPane.add(lblNewLabel_1_1);
		lblModificar.setIcon(new ImageIcon(FrmListadoActividadesDeCampo.class.getResource("/Img/Modificar2.png")));
		lblModificar.setBounds(594, 69, 63, 50);
		contentPane.add(lblModificar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 130, 977, 454);
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("LISTADO DE ACTIVIDADES DE CAMPO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(332, 11, 445, 30);
		contentPane.add(lblNewLabel);

		table = new JTable();
		scrollPane.setColumnHeaderView(table);

		JLabel lblEliminar = new JLabel("");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer idForm = -1;
				try {
					idForm = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				} catch (Exception ex) {
					// TODO: handle exception
				}
				if (idForm > -1) {
					if (FormularioController.eliminarActividadCampo(idForm)) {
						JOptionPane.showMessageDialog(contentPane, "Actividad Eliminada", "Exito",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(contentPane, "Actividad No Eliminada", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Elija una actividad", "Atención",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		lblEliminar.setIcon(new ImageIcon(FrmListadoActividadesDeCampo.class.getResource("/Img/Negativo.png")));
		lblEliminar.setBounds(472, 69, 63, 50);
		contentPane.add(lblEliminar);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(FrmListadoActividadesDeCampo.class.getResource("/Img/FondoGenerico.png")));
		lblFondo.setBounds(0, 0, 1029, 613);
		contentPane.add(lblFondo);
		ListadoFormularios();
	}

	public void ListadoFormularios() {
		DefaultTableModel modelo = new DefaultTableModel();
		final String[] columnNames = { "id Formulario", "Nombre", "Resumen", "Equipamiento", "Metodo Muestreo",
				"Tipo Muestreo", "Usuario", "Departamento", "Localidad", "Zona" };

		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] fila = new Object[columnNames.length];
		for (Formulario objForm : FormularioController.ListadoActividadesCampo()) {
			fila[0] = objForm.getIdForm();
			fila[1] = objForm.getNombre();
			fila[2] = objForm.getResumen();
			fila[3] = objForm.getEquipamiento().getNombre();
			fila[4] = objForm.getMetodoMuestreo().getNombre();
			fila[5] = objForm.getTipoMuestreo().getNombre();
			fila[6] = objForm.getUsuario().getNombreUsuario();
			fila[7] = objForm.getZona().getLocalidad().getDepartamento().getNombre();
			fila[8] = objForm.getZona().getLocalidad().getNombre();
			fila[9] = objForm.getZona().getLocalidad().getDepartamento().getNombre();
			modelo.addRow(fila);
		}
		table.setModel(modelo);
	}
}
