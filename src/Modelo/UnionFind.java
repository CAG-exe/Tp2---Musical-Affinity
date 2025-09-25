package Modelo;

public class UnionFind {

	private int [] padre;
	private int[] rango;
	
	public UnionFind(int vertices) {
		padre = new int[vertices];
		rango = new int[vertices];
		
		for (int i = 0; i < vertices; i++) {
			padre[i] = i;
			rango[i] = 0;
		}
	}
	
	public boolean Union(int a, int b){
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
		if(padre[a] != a) {
			padre[a] = find(padre[a]);
		}
		return padre[a];
	}
	
}
