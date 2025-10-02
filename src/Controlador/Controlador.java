package Controlador;

import Modelo.AfinidadMusical;
import Visual.Menu;
import Visual.NuevoUsuarioDatos;
import Visual.UISwing;

public class Controlador {
	private UISwing visual;
	private AfinidadMusical modelo;
	private Menu menu;
	
	public Controlador() {
	}
	
	public void agregarModeloYVisual(UISwing visual, AfinidadMusical modelo) {
		this.visual = visual;
		this.modelo = modelo;
	}

	public void mostrarPanelUsuarios() {
		visual.mostrarPanel("Usuarios");
	}

	public void mostrarPanelGrafo() {
		visual.mostrarPanel("Grafo");
	}
	
	public void mostrarPanelMenu() {
		visual.mostrarPanel("CrearUsuario");
	}
	
	public void añadirMenuAlControlador(Menu menu) {
		this.menu = menu;
	}
	
	public void guardarNuevoUsuario(NuevoUsuarioDatos dto) {
		 if (dto.getNombre().length() < 3) {
            menu.avisoNombreCorto();
         }
         else if(modelo.usuarioYaRegistrado(dto.getNombre())) {
        	 menu.avisoNombreRegistrado();
         }
         else {
        	 menu.añadirUsuarioAFila();
             modelo.registrarUsuario(dto.getNombre(), dto.getTango(), dto.getFolclore(),dto.getRock(), dto.getUrbano());
         }
		
	}
}
