package main.java.ar.edu.utn.frba.ia.tp2_ag;

import org.junit.Assert;
import org.junit.Test;

import main.java.ar.edu.utn.frba.ia.ag.AlgoritmoGenetico;
import main.java.ar.edu.utn.frba.ia.ag.Configuracion;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;
import main.java.ar.edu.utn.frba.ia.ag.paro.CantidadDeCiclos;
import main.java.ar.edu.utn.frba.ia.ag.paro.CriterioDeParo;

public class AlgoritmoGeneticoTPTest {
	
	@Test
	public void testUnaVuelta() {
		
		CriterioDeParo paroInstantaneo = new CantidadDeCiclos(5000L);
		Configuracion config = new ConfiguracionTP();
		config.setCriterioDeParo(paroInstantaneo);
		
		AlgoritmoGenetico ag = new AlgoritmoGeneticoTP(config, Cromosoma.class);
		Individuo resultado = ag.ejecutar();
		Assert.assertNotNull(resultado);
		System.out.println("Cromosoma seleccionado");
		System.out.println("Aptitud: "+resultado.aptitud());
		((Cromosoma)resultado).printCromosoma();
		
	}
	
	public void testCruzamiento() {

		
	}
	
	public void testLoggearEstado() {
	}
	
}
