package Modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class AristaTest {
	
	 @Test
	 public void testConstructor() {
		 int origen = 1;
		 int destino = 2;
		 int peso = 2;
		 Arista arista = new Arista(origen, destino, peso);
	 }
	 
	 @Test
	 public void testGetterOrigen() {
		 int origen = 2;
		 
		 Arista arista = new Arista(origen, 0, 0);
		 
		 assertEquals(origen, arista.getOrigen());
	 }
	 
	 @Test
	 public void testGetterDestino() {
		 int destino = 2;
		 Arista arista = new Arista(0, destino, 0);
		 
		 assertEquals(destino, arista.getDestino());
	 }

	 
	 @Test
	 public void testGetterPeso() {
		 int peso = 10;
		 
		 Arista arista = new Arista(0, 0, peso);
		 
		 assertEquals(peso, arista.getPeso());
	 }
	 
	 @Test
	  public void testToString() {
		 Arista arista = new Arista(5, 1, 5);
	     
		 String resultado = arista.toString();
		 String esperado = "[origen=5,destino=1,peso=5]";
	
		 assertEquals(esperado, resultado);
	 }
}
