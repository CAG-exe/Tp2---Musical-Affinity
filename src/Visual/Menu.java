package Visual;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import Visual.Menu;



public class Menu {
	private JFrame ventanaInicio;
	private ImageIcon icono;

	public static void main(String[] args) {
				try {
					Menu window = new Menu();
					window.ventanaInicio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	public Menu() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		inicializar();
	}

	
	private void inicializar() {
		
		ventanaInicio = new JFrame();
		ventanaInicio.setTitle("Afinidad-musical");
		ventanaInicio.setResizable(false);
		ventanaInicio.setBounds(100, 100, 800, 600);
		ventanaInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		icono = new ImageIcon(getClass().getResource("/img/icono.png"));
		favIcon(icono.getImage());
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(Menu.class.getResource("/img/imagennn.jpg")));
		fondo.setBounds(0, 0, 800, 600);
		ventanaInicio.getContentPane().add(fondo);
		
}
	public void favIcon(Image image) {
		ventanaInicio.setIconImage(image);
	}
}
