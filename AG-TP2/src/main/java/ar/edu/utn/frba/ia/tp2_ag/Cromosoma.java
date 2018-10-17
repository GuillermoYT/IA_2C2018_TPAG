package main.java.ar.edu.utn.frba.ia.tp2_ag;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;

public class Cromosoma extends Individuo {
	
	private List<PersonajeGrecia> personajes;
	
	private PersonajeGrecia tales;
	private PersonajeGrecia pitaco;
	private PersonajeGrecia solon;
	private PersonajeGrecia bias;


	public enum Personaje{
		Tales, Pitaco, Solon, Bias
	}
	
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
	public PersonajeGrecia getTales() {
		return tales;
	}

	public void setTales(PersonajeGrecia tales) {
		this.tales = tales;
	}

	public PersonajeGrecia getPitaco() {
		return pitaco;
	}

	public void setPitaco(PersonajeGrecia pitaco) {
		this.pitaco = pitaco;
	}

	public PersonajeGrecia getSolon() {
		return solon;
	}

	public void setSolon(PersonajeGrecia solon) {
		this.solon = solon;
	}

	public PersonajeGrecia getBias() {
		return bias;
	}

	public void setBias(PersonajeGrecia bias) {
		this.bias = bias;
	}	
	
	public List<PersonajeGrecia> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<PersonajeGrecia> personajes) {
		this.personajes = personajes;
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
				
		personajes = new ArrayList<>();
		personajes.add(getTales());
		personajes.add(getPitaco());
		personajes.add(getSolon());
		personajes.add(getBias());
        
		//return this.aptitudTales() + this.aptitudPitaco() + this.aptitudSolon() + this.aptitudBias();
		return penalizacionPorRepetidos() + this.aptitudGeneral(getTales()) + this.aptitudGeneral(getPitaco()) + this.aptitudGeneral(getSolon()) + this.aptitudGeneral(getBias());
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
		
		this.setTales(new PersonajeGrecia(Personaje.Tales,pilaFechas.pop(),pilaLugares.pop(),pilaDedicacion.pop(),pilaActividades.pop()));
		this.setPitaco(new PersonajeGrecia(Personaje.Pitaco,pilaFechas.pop(),pilaLugares.pop(),pilaDedicacion.pop(),pilaActividades.pop()));
		this.setSolon(new PersonajeGrecia(Personaje.Solon,pilaFechas.pop(),pilaLugares.pop(),pilaDedicacion.pop(),pilaActividades.pop()));
		this.setBias(new PersonajeGrecia(Personaje.Bias,pilaFechas.pop(),pilaLugares.pop(),pilaDedicacion.pop(),pilaActividades.pop()));
		
		personajes = new ArrayList<>();
		personajes.add(getTales());
		personajes.add(getPitaco());
		personajes.add(getSolon());
		personajes.add(getBias());
	}
	
	public void printCromosoma(){
		System.out.println("Tales: "+this.tales.printPersonaje());
		System.out.println("Pitaco: "+this.pitaco.printPersonaje());
		System.out.println("Solon: "+this.solon.printPersonaje());
		System.out.println("Bias: "+this.bias.printPersonaje());
		System.out.println("====================================");
	}
	
	
	private double aptitudTales(){
		double value=10;
		PersonajeGrecia personaje = getTales();
		//Tales nacio en el 660 AC y no lucho contra los persas
		if(personaje.getFecha_nac().equals(FechaNacimiento.AC660) && !personaje.getOtras().equals(OtrasActividades.LuchaContraPersas)){
			//value+=5;
		}else{
			value-=5;
		}				
		//Tales no lucho contra los persas
		//if(!personaje.getOtras().equals(OtrasActividades.LuchaContraPersas)){
		//	value+=2;
		//}else{
		//	value-=3;
		//}
		value+=this.aptitudGeneralAux(personaje);
		return value;
	}
	
	private double aptitudPitaco(){
		double value=10;
		PersonajeGrecia personaje = getPitaco();
		//Pitaco fue legislador y no nacio en atica. 
		if(personaje.getDedicacion().equals(Dedicacion.Legislador) && !personaje.getLugar_nac().equals(LugarNacimiento.Atica)){
			//value+=5;
		}else{
			value-=5;
		}
		//Pitaco no nacio en atica
		//if(!personaje.getLugar_nac().equals(LugarNacimiento.Atica)){
		//	value+=2;
		//}else{
		//	value-=3;
		//}
		value+=this.aptitudGeneralAux(personaje);
		return value;
	}	
	
	private double aptitudSolon(){
		double value=10;
		PersonajeGrecia personaje = getSolon();
		//Solon lucho contra los megarenses y no era de mileto
		if(personaje.getOtras().equals(OtrasActividades.LuchaContraMegarenses) && !personaje.getLugar_nac().equals(LugarNacimiento.Mileto)){
			//value+=5;
		}else{
			value-=5;
		}		
		//Solon no era de mileto
		//if(!personaje.getLugar_nac().equals(LugarNacimiento.Mileto)){
		//	value+=2;
		//}else{
		//	value-=3;
		//}		
		value+=this.aptitudGeneralAux(personaje);		
		return value;
	}	
	
	private double aptitudBias(){
		double value=10;
		PersonajeGrecia personaje = getBias();
		//Bias era de Priene y no nacio en el 650 AC
		if(personaje.getLugar_nac().equals(LugarNacimiento.Priene) && !personaje.getFecha_nac().equals(FechaNacimiento.AC650)){
			//value+=5;
		}else{
			value-=5;
		}
		//Bias no nacio en el 650 AC
		//if(!personaje.getFecha_nac().equals(FechaNacimiento.AC650)){
		//	value+=2;
		//}else{
		//	value-=3;
		//}			
		value+=this.aptitudGeneralAux(personaje);
		return value;
	}
	
	private double aptitudGeneralAux(PersonajeGrecia personaje){
		double value=0;
		
		//El personaje que nacio en tracia.
		if(personaje.getLugar_nac().equals(LugarNacimiento.Tracia)){
			//Fue tirano de Mitilene y no era estadista
			if (personaje.getOtras().equals(OtrasActividades.TiranoDeMitilene) && !personaje.getDedicacion().equals(Dedicacion.Estadista)) {
				//value+=1;				
			}else {
				value-=5;				
			}
		}
		
		//El que fue filosofo
		if(personaje.getDedicacion().equals(Dedicacion.Filosofo)){
			//se dedico a las matematicas, la astronomia y la fisica y no nacio en el 570 AC
			if(personaje.getOtras().equals(OtrasActividades.EstudiosDeMatAstFis) && !personaje.getFecha_nac().equals(FechaNacimiento.AC570)) {
				//value+=1;				
			}else {
				value-=5;				
			}
		}
		
		//El que nacio en el 640 AC 
		if(personaje.getFecha_nac().equals(FechaNacimiento.AC640)){
			//Nacio en atica y no era codificador de leyes
			if(personaje.getLugar_nac().equals(LugarNacimiento.Atica) && !personaje.getDedicacion().equals(Dedicacion.CodificadorLeyes)) {
				//value+=1;				
			}else {
				value-=5;				
			}
		}
		
		return value;

	}
	
	private double aptitudGeneral(PersonajeGrecia personaje){
		double value=10;
		//Tales nacio en el 660 AC y no lucho contra los persas
		if(personaje.getPersonaje().equals(Personaje.Tales)) {
			if(personaje.getFecha_nac().equals(FechaNacimiento.AC660) && !personaje.getOtras().equals(OtrasActividades.LuchaContraPersas)){
				//value+=30;
			}else{
				value-=5;
			}				
		}
		
		//Pitaco fue legislador y no nacio en atica. 
		if(personaje.getPersonaje().equals(Personaje.Pitaco)) {
			if(personaje.getDedicacion().equals(Dedicacion.Legislador) && !personaje.getLugar_nac().equals(LugarNacimiento.Atica)){
				//value+=30;
			}else{
				value-=5;
			}			
		}
		
		//Solon lucho contra los megarenses y no era de mileto
		if(personaje.getPersonaje().equals(Personaje.Solon)) {
			if(personaje.getOtras().equals(OtrasActividades.LuchaContraMegarenses) && !personaje.getLugar_nac().equals(LugarNacimiento.Mileto)){
				//value+=30;
			}else{
				value-=5;
			}					
		}
		
		//Bias era de Priene y no nacio en el 650 AC
		if(personaje.getPersonaje().equals(Personaje.Bias)) {
			if(personaje.getLugar_nac().equals(LugarNacimiento.Priene) && !personaje.getFecha_nac().equals(FechaNacimiento.AC650)){
				//value+=30;
			}else{
				value-=5;
			}			
		}
		
		//El personaje que nacio en tracia.
		if(personaje.getLugar_nac().equals(LugarNacimiento.Tracia)){
			//Fue tirano de Mitilene y no era estadista
			if (personaje.getOtras().equals(OtrasActividades.TiranoDeMitilene) && !personaje.getDedicacion().equals(Dedicacion.Estadista)) {
				//value+=30;				
			}else {
				value-=5;				
			}
		}
		
		//El que fue filosofo
		if(personaje.getDedicacion().equals(Dedicacion.Filosofo)){
			//se dedico a las matematicas, la astronomia y la fisica y no nacio en el 570 AC
			if(personaje.getOtras().equals(OtrasActividades.EstudiosDeMatAstFis) && !personaje.getFecha_nac().equals(FechaNacimiento.AC570)) {
				//value+=30;				
			}else {
				value-=5;				
			}
		}
		
		//El que nacio en el 640 AC 
		if(personaje.getFecha_nac().equals(FechaNacimiento.AC640)){
			//Nacio en atica y no era codificador de leyes
			if(personaje.getLugar_nac().equals(LugarNacimiento.Atica) && !personaje.getDedicacion().equals(Dedicacion.CodificadorLeyes)) {
				//value+=30;				
			}else {
				value-=5;				
			}
		}
		
		return value;

	}


	private double penalizacionPorRepetidos() {
	    double value = 0;

	    value = (repetidosFechaNacimiento() + repetidosLugarNacimiento() + repetidosDedicacion() + repetidosOtrasActividades());
	    
	    return value;
    }

    private int repetidosFechaNacimiento() {

	    return cantidadConFechaNacimiento(FechaNacimiento.AC570) +
	    		cantidadConFechaNacimiento(FechaNacimiento.AC640) +
	    		cantidadConFechaNacimiento(FechaNacimiento.AC650) + 
	    		cantidadConFechaNacimiento(FechaNacimiento.AC660);

    }

    private int cantidadConFechaNacimiento(FechaNacimiento fecha) {
        int repetidos = 0;

        int fechasNacimientoRep = personajes.stream().filter(p -> p.getFecha_nac().equals(fecha)).collect(toList()).size();
        if (fechasNacimientoRep > 1) {repetidos+=fechasNacimientoRep-1;}

        return repetidos;
    }

    private int repetidosLugarNacimiento() {

	    return cantidadConLugarNacimiento(LugarNacimiento.Atica) +
	    		cantidadConLugarNacimiento(LugarNacimiento.Mileto) +
	    		cantidadConLugarNacimiento(LugarNacimiento.Priene) +
	    		cantidadConLugarNacimiento(LugarNacimiento.Tracia);
    }

    private int cantidadConLugarNacimiento(LugarNacimiento lugar) {
        int repetidos = 0;

        int lugarNacimientoRep = personajes.stream().filter(p -> p.getLugar_nac().equals(lugar)).collect(toList()).size();
        if (lugarNacimientoRep > 1) {repetidos+=lugarNacimientoRep-1;}

        return repetidos;
    }

    private int repetidosDedicacion() {

	    return cantidadConDedicacion(Dedicacion.CodificadorLeyes) +
	    		cantidadConDedicacion(Dedicacion.Estadista) +
	    		cantidadConDedicacion(Dedicacion.Filosofo) +
	    		cantidadConDedicacion(Dedicacion.Legislador);
    }

    private int cantidadConDedicacion(Dedicacion dedicacion) {
        int repetidos = 0;

        int dedicacionRep = personajes.stream().filter(p -> p.getDedicacion().equals(dedicacion)).collect(toList()).size();
        if (dedicacionRep > 1) {repetidos+=dedicacionRep-1;}

        return repetidos;
    }

    private int repetidosOtrasActividades() {

	    return cantidadConOtrasActividadess(OtrasActividades.EstudiosDeMatAstFis) +
	    		cantidadConOtrasActividadess(OtrasActividades.LuchaContraMegarenses) +
	    		cantidadConOtrasActividadess(OtrasActividades.LuchaContraPersas) +
	    		cantidadConOtrasActividadess(OtrasActividades.TiranoDeMitilene);
    }

    private int cantidadConOtrasActividadess(OtrasActividades actividad) {
        int repetidos = 0;

        int otrasActividadesRep = personajes.stream().filter(p -> p.getOtras().equals(actividad)).collect(toList()).size();
        if (otrasActividadesRep > 1) {repetidos+=otrasActividadesRep-1;}

        return repetidos;
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
	}

	
	
}
