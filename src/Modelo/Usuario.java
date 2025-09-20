package Modelo;

public class Usuario {
	String nombre;
	int interesTango;
	int interesFolclore;
	int interesRockNacional;
	int interesGeneroUrbano;
	
	
	public Usuario(String nombre, int tango, int folclore, int rock, int urbano){
		if(nombre == null || nombre.length() <3) {
			throw new IllegalArgumentException("El nombre de usuario debe tener almenos 3 caracteres.");
		}
		cumpleRangoDeInteres(tango);
		cumpleRangoDeInteres(folclore);
		cumpleRangoDeInteres(rock);
		cumpleRangoDeInteres(urbano);
		
		this.nombre = nombre;
		this.interesFolclore = folclore;
		this.interesTango = tango;
		this.interesRockNacional = rock;
		this.interesGeneroUrbano = urbano;
	}


	private void cumpleRangoDeInteres(Integer interes) {
		if(interes<1 || interes >5) {
			throw new IllegalArgumentException("El rango de los intereses musicales se expresa como un entero entre 1 y 5, siendo 1 el menor interés y 5 el máximo interés en el género musical.");
		}
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public int getInteresTango() {
		return interesTango;
	}
	
	public int getInteresFolclore() {
		return interesFolclore;
	}
	
	public int getInteresRockNacional() {
		return interesRockNacional;
	}
	
	public int getInteresGeneroUrbano() {
		return interesGeneroUrbano;
	}
}
