package Controlador;

import Modelo.AfinidadMusical;
import Modelo.EstadisticasGrupo;
import Visual.CrearUsuario;
import Visual.MensajeDePocosUsuarios;
import Visual.NuevoUsuarioDatos;
import Visual.UISwing;

import java.awt.Window;
import java.util.Collection;

import javax.swing.JDialog;

public class Controlador {
	private UISwing visual;
	private AfinidadMusical modelo;
	private CrearUsuario crearUsuario;
	private MensajeDePocosUsuarios mensajeDePocosUsuarios;
	
	public Controlador() {
	}
	
	public void agregarModeloYVisual(UISwing visual, AfinidadMusical modelo) {
		this.visual = visual;
		this.modelo = modelo;
	}

	public void mostrarPanelEstadisticas() {
		if(visual.panelActualEs("Estadisticas")){
			return;
		}
		guardarCantidadDeGruposActual();
		
		if(modelo.getCantidadDeUsuarios()<2) {
			mostrarDialogoDeError();
			return;
		 }
		
		if(cantidadDeUsuariosBaja()) {
			mostrarDialogoDeError();
		}

		try {
			// 1. Obtiene la cantidad de grupos desde la UI.
			String valorComboBox = visual.getComboBoxCantidadGrupos();
			int cantGrupos = Integer.parseInt(valorComboBox);

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
		if(visual.panelActualEs("Grafo")){
			return;
		}
		
		guardarCantidadDeGruposActual();
		
		if(cantidadDeUsuariosBaja()) {
			mostrarDialogoDeError();
		}
		
		visual.mostrarPanel("Grafo");
		visual.recargarGrafo();
		visual.cambiarTituloDePaginaGrafo();

	}
	
	private void guardarCantidadDeGruposActual() {
		String cantGruposString = visual.getComboBoxCantidadGrupos();
		int cantidadDeGrupos = Integer.parseInt(cantGruposString);
		modelo.setCantidadDeGrupos(cantidadDeGrupos);
	}

	public boolean cantidadDeUsuariosBaja() {
		int cantUsuarios = modelo.getCantidadDeUsuarios();
		int cantGrupos = modelo.getCantidadDeGrupos();
		return cantGrupos > cantUsuarios;
	}
	
	public void mostrarPanelMenu() {
		if(visual.panelActualEs("CrearUsuario")){
			return;
		}
		visual.mostrarPanel("CrearUsuario");
		visual.cambiarTituloDePaginaCrearUsuario();
		crearUsuario.recargarTextoEnTextFieldNombre();
	}
	

	
	public void añadirMenuAlControlador(CrearUsuario CrearUsuario) {
		this.crearUsuario = CrearUsuario;
	}
	
	public void guardarNuevoUsuario() {
		NuevoUsuarioDatos dto = crearUsuario.generarNuevoUsuarioSegunDatosActuales();
		
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
		habilitarBotonGrafo();
	}

	public int[][] getMatrizDeUsuarios() {
		return modelo.getLimitadaMatrizDeUsuarios();
	}

	public void habilitarBotonGrafo() {
		if(modelo.getCantidadDeUsuarios() > 2) {
			visual.habilitarBotonGrafo();
		}
	}
	
	public void clicEnTextFieldNombre() {
		crearUsuario.clicEnTextField();
	}
	
	public void eliminarUsuario() throws ArrayIndexOutOfBoundsException{
		int indiceUsuario = crearUsuario.getIndiceDeUsuarioseleccionado();
		if(indiceUsuario != -1) { //Se seleccionó algun usuario
			String nombreUsuario = crearUsuario.getNombreDeUsaurioEnLaTabla(indiceUsuario);
			crearUsuario.eliminarUsuarioDeLaTablaSegunIndex(indiceUsuario);
			modelo.eliminarUsuario(nombreUsuario);
		}
	}
	
	public String getComboBoxCantidadGrupos() {
		if(visual == null) {
			return "2"; // Valor por defecto o manejo de error
		}
		return visual.getComboBoxCantidadGrupos();	
	}

	public Object[][] getMatrizGrupos() {
		if(modelo == null) {
			return new String[0][0];
		}
		return modelo.getMatrizGrupos(getComboBoxCantidadGrupos());
	}
	
	public void mostrarDialogoDeError() {
		mensajeDePocosUsuarios = new MensajeDePocosUsuarios(this);
		mensajeDePocosUsuarios.setVisible(true);
	}

	public void cerrarDialog() {
		mensajeDePocosUsuarios.dispose();
	}
}