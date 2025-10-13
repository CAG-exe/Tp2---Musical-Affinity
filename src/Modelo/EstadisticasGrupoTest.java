package Modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EstadisticasGrupoTest {
	
	private EstadisticasGrupo estadisticas;
	
	@Before
	public void testContructor() {
		estadisticas = new EstadisticasGrupo(1);
	}
	
	
	@Test
    public void testConstructorIdCorrectamente() {
        EstadisticasGrupo grupo = new EstadisticasGrupo(10);
        assertEquals(10, grupo.getIdGrupo());
    }
	
	@Test (expected = IllegalArgumentException.class)
    public void testConstructorConIdNegativo() {
		estadisticas = new EstadisticasGrupo(-12);
    }
    @Test
    public void testConstructorIdCero() {
        EstadisticasGrupo grupo = new EstadisticasGrupo(0);
        assertEquals(0, grupo.getIdGrupo());
    }
	
	@Test
	public void testAgregarMiembro_AumentaCantidadYLoContieneEnLaLista() {

        Usuario u = new Usuario("Juan",5, 3, 2, 1);
        estadisticas.agregarMiembro(u);

        assertTrue(estadisticas.getMiembros().contains(u));
        assertEquals(1,estadisticas.getCantidadMiembros());
    }
	
    @Test
    public void testSetYAfinidadPromedio() {
        estadisticas.setAfinidadPromedio(2.5);
        
        assertEquals(2.5, estadisticas.getAfinidadPromedio(), 0.001);
    }

    @Test
    public void testCalcularPromediosConGrupoVacio() {
        estadisticas.calcularPromediosDeIntereses();

        assertEquals(0, estadisticas.getPromedioTango(),0.001);
        assertEquals(0, estadisticas.getPromedioFolclore(),0.001);
        assertEquals(0, estadisticas.getPromedioRock(),0.001);
        assertEquals(0, estadisticas.getPromedioUrbano(),0.001);
    }

    @Test
    public void testCalcularPromediosConUnSoloUsuario() {
        Usuario usuario = new Usuario("Diego",2,3,4,3);
        estadisticas.agregarMiembro(usuario);
        
        
        estadisticas.calcularPromediosDeIntereses();

        assertEquals(2, estadisticas.getPromedioTango(),0.001);
        assertEquals(3, estadisticas.getPromedioFolclore(),0.001);
        assertEquals(4, estadisticas.getPromedioRock(),0.001);
        assertEquals(3, estadisticas.getPromedioUrbano(),0.001);
    }
    
    
    @Test
    public void testCalcularPromediosConUnMuchosUsuarios() {
        Usuario usuario = new Usuario("Diego",2,3,2,3);
        estadisticas.agregarMiembro(usuario);
        
        Usuario usuario2 = new Usuario("Juan",4,5,5,1);
        estadisticas.agregarMiembro(usuario2);
        
        Usuario usuario3 = new Usuario("Pedro",3,1,5,2);
        estadisticas.agregarMiembro(usuario3);
        
        
        estadisticas.calcularPromediosDeIntereses();

        assertEquals(3, estadisticas.getPromedioTango(),0.001);
        assertEquals(3, estadisticas.getPromedioFolclore(),0.001);
        assertEquals(4, estadisticas.getPromedioRock(),0.001);
        assertEquals(2, estadisticas.getPromedioUrbano(),0.001);
    }
    @Test
    public void testListaNoEsNula() {
        assertNotNull(estadisticas.getMiembros());
    }
    @Test
    public void testAgregarMiembro_MultiplesMiembros() {
        Usuario usuario = new Usuario("Diego",2,3,2,3);
        estadisticas.agregarMiembro(usuario);
        
        Usuario usuario2 = new Usuario("Juan",4,5,5,1);
        estadisticas.agregarMiembro(usuario2);
        
        Usuario usuario3 = new Usuario("Pedro",3,1,5,2);
        estadisticas.agregarMiembro(usuario3);
        
        assertEquals(3, estadisticas.getCantidadMiembros());
    }
}
