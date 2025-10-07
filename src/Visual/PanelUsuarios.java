package Visual;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Map;
import Modelo.Grafo;
import Modelo.Usuario;

public class PanelUsuarios extends JPanel {

    private static final long serialVersionUID = 1L;
    private Grafo grafo;

    public PanelUsuarios(Grafo grafo) {
        this.grafo = grafo;
        setBackground(new Color(220, 225, 195));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (grafo == null || grafo.getUsuarios().isEmpty()) {
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Tahoma", Font.BOLD, 18));
            g.drawString("No hay usuarios cargados.", 50, 100);
            return;
        }

        // Acumular intereses
        int totalUsuarios = grafo.getUsuarios().size();
        double sumaTango = 0, sumaFolclore = 0, sumaRock = 0, sumaUrbano = 0;

        for (Map.Entry<Integer, Usuario> entry : grafo.getUsuarios().entrySet()) {
            Usuario u = entry.getValue();
            if (u == null) continue;

            sumaTango += u.getInteresTango();
            sumaFolclore += u.getInteresFolclore();
            sumaRock += u.getInteresRockNacional();
            sumaUrbano += u.getInteresGeneroUrbano();
        }

        // Promedios
        double promTango = sumaTango / totalUsuarios;
        double promFolclore = sumaFolclore / totalUsuarios;
        double promRock = sumaRock / totalUsuarios;
        double promUrbano = sumaUrbano / totalUsuarios;

        double[] valores = {promTango, promFolclore, promRock, promUrbano};
        String[] etiquetas = {"Tango", "Folclore", "Rock", "Urbano"};
        Color[] colores = {Color.RED, new Color(255, 200, 0), Color.BLUE, Color.MAGENTA};

        int anchoBarra = 80;
        int espacio = 50;
        int x = 100;
        int baseY = getHeight() - 100;
        int alturaMax = 200; // escala visual
        double valorMax = 5.0; // máximo posible según la clase Usuario

        g.setFont(new Font("Tahoma", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString("Promedio de interés por género musical", 80, 50);

        // Ejes
        g.drawLine(80, baseY, getWidth() - 50, baseY);
        g.drawLine(80, baseY - alturaMax, 80, baseY);

        for (int i = 0; i < valores.length; i++) {
            int altura = (int) ((valores[i] / valorMax) * alturaMax);
            int y = baseY - altura;

            g.setColor(colores[i]);
            g.fillRect(x, y, anchoBarra, altura);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, anchoBarra, altura);

            g.drawString(etiquetas[i], x + 10, baseY + 20);
            g.drawString(String.format("%.1f", valores[i]), x + 25, y - 10);

            x += anchoBarra + espacio;
        }

        // Escala de referencia
        g.setFont(new Font("Tahoma", Font.PLAIN, 12));
        for (int i = 0; i <= 5; i++) {
            int y = baseY - (int) ((i / valorMax) * alturaMax);
            g.drawLine(75, y, 85, y);
            g.drawString(String.valueOf(i), 60, y + 5);
        }
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
        repaint();
    }
}
