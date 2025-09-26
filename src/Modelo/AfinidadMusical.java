package Modelo;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AfinidadMusical {
	List<Usuario> usuariosJson;
	HashMap<String, Usuario> usuariosRegistrados = new HashMap<String, Usuario>();
	List<Usuario> usuariosSelecionadosParaElGrafo;
	Grafo matrizRelacion;

	public void guardarUsuariosDeLaBaseDeDatos() {
		// traer el archivo json
		InputStream input = AfinidadMusical.class.getResourceAsStream("BaseDeDatosDeUsuarios.json");

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
	}

	public void generarMatrizDeRelacionDeUsuarios() {
		matrizRelacion = new Grafo(usuariosJson.size()); // Genera y guarda la matriz que contendra la relaciones de
														// intereses entre usuarios
	}
	
	// Guarda el usuario nuevo en el mapa de usuarios registrados. Si el nombre del usuario ya existe, lanza excepsion.
	public void registrarUsuario(String nombre, int tango, int folclore, int rock, int urbano) {
		if(usuariosRegistrados.containsKey(nombre)) {
			throw new IllegalArgumentException("El nombre de usuario ya esta registrado, prueba con otro.");
		}
		Usuario nuevoUsuario = new Usuario(nombre,tango,folclore,rock,urbano);
		usuariosRegistrados.put(nombre, nuevoUsuario);
	}
}
