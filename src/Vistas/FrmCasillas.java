package Vistas;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.Modelo.Casilla;
import com.Modelo.TipoInput;
import com.Modelo.UnidadesMedida;

import Controllers.CasillaController;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class FrmCasillas extends JFrame {

	private JPanel contentPane;
	private JTextArea txtDescripcion;
	private JTable table;
	private JComboBox<TipoInput> comboTipoDato;
	private JComboBox<UnidadesMedida> comboUnidadMedida;
	private JTextField txtNombre;
	private JLabel lblIdCasilla;
	private JLabel lblAgregar;
	private JLabel lblEliminar;

	/**
	 * Create the frame.
	 */
	public FrmCasillas() {
		setTitle("Mantenimiento de Casillas");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 909, 640);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		lblEliminar = new JLabel("");
		lblEliminar.setIcon(new ImageIcon(FrmCasillas.class.getResource("/Img/Negativo.png")));
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (CasillaController.bajaCasilla(Integer.parseInt(lblIdCasilla.getText()))) {
					JOptionPane.showMessageDialog(contentPane, "Casilla eliminada", "Exito",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(contentPane, "Casilla no puede ser eliminada", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				limpiarCampos();
			}
		});
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setColumns(250);
		txtDescripcion.setBounds(60, 147, 772, 86);
		
		contentPane.add(txtDescripcion);
		
		lblEliminar.setBounds(808, 86, 48, 50);
		contentPane.add(lblEliminar);
		
		lblAgregar = new JLabel("");
		lblAgregar.setIcon(new ImageIcon(FrmCasillas.class.getResource("/Img/positivo.jpg")));
		lblAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!txtNombre.getText().equals("") && txtNombre.getText().length() < 50) {
					if (txtDescripcion.getText().length() < 50) {
						if (comboTipoDato.getSelectedItem() != null) {
							if (comboUnidadMedida.getSelectedItem() != null) {
								Casilla casilla = new Casilla();
								casilla.setDescripcion(txtDescripcion.getText());
								casilla.setNombre(txtNombre.getText());
								casilla.setTipoInput((TipoInput) comboTipoDato.getSelectedItem());
								casilla.setUnidadesMedida((UnidadesMedida) comboUnidadMedida.getSelectedItem());
								Integer IdCasilla = null;
								try {
									IdCasilla = Integer.parseInt(lblIdCasilla.getText());
								} catch (Exception a) {
								}

								if (IdCasilla != null) {
									casilla.setIdCasilla(IdCasilla);
								}

								if (CasillaController.altaCasilla(casilla)) {
									JOptionPane.showMessageDialog(contentPane, "Casilla Agregada", "Exito",
											JOptionPane.INFORMATION_MESSAGE);
									cargarTablaCasilla();
								} else {
									JOptionPane.showMessageDialog(contentPane, "Casilla no agregada Verifique", "Error",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(contentPane,
										"El campo Unidad Medida debe ser seleccionado", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(contentPane, "El campo Tipo Dato debe ser seleccionado",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(contentPane,
								"El campo Descripción debe contener hasta 50 caracteres", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "El campo nombre debe contener entre 50 y 0 caracteres",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				limpiarCampos();
			}
		});
		lblAgregar.setBounds(808, 25, 54, 50);
		contentPane.add(lblAgregar);

		txtNombre = new JTextField();
		txtNombre.setBorder(null);
		txtNombre.setBounds(60, 68, 242, 23);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		
		comboTipoDato = new JComboBox<TipoInput>();
		comboTipoDato.setBounds(339, 68, 164, 22);
		contentPane.add(comboTipoDato);

		comboUnidadMedida = new JComboBox<UnidadesMedida>();
		comboUnidadMedida.setBounds(566, 68, 172, 22);
		contentPane.add(comboUnidadMedida);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 285, 833, 305);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblEliminar.show();
				lblAgregar.setIcon(new ImageIcon(FrmCasillas.class.getResource("/Img/Modificar2.png")));
				lblAgregar.setText("Modificar");
				table.getSelectedColumn();
				Integer codigo = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				Casilla casilla = CasillaController.obtenerCasilla(codigo);
				lblIdCasilla.setText(String.valueOf(codigo));
				txtDescripcion.setText(casilla.getDescripcion());
				txtNombre.setText(casilla.getNombre());
				comboTipoDato.setSelectedItem(casilla.getTipoInput());
				comboUnidadMedida.setSelectedItem(casilla.getUnidadesMedida());
			}
		});
		scrollPane.setViewportView(table);

		lblIdCasilla = new JLabel("");
		lblIdCasilla.setBounds(160, 244, 70, 30);
		contentPane.add(lblIdCasilla);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FrmCasillas.class.getResource("/Img/PantallaCasilla.png")));
		lblNewLabel_2.setBounds(0, 0, 901, 614);
		contentPane.add(lblNewLabel_2);
		inicialidador();

	}

	private void inicialidador() {
		lblEliminar.hide();

		for (UnidadesMedida unidad : CasillaController.listarUnidadesMedidas()) {
			comboUnidadMedida.addItem(unidad);
		}

		for (TipoInput input : CasillaController.listarTiposInput()) {
			comboTipoDato.addItem(input);
		}

		cargarTablaCasilla();
	}

	private void cargarTablaCasilla() {
		DefaultTableModel modelo = new DefaultTableModel();

		final String[] columnNames = { "id Casilla ", "Descripción", "Nombre", "Tipo de Input", "Unidad Medida" };

		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] fila = new Object[columnNames.length];

		for (Casilla objCasilla : CasillaController.listarTodas()) {
			fila[0] = objCasilla.getIdCasilla();
			fila[1] = objCasilla.getDescripcion();
			fila[2] = objCasilla.getNombre();
			fila[3] = objCasilla.getUnidadesMedida().getNombre();
			fila[4] = objCasilla.getTipoInput().getNombre();
			modelo.addRow(fila);
		}
		table.setModel(modelo);
	}

	private void limpiarCampos() {
		cargarTablaCasilla();
		txtDescripcion.setText("");
		txtNombre.setText("");
		lblEliminar.hide();
		lblAgregar.setIcon(new ImageIcon(FrmCasillas.class.getResource("/Img/positivo.jpg")));
		lblIdCasilla.setText("");
	}
}
