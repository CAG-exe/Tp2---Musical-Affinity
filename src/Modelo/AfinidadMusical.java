package Modelo;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AfinidadMusical {
	List<Usuario> usuarios;
	Matriz matrizRelacion;
	
	public void guardarUsuariosDeLaBaseDeDatos() {
		//traer el archivo json
		InputStream input = AfinidadMusical.class.getResourceAsStream("BaseDeDatosDeUsuarios.json");
		
		if (input == null) {
			System.out.println("No se encontró el archivo");
			return;
		}
		//Convierte al imput en un reader para que java pueda leer el JSON
		InputStreamReader reader = new InputStreamReader(input);
		Gson gson = new Gson(); //librería Gson, que permite convertir entre JSON y objetos Java
		
		Type tipoLista = new TypeToken<List<Usuario>>(){}.getType(); //Define que el JSON representa una lista de Usuarios
		this.usuarios = gson.fromJson(reader, tipoLista); //Convierte el contenido del JSON en una lista de Usuarios.
    }
	
	public void generarMatrizDeRelacionDeUsuarios() {
		matrizRelacion = new Matriz(usuarios.size()); //Genera y guarda la matriz que contendra la relaciones de intereses entre usuarios
	}
	
	
	public void guardarEnMatrizLaAfinidadEntreTodosLosUsuarios() {
		ArrayList<Usuario> usuariosArray = (ArrayList<Usuario>) usuarios;
		
		//recorrer los diferentes usuarios sin repetir pares
		for(int i = 0; i < usuariosArray.size(); i++) {
			for(int c = i+1; c<usuariosArray.size(); c++) {
				
				//verifica que la afinidad entre los usuarios no tenga un valor definido
			
				if(matrizRelacion.tieneValorInvalido(i,c)) { 
					
					//calcula la afinidad entre un par de usuarios
					int afinidadValor =  calcularAfinidadEntreUsuarios(usuariosArray.get(i), usuariosArray.get(c)); 
					
					//Guarda el valor de la afinidad en la matriz
					matrizRelacion.setValor(i, c, afinidadValor);
					matrizRelacion.setValor(c, i, afinidadValor); //para la simetria
				}
				
			}
		}
	}
	
	
	//Hace el calculo del algoritmo para saber que tan parecidos son los intereses entre usuarios
	public int calcularAfinidadEntreUsuarios(Usuario primerUsuario, Usuario segundoUsuario) {
		int interesTango = primerUsuario.compararInteresTango(segundoUsuario);
		int interesFolclore = primerUsuario.compararInteresFolclore(segundoUsuario);
		int interesRockNacional = primerUsuario.compararInteresRock(segundoUsuario);
		int interesGeneroUrbano = primerUsuario.compararInteresUrbano(segundoUsuario);
		
		return interesTango + interesFolclore + interesRockNacional + interesGeneroUrbano;
	}
}
