package Modelo;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
		rellenarMatrizConValorInvalido(matriz);
		listaAristas = new ArrayList<Arista>();
		usuarios = new HashMap<Integer, Usuario>();
	}

	private void rellenarMatrizConValorInvalido(int[][] mat) {
		for (int fila = 0; fila < mat.length; fila++) {
			for (int columna = 0; columna < mat.length; columna++) {
				mat[fila][columna] = valorInvalido;
			}
		}
	}

	public boolean tieneValorInvalido(int i, int c) {
		return matriz[i][c] == valorInvalido;
	}
	
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
	
	// Métodos de búsqueda y verificación

	public int indiceDeUsuario(Usuario u) {
	    if (u == null) return -1;
	    for (Map.Entry<Integer, Usuario> e : usuarios.entrySet()) {
	        Usuario val = e.getValue();
	        if (val != null && val.equals(u)) { // usa equals() de Usuario
	            return e.getKey();
	        }
	    }
	    return -1;
	}

	public boolean existeUsuario(Usuario u) {
	    return indiceDeUsuario(u) != -1;
	}

	public boolean existeUsuarioPorNombre(String nombre) {
	    if (nombre == null) return false;
	    for (Usuario val : usuarios.values()) {
	        if (val != null && nombre.equalsIgnoreCase(val.getNombre())) return true;
	    }
	    return false;
	}

	public int[] componentesConexos() {
	    int n = usuarios.size();
	    int[] comp = new int[n];
	    Arrays.fill(comp, -1);
	    int id = 0;
	    for (int i = 0; i < n; i++) {
	        if (comp[i] == -1) {
	            dfsComponentes(i, id, comp);
	            id++;
	        }
	    }
	    return comp;
	}

	private void dfsComponentes(int v, int id, int[] comp) {
	    comp[v] = id;
	    int n = usuarios.size();
	    for (int u = 0; u < n; u++) {
	        if (u != v && matriz[v][u] != valorInvalido && comp[u] == -1) {
	            dfsComponentes(u, id, comp);
	        }
	    }
	}

	public boolean pertenecenMismoGrupo(Usuario a, Usuario b) {
	    int ia = indiceDeUsuario(a);
	    int ib = indiceDeUsuario(b);
	    if (ia == -1 || ib == -1) return false;
	    int[] comp = componentesConexos();
	    return comp[ia] == comp[ib];
	}

	private int[] componentesDesdeMatriz(int[][] mat) {
	    int n = mat.length;
	    int[] comp = new int[n];
	    Arrays.fill(comp, -1);
	    int id = 0;
	    for (int i = 0; i < n; i++) {
	        if (comp[i] == -1) {
	            dfsComponentesMatriz(i, id, comp, mat);
	            id++;
	        }
	    }
	    return comp;
	}

	private void dfsComponentesMatriz(int v, int id, int[] comp, int[][] mat) {
	    comp[v] = id;
	    int n = mat.length;
	    for (int u = 0; u < n; u++) {
	        if (u != v && mat[v][u] != valorInvalido && comp[u] == -1) {
	            dfsComponentesMatriz(u, id, comp, mat);
	        }
	    }
	}


	private void verificarVertice(int i) {
		if (i < 0)
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);

		if (i >= matriz.length)
			redimensionMatriz();
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
		while(e<usuarios.size()-1 && i < aristasOrdenadas.size()) { 
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
	
	public List<Arista> eliminarAristaMayorPeso(List<Arista> listaDeAristas, int cantGrupos) {
		
		for(int i = 1; i < cantGrupos; i++) {
			if (listaDeAristas.isEmpty()) break; // Si no hay más aristas, paramos
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
	
	public void removerUsuario(Usuario usuario) {
		if (!existeUsuario(usuario)) {
			return;
		}

		List<Usuario> usuariosRestantes = crearListaDeUsuariosActualSinUnUsuario(usuario);
		
		recontruirGrafoSegunUnaListaDeUsuarios(usuariosRestantes);
	}

	private List<Usuario> crearListaDeUsuariosActualSinUnUsuario(Usuario usuario) {
		List<Usuario> usuariosRestantes = new ArrayList<>();
		for (Usuario usuarioActual : usuarios.values()) {
			if (!usuarioActual.equals(usuario)) {
				usuariosRestantes.add(usuarioActual);
			}
		}
		return usuariosRestantes;
	}
	
	private void recontruirGrafoSegunUnaListaDeUsuarios(List<Usuario> usuariosRestantes) {
		listaAristas = new ArrayList<Arista>();
		usuarios = new HashMap<Integer, Usuario>(); 
		for (Usuario usuarioRestante : usuariosRestantes) {
			agregarUsuario(usuarioRestante);
		}
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

	@SuppressWarnings("unchecked")
	public Map<Integer,Usuario> getUsuarios() {
		return (Map<Integer, Usuario>) usuarios.clone();
	}
	
	public String[][] matrizString(int cantidadDeUsuarios, int cantGrupos){
		String[][] matrizString = new String[cantidadDeUsuarios][cantidadDeUsuarios];
		for (int fila = 0; fila < matrizString.length; fila++) {
			for (int columna = 0; columna < matrizString.length; columna++) {
				matrizString[fila][columna] = "∞";
			}
		}
		
		ArrayList<Arista>listaAriastaKruskal = (ArrayList<Arista>) extraerGruposDeArbol(cantGrupos);
		rellenarMatrizConDatosDelArbol(matrizString, listaAriastaKruskal);
		return matrizString;
		
	}

	private void rellenarMatrizConDatosDelArbol(String[][] matrizString, ArrayList<Arista> listaAriastaKruskal) {
		for(Arista arista: listaAriastaKruskal) {
			int i = arista.getOrigen();
			int j = arista.getDestino();
			matrizString[i][j] = Integer.toString(arista.getPeso());
			matrizString[j][i] = Integer.toString(arista.getPeso());
			}
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
    
    // --- NUEVO MÉTODO ---
    public Collection<EstadisticasGrupo> calcularEstadisticasPorGrupo(int cantGrupos) {
        // 1. Generamos el grafo con la cantidad de grupos deseada
        ArrayList<Arista> aristasDelGrafoAgrupado = (ArrayList<Arista>)extraerGruposDeArbol(cantGrupos);

        int[] componentes = componentesConexasDeGrupos(cantGrupos);
 
        
        // 4. Usamos un Map para organizar las estadísticas por ID de grupo
        Map<Integer, EstadisticasGrupo> estadisticasMap = new HashMap<>();

        // 5. Agrupamos usuarios en sus respectivos grupos
        for (int i = 0; i < componentes.length; i++) {
            int idGrupo = componentes[i];
            Usuario usuario = usuarios.get(i);

            estadisticasMap.putIfAbsent(idGrupo, new EstadisticasGrupo(idGrupo));
            estadisticasMap.get(idGrupo).agregarMiembro(usuario);
        }

        // 6. Calculamos la afinidad promedio para cada grupo
        for (EstadisticasGrupo stats : estadisticasMap.values()) {
            double sumaPesos = 0;
            int cantidadAristasInternas = 0;
            List<Usuario> miembros = stats.getMiembros();
            
            // Recorremos las aristas del grafo agrupado
            for (Arista arista : aristasDelGrafoAgrupado) {
                // Verificamos si ambos extremos de la arista pertenecen a este grupo
                Usuario origen = usuarios.get(arista.getOrigen());
                Usuario destino = usuarios.get(arista.getDestino());
                if (miembros.contains(origen) && miembros.contains(destino)) {
                    sumaPesos += arista.getPeso();
                    cantidadAristasInternas++;
                }
            }
            
            if (cantidadAristasInternas > 0) {
                stats.setAfinidadPromedio(sumaPesos / cantidadAristasInternas);
            }
        }

        // 7. Calculamos los promedios de intereses para cada grupo
        for (EstadisticasGrupo stats : estadisticasMap.values()) {
            stats.calcularPromediosDeIntereses();
        }
        
        // 8. Devolvemos la colección de estadísticas
        return estadisticasMap.values();
    }

	private List<Arista> extraerGruposDeArbol(int cantGrupos) {
		ArrayList<Arista> aristasDelGrafoAgrupado = Kruskal();
        aristasDelGrafoAgrupado = (ArrayList<Arista>) eliminarAristaMayorPeso(aristasDelGrafoAgrupado, cantGrupos);
		return aristasDelGrafoAgrupado;
	}

	private int[] componentesConexasDeGrupos(int cantGrupos) {
		ArrayList<Arista> aristasDelGrafoAgrupado = (ArrayList<Arista>) extraerGruposDeArbol(cantGrupos);

        int n = usuarios.size();
        int[][] matrizTemporal = new int[n][n];
        rellenarMatrizConValorInvalido(matrizTemporal);
        for (Arista ar : aristasDelGrafoAgrupado) {
            matrizTemporal[ar.getOrigen()][ar.getDestino()] = ar.getPeso();
            matrizTemporal[ar.getDestino()][ar.getOrigen()] = ar.getPeso();
        }

        // 3. Obtenemos los componentes conexos de esta matriz temporal
        int[] componentes = componentesDesdeMatriz(matrizTemporal);
		return componentes;
	}
	
	public String[][] getListaDeGrupo(int cantGrupos) {
		int[] componentes = componentesConexasDeGrupos(cantGrupos);
	    Map<Integer, List<Integer>> grupos = new HashMap<>();

	    // Agrupar usuarios por componente
	    for (int i = 0; i < componentes.length; i++) {
	        grupos.putIfAbsent(componentes[i], new ArrayList<>());
	        grupos.get(componentes[i]).add(i);
	    }

	    // Calcular cantidad máxima de filas necesarias
	    int maxFilas = grupos.values().stream().mapToInt(List::size).max().orElse(0);
	    String[][] ListaDeGrupos = new String[maxFilas][cantGrupos * 2];

	    // Rellenar la matriz por grupo
	    for (Map.Entry<Integer, List<Integer>> entry : grupos.entrySet()) {
	        int grupo = entry.getKey();
	        int col = grupo * 2;
	        List<Integer> indices = entry.getValue();

	        for (int fila = 0; fila < indices.size(); fila++) {
	            int i = indices.get(fila);
	            ListaDeGrupos[fila][col] = Integer.toString(i);
	            ListaDeGrupos[fila][col + 1] = usuarios.get(i).getNombre();
	        }
	    }
		return ListaDeGrupos;
	}
}