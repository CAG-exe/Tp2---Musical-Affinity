package Modelo;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;


public class AfinidadMusical {
	List<Usuario> usuariosJson;
	HashMap<String, Usuario> usuariosRegistrados = new HashMap<String, Usuario>();
	List<Usuario> usuariosSelecionadosParaElGrafo;
	static Grafo matrizRelacion;
	private int cantidadDeGrupos;
	
	public AfinidadMusical() {
        // La inicialización del grafo debe hacerse antes de usarlo
        matrizRelacion = new Grafo(16); // Tamaño inicial, podría ser dinámico
		guardarUsuariosDeLaBaseDeDatos();
		usuariosSelecionadosParaElGrafo = new ArrayList<Usuario>();
	}

	public void guardarUsuariosDeLaBaseDeDatos() {
			// traer el archivo json
			InputStream input = AfinidadMusical.class.getResourceAsStream("/BaseDeDatosDeUsuarios.json");

			if (input == null) {
				System.out.println("No se encontró el archivo /BaseDeDatosDeUsuarios.json");
				return;
			}
			// Convierte al imput en un reader para que java pueda leer el JSON
			InputStreamReader reader = new InputStreamReader(input);
			Gson gson = new Gson(); // librería Gson, que permite convertir entre JSON y objetos Java

			Type tipoLista = new TypeToken<List<Usuario>>() {
			}.getType(); // Define que el JSON representa una lista de Usuarios
			this.usuariosJson = gson.fromJson(reader, tipoLista); // Convierte el contenido del JSON en una lista de Usuarios.
			
		for (Usuario u : usuariosJson) { 
			usuariosRegistrados.put(u.getNombre(), u);
			matrizRelacion.agregarUsuario(u);
		}
		
	}

	public void generarMatrizDeRelacionDeUsuarios() {
		matrizRelacion = new Grafo(16); // Genera y guarda la matriz que contendra la relaciones de
														// intereses entre usuarios
	}
	
	// Guarda el usuario nuevo en el mapa de usuarios registrados. Si el nombre del usuario ya existe, lanza excepsion.
	public void registrarUsuario(String nombre, int tango, int folclore, int rock, int urbano) {
		if(usuariosRegistrados.containsKey(nombre)) {
			throw new IllegalArgumentException("El nombre de usuario ya esta registrado, prueba con otro.");
		}
		Usuario nuevoUsuario = new Usuario(nombre,tango,folclore,rock,urbano);
		usuariosRegistrados.put(nombre, nuevoUsuario);
		matrizRelacion.agregarUsuario(nuevoUsuario);
	}

	public boolean usuarioYaRegistrado(String text) {
		return usuariosRegistrados.containsKey(text);
	}

	public static Map<Integer,Usuario> getUsuarios() {
		return matrizRelacion.getUsuarios();
	}
    
    public Grafo getGrafo() { // Getter para el grafo
        return matrizRelacion;
    }

	public String[][] getGrafoMatrizString(int cantidadDeUsuarios) {
		return matrizRelacion.matrizString(cantidadDeUsuarios, cantidadDeGrupos);
	}

	public int getCantidadDeUsuarios() {
		return usuariosRegistrados.size();
	}

	public String[][] getListaUsuariosMatrizString() {
		String[][] listaUsuarios = new String[getCantidadDeUsuarios()][2];
		for (int i = 0; i < getCantidadDeUsuarios(); i++) {
			listaUsuarios[i][0] = Integer.toString(i);
			listaUsuarios[i][1] = matrizRelacion.getUsuarios().get(i).getNombre();
		}
		return listaUsuarios;
	}
	
	public void setCantidadDeGrupos(int cant) {
		cantidadDeGrupos = cant;
	}

	public int[][] getLimitadaMatrizDeUsuarios() {
		return matrizRelacion.getLimitadaMatrizDeUsuarios(getCantidadDeUsuarios());
	}
    
    // --- NUEVO MÉTODO ---
    public Collection<EstadisticasGrupo> calcularEstadisticas(int cantGrupos) {
        if (matrizRelacion != null) {
            return matrizRelacion.calcularEstadisticasPorGrupo(cantGrupos);
        }
        return new ArrayList<>(); // Devuelve lista vacía si el grafo no existe
    }
}