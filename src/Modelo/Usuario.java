package Modelo;

import java.util.ArrayList;

public class Usuario {
	private String nombre;
	private int interesTango;
	private int interesFolclore;
	private int interesRockNacional;
	private int interesGeneroUrbano;
	private int MinInteres = 1;
	private int MaxInteres = 5;

	public Usuario(String nombre, int tango, int folclore, int rock, int urbano) {
		if (nombre == null || nombre.length() < 3) {
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

	// Como el rango de interes debe ser entre 1 y 5, se verifica que estos son
	// ingresados correctamente.
	private void cumpleRangoDeInteres(Integer interes) {
		if (interes < MinInteres || interes > MaxInteres) {
			throw new IllegalArgumentException(
					"El rango de los intereses musicales se expresa como un entero entre 1 y 5, siendo 1 el menor interés y 5 el máximo interés en el género musical.");
		}
	}

	public int compararIntereses(Usuario u) {
		int diferenciaTotal = 0;
		diferenciaTotal += this.compararInteresTango(u);
		diferenciaTotal += this.compararInteresFolclore(u);
		diferenciaTotal += this.compararInteresRock(u);
		diferenciaTotal += this.compararInteresUrbano(u);

		return diferenciaTotal;
	}

	// Metodos que comparan el interes sobre un genero entre usuarios.
	public int compararInteresTango(Usuario u) {
		return interesesPuntuales(this.getInteresTango(), u.getInteresTango());
	}

	public int compararInteresFolclore(Usuario u) {
		return interesesPuntuales(this.getInteresFolclore(), u.getInteresFolclore());
	}

	public int compararInteresRock(Usuario u) {
		return interesesPuntuales(this.getInteresRockNacional(), u.getInteresRockNacional());
	}

	public int compararInteresUrbano(Usuario u) {
		return interesesPuntuales(this.getInteresGeneroUrbano(), u.getInteresGeneroUrbano());
	}

	// Obtiene el valor absoluto de la resta de los dos niveles, y te devuelve la
	// diferencia
	private int interesesPuntuales(int gustoUno, int gustoDos) {
		return (Math.abs(gustoUno - gustoDos));
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
