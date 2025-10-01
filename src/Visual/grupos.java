package Visual;


import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;


public class grupos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame ventanaVerGrupos;
	private JTable tabla1;
	private JTable tabla2;
	private JPanel contentPane;
	



	public grupos() {
		initialize();
	}

	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ventanaVerGrupos = new JFrame();
		ventanaVerGrupos.getContentPane().setBackground(Color.WHITE);
		ventanaVerGrupos.setTitle("Afinidad-musical");
		ventanaVerGrupos.setResizable(false);
		ventanaVerGrupos.setBounds(790, 100, 400, 504);
		ventanaVerGrupos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaVerGrupos.getContentPane().setLayout(null);
		ventanaVerGrupos.setVisible(true);
		
		DefaultTableModel modelo1=new DefaultTableModel();
		modelo1.addColumn("Nombre");
		
		DefaultTableModel modelo2=new DefaultTableModel();
		modelo2.addColumn("Nombre");
		
		
		tabla1 = new JTable();
		tabla1.setFocusable(false);
		tabla1.setFont(new Font("Arial", Font.BOLD, 13));
		tabla1.setBounds(43, 64, 137, 299);
		tabla1.setModel(modelo1);
			
		
		tabla2 = new JTable();
		tabla2.setFocusable(false);
		tabla2.setFont(new Font("Arial", Font.BOLD, 13));
		tabla2.setBounds(218, 64, 137, 299);
		tabla2.setModel(modelo2);
			
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 64, 143, 299);
		ventanaVerGrupos.getContentPane().add(scrollPane);
		scrollPane.setViewportView(tabla1);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(212, 64, 143, 299);
		ventanaVerGrupos.getContentPane().add(scrollPane2);
		scrollPane2.setViewportView(tabla2);
		
		JLabel etiquetaGrupo1 = new JLabel("Grupo 1");
		etiquetaGrupo1.setFont(new Font("Arial", Font.BOLD, 16));
		etiquetaGrupo1.setBounds(71, 27, 76, 26);
		ventanaVerGrupos.getContentPane().add(etiquetaGrupo1);
		
		JLabel etiquetaGrupo2 = new JLabel("Grupo 2");
		etiquetaGrupo2.setFont(new Font("Arial", Font.BOLD, 16));
		etiquetaGrupo2.setBounds(243, 27, 76, 26);
		ventanaVerGrupos.getContentPane().add(etiquetaGrupo2);
		
	}

	public void mostrarVentana() {
		ventanaVerGrupos.setVisible(true);
    }
}
