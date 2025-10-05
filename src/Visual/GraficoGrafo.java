package Visual;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class GraficoGrafo {

	private Graph grafo;


	public GraficoGrafo(int[][] matriz) {

		generarGrafo(matriz);
        grafo.display();
		
	}

	private void generarGrafo(int[][] matriz) {
		System.setProperty("org.graphstream.ui", "swing");
        Graph Nuevo_grafo = new SingleGraph("Grafo desde matriz");
        
        // Crear nodos
        for (int i = 0; i < matriz.length; i++) {
            Node node = Nuevo_grafo.addNode(String.valueOf(i));
            node.setAttribute("ui.label", String.valueOf(i));
        }

        // Crear aristas
        for (int i = 0; i < matriz.length; i++) {
            for (int j = i + 1; j < matriz[i].length; j++) {
                if (matriz[i][j] > -1) {
                    String edgeId = i + "-" + j;
                    Nuevo_grafo.addEdge(edgeId, String.valueOf(i), String.valueOf(j));
                }
            }
        }
        
        // Estilo visual
        Nuevo_grafo.setAttribute("ui.stylesheet", 
            "node { fill-color: #ED2C07; size: 30px; text-size: 14px; }" +
            "edge { fill-color: #6D239E; }"
        );
        Nuevo_grafo.setAttribute("ui.quality");
        Nuevo_grafo.setAttribute("ui.antialias");

        this.grafo = Nuevo_grafo;
	}

}
