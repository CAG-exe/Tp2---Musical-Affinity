package Modelo;

import static org.junit.Assert.*;
import org.junit.Test;

import net.miginfocom.layout.AC;

public class grafoTest {
	@Test ( expected = IllegalArgumentException.class )
	public void verticeNegativoTest() 
	{
		Grafo grafo = new Grafo( 5 );
		grafo.agregarArista( -1, 3, 5 ); 
	}
	@Test(expected = IllegalArgumentException.class)
	public void crearMatrizFueraDeRangoTest() {
		@SuppressWarnings("unused")
		Grafo matriz = new Grafo(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void crearMatrizTamaño0Test() {
		@SuppressWarnings("unused")
		Grafo matriz = new Grafo(0);
	}	
	
	@Test
	public void aristaExistenteTest() {
		Grafo grafo = new Grafo( 5 );
		grafo.agregarArista( 2, 3, 5 );
		assertTrue( grafo.existeArista( 2, 3 ) );
	}
	
	
	@Test
	public void aristaOpuestaTest() {
		Grafo grafo = new Grafo( 5 );
		grafo.agregarArista( 2, 3, 5 );
		assertTrue( grafo.existeArista( 3, 2) );
	}
	
	@Test
	public void aristaInexistenteTest() {
		Grafo grafo = new Grafo( 5 );
		grafo.agregarArista( 2, 3, 5 );
		assertFalse( grafo.existeArista( 1, 4 ) );
	}
	
	
	@Test
	public void AgregarAristaDosVecesTest() {
		Grafo grafo = new Grafo( 5 );
		grafo.agregarArista(2, 4, 5);
		grafo.agregarArista(2, 4, 5);
		assertTrue(grafo.existeArista(2, 4));
	}
	@Test
	public void UsuarioTest() {
	    Grafo grafo = new Grafo(5);
	    Usuario u1 = new Usuario("Ana",1,2,3,4);
	    Usuario u2 = new Usuario("Luis",2,3,4,5);
	    grafo.agregarUsuario(u1);
	    grafo.agregarUsuario(u2);
	    grafo.removerUsuario(u1);
	    assertFalse(grafo.existeUsuario(u1));
	    assertTrue(grafo.existeUsuario(u2));
	}
	@Test
	public void ExisteUsuario() {
	    Grafo grafo = new Grafo(5);
	    Usuario u1 = new Usuario("Ana",1,2,3,4);
	    Usuario u2 = new Usuario("Fede",1,2,3,4);
	    grafo.agregarUsuario(u1);
	    assertTrue(grafo.existeUsuario(u1));
	    assertFalse(grafo.existeUsuario(u2));
	}
	
	@Test
	public void MatrizStringTest() {
		int cantUsuarios = 3;
		int cantGrupos = 2;
		Grafo grafo = new Grafo(5);
		Usuario u1 = new Usuario("Ana",1,2,3,4);
	    Usuario u2 = new Usuario("Luis",2,3,4,5);
	    Usuario u3 = new Usuario("Juan",2,3,4,5);
	    grafo.agregarUsuario(u1);
	    grafo.agregarUsuario(u2);
	    grafo.agregarUsuario(u3);
	    
	    String[][] resultado = grafo.matrizString(cantUsuarios, cantGrupos);
	    String[][] esperado = {
	            {"∞", "∞", "∞"},
	            {"∞", "∞", "0"},
	            {"∞", "0", "∞"}
	        };
	    
	    assertArrayEquals(esperado, resultado);
	    	
	    
	}
	
	
	@Test
	public void MatrizStringSinUsuariosTest() {
		int cantidadDeUsuarios = 3;
        int cantGrupos = 2;
        
        Grafo grafo = new Grafo(3);
		
        String[][] resultado = grafo.matrizString(cantidadDeUsuarios, cantGrupos);
        String[][] esperado = {
            {"∞", "∞", "∞"},
            {"∞", "∞", "∞"},
            {"∞", "∞", "∞"}
        };
        
        assertArrayEquals(esperado, resultado);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void MatrizStringCantGruposNegaticaTest() {
		int cantidadDeUsuarios = 3;
        int cantGrupos = -2;
        
        Grafo grafo = new Grafo(3);
		
        String[][] resultado = grafo.matrizString(cantidadDeUsuarios, cantGrupos);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void MatrizStringCantUsuariosNegaticaTest() {
		int cantidadDeUsuarios = -3;
        int cantGrupos = 2;
        
        Grafo grafo = new Grafo(3);
		
        String[][] resultado = grafo.matrizString(cantidadDeUsuarios, cantGrupos);
	}
}
