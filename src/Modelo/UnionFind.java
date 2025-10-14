package Modelo;

public class UnionFind {

	private int[] padre;
	private int[] rango;
	
	public int[] getPadre() {
		return padre;
	}

	public int[] getRango() {
		return rango;
	}

	public UnionFind(int vertices) {
		if(vertices < 0) {
			throw new IllegalArgumentException("La cantidad de vertices debe ser mayor o igual a 0.");
		}
		padre = new int[vertices];
		rango = new int[vertices];
		
		for (int i = 0; i < vertices; i++) {
			padre[i] = i;
			rango[i] = 0;
		}
	}
	
	public boolean Union(int a, int b){
		if(a< 0 || b < 0) {
			throw new IllegalArgumentException("Los vertices ingresados son negativos.");
		}
		
		int raizA = find(a);
		int raizB = find(b);
		
		if(raizA != raizB) {
			if(rango[raizA] > rango[raizB]) {
				padre[raizB] = raizA;
			}else if(rango[raizA] < rango[raizB]) {
				padre[raizA] = raizB;
			}else {
				padre[raizB] = raizA;
				rango[raizA]++;
			}
		}
		return true ;
		
	}

	public int find(int a) {
		if(a > padre.length-1 || a<0) {
			throw new IllegalArgumentException("El indice del vertice es invalido.");
		}
		
		if(padre[a] != a) {
			padre[a] = find(padre[a]);
		}
		return padre[a];
	}
	
	
	
}
