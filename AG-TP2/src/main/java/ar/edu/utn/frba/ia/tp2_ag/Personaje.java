package main.java.ar.edu.utn.frba.ia.tp2_ag;

import main.java.ar.edu.utn.frba.ia.tp2_ag.Cromosoma.FechaNacimiento;
import main.java.ar.edu.utn.frba.ia.tp2_ag.Cromosoma.LugarNacimiento;
import main.java.ar.edu.utn.frba.ia.tp2_ag.Cromosoma.Dedicacion;
import main.java.ar.edu.utn.frba.ia.tp2_ag.Cromosoma.OtrasActividades;

public class Personaje {

	private FechaNacimiento fecha_nac;
	private LugarNacimiento lugar_nac;
	private Dedicacion dedicacion;
	private OtrasActividades otras;

	public Personaje(FechaNacimiento fechaNac, LugarNacimiento LugarNac, Dedicacion dedic, OtrasActividades actividades) {
		this.fecha_nac = fechaNac;
		this.lugar_nac = LugarNac;
		this.dedicacion = dedic;
		this.otras = actividades;
	}

	public String printPersonaje(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("{");
		buffer.append("fecha nacimiento:"+this.fecha_nac);
		buffer.append(",");
		buffer.append("Lugar Nacimiento:"+this.lugar_nac);
		buffer.append(",");
		buffer.append("Dedicacion:"+this.dedicacion);
		buffer.append(",");		
		buffer.append("Otras Actividades:"+this.otras);
		buffer.append("}");		
		return buffer.toString();
	}

	public FechaNacimiento getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(FechaNacimiento fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public LugarNacimiento getLugar_nac() {
		return lugar_nac;
	}

	public void setLugar_nac(LugarNacimiento lugar_nac) {
		this.lugar_nac = lugar_nac;
	}

	public Dedicacion getDedicacion() {
		return dedicacion;
	}

	public void setDedicacion(Dedicacion dedicacion) {
		this.dedicacion = dedicacion;
	}

	public OtrasActividades getOtras() {
		return otras;
	}

	public void setOtras(OtrasActividades otras) {
		this.otras = otras;
	}

}
