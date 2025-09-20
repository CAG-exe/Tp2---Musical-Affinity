package Modelo;

public class Matriz {
	int[][] matriz;
	int valorInvalido = -1;
 	
	public Matriz(int tamanio) {
		matriz = new int[tamanio][tamanio];
		for(int fila= 0; fila<matriz.length; fila++) {
			for(int columna = 0; columna<matriz.length; columna++) {
				matriz[fila][columna] = valorInvalido;
			}
		}
	}
	
	public void setValor(int fila, int columna, int valor) {
		try {
			matriz[fila][columna] = valor;
		}
		catch (Exception e) {
			throw new IllegalArgumentException("La fila y columna no son validos.");
		}
	}
	
	public int getValor(int fila, int columna) {
		try {
			return matriz[fila][columna];
		}
		catch (Exception e) {
			throw new IllegalArgumentException("La fila y columna no son validos.");
		}
	}
}
