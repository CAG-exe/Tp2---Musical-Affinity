package Modelo;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AfinidadMusical {
	List<Usuario> usuariosJson;
	HashMap<String, Usuario> usuariosRegistrados = new HashMap<String, Usuario>();
	List<Usuario> usuariosSelecionadosParaElGrafo;
	static Grafo matrizRelacion;   /// modificar despues de la implementacion del main
	private int cantidadDeGrupos;
	
	public AfinidadMusical() {
		guardarUsuariosDeLaBaseDeDatos();
		generarMatrizDeRelacionDeUsuarios();
		usuariosSelecionadosParaElGrafo = new ArrayList<Usuario>();
	}

	public void guardarUsuariosDeLaBaseDeDatos() {
			// traer el archivo json
			InputStream input = AfinidadMusical.class.getResourceAsStream("/BaseDeDatosDeUsuarios.json");

			if (input == null) {
				System.out.println("No se encontró el archivo");
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

	public String[][] getGrafoMatrizString(int cantidadDeUsuarios) {
		return matrizRelacion.matrizString(cantidadDeUsuarios, cantidadDeGrupos);
	}

	public int getCantidadDeUsuarios() {
		return usuariosRegistrados.size();
	}
	public void eliminarUsuario(String nombre) {
	    Usuario usuarioAEliminar = usuariosRegistrados.get(nombre);

	    if (usuarioAEliminar != null) {
	        usuariosRegistrados.remove(nombre);
	        matrizRelacion.removerUsuario(usuarioAEliminar);
	    }
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

	public Grafo getGrafo() {
		return matrizRelacion;
	}
}
