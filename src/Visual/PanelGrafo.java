package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.lowagie.text.Font;

import Controlador.Controlador;
import Modelo.AfinidadMusical;

public class PanelGrafo extends JPanel {
	private JScrollPane scrollPane , scrollGrupos;
	private JScrollPane scrollMatrizAdyacencia;
	private JButton btnMostrarGraficoGrafo;
	private AfinidadMusical afinidadMusical;
	private Controlador controlador;
	private JButton btnMostrarMatrizAdyacencia;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelGrafo(AfinidadMusical afinidadMusical, Controlador controlador) {
		this.afinidadMusical = afinidadMusical;
		this.controlador = controlador;
		setLayout(new BorderLayout());
		setBackground(new Color(220, 225, 195));
		
		scrollPane = new JScrollPane();
		add(scrollPane,BorderLayout.WEST);
		scrollPane.setVisible(false);
		
		JPanel PanelSurBotones = new JPanel(new GridLayout(2, 1));

		btnMostrarGraficoGrafo = new JButton("Mostrar Gráfico del Grafo");	
		btnMostrarGraficoGrafo.setEnabled(false);
		btnMostrarMatrizAdyacencia = new JButton("Mostrar Matriz Adyacencia");
		
		configuracionDelBoton();
		generarListaUsuariosVisual();
		
		PanelSurBotones.add(btnMostrarGraficoGrafo);
		PanelSurBotones.add(btnMostrarMatrizAdyacencia);
		add(PanelSurBotones,BorderLayout.SOUTH);
		
		scrollMatrizAdyacencia = new JScrollPane();
		add(scrollMatrizAdyacencia,BorderLayout.EAST);
		scrollMatrizAdyacencia.setVisible(false);
		generarMatrizGrafoVisual();
		
		scrollGrupos = new JScrollPane();
		add(scrollGrupos,BorderLayout.CENTER);
		scrollGrupos.setVisible(true);
		configurarScrollGrupos();
		
		
	}


	private void configurarScrollGrupos() {
	   Object[][] data = controlador.getMatrizGrupos();
       String[] columnNames = darHeaderDeGrupos(controlador.getComboBoxCantidadGrupos());

        JTable table = new JTable(data, columnNames);

        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            String grupo = "Grupo " + ((i / 2) + 1);
            String sub = (i % 2 == 0) ? "ID" : "Nombre";
            columnModel.getColumn(i).setHeaderRenderer(new MultiHeaderRenderer(grupo, sub));
        }
        scrollGrupos.setViewportView(table);
	}
	
	private String[] darHeaderDeGrupos(String cantidadDeGrupos) {
		switch (cantidadDeGrupos) {
		case "2":
			return new String[] {"ID1","Nombre1","ID2","Nombre2"};
		case "3":
			return new String[] {"ID1","Nombre1","ID2","Nombre2","ID3","Nombre3"};
		case "4":
			return new String[] {"ID1","Nombre1","ID2","Nombre2","ID3","Nombre3","ID4","Nombre4"};
		case "5":
			return new String[] {"ID1","Nombre1","ID2","Nombre2","ID3","Nombre3","ID4","Nombre4","ID5","Nombre5"};
		}
		return null;
	}

	private void configuracionDelBoton()	{

		makeUpBotones(btnMostrarGraficoGrafo);
		makeUpBotones(btnMostrarMatrizAdyacencia);
		
		btnMostrarGraficoGrafo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnMostrarGraficoGrafo.isEnabled()){
					mostrarGraficoGrafo();
				}
			}
			
		});
		btnMostrarMatrizAdyacencia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnMostrarMatrizAdyacencia.isEnabled()) {
					if(scrollGrupos.isVisible()) {
						scrollGrupos.setVisible(false);
						scrollMatrizAdyacencia.setVisible(true);
						scrollPane.setVisible(true);
						revalidate();
						repaint();
					}
					else {
						scrollMatrizAdyacencia.setVisible(false);
						scrollPane.setVisible(false);
						scrollGrupos.setVisible(true);
						revalidate();
						repaint();

					}
				}
			}
		});
	}


	private void makeUpBotones(JButton boton) {
		boton.setContentAreaFilled(true);
		boton.setOpaque(true);
		boton.setBackground(new Color(9, 61, 39));
		boton.setPreferredSize(new Dimension(200,40));
		boton.setForeground(new Color(255, 255, 255));
	}
	
	public void mostrarGraficoGrafo() {
		int cantidadDeUsuarios = afinidadMusical.getCantidadDeUsuarios();
		String[][] matrizUsuarios= afinidadMusical.getGrafoMatrizString(cantidadDeUsuarios);
		GraficoGrafo graficoGrafo = new GraficoGrafo(matrizUsuarios);
		graficoGrafo.mostrar();
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
		scrollMatrizAdyacencia.setPreferredSize(new Dimension(565,0));
		
		
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
		configurarScrollGrupos();
		controlador.habilitarBotonGrafo();
		
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
	
    static class MultiHeaderRenderer extends JPanel implements TableCellRenderer {
        private static final long serialVersionUID = 1L;

		public MultiHeaderRenderer(String grupo, String subEncabezado) {
            setLayout(new GridLayout(2, 1));
            JLabel grupoLabel = new JLabel(grupo, JLabel.CENTER);
            JLabel subLabel = new JLabel(subEncabezado, JLabel.CENTER);
            grupoLabel.setFont(grupoLabel.getFont().deriveFont(Font.BOLD));
            add(grupoLabel);
            add(subLabel);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

	public void habilitarBotonGrafo() {
		btnMostrarGraficoGrafo.setEnabled(true);
		repaint();
		revalidate();
		
	}
}
