package Modelo;

import static org.junit.Assert.*;
import org.junit.Test;

public class AfinidadMusicalTest {

	@Test
	public void registrarUsuarioTest() {
		AfinidadMusical model = new AfinidadMusical();
		
		model.registrarUsuario("Mario", 4, 3, 2, 1);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void registrarUsuarioRepetidoTest() {
		AfinidadMusical model = new AfinidadMusical();
		
		model.registrarUsuario("Mario", 4, 3, 2, 1);
		model.registrarUsuario("Mario", 1, 2, 2, 1);
	}
	
	
	@Test
	public void usuarioYaRegistradoTest() {
		AfinidadMusical model = new AfinidadMusical();
		
		model.registrarUsuario("Mario", 4, 3, 2, 1);
		
		assertTrue(model.usuarioYaRegistrado("Mario"));
	}
	
	
	@Test
	public void usuarioSinRegistrarTest() {
		AfinidadMusical model = new AfinidadMusical();
		
		model.registrarUsuario("Mario", 4, 3, 2, 1);
		
		assertFalse(model.usuarioYaRegistrado("Paul"));
	}
    @Test
    public void eliminarUsuarioExistenteTest() {
		AfinidadMusical model = new AfinidadMusical();

        model.eliminarUsuario("Juan Pérez");

        assertFalse(model.usuarioYaRegistrado("Juan Pérez"));
    }
    

}
