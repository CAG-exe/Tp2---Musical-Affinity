package Controlador;

import Modelo.AfinidadMusical;
import Modelo.EstadisticasGrupo;
import Visual.CrearUsuario;
import Visual.NuevoUsuarioDatos;
import Visual.UISwing;
import java.util.Collection;

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
		if (modelo.getCantidadDeUsuarios() < 2) {
			System.out.println("No hay suficientes usuarios para generar estadísticas.");
			// Opcional: podrías usar un JOptionPane para mostrar un error en la UI.
			return;
		}

		try {
			// 1. Obtiene la cantidad de grupos desde la UI.
			String valorComboBox = visual.getComboBoxCantidadGrupos();
			int cantGrupos = Integer.parseInt(valorComboBox);
			
			System.out.println("Controlador: Solicitando cálculo de estadísticas para " + cantGrupos + " grupos.");

			// 2. Pide al modelo que realice el cálculo.
			Collection<EstadisticasGrupo> estadisticasCalculadas = modelo.calcularEstadisticas(cantGrupos);
			
			if (estadisticasCalculadas.isEmpty()) {
				System.out.println("Controlador: El cálculo no devolvió resultados.");
			} else {
				System.out.println("Controlador: Se recibieron " + estadisticasCalculadas.size() + " grupos de estadísticas.");
			}

			// 3. Pasa los resultados al panel visual.
			visual.getPanelUsuarios().mostrarEstadisticas(estadisticasCalculadas);
			
			// 4. Muestra el panel y actualiza el título.
			visual.mostrarPanel("Estadisticas");
			visual.cambiarTituloDePaginaEstadisticas();

		} catch (Exception e) {
			System.err.println("Ocurrió un error al generar las estadísticas:");
			e.printStackTrace();
		}
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
		if (dto.getNombre().equals("Ingrese su nombre de usuario")) {
			crearUsuario.ingreseNombre();
		}
		else if (dto.getNombre().length() < 3) {
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