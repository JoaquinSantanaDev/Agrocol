package Vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.Modelo.Casilla;
import com.Modelo.PlantillaCasilla;
import com.Modelo.PlantillaFormulario;

import Controllers.CasillaController;
import Controllers.FormularioController;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class FrmAltaPlantillaFormulario extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNombre;
	private JComboBox<Casilla> comboBox;
	private List<Casilla> colCasillas = new ArrayList<Casilla>();
	private JTextField txtDescripcion;
	private JLabel lblIdForm;
	private JLabel lblNewLabel_1;

	/**
	 * Create the frame.
	 */
	public FrmAltaPlantillaFormulario(PlantillaFormulario form) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1043, 645);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 251, 985, 317);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer codigo = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				if (codigo != null) {
					for (Casilla obj : colCasillas) {
						if (obj.getIdCasilla().equals(codigo)) {
							comboBox.addItem(obj);
							colCasillas.remove(obj);
							cargarTabla();
							break;
						}
					}
				}
			}
		});
		scrollPane.setViewportView(table);
		txtNombre = new JTextField();
		txtNombre.setBounds(151, 108, 157, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre Formulario");
		lblNewLabel.setBounds(151, 83, 157, 14);
		contentPane.add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.setBounds(581, 107, 211, 22);
		contentPane.add(comboBox);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Casilla casilla = (Casilla) comboBox.getSelectedItem();
				if (casilla != null) {
					colCasillas.add(casilla);
					comboBox.removeItem(comboBox.getSelectedItem());
					cargarTabla();
				}
			}
		});
		btnAgregar.setBounds(819, 107, 89, 23);
		contentPane.add(btnAgregar);

		JButton BtnGuardar = new JButton("Guardar Formulario");
		BtnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNombre.getText().length() > 0 && txtNombre.getText().length() < 100) {
					if (txtDescripcion.getText().length() < 100) {
						PlantillaFormulario plantilla = new PlantillaFormulario();
						try {
							plantilla.setIdPlantilla(Integer.parseInt(lblIdForm.getText()));
						} catch (Exception ex) {
							// TODO: handle exception
						}

						plantilla.setDescripcionPlantilla(txtDescripcion.getText());
						plantilla.setNombrePlantilla(txtNombre.getText());
						plantilla.setActivo(true);
						for (Casilla cas : colCasillas) {
							PlantillaCasilla plantillaCasilla = new PlantillaCasilla();
							plantillaCasilla.setCasilla(cas);
							plantillaCasilla.setPlantillaFormulario(plantilla);
							plantilla.addPlantillaCasilla(plantillaCasilla);
						}
						if (FormularioController.altaPlantillaFormulario(plantilla)) {
							JOptionPane.showMessageDialog(contentPane, "Formulario Creado", "Exito",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
							FrmAltaPlantillaFormulario frm = new FrmAltaPlantillaFormulario(null);
						} else {
							JOptionPane.showMessageDialog(contentPane, "No se ah podido generar el Formulario", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(contentPane,"Descripcion debe contener hasta 100 caracteres", "Error",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Ingrese nombre debe contener entre 0 y 255 Caracteres",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		BtnGuardar.setBounds(375, 172, 188, 23);
		contentPane.add(BtnGuardar);

		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(369, 83, 157, 14);
		contentPane.add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(370, 108, 171, 20);
		contentPane.add(txtDescripcion);

		lblIdForm = new JLabel("");
		lblIdForm.setBounds(20, 11, 48, 14);
		contentPane.add(lblIdForm);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FrmAltaPlantillaFormulario.class.getResource("/Img/FondoGenerico3.png")));
		lblNewLabel_1.setBounds(0, 0, 1027, 615);
		contentPane.add(lblNewLabel_1);
		cargarCasillas();

		if (form != null) {
			cargarCombosModificar(form);
		}

	}

	private void cargarCombosModificar(PlantillaFormulario form) {
		List<PlantillaCasilla> colformDinamic = form.getPlantillaCasillas();
		txtNombre.setText(form.getNombrePlantilla());
		txtDescripcion.setText(form.getDescripcionPlantilla());
		lblIdForm.setText(String.valueOf(form.getIdPlantilla()));

		for (PlantillaCasilla formDinamico : colformDinamic) {
			Casilla cas = formDinamico.getCasilla();
			colCasillas.add(cas);
			comboBox.removeItem(cas);
		}
		cargarTabla();
	}

	public void cargarCasillas() {
		for (Casilla obj : CasillaController.listarTodas()) {
			comboBox.addItem(obj);
		}
	}

	public void cargarTabla() {
		DefaultTableModel modelo = new DefaultTableModel();

		final String[] columnNames = { "id Casilla ", "Descripción", "Nombre", "Tipo de Input", "Unidad Medida" };

		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] fila = new Object[columnNames.length];

		for (Casilla objCasilla : colCasillas) {
			fila[0] = objCasilla.getIdCasilla();
			fila[1] = objCasilla.getDescripcion();
			fila[2] = objCasilla.getNombre();
			fila[3] = objCasilla.getUnidadesMedida().getNombre();
			fila[4] = objCasilla.getTipoInput().getNombre();
			modelo.addRow(fila);
		}
		table.setModel(modelo);
	}
}
