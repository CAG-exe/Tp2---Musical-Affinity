package Modelo;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Grafo {

	private HashMap<Integer, Usuario> usuarios;
	
	protected int[][] matriz;
	private ArrayList<Arista> listaAristas;
	
	private int valorInvalido = -1;

	public Grafo(int tamanio) {
		if (tamanio <= 0) {
			throw new IllegalArgumentException("El tamaño de la matriz es invalido");
		}
		matriz = new int[tamanio][tamanio];
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int columna = 0; columna < matriz.length; columna++) {
				matriz[fila][columna] = valorInvalido;
			}
		}
		listaAristas = new ArrayList<Arista>();
		usuarios = new HashMap<Integer, Usuario>();
	}

	// guarda el valor ingresado en la fila-columna
	public void setValor(int fila, int columna, int valor) {
		if (valor > 5 || valor < 0) {
			throw new IllegalArgumentException("El valor debe estar entre 0 y 5");
		}

		try {
			matriz[fila][columna] = valor;
		} catch (Exception e) {
			throw new IllegalArgumentException("La fila y columna no son validos.");
		}
	}

	// devuelve el valor de la fila-columna
	public int getValor(int fila, int columna) {
		try {
			return matriz[fila][columna];
		} catch (Exception e) {
			throw new IllegalArgumentException("La fila y columna no son validos.");
		}
	}

	public boolean tieneValorInvalido(int i, int c) {
		return matriz[i][c] == valorInvalido;
	}

	//
	//
	//
	public void agregarUsuario(Usuario u) {
		usuarios.put(usuarios.size(), u);
		agregarUsuarioAGrafo(usuarios.size()-1);
	}

	private void agregarUsuarioAGrafo(int PosicionDelUsuario) {
		for (int indiceU = 0; indiceU < usuarios.size() - 1; indiceU++) {
			int indiceS = usuarios.get(indiceU).compararIntereses(usuarios.get(PosicionDelUsuario));
			agregarArista(indiceU, PosicionDelUsuario, indiceS);
		}
	}

	// Agregado de aristas
	public void agregarArista(int i, int j, int indiceDeSimilitud) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		matriz[i][j] = indiceDeSimilitud;
		matriz[j][i] = indiceDeSimilitud;
		listaAristas.add(new Arista(i, j, indiceDeSimilitud));
	}

	// Informa si existe la arista especificada
	public boolean existeArista(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		return matriz[i][j] != valorInvalido;
	}

	private void verificarVertice(int i) {
		if (i < 0)
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);

		if (i >= matriz.length)
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j) {
		if (i == j)
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}
	
	public ArrayList<Arista> Kruskal() {
		ArrayList<Arista> resultado = new ArrayList<Arista>();
		List<Arista> aristasOrdenadas = listaAristas.stream()
				.sorted(Comparator.comparing(Arista::getPeso))
				.collect(Collectors.toList());
		
		int e = 0; // Index variable for result[]
        int i = 0; // Index variable for sorted edges
        
		aristasOrdenadas = (ArrayList<Arista>) aristasOrdenadas;
		UnionFind uf = new UnionFind(usuarios.size());
		while(e<usuarios.size()-1) {
			Arista arista = aristasOrdenadas.get(i++);
			
			int V1 = uf.find(arista.getOrigen());
			int V2 = uf.find(arista.getDestino());
			
			if(V1 != V2) {
				e++;
				resultado.add(arista);
				uf.Union(arista.getOrigen(), arista.getDestino());
			}
		}
		return resultado;
		
	}
	
	public void eliminarAristaMayorPeso() {
		Arista mayorPeso = listaAristas.stream().
				max(Comparator.comparing(Arista::getPeso))
				.orElse(null);
		if(mayorPeso != null) {
			matriz[mayorPeso.getOrigen()][mayorPeso.getDestino()] = valorInvalido;
			matriz[mayorPeso.getDestino()][mayorPeso.getOrigen()] = valorInvalido;
			listaAristas.remove(mayorPeso);
		}
	}
	
	public List<Arista> eliminarAristaMayorPeso(List<Arista> listaDeAristas, int cantGrupos) {
		if(cantGrupos > listaDeAristas.size()) {
			System.out.println("LA CANTIDAD DE GRUPOS CONEXOS SUPERAN LA CANTIDAD DE USUARIOS. SE ESTABLECERÁ GRUPOS = 2.");
			cantGrupos = 2;
		}
		
		for(int i = 1; i<cantGrupos; i++) {
			Arista mayorPeso = listaDeAristas.stream().
					max(Comparator.comparing(Arista::getPeso))
					.orElse(null);
			if(mayorPeso != null) {
				listaDeAristas.remove(mayorPeso);
			}
		}
		return listaDeAristas;
	}
	
	public void eliminarArista( int i, int j ) 
	{
		verificarVertice( i );
		verificarVertice( j );
		verificarDistintos( i, j );

		matriz[ i ][ j ] = -1;  
		matriz[ j ][ i ] = -1;
	}
	
	private void redimensionMatriz() {
		int[][] nuevaMatriz = new int[matriz.length*2][matriz.length*2];
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int columna = 0; columna < matriz.length; columna++) {
				nuevaMatriz[fila][columna] = matriz[fila][columna];
			}
		}
		matriz = nuevaMatriz;
	}

	public Map<Integer,Usuario> getUsuarios() {
		return usuarios;
	}
	
	public String[][] matrizString(int cantidadDeUsuarios, int cantGrupos){
		String[][] matrizString = new String[cantidadDeUsuarios][cantidadDeUsuarios];
		for (int fila = 0; fila < matrizString.length; fila++) {
			for (int columna = 0; columna < matrizString.length; columna++) {
				matrizString[fila][columna] = "∞";
			}
		}
		
		ArrayList<Arista> listaAriastaKruskal = Kruskal();
		listaAriastaKruskal = (ArrayList<Arista>) eliminarAristaMayorPeso(listaAriastaKruskal, cantGrupos);
		for(Arista arista: listaAriastaKruskal) {
			int i = arista.getOrigen();
			int j = arista.getDestino();
			matrizString[i][j] = Integer.toString(arista.getPeso());
			matrizString[j][i] = Integer.toString(arista.getPeso());
			}
		return matrizString;
		
	}

	public int[][] getLimitadaMatrizDeUsuarios(int cantidadDeUsuarios) {
		int[][] limitadaMatriz = new int[cantidadDeUsuarios][cantidadDeUsuarios];
		for(int fila=0;fila<cantidadDeUsuarios;fila++) {
			for(int columna=0;columna<cantidadDeUsuarios;columna++) {
				limitadaMatriz[fila][columna] = matriz[fila][columna];
			}
		}
		return limitadaMatriz;
	}
}
