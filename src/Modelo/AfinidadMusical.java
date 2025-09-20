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
	Matriz matrizDeRelacion;
	
	public void guardarUsuariosDeLaBaseDeDatos() {
		InputStream input = AfinidadMusical.class.getResourceAsStream("BaseDeDatosDeUsuarios.json");
		if (input == null) {
			System.out.println("No se encontró el archivo");
			return;
		}
       
		InputStreamReader reader = new InputStreamReader(input);
		Gson gson = new Gson();
		
		Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();
		this.usuarios = gson.fromJson(reader, tipoLista);
    }
	
	public void generarMatrizDeRelacionDeUsuarios() {
		
	}
}
