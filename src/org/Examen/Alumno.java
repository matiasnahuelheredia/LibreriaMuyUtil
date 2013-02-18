package org.Examen;


import java.io.Serializable;
import java.util.Date;

public class Alumno extends Persona  implements Serializable {

       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int legajo;
       private Date FechaDeIngreso;
	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public Date getFechaDeIngreso() {
		return FechaDeIngreso;
	}

	public void setFechaDeIngreso(Date fechaDeIngreso) {
		FechaDeIngreso = fechaDeIngreso;
	}
       

	
	
	
}
