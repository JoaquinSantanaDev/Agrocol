package Vistas;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmListados extends JFrame {

	
	
	public FrmListados() {
		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaRol.class.getResource("/Img/Logo_SL.png")));
		setResizable(false);
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmListadoFormDeExperto frm = new FrmListadoFormDeExperto();
			}
		});
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmListadoPlantillas frm = new FrmListadoPlantillas();
			}
		});
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmListadoActividadesDeCampo frm = new FrmListadoActividadesDeCampo();
			}
		});
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmListadoActividadesPorFecha frm = new FrmListadoActividadesPorFecha();
			}
		});
		lblNewLabel_2_1_1_1.setBounds(662, 31, 200, 181);
		getContentPane().add(lblNewLabel_2_1_1_1);
		lblNewLabel_2_1_1.setBounds(456, 31, 200, 181);
		getContentPane().add(lblNewLabel_2_1_1);
		lblNewLabel_2_1.setBounds(246, 31, 200, 181);
		getContentPane().add(lblNewLabel_2_1);
		lblNewLabel_2.setBounds(36, 31, 200, 181);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("LISTA FORMULARIOS");
		lblNewLabel_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_2.setBounds(267, 214, 163, 25);
		getContentPane().add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("AC. POR ESTACION MUESTREO");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(850, 214, 242, 25);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("AC \r\nENTRE FECHAS");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(701, 214, 137, 25);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ACTIVIDADES DE CAMPO");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(465, 219, 192, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("AC. DE USUARIO EXPERTO");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(36, 219, 192, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmListadoFormPorEstacionMuestreo frm = new  FrmListadoFormPorEstacionMuestreo();
			}
		});
		lblNewLabel.setIcon(new ImageIcon(FrmListados.class.getResource("/Img/Listados.jpeg")));
		lblNewLabel.setBounds(0, 0, 1113, 280);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1_1.setBounds(872, 31, 200, 181);
		getContentPane().add(lblNewLabel_2_1_1_1_1);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1108,321);
		setLocationRelativeTo(null);
		
		
	}
}
