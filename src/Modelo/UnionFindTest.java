package Modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UnionFindTest {
	private UnionFind uf;
	
	@Before
	public void testConstructor() {
		uf = new UnionFind(10);
	}
	
	
	@Test
	public void testEstadoInicial() {
		//cada elemento es la raíz de su propio conjunto.
		for(int i = 0; i<10; i++) {
			assertEquals(i, uf.find(i));
		}
	}
	
	@Test ( expected = IllegalArgumentException.class )
	public void testConstructorConVerticeNegativo() {
		uf = new UnionFind(-2);
	}
	
	@Test
	public void testConstructorConVerticesCero() {
		uf = new UnionFind(0);
	}
	
	
	@Test ( expected = IllegalArgumentException.class )
	public void testFindIndiceInvalido() {
		uf.find(10);
	}
	
	@Test ( expected = IllegalArgumentException.class )
	public void testFindIndiceNegativo() {
		uf.find(-1);
	}
	
	@Test
	public void testUnion() {
		uf.Union(1, 2);
		
		assertEquals(uf.find(1), uf.find(2));
	}
	
	@Test ( expected = IllegalArgumentException.class )
	public void testUnionConVerticesInvalidos() {
		uf.Union(-1, 2);
	}
	
	@Test ( expected = IllegalArgumentException.class )
	public void testUnionConVerticesBInvalidos() {
		uf.Union(1, -2);
	}
	
	@Test ( expected = IllegalArgumentException.class )
	public void testUnionConVerticesAmbosInvalidos() {

		uf.Union(-1, -2);
	}
	@Test
	public void testUnionDeElementosYaUnidos() {
	    UnionFind uf = new UnionFind(5);
	    uf.Union(0, 1);
	    boolean resultado = uf.Union(0, 1);

	    assertTrue(resultado);
	    assertEquals(uf.find(0), uf.find(1)); 
	}
	
	@Test
	public void testUnionPorRango() {
	    UnionFind uf = new UnionFind(5);
	    uf.getRango()[0] = 2;
	    uf.getRango()[1] = 1;

	    uf.Union(0, 1);

	    assertEquals(0, uf.find(1)); 
	}
	
	@Test
	public void testRangoSeActualiza() {
	    UnionFind uf = new UnionFind(5);
	    uf.Union(0, 1);
	    assertEquals(1, uf.getRango()[uf.find(0)]);
	}
	
	@Test
	public void testUnionPorRangoMenor() {
	    UnionFind uf = new UnionFind(5);

	    uf.getPadre()[0] = 0;
	    uf.getPadre()[1] = 1; 

	    uf.getRango()[0] = 1;
	    uf.getRango()[1] = 2;

	    uf.Union(0, 1);

	    assertEquals(1, uf.getPadre()[0]);
	    assertEquals(1, uf.find(0));
	    assertEquals(1, uf.find(1));
	}
}
