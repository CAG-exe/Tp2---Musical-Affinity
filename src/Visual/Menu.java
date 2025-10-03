package Visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

import Controlador.Controlador;
import Modelo.AfinidadMusical;

import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Visual.Menu;

public class Menu extends JPanel {
	private JSlider tango;
	private JSlider folclore;
	private JSlider rock;
	private JSlider urbano;
	private JTextField nombre;
	private JLabel aviso;
	private JTable tabla;
	private DefaultTableModel modelo;
	private AfinidadMusical afinidadMusical;
	private Controlador controlador;


	public Menu(AfinidadMusical afinidadMusical, Controlador controlador) {
		this.afinidadMusical = afinidadMusical;
		this.controlador = controlador;
		inicializar();
	}

	private void inicializar() {

		this.setLayout(null);
		this.setBackground(new Color(220, 225, 195));

		JLabel ingreseNombre = new JLabel("Ingrese nombre:");
		ingreseNombre.setFont(new Font("Arial", Font.BOLD, 13));
		ingreseNombre.setForeground(Color.black);
		ingreseNombre.setBounds(10, 31, 109, 33);
		this.add(ingreseNombre);

		nombre = new JTextField();
		nombre.setFont(new Font("Arial", Font.BOLD, 14));
		nombre.setBounds(129, 31, 250, 33);
		this.add(nombre);

		aviso = new JLabel("");
		aviso.setForeground(Color.BLUE);
		aviso.setFont(new Font("Arial", Font.BOLD, 12));
		aviso.setBounds(67, 91, 310, 18);
		this.add(aviso);

		JLabel Tango = new JLabel("Tango");
		Tango.setFont(new Font("Arial", Font.BOLD, 13));
		Tango.setForeground(Color.black);
		Tango.setBounds(21, 124, 70, 22);
		this.add(Tango);

		tango = new JSlider();
		tango.setBackground(Color.WHITE);
		tango.setFont(new Font("Arial", Font.BOLD, 12));
		tango.setBounds(110, 124, 200, 60);
		tango.setValue(1);
		tango.setMinimum(1);
		tango.setMaximum(5);
		tango.setForeground(Color.black);
		tango.setPaintLabels(true);
		tango.setPaintTicks(true);
		tango.setMajorTickSpacing(1);
		this.add(tango);

		JLabel Folclore = new JLabel("Folclore");
		Folclore.setFont(new Font("Arial", Font.BOLD, 13));
		Folclore.setBounds(21, 214, 70, 22);
		Folclore.setForeground(Color.black);
		this.add(Folclore);

		folclore = new JSlider();
		folclore.setBackground(Color.WHITE);
		folclore.setFont(new Font("Arial", Font.BOLD, 12));
		folclore.setBounds(110, 214, 200, 60);
		folclore.setValue(1);
		folclore.setMinimum(1);
		folclore.setMaximum(5);
		folclore.setForeground(Color.black);
		folclore.setPaintLabels(true);
		folclore.setPaintTicks(true);
		folclore.setMajorTickSpacing(1);
		this.add(folclore);

		JLabel Rock = new JLabel("Rock");
		Rock.setFont(new Font("Arial", Font.BOLD, 13));
		Rock.setBounds(21, 304, 70, 22);
		Rock.setForeground(Color.black);
		this.add(Rock);

		rock = new JSlider();
		rock.setBackground(Color.WHITE);
		rock.setFont(new Font("Arial", Font.BOLD, 12));
		rock.setBounds(110, 304, 200, 60);
		rock.setValue(1);
		rock.setMinimum(1);
		rock.setMaximum(5);
		rock.setForeground(Color.black);
		rock.setPaintLabels(true);
		rock.setPaintTicks(true);
		rock.setMajorTickSpacing(1);
		this.add(rock);

		JLabel Urbano = new JLabel("Urbano");
		Urbano.setFont(new Font("Arial", Font.BOLD, 13));
		Urbano.setBounds(21, 394, 70, 22);
		Urbano.setForeground(Color.black);
		this.add(Urbano);

		urbano = new JSlider();
		urbano.setBackground(Color.WHITE);
		urbano.setFont(new Font("Arial", Font.BOLD, 12));
		urbano.setBounds(110, 394, 200, 60);
		urbano.setValue(1);
		urbano.setMinimum(1);
		urbano.setMaximum(5);
		urbano.setForeground(Color.black);
		urbano.setPaintLabels(true);
		urbano.setPaintTicks(true);
		urbano.setMajorTickSpacing(1);
		this.add(urbano);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 158, 257, 222);
		this.add(scrollPane);
		
		tabla = new JTable();
		tabla.setFocusable(false);
		scrollPane.setViewportView(tabla);
		
		modelo = new DefaultTableModel();
		tabla.setModel(modelo);
		
		modelo.addColumn("nombre");
		modelo.addColumn("iT");
		modelo.addColumn("iF");
		modelo.addColumn("iR");
		modelo.addColumn("iU");

		
		JButton guardarPersona = new JButton("Guardar Datos");
		guardarPersona.setFocusable(false);
		guardarPersona.setFont(new Font("Arial", Font.BOLD, 14));
		guardarPersona.addActionListener(e ->{
			
			if (nombre.getText().length() < 3) {
			    avisoNombreCorto();
			}
			else if (afinidadMusical.usuarioYaRegistrado(nombre.getText())) {
			    avisoNombreRegistrado();
			}
			else {	
			
			NuevoUsuarioDatos dto = NuevoUsuarioDatos.builder()
		            .nombre(nombre.getText())
		            .tango(tango.getValue())
		            .folclore(folclore.getValue())
		            .rock(rock.getValue())
		            .urbano(urbano.getValue())
		            .build();

            controlador.guardarNuevoUsuario(dto);
            añadirUsuarioAFila();
			}

		});
		
		
		guardarPersona.setBounds(300, 454, 150, 50);
		this.add(guardarPersona);
	}
	
	
	public void avisoNombreCorto() {
		aviso.setText("Tu nombre no puede tener menos de 3 letras");
	}
	
	public void avisoNombreRegistrado() {
		aviso.setText("Ya hay un usuario con ese nombre, prueba con otro.");
	}
	
	public void añadirUsuarioAFila() {
		 Object[] fila = new Object[5];
         fila[0] = nombre.getText();
         fila[1] = tango.getValue();
         fila[2] = folclore.getValue();
         fila[3] = rock.getValue();
         fila[4] = urbano.getValue();
         
         modelo.addRow(fila);	
   }
	
}