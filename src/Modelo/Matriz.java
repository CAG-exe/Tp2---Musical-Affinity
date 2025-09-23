package Modelo;

public class Matriz {
	protected int[][] matriz;
	protected int valorInvalido = -1;
 	
	public Matriz(int tamanio) {
		if(tamanio <=0) {
			throw new IllegalArgumentException("El tamaño de la matriz es invalido");
		}
		matriz = new int[tamanio][tamanio];
		for(int fila= 0; fila<matriz.length; fila++) {
			for(int columna = 0; columna<matriz.length; columna++) {
				matriz[fila][columna] = valorInvalido;
			}
		}
	}
	
	
	//guarda el valor ingresado en la fila-columna
	public void setValor(int fila, int columna, int valor) {
		if(valor >5 || valor <0) {
			throw new IllegalArgumentException("El valor debe estar entre 0 y 5");
		}
		
		try {
			matriz[fila][columna] = valor;
		}
		catch (Exception e) {
			throw new IllegalArgumentException("La fila y columna no son validos.");
		}
	}
	
	//devuelve el valor de la fila-columna
	public int getValor(int fila, int columna) {
		try {
			return matriz[fila][columna];
		}
		catch (Exception e) {
			throw new IllegalArgumentException("La fila y columna no son validos.");
		}
	}


	public boolean tieneValorInvalido(int i, int c) {
		return matriz[i][c] == valorInvalido;
	}
}
