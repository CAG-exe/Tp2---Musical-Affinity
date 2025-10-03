package Visual;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelUsuarios extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelUsuarios() {
		setBackground(new Color(220, 225, 195));
		
		
		//JButton btnCrear = new JButton("Crear Usuario");
		JButton btnBuscar = new JButton("Buscar Usuario");
		JButton btnEditar = new JButton("Editar Usuario");
		JButton btnEliminar = new JButton("Eliminar Usuario");

		// Le da un estilo a los botones
		Font botonFont = new Font("Tahoma", Font.PLAIN, 15);
		//btnCrear.setFont(botonFont);
		btnBuscar.setFont(botonFont);
		btnEditar.setFont(botonFont);
		btnEliminar.setFont(botonFont);

		// Cambia la imagen del Cursor
		//btnCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// Quitar bordes a los botones
		//btnCrear.setBorderPainted(false);
		btnBuscar.setBorderPainted(false);
		btnEditar.setBorderPainted(false);
		btnEliminar.setBorderPainted(false);

		// Layout simple para el panel_2
		// Layout tipo Flow para que no se estiren
		setLayout(new java.awt.FlowLayout(FlowLayout.LEFT, 10, 10));

		// Dar tamaño preferido a los botones
		Dimension buttonSize = new Dimension(150, 40); // ancho 150px, alto 40px
		//btnCrear.setPreferredSize(buttonSize);
		btnBuscar.setPreferredSize(buttonSize);
		btnEditar.setPreferredSize(buttonSize);
		btnEliminar.setPreferredSize(buttonSize);

		// Agregar botones al panel
		//panel_2.add(btnCrear);
		add(btnBuscar);
		add(btnEditar);
		add(btnEliminar);
	}

}
