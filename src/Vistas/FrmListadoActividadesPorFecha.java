package Vistas;


import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.Modelo.Formulario;
import com.toedter.calendar.JDateChooser;

import Controllers.FormularioController;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.text.ParseException;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FrmListadoActividadesPorFecha extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JFormattedTextField txtHoraDesde;
	private JFormattedTextField txtHoraHasta;
	private JDateChooser fechaDesde;
	private JDateChooser FechaHasta;
	private JLabel lblNewLabel_1;

	/**
	 * Create the frame.
	 */
	public FrmListadoActividadesPorFecha() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 909, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		setResizable(false);
		setTitle("Listado de Actuvidades de campo entre fechas");
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmListadoActividadesPorFecha.class.getResource("/Img/Lupa.png")));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Date desde = fechaDesde.getDate();
					Date hasta = FechaHasta.getDate();

					desde.setHours(Integer.parseInt(txtHoraDesde.getText().split(":")[0]));
					desde.setMinutes(Integer.parseInt(txtHoraDesde.getText().split(":")[1]));

					hasta.setHours(Integer.parseInt(txtHoraHasta.getText().split(":")[0]));
					hasta.setMinutes(Integer.parseInt(txtHoraHasta.getText().split(":")[1]));

					
					if(desde.compareTo(hasta) > 0) {
						JOptionPane.showMessageDialog(contentPane, "Verifique fecha desde debe ser menor a hasta", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						cargarListadoActividadesCampo(desde, hasta);
					}
					
		

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, "Verifique Datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		lblNewLabel_1 = new JLabel(" ?");
		lblNewLabel_1.setToolTipText("Esta pantalla Lista actividades de campo seg\u00FAn las fechas especificadas");
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel_1.setBackground(new Color(0, 0, 139));
		lblNewLabel_1.setBounds(803, 27, 29, 38);
		contentPane.add(lblNewLabel_1);
		lblNewLabel.setBounds(636, 46, 50, 50);
		contentPane.add(lblNewLabel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 161, 843, 402);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		FechaHasta = new JDateChooser();
		FechaHasta.setBounds(351, 61, 134, 20);
		contentPane.add(FechaHasta);

		fechaDesde = new JDateChooser();
		fechaDesde.setBounds(50, 61, 134, 20);
		contentPane.add(fechaDesde);

		try {
			MaskFormatter mascara = new MaskFormatter("##:##");
			txtHoraDesde = new JFormattedTextField(mascara);
			txtHoraDesde.setText("00:00");
			txtHoraDesde.setBounds(214, 61, 67, 20);
			contentPane.add(txtHoraDesde);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			MaskFormatter mascara;
			mascara = new MaskFormatter("##:##");
			txtHoraHasta = new JFormattedTextField(mascara);
			txtHoraHasta.setText("00:00");
			txtHoraHasta.setBounds(534, 61, 67, 20);
			contentPane.add(txtHoraHasta);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(FrmListadoActividadesPorFecha.class.getResource("/Img/pantallaacfecha.jpeg")));
		lblNewLabel_2.setBounds(0, 0, 910, 594);
		contentPane.add(lblNewLabel_2);


	}

	private void cargarListadoActividadesCampo(Date fechaDesde, Date fechaHasta) {

		DefaultTableModel modelo = new DefaultTableModel();

		final String[] columnNames = { "id Formulario", "Nombre", "Resumen", "Equipamiento", "Metodo Muestreo",
				"Tipo Muestreo", "Usuario", "Departamento", "Localidad", "Zona" };

		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] fila = new Object[columnNames.length];

		for (Formulario objForm : FormularioController.cargarListadoActividadesCampo(fechaDesde, fechaHasta)) {
			fila[0] = objForm.getIdForm();
			fila[1] = objForm.getFormularioDinamicos().get(0).getPlantillaCasilla().getPlantillaFormulario().getNombrePlantilla();
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
