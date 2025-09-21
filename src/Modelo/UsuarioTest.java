package Modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	Usuario usuarios;

	@Before
	public void inicializar() {
		usuarios = new Usuario("Juan Pérez", 3, 5, 4, 1);
	}
	@Test 
	(expected = RuntimeException.class)
	public void agregarNombreInvalidoTest() {
		usuarios.agregarUsuario("Ju", 1, 2, 3, 3);
	}
	@Test 
	(expected = IllegalArgumentException.class)
	public void interesTangoFueraDeRangoTest() {
		usuarios.agregarUsuario("Juan Pérez", 6, 2, 3, 3);
	}
	@Test 
	(expected = IllegalArgumentException.class)
	public void interesFolcloreFueraDeRangoTest() {
		usuarios.agregarUsuario("Juan Pérez", 1, -1, 3, 3);
	}
	@Test 
	(expected = IllegalArgumentException.class)
	public void interesRockNacionalFueraDeRangoTest() {
		usuarios.agregarUsuario("Juan Pérez", 1, 1, -1, 3);
	}
	@Test 
	(expected = IllegalArgumentException.class)
	public void interesGeneroUrbanoFueraDeRangoTest() {
		usuarios.agregarUsuario("Juan Pérez", 1, 2, 3, 0);
	}
}
