package Vistas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.Modelo.PlantillaFormulario;

import Controllers.FormularioController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmOpcionListadoPlantilla extends JFrame {

	
	
	public FrmOpcionListadoPlantilla(Integer idPlantilla,FrmListadoPlantillas obj) {
		getContentPane().setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 640,304);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		setResizable(false);
		
		JLabel lblEliminar = new JLabel("");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(FormularioController.eliminarFormulario(idPlantilla)) {
					JOptionPane.showMessageDialog(null, "Formulario Eliminado con Exito","Exito",JOptionPane.INFORMATION_MESSAGE);
					FrmListadoPlantillas a = new FrmListadoPlantillas();
				}else {
					JOptionPane.showMessageDialog(null, "No se ah podido eliminar formulario","Error",JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}
		});
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEliminar.setBounds(433, 11, 203, 188);
		getContentPane().add(lblEliminar);
		
		JLabel lblNueva = new JLabel("");
		lblNueva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmActividadCampo frm  =  new FrmActividadCampo(idPlantilla, null);
				dispose();
			}
		});
		lblNueva.setForeground(Color.WHITE);
		lblNueva.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNueva.setBounds(34, 15, 166, 180);
		getContentPane().add(lblNueva);
		
		JLabel lblModificar = new JLabel("");
		lblModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PlantillaFormulario plantilla = FormularioController.obtenerPlantillaFormulario(idPlantilla);				
				FrmAltaPlantillaFormulario frm = new FrmAltaPlantillaFormulario(plantilla);
				dispose();
			}
		});
		lblModificar.setForeground(Color.WHITE);
		lblModificar.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblModificar.setBounds(222, 19, 201, 180);
		getContentPane().add(lblModificar);
		
		JLabel lblNewLabel_2_1 = new JLabel("Modificar Formulario");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(222, 218, 201, 14);
		getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Eliminar Formulario");
		lblNewLabel_2_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_2.setBounds(446, 210, 190, 30);
		getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2 = new JLabel("Nueva Actividad");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(34, 218, 166, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FrmOpcionListadoPlantilla.class.getResource("/Img/PantallaActividad2.png")));
		lblNewLabel_1.setBounds(0, 0, 636, 278);
		getContentPane().add(lblNewLabel_1);
				
		
	}
}
