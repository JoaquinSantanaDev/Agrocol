package Vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.Modelo.Casilla;
import com.Modelo.Departamento;
import com.Modelo.Equipamiento;
import com.Modelo.EstacionMuestreo;
import com.Modelo.Formulario;
import com.Modelo.FormularioDinamico;
import com.Modelo.Localidad;
import com.Modelo.MetodoMuestreo;
import com.Modelo.PlantillaCasilla;
import com.Modelo.PlantillaFormulario;
import com.Modelo.TipoMuestreo;
import com.Modelo.Zona;

import Controllers.CasillaController;
import Controllers.FormularioController;
import Genericos.UsuarioLogeadoSingleton;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class FrmActividadCampo extends JFrame {

	private JPanel contentPane;
	private JTextField txtGeoPunto;
	private List<PlantillaCasilla> listaCasillas;
	private List<JTextField> listaDeItems;
	private JComboBox<Localidad> comboLocalidad;
	private JComboBox<Departamento> comboDepto;
	private JComboBox<Zona> comboZonas;
	private JComboBox<EstacionMuestreo> comboEstacionMuestreo;
	private JComboBox<TipoMuestreo> comboTipoMuestreo;
	private JComboBox<MetodoMuestreo> comboMetodoMuestreo;
	private JComboBox<Equipamiento> comboEquipamiento;
	private JDateChooser txtFecha;
	private JFormattedTextField hora;
	private JLabel lblUsuarioLog;
	private JLabel lblIdFormulario;

	/**
	 * Create the frame.
	 */
	public FrmActividadCampo(Integer idPlantilla, Formulario form) {
		setTitle("Actividad de Campo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		listaDeItems = new LinkedList<JTextField>();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 908, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		txtGeoPunto = new JTextField();
		txtGeoPunto.setColumns(10);
		txtGeoPunto.setBounds(442, 56, 182, 20);
		contentPane.add(txtGeoPunto);

		JTextArea txtResumen = new JTextArea();
		txtResumen.setBounds(174, 193, 669, 43);
		contentPane.add(txtResumen);

		JPanel panel = new JPanel();
		panel.setBounds(35, 296, 824, 242);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
					PlantillaFormulario plantForm = FormularioController.obtenerPlantillaFormulario(idPlantilla);
					Formulario formulario = new Formulario();
					formulario.setEquipamiento((Equipamiento) comboEquipamiento.getSelectedItem());
					formulario.setEstacionMuestreo((EstacionMuestreo) comboEstacionMuestreo.getSelectedItem());
					formulario.setMetodoMuestreo((MetodoMuestreo) comboMetodoMuestreo.getSelectedItem());
					formulario.setTipoMuestreo((TipoMuestreo) comboTipoMuestreo.getSelectedItem());
					formulario.setGeopunto(txtGeoPunto.getText());
					Date fechaActual = txtFecha.getDate();
					fechaActual.setHours(Integer.parseInt(hora.getText().split(":")[0]));
					fechaActual.setMinutes(Integer.parseInt(hora.getText().split(":")[1]));
					formulario.setFecha(fechaActual);
					formulario.setResumen(txtResumen.getText());
					formulario.setZona((Zona) comboZonas.getSelectedItem());
					formulario.setActivo(true);
					formulario.setUsuario(UsuarioLogeadoSingleton.getInstancia().getUsuario());
					
					if(!lblIdFormulario.getText().isEmpty()) {
						formulario.setIdForm(Integer.parseInt(lblIdFormulario.getText()));
					}
					
					if (plantForm == null) {
						formulario.setNombre(form.getNombre());

					} else {
						formulario.setNombre(plantForm.getNombrePlantilla());
					}

					listaDeItems.forEach(item -> {
						FormularioDinamico formDinamico = new FormularioDinamico();
						formDinamico.setIdFormularioDinamico(null);

						try {
							// si es Entero
							formDinamico.setParametro(String.valueOf(Integer.parseInt(item.getText().trim())));
						} catch (Exception ex) {
							// Si es Texto
							formDinamico.setParametro(item.getText());
						}

						// si es Una Fecha
						if (item.getText().contains("/")) {
							String fecha = item.getText();
							formDinamico.setParametro(
									fecha.substring(0, 2) + fecha.substring(3, 5) + fecha.substring(6, 10));
						}

						// Si es Un numero con decimales
						if (item.getText().contains(".")) {
							String fecha = item.getText();
							formDinamico.setParametro(String.valueOf(Double.parseDouble(item.getText().trim())));
						}

						PlantillaCasilla plant = new PlantillaCasilla();
						plant.setIdPlantillaCasilla(Integer.parseInt(item.getName()));
						formDinamico.setPlantillaCasilla(plant);
						formulario.addFormularioDinamicos(formDinamico);
					});

					if (FormularioController.altaActividadCampo(formulario)) {
						JOptionPane.showMessageDialog(contentPane, "Actividad de Campo Guardad con exito!", "Exito",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(contentPane, "Actividad de Campo Guardad no registrada!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnGuardar.setBounds(375, 549, 89, 23);
		contentPane.add(btnGuardar);

		comboEquipamiento = new JComboBox();
		comboEquipamiento.setBounds(47, 160, 176, 22);
		contentPane.add(comboEquipamiento);

		comboLocalidad = new JComboBox();
		comboLocalidad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Localidad locSel = (Localidad) comboLocalidad.getSelectedItem();
				comboZonas.removeAllItems();
				if (locSel != null) {
					for (Zona zona : FormularioController.listadDeZonas(locSel.getIdLocalidad())) {
						comboZonas.addItem(zona);

					}
				}
			}
		});
		comboLocalidad.setBounds(47, 111, 176, 22);
		contentPane.add(comboLocalidad);

		comboDepto = new JComboBox();
		comboDepto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Departamento depSelec = (Departamento) comboDepto.getSelectedItem();
				comboLocalidad.removeAllItems();
				if (depSelec != null) {
					for (Localidad loc : FormularioController.listadDeLocalidades(depSelec.getIdDepartamento())) {
						comboLocalidad.addItem(loc);
					}
				}

			}
		});
		comboDepto.setBounds(642, 55, 182, 22);
		contentPane.add(comboDepto);

		comboZonas = new JComboBox();
		comboZonas.setBounds(232, 111, 183, 22);
		contentPane.add(comboZonas);

		comboEstacionMuestreo = new JComboBox();
		comboEstacionMuestreo.setBounds(442, 111, 182, 22);
		contentPane.add(comboEstacionMuestreo);

		comboMetodoMuestreo = new JComboBox();
		comboMetodoMuestreo.setBounds(642, 111, 182, 22);
		contentPane.add(comboMetodoMuestreo);

		comboTipoMuestreo = new JComboBox();
		comboTipoMuestreo.setBounds(233, 160, 182, 22);
		contentPane.add(comboTipoMuestreo);

		lblUsuarioLog = new JLabel("");
		lblUsuarioLog.setBounds(618, 11, 98, 26);
		contentPane.add(lblUsuarioLog);

		txtFecha = new JDateChooser();
		txtFecha.setBounds(47, 54, 176, 20);
		contentPane.add(txtFecha);

		try {
			MaskFormatter mascara = new MaskFormatter("##:##");
			hora = new JFormattedTextField(mascara);
			hora.setText("00:00");
			hora.setBounds(233, 56, 182, 20);
			contentPane.add(hora);
		} catch (ParseException e1) {
		}

		lblIdFormulario = new JLabel("");
		lblIdFormulario.setBounds(72, 36, 56, 14);
		contentPane.add(lblIdFormulario);
		
		JLabel Fecha_1 = new JLabel("");
		Fecha_1.setIcon(new ImageIcon(FrmActividadCampo.class.getResource("/Img/PantallaActividadDeCampo.png")));
		Fecha_1.setBounds(0, 0, 892, 591);
		contentPane.add(Fecha_1);

		int x = 10;

		int ylbl = 11;
		int yTex = 31;
		cargaInicial();

		if (idPlantilla != null) {
			ListaCasillaplantilla(idPlantilla);
		} else {
			listaCasillas = new ArrayList<PlantillaCasilla>();
			for (FormularioDinamico formDinamic : form.getFormularioDinamicos()) {
				formDinamic.getPlantillaCasilla().setParametro(formDinamic.getParametro());
				listaCasillas.add(formDinamic.getPlantillaCasilla());
				comboDepto.setSelectedItem(form.getZona().getLocalidad().getDepartamento());
				comboLocalidad.setSelectedItem(form.getZona().getLocalidad());
				comboZonas.setSelectedItem(form.getZona());
				comboMetodoMuestreo.setSelectedItem(form.getMetodoMuestreo());
				comboTipoMuestreo.setSelectedItem(form.getTipoMuestreo());
				comboEstacionMuestreo.setSelectedItem(form.getEstacionMuestreo());
				comboEquipamiento.setSelectedItem(form.getEquipamiento());
				txtResumen.setText(form.getResumen());
				txtGeoPunto.setText(form.getGeopunto());
				txtFecha.setDate(form.getFecha());
				DateFormat hourFormat = new SimpleDateFormat("HH:mm");
				hora.setText(hourFormat.format(form.getFecha()));
				lblIdFormulario.setText(String.valueOf(form.getIdForm()));
				lblUsuarioLog.setText(form.getUsuario().getNombreUsuario());
			}
		}

		for (PlantillaCasilla plantCasill : listaCasillas) {
			JLabel lblNombreCampo = new JLabel(plantCasill.getCasilla().getNombre());
			lblNombreCampo.setBounds(x, ylbl, 60, 14);
			panel.add(lblNombreCampo);

			JFormattedTextField txtCampo = new JFormattedTextField();

			try {
				MaskFormatter mascara;
				switch (plantCasill.getCasilla().getTipoInput().getIdTipoInput()) {

				case 1:
					mascara = new MaskFormatter("");
					txtCampo = new JFormattedTextField();
					txtCampo.setText("");
					if (form != null) {
						txtCampo.setText(plantCasill.getParametro());
					}
					break;
				case 2:
					mascara = new MaskFormatter("##/##/####");
					txtCampo = new JFormattedTextField(mascara);
					txtCampo.setText("21/11/2020");
					if (form != null) {
						txtCampo.setText(plantCasill.getParametro().substring(0, 4) + "/"
								+ plantCasill.getParametro().substring(4, 6) + "/"
								+ plantCasill.getParametro().substring(6, 8));
					}
					break;
				case 3:
					mascara = new MaskFormatter("#######.##");
					txtCampo = new JFormattedTextField(mascara);
					txtCampo.setText("0000000.00");
					break;
				case 4:
					mascara = new MaskFormatter("#######");
					txtCampo = new JFormattedTextField(mascara);
					txtCampo.setText("0000000");

					if (form != null) {
						txtCampo.setText(plantCasill.getParametro());
					}

					break;
				default:
					mascara = new MaskFormatter("");
					txtCampo = new JFormattedTextField();

					if (form != null) {
						txtCampo.setText(plantCasill.getParametro());
					}

					break;
				}

				txtCampo.setBounds(x, yTex, 96, 20);
				txtCampo.setColumns(10);
				txtCampo.setName(String.valueOf(plantCasill.getIdPlantillaCasilla()));

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			panel.add(txtCampo);

			if (x + 106 > 650) {
				x = 10;
				ylbl += 50;
				yTex += 50;
			} else {
				x += 106;
			}

			listaDeItems.add(txtCampo);
		}

	}

	private void ListaCasillaplantilla(Integer xIdPlantillaCasilla) {
		listaCasillas = CasillaController.ObtenerCasillaDePlantilla(xIdPlantillaCasilla);
	}

	private void cargaInicial() {
		for (Departamento dep : FormularioController.listadoDepartamentos()) {
			comboDepto.addItem(dep);
		}

		for (MetodoMuestreo met : FormularioController.listadoMetodoMuestreo()) {
			comboMetodoMuestreo.addItem(met);
		}

		for (TipoMuestreo tipo : FormularioController.listadoTipoMuestreo()) {
			comboTipoMuestreo.addItem(tipo);
		}

		for (EstacionMuestreo est : FormularioController.listadoEstacionMuestreo()) {
			comboEstacionMuestreo.addItem(est);
		}

		for (Equipamiento equp : FormularioController.listadoEquipamientos()) {
			comboEquipamiento.addItem(equp);
		}

		lblUsuarioLog.setText(UsuarioLogeadoSingleton.getInstancia().getUsuario().getNombreUsuario());

	}

	private boolean validarCampos() {

		if (txtFecha.getDate() == null || txtFecha.getDate().equals("")) {
			JOptionPane.showMessageDialog(contentPane, "Debe Seleccionar una fecha valida!", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		try {
			if (Integer.parseInt(hora.getText().split(":")[0]) < 0
					|| Integer.parseInt(hora.getText().split(":")[0]) > 24) {
				JOptionPane.showMessageDialog(contentPane, "Debe Seleccionar una hora Valida !", "Atención",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
			if (Integer.parseInt(hora.getText().split(":")[1]) > 60
					|| Integer.parseInt(hora.getText().split(":")[1]) < 0) {
				JOptionPane.showMessageDialog(contentPane, "Debe Seleccionar una hora Valida !", "Atención",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(contentPane, "Debe Seleccionar una hora Valida !", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (txtGeoPunto.getText() == null || txtGeoPunto.getText().equals("")) {
			JOptionPane.showMessageDialog(contentPane, "Debe ingresar un la GeoLocalización!", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return false;

		}

		if (comboZonas.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(contentPane, "Debe Seleccionar Zona!", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return false;

		}

		if (comboMetodoMuestreo.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(contentPane, "Debe Seleccionar Metodo de Muestreo!", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (comboEquipamiento.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(contentPane, "Debe Seleccionar Equipamiento!", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return false;

		}
		if (comboEstacionMuestreo.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(contentPane, "Debe Seleccionar Estación de Muestreo!", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return false;

		}
		if (comboTipoMuestreo.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(contentPane, "Debe Seleccionar Tipo de Muestreo!", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return false;

		}

		return true;

	}
}
