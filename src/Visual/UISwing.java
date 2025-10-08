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
import java.util.Arrays;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Rectangle;

public class UISwing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel background;
	private JPanel Contenedor;
	private JScrollPane scrollPane;
	private JScrollPane scrollMatrizAdyacencia;
	private JLabel _TituloDePagina;
	private PanelGrafo panelGrafo;
	private PanelUsuarios panelUsuario;
	private CrearUsuario panelCrearUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public UISwing(Controlador controlador, AfinidadMusical afinidadMusical) {
		this.panelUsuario = new PanelUsuarios(afinidadMusical.getGrafo());
		this.panelGrafo = new PanelGrafo(afinidadMusical, controlador);
		
		setMinimumSize(new Dimension(1050, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 720);
		background = new JPanel();
		background.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(background);
		
		JPanel panelLateralDeSolapas = new JPanel();
		panelLateralDeSolapas.setBackground(new Color(06, 57, 64));
		
		JSeparator separator = new JSeparator();
		
		JButton UsuariosBoton = new JButton("Crear Usuarios");
		
		UsuariosBoton.addActionListener(
				e -> controlador.mostrarPanelMenu());
		
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
		
		JPanel panelTituloDePagina = new JPanel();
		panelTituloDePagina.setBackground(new Color(25, 94, 99));
		
		
		JButton EstadisticasButton = new JButton("Estadisticas");
		EstadisticasButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		EstadisticasButton.addActionListener(e -> {
			controlador.mostrarPanelEstadisticas();
		});
		
		panelCrearUsuario = new CrearUsuario(afinidadMusical, controlador); 

		controlador.añadirMenuAlControlador(panelCrearUsuario);
		

		EstadisticasButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		EstadisticasButton.setBorderPainted(false);
		EstadisticasButton.setBorder(null);
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));

		Image scaledImage = originalIcon.getImage().getScaledInstance(220, 75, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		JLabel LogoLabel = new JLabel();
		LogoLabel.setIcon(scaledIcon);
		getContentPane().add(LogoLabel);
		GroupLayout gl_panelLateralDeSolapas = new GroupLayout(panelLateralDeSolapas);
		gl_panelLateralDeSolapas.setHorizontalGroup(
			gl_panelLateralDeSolapas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLateralDeSolapas.createSequentialGroup()
					.addGap(21)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
				.addComponent(GrafoButton, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
				.addComponent(UsuariosBoton, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
				.addComponent(EstadisticasButton, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panelLateralDeSolapas.createSequentialGroup()
					.addContainerGap()
					.addComponent(LogoLabel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelLateralDeSolapas.setVerticalGroup(
			gl_panelLateralDeSolapas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLateralDeSolapas.createSequentialGroup()
					.addGap(21)
					.addComponent(LogoLabel)
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addGroup(gl_panelLateralDeSolapas.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelLateralDeSolapas.createSequentialGroup()
							.addGap(51)
							.addComponent(GrafoButton, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addComponent(UsuariosBoton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelLateralDeSolapas.createSequentialGroup()
							.addGap(100)
							.addComponent(EstadisticasButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))))
		);
		panelLateralDeSolapas.setLayout(gl_panelLateralDeSolapas);
		
		
		
		
		Contenedor = new JPanel(new CardLayout());
		Contenedor.add(panelCrearUsuario, "CrearUsuario");
		Contenedor.add(panelUsuario,"Estadisticas");
		Contenedor.add(panelGrafo,"Grafo");
		mostrarPanel("CrearUsuario");
		
		

		GroupLayout gl_background = new GroupLayout(background);
		gl_background.setHorizontalGroup(
			gl_background.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_background.createSequentialGroup()
					.addGroup(gl_background.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_background.createSequentialGroup()
							.addGap(5)
							.addComponent(LogoLabel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelLateralDeSolapas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_background.createParallelGroup(Alignment.LEADING)
						.addComponent(Contenedor, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
						.addComponent(panelTituloDePagina, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)))
		);
		gl_background.setVerticalGroup(
			gl_background.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_background.createSequentialGroup()
					.addGap(16)
					.addComponent(LogoLabel))
				.addComponent(panelLateralDeSolapas, GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
				.addGroup(gl_background.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_background.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_background.createSequentialGroup()
							.addGap(124)
							.addComponent(Contenedor, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
						.addGroup(gl_background.createSequentialGroup()
							.addComponent(panelTituloDePagina, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(504, Short.MAX_VALUE))))
		);
				
		JLabel TituloDePagina = new JLabel("CREAR USUARIOS");
		this._TituloDePagina = TituloDePagina;
		TituloDePagina.setForeground(new Color(255, 255, 255));
		TituloDePagina.setBorder(new EmptyBorder(0, 300, 0, 0));
		TituloDePagina.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_panelTituloDePagina = new GroupLayout(panelTituloDePagina);
		gl_panelTituloDePagina.setHorizontalGroup(
			gl_panelTituloDePagina.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelTituloDePagina.createSequentialGroup()
					.addContainerGap()
					.addComponent(TituloDePagina, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelTituloDePagina.setVerticalGroup(
			gl_panelTituloDePagina.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTituloDePagina.createSequentialGroup()
					.addContainerGap()
					.addComponent(TituloDePagina, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelTituloDePagina.setLayout(gl_panelTituloDePagina);
		background.setLayout(gl_background);
		
	}
	
	public void mostrarPanel(String nombre) {
        CardLayout cl = (CardLayout) Contenedor.getLayout();
        cl.show(Contenedor, nombre);
    }
	
	public void cambiarTituloDePaginaEstadisticas() {
		_TituloDePagina.setText("ESTADISTICAS");
		_TituloDePagina.setBorder(new EmptyBorder(0, 300, 0, 0));
	}
	
	public void cambiarTituloDePaginaGrafo() {
		_TituloDePagina.setText("GRAFO");
		_TituloDePagina.setBorder(new EmptyBorder(0, 330, 0, 0));
	}
	public void cambiarTituloDePaginaCrearUsuario() {
		_TituloDePagina.setText("CREAR USUARIOS");
		_TituloDePagina.setBorder(new EmptyBorder(0, 300, 0, 0));
	}

	public void recargarGrafo(AfinidadMusical modelo) {
		this.panelGrafo.recargarGrafo(modelo);
	}
	
	public String getComboBoxCantidadGrupos() {
		return this.panelCrearUsuario.getComboBoxGrupos();
	}

	public void habilitarBotonGrafo() {
		panelGrafo.habilitarBotonGrafo();
		
	}
	
}

