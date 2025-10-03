package Visual;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

import Controlador.Controlador;
import Modelo.AfinidadMusical;
import Modelo.Usuario;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;

public class UISwing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel background;
	private JPanel Contenedor;
	private JScrollPane scrollPane;
	private JScrollPane scrollMatrizAdyacencia;
	private JLabel _TituloDePagina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public UISwing(Controlador controlador, AfinidadMusical afinidadMusical) {
		setMinimumSize(new Dimension(1050, 720));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 720);
		background = new JPanel();
		background.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(background);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(06, 57, 64));
		
		JSeparator separator = new JSeparator();
		
		JButton UsuariosBoton = new JButton("Usuarios");
		
		UsuariosBoton.addActionListener(
				e -> controlador.mostrarPanelUsuarios());
		
		UsuariosBoton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		UsuariosBoton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		UsuariosBoton.setBorderPainted(false);
		UsuariosBoton.setBorder(null);
		
		JButton GrafoButton = new JButton("Grafo");
		GrafoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GrafoButton.setBorderPainted(false);
		GrafoButton.setBorder(null);
		GrafoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GrafoButton.addActionListener(
				e -> controlador.mostrarPanelGrafo());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(25, 94, 99));
		
		
		JButton RegistroUsuariosButton = new JButton("Crear Usuario");
		RegistroUsuariosButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		RegistroUsuariosButton.addActionListener(e -> {
			mostrarPanel("CrearUsuario");
			cambiarTituloDePaginaCrearUsuario();
		});
		
		Menu panelCrearUsuario = new Menu(afinidadMusical, controlador); 

		controlador.añadirMenuAlControlador(panelCrearUsuario);
		

		RegistroUsuariosButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		RegistroUsuariosButton.setBorderPainted(false);
		RegistroUsuariosButton.setBorder(null);
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));

		Image scaledImage = originalIcon.getImage().getScaledInstance(220, 75, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		JLabel LogoLabel = new JLabel();
		LogoLabel.setIcon(scaledIcon);
		getContentPane().add(LogoLabel);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
				.addComponent(GrafoButton, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
				.addComponent(UsuariosBoton, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
				.addComponent(RegistroUsuariosButton, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(LogoLabel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(LogoLabel)
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(51)
							.addComponent(GrafoButton, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addComponent(UsuariosBoton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(100)
							.addComponent(RegistroUsuariosButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(220, 225, 195));
		
		
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
		panel_2.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT, 10, 10));

		// Dar tamaño preferido a los botones
		Dimension buttonSize = new Dimension(150, 40); // ancho 150px, alto 40px
		//btnCrear.setPreferredSize(buttonSize);
		btnBuscar.setPreferredSize(buttonSize);
		btnEditar.setPreferredSize(buttonSize);
		btnEliminar.setPreferredSize(buttonSize);

		// Agregar botones al panel
		//panel_2.add(btnCrear);
		panel_2.add(btnBuscar);
		panel_2.add(btnEditar);
		panel_2.add(btnEliminar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new BorderLayout());
		panel_3.setBackground(new Color(220, 225, 195));
		
		scrollPane = new JScrollPane();
		JTable UsuariosTabla = new JTable();
		scrollPane.setViewportView(UsuariosTabla);
		panel_3.add(scrollPane,BorderLayout.WEST);
		scrollPane.setPreferredSize(new Dimension(200,0));
		
		
		UsuariosTabla.setFocusable(false);
		DefaultTableModel tablaModel = new DefaultTableModel();
		UsuariosTabla.setModel(tablaModel);
		tablaModel.addColumn("ID");
		tablaModel.addColumn("Nombre");
		UsuariosTabla.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		
		///test de datos en la tabla
		tablaModel.addRow(new Object[] {0,"Juan"});
		tablaModel.addRow(new Object[] {1,"Maria"});	
		tablaModel.addRow(new Object[] {2,"Pedro"});
		tablaModel.addRow(new Object[] {3,"Luisa"});
		tablaModel.addRow(new Object[] {4,"Ana"});
		tablaModel.addRow(new Object[] {5,"Luis"});
		
		///
		String[][] matrizTexto=afinidadMusical.getGrafoMatrizString();
		String[] columnas = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
		DefaultTableModel MatrizModel = new DefaultTableModel(matrizTexto,columnas);
		scrollMatrizAdyacencia = new JScrollPane();
		JTable MatrizAdyacenciaTabla = new JTable(MatrizModel);
		MatrizAdyacenciaTabla.setRowHeight(30);
		scrollMatrizAdyacencia.setViewportView(MatrizAdyacenciaTabla);
		panel_3.add(scrollMatrizAdyacencia,BorderLayout.EAST);
		scrollMatrizAdyacencia.setPreferredSize(new Dimension(500,0));
		
		
		JList<String> rowHeader = new JList<>(columnas);
        rowHeader.setFixedCellWidth(30);
        rowHeader.setFixedCellHeight(MatrizAdyacenciaTabla.getRowHeight());
        rowHeader.setCellRenderer(new RowHeaderRenderer(MatrizAdyacenciaTabla));
        
        
        scrollMatrizAdyacencia.setRowHeaderView(rowHeader);
		
		Contenedor = new JPanel(new CardLayout());
		Contenedor.add(panel_2,"Usuarios");
		Contenedor.add(panel_3,"Grafo");
		mostrarPanel("Usuarios");
		
		Contenedor.add(panelCrearUsuario, "CrearUsuario");

		GroupLayout gl_background = new GroupLayout(background);
		gl_background.setHorizontalGroup(
			gl_background.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_background.createSequentialGroup()
					.addGroup(gl_background.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_background.createSequentialGroup()
							.addGap(5)
							.addComponent(LogoLabel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_background.createParallelGroup(Alignment.LEADING)
						.addComponent(Contenedor, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)))
		);
		gl_background.setVerticalGroup(
			gl_background.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_background.createSequentialGroup()
					.addGap(16)
					.addComponent(LogoLabel))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
				.addGroup(gl_background.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_background.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_background.createSequentialGroup()
							.addGap(124)
							.addComponent(Contenedor, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
						.addGroup(gl_background.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(504, Short.MAX_VALUE))))
		);
				
		JLabel TituloDePagina = new JLabel("USUARIOS");
		this._TituloDePagina = TituloDePagina;
		TituloDePagina.setForeground(new Color(255, 255, 255));
		TituloDePagina.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(332)
					.addComponent(TituloDePagina, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(321))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(TituloDePagina, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
		);
		panel_1.setLayout(gl_panel_1);
		background.setLayout(gl_background);
		
	}
	
	public void mostrarPanel(String nombre) {
        CardLayout cl = (CardLayout) Contenedor.getLayout();
        cl.show(Contenedor, nombre);
    }
	
	public void cambiarTituloDePaginaUsuario() {
		_TituloDePagina.setText("USUARIOS");
	}
	
	public void cambiarTituloDePaginaGrafo() {
		_TituloDePagina.setText("GRAFO");
	}
	public void cambiarTituloDePaginaCrearUsuario() {
		_TituloDePagina.setText("CREAR USUARIO");
	}
	
	// Renderizador para que las filas se vean como encabezados
	static class RowHeaderRenderer extends JLabel implements ListCellRenderer<String> {
	    RowHeaderRenderer(JTable table) {
	        JTableHeader header = table.getTableHeader();
	        setOpaque(true);
	        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
	        setHorizontalAlignment(CENTER);
	        setForeground(header.getForeground());
	        setBackground(header.getBackground());
	        setFont(header.getFont());
	    }

	    @Override
	    public Component getListCellRendererComponent(JList<? extends String> list,String value, int index,boolean isSelected, boolean cellHasFocus) {
	        setText(value);
	        return this;
	    }
	}

	
}

