package main.java.ar.edu.utn.frba.ia.tp2_ag;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import main.java.ar.edu.utn.frba.ia.ag.AlgoritmoGenetico;
import main.java.ar.edu.utn.frba.ia.ag.Configuracion;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;

public class AlgoritmoGeneticoTP extends AlgoritmoGenetico {

	public AlgoritmoGeneticoTP(Configuracion configuracion, Class<? extends Individuo> individuoClass) {
		super(configuracion,individuoClass);
		
		try {
			Handler fileHandler = new FileHandler("./agTp.log", false);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			Logger.getLogger(
					Logger.GLOBAL_LOGGER_NAME).addHandler(fileHandler);
			
		} catch (SecurityException e) {
		} catch (IOException e) {
		}
	}
	
	@Override
	protected void generarPoblacionInicial(Class<? extends Individuo> individuoClass) {
		
		for (int i = 0; i < this.configuracion.getPoblacionInicial(); i++) {
			try {
				this.agregarIndividuo(individuoClass.newInstance().generarRandom());
			} catch (Exception e) {
				Logger.getLogger(
					Logger.GLOBAL_LOGGER_NAME).severe(
						"No se puede crear una instancia de "
						+ individuoClass.getName()
						+ ". Probablemente no tenga un constructor vacio."
						+ " // CAUSA: " + e);
			}
		}
	}
}
