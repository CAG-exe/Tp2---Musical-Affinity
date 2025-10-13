package Modelo;

import static org.junit.Assert.*;
import org.junit.Test;

public class grafoTest {
	@Test ( expected = IllegalArgumentException.class )
	public void verticeNegativoTest() 
	{
		Grafo grafo = new Grafo( 5 );
		grafo.agregarArista( -1, 3, 5 ); 
	}
	@Test(expected = IllegalArgumentException.class)
	public void crearMatrizFueraDeRangoTest() {
		Grafo matriz = new Grafo(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void crearMatrizTamaño0Test() {
		Grafo matriz = new Grafo(0);
	}

	
	@Test ( expected = IllegalArgumentException.class )
	public void primerVerticeExcedidoTest() 
	{
		Grafo grafo = new Grafo( 5 );
		grafo.agregarArista( 5, 2, 5 );
	}
	
	@Test ( expected = IllegalArgumentException.class )
	public void segundoVerticeExcedidoTest() 
	{
		Grafo grafo = new Grafo( 5 );
		grafo.agregarArista( 2, 5, 5 );
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
	public void EliminarAristaExistenteTest() {
		Grafo grafo = new Grafo( 5 );
		grafo.agregarArista( 2, 4, 5 );
		grafo.eliminarArista( 2, 4 );
		assertFalse( grafo.existeArista( 2, 4 ) );
	}
	
	@Test
	public void EliminarAristaInexistenteTest() {
		Grafo grafo = new Grafo( 5 );
		grafo.eliminarArista( 2, 4 );
		assertFalse( grafo.existeArista( 2, 4 ) );
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
	public void ExisteUsuarioPorNombreTest() {
	    Grafo grafo = new Grafo(5);
	    Usuario u1 = new Usuario("Ana",1,2,3,4);
	    grafo.agregarUsuario(u1);
	    assertTrue(grafo.existeUsuarioPorNombre("ana"));
	    assertFalse(grafo.existeUsuarioPorNombre("Pedro"));
	}
}
