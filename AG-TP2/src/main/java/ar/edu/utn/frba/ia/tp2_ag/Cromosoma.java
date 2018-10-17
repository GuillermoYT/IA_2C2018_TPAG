package main.java.ar.edu.utn.frba.ia.tp2_ag;

import java.util.Stack;
import java.util.logging.Logger;

import main.java.ar.edu.utn.frba.ia.ag.Individuo;

public class Cromosoma extends Individuo {
	

	private Personaje tales;
	private Personaje pitaco;
	private Personaje solon;
	private Personaje bias;



	public enum FechaNacimiento{
		AC660, AC650, AC570, AC640
	}
	
	public enum LugarNacimiento{
		Atica, Mileto, Priene, Tracia 
	}
	
	public enum Dedicacion{
		Legislador, Estadista, Filosofo, CodificadorLeyes
	}
	
	public enum OtrasActividades{
		LuchaContraPersas, LuchaContraMegarenses, TiranoDeMitilene, EstudiosDeMatAstFis
	}
	
	//SETTERS and GETTERS
	public Personaje getTales() {
		return tales;
	}

	public void setTales(Personaje tales) {
		this.tales = tales;
	}

	public Personaje getPitaco() {
		return pitaco;
	}

	public void setPitaco(Personaje pitaco) {
		this.pitaco = pitaco;
	}

	public Personaje getSolon() {
		return solon;
	}

	public void setSolon(Personaje solon) {
		this.solon = solon;
	}

	public Personaje getBias() {
		return bias;
	}

	public void setBias(Personaje bias) {
		this.bias = bias;
	}
	
	
	//carga pila para fechas de nacimiento
	private void cargarPilaFechaNacimiento(Stack<FechaNacimiento> pilaFechas){
		while(FechaNacimiento.values().length != pilaFechas.size()){
			FechaNacimiento element=FechaNacimiento.values()[(int) (Math.random() * FechaNacimiento.values().length)];
			if(!pilaFechas.contains(element)){
				pilaFechas.push(element);
			}
		}
	}
	
	//carga pila para lugares de nacimiento
	private void cargarPilaLugarNacimiento(Stack<LugarNacimiento> pilaLugares){
		while(LugarNacimiento.values().length != pilaLugares.size()){
			LugarNacimiento element=LugarNacimiento.values()[(int) (Math.random() * LugarNacimiento.values().length)];
			if(!pilaLugares.contains(element)){
				pilaLugares.push(element);
			}
		}
	}
	
	//carga pila para dedicaciones
	private void cargarPilaDedicaciones(Stack<Dedicacion> pilaDedicacion){
		while(Dedicacion.values().length != pilaDedicacion.size()){
			Dedicacion element=Dedicacion.values()[(int) (Math.random() * Dedicacion.values().length)];
			if(!pilaDedicacion.contains(element)){
				pilaDedicacion.push(element);
			}
		}
	}
	
	//carga pila para otras actividades
	private void cargarPilaActividades(Stack<OtrasActividades> pilaActividades){
		while(OtrasActividades.values().length != pilaActividades.size()){
			OtrasActividades element=OtrasActividades.values()[(int) (Math.random() * OtrasActividades.values().length)];
			if(!pilaActividades.contains(element)){
				pilaActividades.push(element);
			}
		}
	}	

	
	@Override
	public double aptitud() {
				
		return this.aptitudTales() + this.aptitudPitaco() + this.aptitudSolon() + this.aptitudBias();
	}
	

	public Cromosoma() {
		
		Stack<FechaNacimiento> pilaFechas=new Stack<Cromosoma.FechaNacimiento>();
		Stack<LugarNacimiento> pilaLugares=new Stack<Cromosoma.LugarNacimiento>();
		Stack<Dedicacion> pilaDedicacion=new Stack<Cromosoma.Dedicacion>();		
		Stack<OtrasActividades> pilaActividades=new Stack<Cromosoma.OtrasActividades>();	
		
		this.cargarPilaFechaNacimiento(pilaFechas);
		this.cargarPilaLugarNacimiento(pilaLugares);
		this.cargarPilaDedicaciones(pilaDedicacion);
		this.cargarPilaActividades(pilaActividades);
		
		this.setTales(new Personaje(pilaFechas.pop(),pilaLugares.pop(),pilaDedicacion.pop(),pilaActividades.pop()));
		this.setPitaco(new Personaje(pilaFechas.pop(),pilaLugares.pop(),pilaDedicacion.pop(),pilaActividades.pop()));
		this.setSolon(new Personaje(pilaFechas.pop(),pilaLugares.pop(),pilaDedicacion.pop(),pilaActividades.pop()));
		this.setBias(new Personaje(pilaFechas.pop(),pilaLugares.pop(),pilaDedicacion.pop(),pilaActividades.pop()));
		
		//this.printCromosoma();
	}
	
	public void printCromosoma(){
		System.out.println("Tales: "+this.tales.printPersonaje());
		System.out.println("Pitaco: "+this.pitaco.printPersonaje());
		System.out.println("Solon: "+this.solon.printPersonaje());
		System.out.println("Bias: "+this.bias.printPersonaje());
		System.out.println("====================================");
	}
	
	
	private double aptitudTales(){
		double value=0;
		Personaje personaje = this.tales;
		//Tales nacio en el año 660 AC
		if(personaje.getFecha_nac().equals(FechaNacimiento.AC660) && !personaje.getOtras().equals(OtrasActividades.LuchaContraPersas)){
			value+=2;
		}else{
			value-=3;
		}				
		//Tales no lucho contra los persas
		//if(!personaje.getOtras().equals(OtrasActividades.LuchaContraPersas)){
		//	value+=2;
		//}else{
		//	value-=3;
		//}
		value+=this.aptitudGeneral(personaje);
		return value;
	}
	
