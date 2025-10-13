package Modelo;

import java.util.ArrayList;
import java.util.List;

public class EstadisticasGrupo {
    private int idGrupo;
    private List<Usuario> miembros = new ArrayList<>();
    private double promedioTango = 0;
    private double promedioFolclore = 0;
    private double promedioRock = 0;
    private double promedioUrbano = 0;
    private double afinidadPromedio = 0; // Promedio del peso de las aristas

    public EstadisticasGrupo(int id) {
    	if(id <0) {
    		throw new IllegalArgumentException("El id no puede ser negativo.");
    	}
        this.idGrupo = id;
    }
    
    public void agregarMiembro(Usuario u) { 
        this.miembros.add(u); 
    }

    public void calcularPromediosDeIntereses() {
        if (miembros.isEmpty()) return;

        double sumaTango = 0, sumaFolclore = 0, sumaRock = 0, sumaUrbano = 0;
        for (Usuario u : miembros) {
            sumaTango += u.getInteresTango();
            sumaFolclore += u.getInteresFolclore();
            sumaRock += u.getInteresRockNacional();
            sumaUrbano += u.getInteresGeneroUrbano();
        }

        int totalMiembros = miembros.size();
        this.promedioTango = sumaTango / totalMiembros;
        this.promedioFolclore = sumaFolclore / totalMiembros;
        this.promedioRock = sumaRock / totalMiembros;
        this.promedioUrbano = sumaUrbano / totalMiembros;
    }

    // --- Getters ---
    public int getIdGrupo() { return idGrupo; }
    public List<Usuario> getMiembros() { return miembros; }
    public int getCantidadMiembros() { return miembros.size(); }
    public double getPromedioTango() { return promedioTango; }
    public double getPromedioFolclore() { return promedioFolclore; }
    public double getPromedioRock() { return promedioRock; }
    public double getPromedioUrbano() { return promedioUrbano; }
    public double getAfinidadPromedio() { return afinidadPromedio; }

    // --- Setters ---
    public void setAfinidadPromedio(double afinidad) { this.afinidadPromedio = afinidad; }
}