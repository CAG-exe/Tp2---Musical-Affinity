package main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

import Controlador.Controlador;
import Modelo.AfinidadMusical;
import Visual.UISwing;

public class Main {

	public static void main(String[] args) {
		try {
		    UIManager.setLookAndFeel( new FlatCarbonIJTheme() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		UIManager.put("Button.arc", 0);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AfinidadMusical afinidadMusical = new AfinidadMusical();
					Controlador controlador = new Controlador();
					UISwing frame = new UISwing(controlador, afinidadMusical);
					controlador.agregarModeloYVisual(frame, afinidadMusical);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
