package Visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Controlador.Controlador;
import Modelo.AfinidadMusical;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import Visual.CrearUsuario;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.util.Map;
import Modelo.Usuario;

public class CrearUsuario extends JPanel {
	private static final long serialVersionUID = 1L;
	private JSlider tango;
	private JSlider folclore;
	private JSlider rock;
	private JSlider urbano;
	private JTextField nombre;
	private JLabel aviso;
	private JTable tabla;
	private DefaultTableModel modeloDeTabla;
	@SuppressWarnings("unused")
	private AfinidadMusical afinidadMusical;
	private Controlador controlador;
	private JComboBox<String[]> comboBoxGrupos;


	public CrearUsuario(AfinidadMusical afinidadMusical, Controlador controlador) {
		this.afinidadMusical = afinidadMusical;
		this.controlador = controlador;
		inicializar();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void inicializar() {

		this.setLayout(null);
		this.setBackground(new Color(220, 225, 195));

		textoDeIngreseNombre();

		crearTextFieldParaIngresarNombreDeUsuario();

		crearTextoDeAvisoDeNombreInvalido();

		crearBarraDeInteresDeTango();

		crearBarraDeInteresDeFolclore();

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
		scrollPane.setBounds(450, 158, 298, 222);
		this.add(scrollPane);
		
		tabla = new JTable();
		tabla.setFocusable(false);
		scrollPane.setViewportView(tabla);
		
		modeloDeTabla = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int filas, int columnas) {
				return false;
			}
		};
		tabla.setModel(modeloDeTabla);
		
		modeloDeTabla.addColumn("nombre");
		modeloDeTabla.addColumn("iT");
		modeloDeTabla.addColumn("iF");
		modeloDeTabla.addColumn("iR");
		modeloDeTabla.addColumn("iU");
		
		JButton eliminarPersona = new JButton("Eliminar usuario");
		eliminarPersona.setFocusable(false);
		eliminarPersona.setFont(new Font("Arial", Font.BOLD, 14));
		eliminarPersona.setBounds(600, 454, 150, 50);
		eliminarPersona.addActionListener(e ->{
			controlador.eliminarUsuario();
		});
		this.add(eliminarPersona);

		
		JButton guardarPersona = new JButton("Guardar Datos");
		guardarPersona.setFocusable(false);
		guardarPersona.setFont(new Font("Arial", Font.BOLD, 14));
		guardarPersona.addActionListener(e ->{
            controlador.guardarNuevoUsuario();

		});
		
		
		guardarPersona.setBounds(300, 454, 150, 50);
		this.add(guardarPersona);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(20, 71, 379, 9);
		add(separator);
		
		comboBoxGrupos = new JComboBox<String[]>();
		comboBoxGrupos.setBounds(686, 391, 51, 28);
		add(comboBoxGrupos);
		comboBoxGrupos.setModel(new DefaultComboBoxModel(new String[] {"2","3","4","5"}));
		
		JLabel textCantidadGrupos = new JLabel("Cantidad de Grupos:");
		textCantidadGrupos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textCantidadGrupos.setBounds(537, 396, 139, 14);
		textCantidadGrupos.setForeground(Color.black);
		add(textCantidadGrupos);
        cargarUsuariosDeLaBaseDeDatos();
	}

	private void crearBarraDeInteresDeFolclore() {
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
	}

	private void crearBarraDeInteresDeTango() {
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
	}

	private void crearTextoDeAvisoDeNombreInvalido() {
		aviso = new JLabel("");
		aviso.setForeground(Color.BLUE);
		aviso.setFont(new Font("Arial", Font.BOLD, 12));
		aviso.setBounds(67, 91, 310, 18);
		this.add(aviso);
	}

	private void crearTextFieldParaIngresarNombreDeUsuario() {
		nombre = new JTextField();
		nombre.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				controlador.clicEnTextFieldNombre();
			}
		});
		nombre.setForeground(Color.GRAY);
		nombre.setText("Ingrese su nombre de usuario");
		nombre.setBackground(new Color(220, 225, 195));
		nombre.setBorder(null);
		nombre.setFont(new Font("Arial", Font.BOLD, 14));
		nombre.setBounds(20, 49, 379, 22);
		this.add(nombre);
	}

	private void textoDeIngreseNombre() {
		JLabel ingreseNombre = new JLabel("Usuario");
		ingreseNombre.setFont(new Font("Arial", Font.BOLD, 19));
		ingreseNombre.setForeground(Color.black);
		ingreseNombre.setBounds(20, 11, 150, 33);
		this.add(ingreseNombre);
	}
	
	public void cargarUsuariosDeLaBaseDeDatos() {
        Map<Integer, Usuario> usuarios = AfinidadMusical.getUsuarios();

        for (Usuario usuario : usuarios.values()) {
            Object[] fila = new Object[5];
            fila[0] = usuario.getNombre();
            fila[1] = usuario.getInteresTango();
            fila[2] = usuario.getInteresFolclore();
            fila[3] = usuario.getInteresRockNacional();
            fila[4] = usuario.getInteresGeneroUrbano();
            
            modeloDeTabla.addRow(fila);
        }
    }
	
	public NuevoUsuarioDatos generarNuevoUsuarioSegunDatosActuales() {
		NuevoUsuarioDatos dto = NuevoUsuarioDatos.builder()
	            .nombre(nombre.getText())
	            .tango(tango.getValue())
	            .folclore(folclore.getValue())
	            .rock(rock.getValue())
	            .urbano(urbano.getValue())
	            .build();
		
		return dto;
	}
	
	public void ingreseNombre() {
		aviso.setText("Por favor ingrese su nombre");
	}
	
	public String getComboBoxGrupos() {
		return (String) comboBoxGrupos.getSelectedItem();
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
         
         modeloDeTabla.addRow(fila);	
   }
	
	public int getIndiceDeUsuarioseleccionado() {
		return tabla.getSelectedRow();
	}
	
	public String getNombreDeUsaurioEnLaTabla(int fila) {
		return (String) tabla.getValueAt(fila, 0);
	}
	
	public void eliminarUsuarioDeLaTablaSegunIndex(int i) {
		modeloDeTabla.removeRow(i);
	}


	public void clicEnTextField() {
		if(nombre.getText().equals("Ingrese su nombre de usuario")) {
			nombre.setText("");
			nombre.setForeground(Color.black);
		};
	}
	
	public void recargarTextoEnTextFieldNombre() {
		nombre.setText("Ingrese su nombre de usuario");
		nombre.setForeground(Color.gray);
	}
}