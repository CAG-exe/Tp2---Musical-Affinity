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

	public void mostrarPanelUsuarios() {
		visual.mostrarPanel("Usuarios");
		visual.cambiarTituloDePaginaUsuario();
	}

	public void mostrarPanelGrafo() {
		visual.mostrarPanel("Grafo");
		visual.cambiarTituloDePaginaGrafo();
	}
	
	public void mostrarPanelMenu() {
		visual.mostrarPanel("CrearUsuario");
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
}
