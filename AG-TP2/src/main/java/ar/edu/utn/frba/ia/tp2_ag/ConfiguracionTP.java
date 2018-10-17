package main.java.ar.edu.utn.frba.ia.tp2_ag;

import main.java.ar.edu.utn.frba.ia.ag.Configuracion;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.BinomialAzar;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.BinomialMascaraDoble;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.MultiPunto;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.Simple;
import main.java.ar.edu.utn.frba.ia.ag.mutacion.MutacionSimple;
import main.java.ar.edu.utn.frba.ia.ag.paro.AptitudMinima;
import main.java.ar.edu.utn.frba.ia.ag.paro.CantidadDeCiclos;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.ControlSobreNumeroEsperado;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Ranking;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Ruleta;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Torneo;


public class ConfiguracionTP extends Configuracion {
	
	public ConfiguracionTP() {
		
		super(new AptitudMinima(10), // criterio de paro
				5000, // cantIndividuosIniciales
				new Ruleta(), // seleccion
				new BinomialAzar(), // cruzamiento
				new MutacionSimple(0)); // mutacion
	}
	
}
