package Modelo;

public class Usuario {
	String nombre;
	int interes_tango;
	int interes_folclore;
	int interes_rock_nacional;
	int interes_genero_urbano;
	
	
	public Usuario(String nombre, int tango, int folclore, int rock, int urbano){
		if(nombre == null || nombre.length() <3) {
			throw new IllegalArgumentException("El nombre de usuario debe tener almenos 3 caracteres.");
		}
		cumpleRangoDeInteres(tango);
		cumpleRangoDeInteres(folclore);
		cumpleRangoDeInteres(rock);
		cumpleRangoDeInteres(urbano);
		
		this.nombre = nombre;
		this.interes_folclore = folclore;
		this.interes_tango = tango;
		this.interes_rock_nacional = rock;
		this.interes_genero_urbano = urbano;
	}


	private void cumpleRangoDeInteres(Integer interes) {
		if(interes<1 || interes >5) {
			throw new IllegalArgumentException("El rango de los intereses musicales se expresa como un entero entre 1 y 5, siendo 1 el menor interés y 5 el máximo interés en el género musical.");
		}
	}
}
