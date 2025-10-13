package Visual;

import Modelo.EstadisticasGrupo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Scrollable;

public class PanelUsuarios extends JPanel implements Scrollable {

    private static final long serialVersionUID = 1L;
    private List<EstadisticasGrupo> estadisticas;
    
    // --- CONSTANTES AJUSTADAS PARA UN CÁLCULO PRECISO ---

    private final int MARGEN_SUPERIOR = 50;
    
    /**
     * Esta es una constante importante, determina la altura de cada grupo
     * Se realiza un cálculo que separa con un margen los distintos tipos de grupos.
     * * El cálculo se basa en el método dibujarEstadisticasDeUnGrupo:
     * 35 (título) + 25 (miembros) + 40 (afinidad) + 30 (título gráfico) + 
     * 150 (alto del gráfico) + 40 (margen post-gráfico) = 320px (altura del bloque)
     * * Este margen se aplica en el bucle de paintComponent:
     * 60px (margen entre grupos)
     * * Total = 320 + 60 = 380px.
     */
    private final int ALTURA_TOTAL_POR_GRUPO = 380; 

    public PanelUsuarios() {
        setBackground(new Color(220, 225, 195));
        this.estadisticas = new ArrayList<>();
    }
    
    public void mostrarEstadisticas(Collection<EstadisticasGrupo> stats) {
        this.estadisticas = new ArrayList<>(stats);
        this.estadisticas.sort(Comparator.comparingInt(EstadisticasGrupo::getIdGrupo));
        
        // Avisa al JScrollPane que el tamaño del contenido ha cambiado
        this.revalidate();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (estadisticas == null || estadisticas.isEmpty()) {
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Tahoma", Font.BOLD, 18));
            g.drawString("No hay estadísticas para mostrar. Genera un grafo primero.", 50, 100);
            return;
        }

        int y_posicion = MARGEN_SUPERIOR;
        for (EstadisticasGrupo grupo : estadisticas) {
            y_posicion = dibujarEstadisticasDeUnGrupo(g, grupo, y_posicion);
            y_posicion += 60; // Margen entre este grupo y el siguiente
        }
    }
    
    private int dibujarEstadisticasDeUnGrupo(Graphics g, EstadisticasGrupo grupo, int y_actual) {
        // (Este método no necesita cambios, está perfecto)
        g.setColor(new Color(0, 80, 85));
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString("Análisis del Grupo " + (grupo.getIdGrupo() + 1), 50, y_actual);
        y_actual += 35;

        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.PLAIN, 16));
        g.drawString("• Cantidad de Miembros: " + grupo.getCantidadMiembros(), 60, y_actual);
        y_actual += 25;
        g.drawString(String.format("• Afinidad Promedio (peso interno): %.2f", grupo.getAfinidadPromedio()), 60, y_actual);
        y_actual += 40;

        g.setFont(new Font("Tahoma", Font.BOLD, 16));
        g.drawString("Promedio de Intereses Musicales del Grupo:", 60, y_actual);
        y_actual += 30;

        double[] valores = {
            grupo.getPromedioTango(),
            grupo.getPromedioFolclore(),
            grupo.getPromedioRock(),
            grupo.getPromedioUrbano()
        };
        String[] etiquetas = {"Tango", "Folclore", "Rock", "Urbano"};
        Color[] colores = {new Color(200, 50, 50), new Color(50, 150, 50), new Color(50, 50, 200), new Color(150, 50, 150)};

        int anchoBarra = 60;
        int espacio = 40;
        int x_barra = 100;
        int alturaMaxGrafico = 150;
        double valorMaxInteres = 5.0;

        g.setColor(Color.GRAY);
        g.drawLine(x_barra - 10, y_actual, x_barra - 10, y_actual + alturaMaxGrafico + 5);
        g.drawLine(x_barra - 10, y_actual + alturaMaxGrafico + 5, x_barra + (anchoBarra + espacio) * 4, y_actual + alturaMaxGrafico + 5);

        for (int i = 0; i < valores.length; i++) {
            int alturaBarra = (int) ((valores[i] / valorMaxInteres) * alturaMaxGrafico);
            int y_barra = y_actual + alturaMaxGrafico - alturaBarra;

            g.setColor(colores[i]);
            g.fillRect(x_barra, y_barra, anchoBarra, alturaBarra);
            g.setColor(Color.BLACK);
            g.drawRect(x_barra, y_barra, anchoBarra, alturaBarra);

            g.setFont(new Font("Tahoma", Font.PLAIN, 14));
            g.drawString(etiquetas[i], x_barra + 5, y_actual + alturaMaxGrafico + 25);
            g.drawString(String.format("%.1f", valores[i]), x_barra + 15, y_barra - 10);
            x_barra += anchoBarra + espacio;
        }
        return y_actual + alturaMaxGrafico + 40;
    }

    // --- MÉTODOS DE LA INTERFAZ SCROLLABLE ---

    @Override
    public Dimension getPreferredSize() {
        int alturaPreferida = MARGEN_SUPERIOR;
        if (estadisticas != null && !estadisticas.isEmpty()) {
            // Se usa la nueva constante para un cálculo exacto.
            alturaPreferida = (estadisticas.size() * ALTURA_TOTAL_POR_GRUPO) + MARGEN_SUPERIOR;
        }
        // Usamos 800 como un ancho predeterminado razonable
        return new Dimension(800, alturaPreferida);
    }
    
    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 20; // Scroll lento (flechas del teclado)
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 100; // Scroll rápido (clic en la barra)
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true; // No queremos scroll horizontal
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false; // Sí queremos scroll vertical
    }
}