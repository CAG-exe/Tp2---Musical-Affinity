package Modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatrizTest {

	@Test
	public void crearMatrizTest() {
		Matriz matriz = new Matriz(5);
	}
	
	@Test
	(expected = IllegalArgumentException.class)
	public void crearMatrizFueraDeRangoTest() {
		Matriz matriz = new Matriz(-1);
	}
	
	@Test
	(expected = IllegalArgumentException.class)
	public void crearMatrizTamaño0Test() {
		Matriz matriz = new Matriz(0);
	}
	
	@Test
	public void setValorTest() {
		Matriz matriz = new Matriz(5);
		
		matriz.setValor(0, 0, 2);
		
		assertEquals(matriz.matriz[0][0],2);
	}
	
	@Test
	(expected = IllegalArgumentException.class)
	public void setValorFueraDeRangoTest() {
		Matriz matriz = new Matriz(5);
		
		matriz.setValor(0, 0, 7);
	}
	
	@Test
	(expected = IllegalArgumentException.class)
	public void setValorFueraDeRangoBajoTest() {
		Matriz matriz = new Matriz(5);
		
		matriz.setValor(0, 0, -1);
	}
	
	@Test
	(expected = IllegalArgumentException.class)
	public void setValorFilaFueraDeRangoTest() {
		Matriz matriz = new Matriz(5);
		
		matriz.setValor(-1, 0, 2);
		
		assertEquals(matriz.matriz[-1][0],2);
	}
	
	@Test
	(expected = IllegalArgumentException.class)
	public void setValorColumnaFueraDeRangoTest() {
		Matriz matriz = new Matriz(5);
		
		matriz.setValor(0, -1, 2);
		
		assertEquals(matriz.matriz[0][-1],2);
	}
	
	@Test
	public void getValorTest() {
		Matriz matriz = new Matriz(5);
		
		matriz.setValor(0, 0, 2);
		
		assertEquals(matriz.getValor(0, 0),2);
	}
	
	@Test
	public void getValorBordeTest() {
		Matriz matriz = new Matriz(5);
		
		matriz.setValor(4, 4, 5);
		
		assertEquals(matriz.getValor(4, 4),5);
	}
	
	
	@Test
	(expected = IllegalArgumentException.class)
	public void getValorFilaFueraDeRangoTest() {
		Matriz matriz = new Matriz(5);
		
		matriz.setValor(-1, 0, 2);
		
		assertEquals(matriz.getValor(-1, 0),2);
	}
	
	@Test
	(expected = IllegalArgumentException.class)
	public void getValorColumnaFueraDeRangoTest() {
		Matriz matriz = new Matriz(5);
		
		matriz.setValor(0, -1, 2);
		
		assertEquals(matriz.getValor(0, -1),2);
	}
	
}
