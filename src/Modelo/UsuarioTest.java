package Modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	Usuario usuario;

	@Before
	public void inicializar() {
		usuario = new Usuario("Juan Pérez", 3, 5, 4, 1);
	}

	@Test
	public void crearUsuarioTest() {
		usuario = new Usuario("Hernesto", 3, 5, 4, 1);

		assertEquals(usuario.getNombre(), "Hernesto");
		assertEquals(usuario.getInteresTango(), 3);
		assertEquals(usuario.getInteresFolclore(), 5);
		assertEquals(usuario.getInteresRockNacional(), 4);
		assertEquals(usuario.getInteresGeneroUrbano(), 1);
	}

	@Test(expected = RuntimeException.class)
	public void crearUsuarioNombreInvalidoTest() {
		usuario = new Usuario("Ju", 1, 2, 3, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void interesTangoFueraDeRangoTest() {
		usuario = new Usuario("Juan Pérez", 6, 2, 3, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void interesFolcloreFueraDeRangoTest() {
		usuario = new Usuario("Juan Pérez", 1, -1, 3, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void interesRockNacionalFueraDeRangoTest() {
		usuario = new Usuario("Juan Pérez", 1, 1, -1, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void interesGeneroUrbanoFueraDeRangoTest() {
		usuario = new Usuario("Juan Pérez", 1, 2, 3, 0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void nombreVacioTest() 
	{
		usuario = new Usuario(" ",2, 1, 5, 2);
	}
	
	@Test
	public void pruebaToString(){
		Usuario u = new Usuario("Mario",1,1,1,1);
		String textoObjetivo = new String("Persona [nombre=Mario]");
		assertEquals(textoObjetivo,u.toString());
	}
	
	@Test
	public void interesesIguales() {
		Usuario u = new Usuario("Mario",1,1,1,1);
		Usuario u1 = new Usuario("Mario",1,1,1,1);
		int interes = u.compararIntereses(u1);
		
		assertEquals(0,interes);
	}
	
	@Test
	public void interesesMuyDistintos() {
		Usuario u = new Usuario("Mario",1,1,1,1);
		Usuario u1 = new Usuario("Mario",5,5,5,5);
		
		int interes = u.compararIntereses(u1);
		assertEquals(16,interes);
	}
}
