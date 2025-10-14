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
		uf.Union(1, -2);
		uf.Union(-1, -2);
	}
}
