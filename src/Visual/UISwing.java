package Visual;

import Controlador.Controlador;
import Modelo.AfinidadMusical;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class UISwing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel background;
	private JPanel Contenedor;
	private JLabel _TituloDePagina;
	private PanelGrafo panelGrafo;
	private PanelUsuarios panelUsuario;
	private CrearUsuario panelCrearUsuario;

	public UISwing(Controlador controlador, AfinidadMusical afinidadMusical) {
		this.panelUsuario = new PanelUsuarios();
		this.panelGrafo = new PanelGrafo(afinidadMusical, controlador);
		
		setMinimumSize(new Dimension(1050, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 720);
		background = new JPanel();
		background.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(background);
		
		JPanel panelLateralDeSolapas = new JPanel();
		panelLateralDeSolapas.setBackground(new Color(6, 57, 64));
		
		JSeparator separator = new JSeparator();
		
		JButton UsuariosBoton = new JButton("Crear Usuarios");
		UsuariosBoton.setBorder(null);
		UsuariosBoton.addActionListener(e -> controlador.mostrarPanelMenu());
		UsuariosBoton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		UsuariosBoton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		UsuariosBoton.setBorderPainted(false);
		
		JButton GrafoButton = new JButton("Grafo");
		GrafoButton.setBorder(null);
		GrafoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GrafoButton.setBorderPainted(false);
		GrafoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GrafoButton.addActionListener(e -> controlador.mostrarPanelGrafo());
		
		JPanel panelTituloDePagina = new JPanel();
		panelTituloDePagina.setBackground(new Color(25, 94, 99));
		
		JButton EstadisticasButton = new JButton("Estadisticas");
		EstadisticasButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EstadisticasButton.addActionListener(e -> controlador.mostrarPanelEstadisticas());
		
		panelCrearUsuario = new CrearUsuario(afinidadMusical, controlador); 
		controlador.añadirMenuAlControlador(panelCrearUsuario);
		
		EstadisticasButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		EstadisticasButton.setBorderPainted(false);
		EstadisticasButton.setBorder(null);
		
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));
		Image scaledImage = originalIcon.getImage().getScaledInstance(220, 75, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		JLabel LogoLabel = new JLabel(scaledIcon);

		GroupLayout gl_panelLateralDeSolapas = new GroupLayout(panelLateralDeSolapas);
		gl_panelLateralDeSolapas.setHorizontalGroup(
			gl_panelLateralDeSolapas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLateralDeSolapas.createSequentialGroup().addGap(21).addComponent(separator, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
				.addComponent(GrafoButton, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
				.addComponent(UsuariosBoton, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
				.addComponent(EstadisticasButton, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panelLateralDeSolapas.createSequentialGroup().addContainerGap().addComponent(LogoLabel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelLateralDeSolapas.setVerticalGroup(
			gl_panelLateralDeSolapas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLateralDeSolapas.createSequentialGroup().addGap(21).addComponent(LogoLabel).addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE).addGap(51)
					.addGroup(gl_panelLateralDeSolapas.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelLateralDeSolapas.createSequentialGroup().addGap(51).addComponent(GrafoButton, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addComponent(UsuariosBoton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelLateralDeSolapas.createSequentialGroup().addGap(100).addComponent(EstadisticasButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))))
		);
		panelLateralDeSolapas.setLayout(gl_panelLateralDeSolapas);
		
		Contenedor = new JPanel(new CardLayout());
		Contenedor.add(panelCrearUsuario, "CrearUsuario");
		JScrollPane scrollEstadisticas = new JScrollPane(panelUsuario);
		Contenedor.add(scrollEstadisticas, "Estadisticas");
		Contenedor.add(panelGrafo,"Grafo");
		mostrarPanel("CrearUsuario");
		
		GroupLayout gl_background = new GroupLayout(background);
		gl_background.setHorizontalGroup(
			gl_background.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_background.createSequentialGroup()
					.addComponent(panelLateralDeSolapas, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_background.createParallelGroup(Alignment.LEADING)
						.addComponent(panelTituloDePagina, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
						.addComponent(Contenedor, GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)))
		);
		gl_background.setVerticalGroup(
			gl_background.createParallelGroup(Alignment.LEADING)
				.addComponent(panelLateralDeSolapas, GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
				.addGroup(gl_background.createSequentialGroup()
					.addComponent(panelTituloDePagina, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addComponent(Contenedor, GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
		);
				
		_TituloDePagina = new JLabel("CREAR USUARIOS");
		_TituloDePagina.setForeground(Color.WHITE);
		_TituloDePagina.setBorder(new EmptyBorder(0, 280, 0, 0));
		_TituloDePagina.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		GroupLayout gl_panelTituloDePagina = new GroupLayout(panelTituloDePagina);
		gl_panelTituloDePagina.setHorizontalGroup(gl_panelTituloDePagina.createSequentialGroup().addContainerGap().addComponent(_TituloDePagina, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE).addContainerGap());
		gl_panelTituloDePagina.setVerticalGroup(gl_panelTituloDePagina.createSequentialGroup().addContainerGap().addComponent(_TituloDePagina, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE).addContainerGap());
		panelTituloDePagina.setLayout(gl_panelTituloDePagina);
		background.setLayout(gl_background);
	}
	
	public void mostrarPanel(String nombre) {
        CardLayout cl = (CardLayout) Contenedor.getLayout();
        cl.show(Contenedor, nombre);
    }
	
	public void cambiarTituloDePaginaEstadisticas() {
		_TituloDePagina.setText("ESTADÍSTICAS POR GRUPO");
		_TituloDePagina.setBorder(new EmptyBorder(0, 220, 0, 0));
	}
	
	public void cambiarTituloDePaginaGrafo() {
		_TituloDePagina.setText("GRAFO DE AFINIDAD");
		_TituloDePagina.setBorder(new EmptyBorder(0, 250, 0, 0));
	}
	public void cambiarTituloDePaginaCrearUsuario() {
		_TituloDePagina.setText("CREAR USUARIOS");
		_TituloDePagina.setBorder(new EmptyBorder(0, 280, 0, 0));
	}

	public void recargarGrafo() {
		this.panelGrafo.recargarGrafo();
	}
	
	public String getComboBoxCantidadGrupos() {
		return this.panelCrearUsuario.getComboBoxGrupos();
	}

	public void habilitarBotonGrafo() {
		panelGrafo.habilitarBotonGrafo();
	}
    
    public PanelUsuarios getPanelUsuarios() {
        return this.panelUsuario;
    }
}