	private double aptitudPitaco(){
		double value=0;
		Personaje personaje = this.pitaco;
		//Pitaco fue legislador. 
		if(personaje.getDedicacion().equals(Dedicacion.Legislador) && !personaje.getLugar_nac().equals(LugarNacimiento.Atica)){
			value+=2;
		}else{
			value-=3;
		}
		//Pitaco no nacio en atica
		//if(!personaje.getLugar_nac().equals(LugarNacimiento.Atica)){
		//	value+=2;
		//}else{
		//	value-=3;
		//}
		value+=this.aptitudGeneral(personaje);
		return value;
	}	
	
	private double aptitudSolon(){
		double value=0;
		Personaje personaje = this.solon;
		//Solon lucho contra los megarenses
		if(personaje.getOtras().equals(OtrasActividades.LuchaContraMegarenses) && !personaje.getLugar_nac().equals(LugarNacimiento.Mileto)){
			value+=2;
		}else{
			value-=3;
		}		
		//Solon no era de mileto
		//if(!personaje.getLugar_nac().equals(LugarNacimiento.Mileto)){
		//	value+=2;
		//}else{
		//	value-=3;
		//}		
		value+=this.aptitudGeneral(personaje);		
		return value;
	}	
	
	private double aptitudBias(){
		double value=0;
		Personaje personaje = this.bias;
		//Bias era de Priene
		if(personaje.getLugar_nac().equals(LugarNacimiento.Priene) && !personaje.getFecha_nac().equals(FechaNacimiento.AC650)){
			value+=2;
		}else{
			value-=3;
		}
		//Bias no nacio en el año 650 AC
		//if(!personaje.getFecha_nac().equals(FechaNacimiento.AC650)){
		//	value+=2;
		//}else{
		//	value-=3;
		//}			
		value+=this.aptitudGeneral(personaje);
		return value;
	}
	
	
	private double aptitudGeneral(Personaje personaje){
		double value=0;
		//El personaje que nacio en tracia.
		if(personaje.getLugar_nac().equals(LugarNacimiento.Tracia)){
			//Fue tirano de Mitilene y no era estadista
			if (personaje.getOtras().equals(OtrasActividades.TiranoDeMitilene) && !personaje.getFecha_nac().equals(Dedicacion.Estadista))
				value+=2;
			else
				value-=4;
		}
		
		//El que fue filosofo
		if(personaje.getDedicacion().equals(Dedicacion.Filosofo)){
			//se dedico a las matematicas, la astronomia y la fisica y no nacio en el año 570 AC
			if(personaje.getOtras().equals(OtrasActividades.EstudiosDeMatAstFis) && !personaje.getFecha_nac().equals(FechaNacimiento.AC570))
				value+=2;
			else
				value-=4;
		}
		
		//El que nacio en el año 640 AC 
		if(personaje.getFecha_nac().equals(FechaNacimiento.AC640)){
			//Nacio en atica y no era codificador de leyes
			if(personaje.getLugar_nac().equals(LugarNacimiento.Atica) && !personaje.getDedicacion().equals(Dedicacion.CodificadorLeyes))
				value+=2;
			else
				value-=4;
		}
		
		return value;

	}






	@Override
	public Individuo generarRandom() {
		Individuo nuevoInd;
		
		try {
			nuevoInd = this.getClass().newInstance();
			return nuevoInd;
		} catch (Exception e) {
			Logger.getLogger(
				Logger.GLOBAL_LOGGER_NAME).severe(
					"No se puede crear una instancia de "
					+ this.getClass().getName()
					+ ". Probablemente no tenga un constructor vacio."
					+ " // CAUSA: " + e);
		}
		//return this.clone();
		return null;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer=new StringBuffer();
		
		buffer.append("\r\n");
		buffer.append("Tales: "+this.tales.printPersonaje()+"\r\n");
		buffer.append("Pitaco: "+this.pitaco.printPersonaje()+"\r\n");
		buffer.append("Solon: "+this.solon.printPersonaje()+"\r\n");
		buffer.append("Bias: "+this.bias.printPersonaje()+"\r\n");
		buffer.append("Aptitud: "+(new Double(aptitud())).toString()+"\r\n");
		
		return buffer.toString();
		//return (new Double(aptitud())).toString();
	}

	/*
	public Stack<Deporte> getPilaDeportes() {
		return pilaDeportes;
	}

	public void setPilaDeportes(Stack<Deporte> pilaDeportes) {
		this.pilaDeportes = pilaDeportes;
	}

	public Stack<Apellido> getPilaApellidos() {
		return pilaApellidos;
	}

	public void setPilaApellidos(Stack<Apellido> pilaApellidos) {
		this.pilaApellidos = pilaApellidos;
	}

	public Stack<Esposa> getPilaEsposas() {
		return pilaEsposas;
	}

	public void setPilaEsposas(Stack<Esposa> pilaEsposas) {
		this.pilaEsposas = pilaEsposas;
	}
	*/
	
	
}
