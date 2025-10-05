package Controlador;

import Modelo.AfinidadMusical;
import Visual.CrearUsuario;
import Visual.NuevoUsuarioDatos;
import Visual.UISwing;

public class Controlador {
	private UISwing visual;
	private AfinidadMusical modelo;
	private CrearUsuario crearUsuario;
	
	public Controlador() {
	}
	
	public void agregarModeloYVisual(UISwing visual, AfinidadMusical modelo) {
		this.visual = visual;
		this.modelo = modelo;
	}

	public void mostrarPanelEstadisticas() {
		visual.mostrarPanel("Estadisticas");
		visual.cambiarTituloDePaginaEstadisticas();
	}

	public void mostrarPanelGrafo() {
		guardarCantidadDeGruposActual();
		
		visual.mostrarPanel("Grafo");
		visual.recargarGrafo(modelo);
		visual.cambiarTituloDePaginaGrafo();
	}
	
	private void guardarCantidadDeGruposActual() {
		String cantGruposString = visual.getComboBoxCantidadGrupos();
		int cantidadDeGrupos = Integer.parseInt(cantGruposString);
		modelo.setCantidadDeGrupos(cantidadDeGrupos);
	}

	public void mostrarPanelMenu() {
		visual.mostrarPanel("CrearUsuario");
		visual.cambiarTituloDePaginaCrearUsuario();
	}
	
	public void añadirMenuAlControlador(CrearUsuario CrearUsuario) {
		this.crearUsuario = CrearUsuario;
	}
	
	public void guardarNuevoUsuario(NuevoUsuarioDatos dto) {
		 if (dto.getNombre().length() < 3) {
			 crearUsuario.avisoNombreCorto();
         }
         else if(modelo.usuarioYaRegistrado(dto.getNombre())) {
        	 crearUsuario.avisoNombreRegistrado();
         }
         else {
        	 crearUsuario.añadirUsuarioAFila();
             modelo.registrarUsuario(dto.getNombre(), dto.getTango(), dto.getFolclore(),dto.getRock(), dto.getUrbano());
         }
		
	}

	public int[][] getMatrizDeUsuarios() {
		return modelo.getLimitadaMatrizDeUsuarios();
	}

	public void habilitarBotonGrafo() {
		if(modelo.getCantidadDeUsuarios() > 2) {
			visual.habilitarBotonGrafo();
		}
		
	}
}
