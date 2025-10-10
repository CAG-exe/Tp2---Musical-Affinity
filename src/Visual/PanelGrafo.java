package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Controlador.Controlador;
import Modelo.AfinidadMusical;

public class PanelGrafo extends JPanel {
	private JScrollPane scrollPane;
	private JScrollPane scrollMatrizAdyacencia;
	private JButton btnMostrarGraficoGrafo;
	private GraficoGrafo graficoGrafo;
	private Controlador controlador;
	private AfinidadMusical afinidadMusical;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelGrafo(AfinidadMusical afinidadMusical, Controlador controlador) {
		this.controlador = controlador;
		this.afinidadMusical = afinidadMusical;
		setLayout(new BorderLayout());
		setBackground(new Color(220, 225, 195));
		
		scrollPane = new JScrollPane();
		add(scrollPane,BorderLayout.WEST);
		btnMostrarGraficoGrafo = new JButton("Mostrar Gráfico del Grafo");	
		btnMostrarGraficoGrafo.setEnabled(false);
		configuracionDelBoton();
		add(btnMostrarGraficoGrafo,BorderLayout.SOUTH);

		
		generarListaUsuariosVisual();
		///
		scrollMatrizAdyacencia = new JScrollPane();
		add(scrollMatrizAdyacencia,BorderLayout.CENTER);
		
		
		
		generarMatrizGrafoVisual();
		
	}
	
	
	
	private void configuracionDelBoton()	{

		btnMostrarGraficoGrafo.setContentAreaFilled(true);
		btnMostrarGraficoGrafo.setOpaque(true);
		btnMostrarGraficoGrafo.setBackground(new Color(9, 61, 39));
		btnMostrarGraficoGrafo.setPreferredSize(new Dimension(200,40));
		btnMostrarGraficoGrafo.setForeground(new Color(255, 255, 255));
		btnMostrarGraficoGrafo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnMostrarGraficoGrafo.isEnabled()){
					mostrarGraficoGrafo();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
		});
			
		
	}
	
	public void mostrarGraficoGrafo() {
		int cantidadDeUsuarios = afinidadMusical.getCantidadDeUsuarios();
		String[][] matrizUsuarios= afinidadMusical.getGrafoMatrizString(cantidadDeUsuarios);
		graficoGrafo = new GraficoGrafo(matrizUsuarios);
	}


	private void generarMatrizGrafoVisual() {
		int cantidadDeUsuarios = afinidadMusical.getCantidadDeUsuarios();
		String[][] matrizTexto=afinidadMusical.getGrafoMatrizString(cantidadDeUsuarios);
		String[] columnas = generarHeaders(cantidadDeUsuarios);
		DefaultTableModel MatrizModel = new DefaultTableModel(matrizTexto,columnas) {
		    private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int filas, int columnas) {
                return false; 
            }
        };

		JTable MatrizAdyacenciaTabla = new JTable(MatrizModel);
		MatrizAdyacenciaTabla.setRowHeight(30);
		scrollMatrizAdyacencia.setViewportView(MatrizAdyacenciaTabla);
		scrollMatrizAdyacencia.setPreferredSize(new Dimension(500,0));
		
		
		JList<String> rowHeader = new JList<>(columnas);
        rowHeader.setFixedCellWidth(30);
        rowHeader.setFixedCellHeight(MatrizAdyacenciaTabla.getRowHeight());
        rowHeader.setCellRenderer(new RowHeaderRenderer(MatrizAdyacenciaTabla));

        
        
        scrollMatrizAdyacencia.setRowHeaderView(rowHeader);
	}
	
	private String[] generarHeaders(int cantidadDeUsuarios) {
		String[] headers = new String[cantidadDeUsuarios];
		for(int i=0;i<cantidadDeUsuarios;i++) {
			headers[i]=String.valueOf(i);
		}
		return headers;
	}

	public void recargarGrafo() {
		generarMatrizGrafoVisual();
		generarListaUsuariosVisual();
		revalidate();
		repaint();
	}
	
	private void generarListaUsuariosVisual() {
		String[][] listaUsuarios= afinidadMusical.getListaUsuariosMatrizString();
		String[] columnas = {"ID","Nombre"};
		DefaultTableModel tablaModel = new DefaultTableModel(listaUsuarios,columnas) {
		    private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int filas, int columnas) {
                return false; 
            }
		};
		
		JTable UsuariosTabla = new JTable(tablaModel);
		scrollPane.setViewportView(UsuariosTabla);
		scrollPane.setPreferredSize(new Dimension(200,100));
		
	}

	// Renderizador para que las filas se vean como encabezados
	static class RowHeaderRenderer extends JLabel implements ListCellRenderer<String> {
	    private static final long serialVersionUID = 1L;

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

	public void habilitarBotonGrafo() {
		btnMostrarGraficoGrafo.setEnabled(true);
		repaint();
		revalidate();
		
	}
}
