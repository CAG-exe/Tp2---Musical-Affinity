package Modelo;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AfinidadMusical {
	   public static void main(String[] args) {
	       try (Reader reader = new FileReader("C:\\Users\\diego\\OneDrive\\Documentos\\GitHub\\Tp2---Musical-Affinity\\src\\Main\\BaseDeDatosDeUsuarios")) {
	            Gson gson = new Gson();

	            Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();
	            List<Usuario> usuarios = gson.fromJson(reader, tipoLista);
	            ArrayList<Usuario> usuario2 = (ArrayList<Usuario>) usuarios;
	            System.out.println(usuario2.get(0).interes_genero_urbano);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	

}
