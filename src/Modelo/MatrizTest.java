package Modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatrizTest {

	@Test
	public void crearMatrizTest() {
		Grafo matriz = new Grafo(5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void crearMatrizFueraDeRangoTest() {
		Grafo matriz = new Grafo(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void crearMatrizTamaño0Test() {
		Grafo matriz = new Grafo(0);
	}

	@Test
	public void setValorTest() {
		Grafo matriz = new Grafo(5);

		matriz.setValor(0, 0, 2);

		assertEquals(matriz.matriz[0][0], 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setValorFueraDeRangoTest() {
		Grafo matriz = new Grafo(5);

		matriz.setValor(0, 0, 7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setValorFueraDeRangoBajoTest() {
		Grafo matriz = new Grafo(5);

		matriz.setValor(0, 0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setValorFilaFueraDeRangoTest() {
		Grafo matriz = new Grafo(5);

		matriz.setValor(-1, 0, 2);

		assertEquals(matriz.matriz[-1][0], 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setValorColumnaFueraDeRangoTest() {
		Grafo matriz = new Grafo(5);

		matriz.setValor(0, -1, 2);

		assertEquals(matriz.matriz[0][-1], 2);
	}

	@Test
	public void getValorTest() {
		Grafo matriz = new Grafo(5);

		matriz.setValor(0, 0, 2);

		assertEquals(matriz.getValor(0, 0), 2);
	}

	@Test
	public void getValorBordeTest() {
		Grafo matriz = new Grafo(5);

		matriz.setValor(4, 4, 5);

		assertEquals(matriz.getValor(4, 4), 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getValorFilaFueraDeRangoTest() {
		Grafo matriz = new Grafo(5);

		matriz.setValor(-1, 0, 2);

		assertEquals(matriz.getValor(-1, 0), 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getValorColumnaFueraDeRangoTest() {
		Grafo matriz = new Grafo(5);

		matriz.setValor(0, -1, 2);

		assertEquals(matriz.getValor(0, -1), 2);
	}

}
