package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.Modelo.EstacionMuestreo;
import com.Modelo.Formulario;
import com.Modelo.Funcionalidad;
import com.Modelo.Usuario;

import Controllers.FormularioController;
import Controllers.UsuarioController;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FrmListadoFormPorEstacionMuestreo extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public FrmListadoFormPorEstacionMuestreo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 879, 672);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		
		JLabel lblNewLabel_1_1 = new JLabel(" ?");
		lblNewLabel_1_1.setToolTipText("Esta pantalla muestra todas las actividades de campo seg\u00FAn la estacion de muestreo selecionada");
		lblNewLabel_1_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel_1_1.setBackground(new Color(0, 0, 139));
		lblNewLabel_1_1.setBounds(820, 23, 29, 38);
		contentPane.add(lblNewLabel_1_1);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 188, 843, 412);
		contentPane.add(scrollPane);
		setResizable(false);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JComboBox comboEstacion = new JComboBox();
		comboEstacion.setBounds(196, 95, 270, 22);
		contentPane.add(comboEstacion);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstacionMuestreo estacion = (EstacionMuestreo) comboEstacion.getSelectedItem();
				cargarListadoActividadesCampo(estacion.getIdEstacionMuestreo());
			}
		});
		btnBuscar.setBounds(494, 95, 89, 23);
		contentPane.add(btnBuscar);
		
		JLabel lblNewLabel = new JLabel("Listado Actividades de Campo segun estaci\u00F3n de muestreo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(250, 23, 539, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(FrmListadoFormPorEstacionMuestreo.class.getResource("/Img/FondoGenerico.png")));
		lblNewLabel_1.setBounds(0, 0, 863, 633);
		contentPane.add(lblNewLabel_1);
		
		for (EstacionMuestreo est : FormularioController.listadoEstacionMuestreo()) {
			comboEstacion.addItem(est);
		}
		
	}
	
	private void cargarListadoActividadesCampo(Integer idEstacion) {
		
		DefaultTableModel modelo = new DefaultTableModel();

		final String[] columnNames = { "id Formulario","Nombre", "Resumen","Equipamiento","Metodo Muestreo","Tipo Muestreo","Usuario","Departamento","Localidad","Zona"};

		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] fila = new Object[columnNames.length];

		for (Formulario objForm : FormularioController.listadoActividadeCapoSegunEstacionDeMuestreo(idEstacion)) {
			fila[0] = objForm.getIdForm();
			fila[1] = objForm.getNombre();
			fila[2] = objForm.getResumen();
			fila[3] = objForm.getEquipamiento().getNombre();
			fila[4] = objForm.getMetodoMuestreo().getNombre();
			fila[5] = objForm.getTipoMuestreo().getNombre();
			fila[6] = objForm.getUsuario().getNombre();
			fila[7] = objForm.getZona().getLocalidad().getDepartamento().getNombre();
			fila[8] = objForm.getZona().getLocalidad().getNombre();
			fila[9] = objForm.getZona().getLocalidad().getDepartamento().getNombre();
			modelo.addRow(fila);
		}
		
		table.setModel(modelo);
	}
}
