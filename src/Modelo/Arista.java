package Modelo;

public class Arista {

   private  int origen, destino, peso;

	public Arista(int o, int d, int p) {
        origen = o;
        destino = d;
        peso = p;
    }
	
    public int getOrigen() {
	return origen;
}

   public int getDestino() {
	return destino;
   }

   public int getPeso() {
	return peso;
   }
   @Override
	public String toString() {
		return "["+ "origen" + "=" + origen + ",destino=" + destino + ",peso=" + peso + "]";
	}

}