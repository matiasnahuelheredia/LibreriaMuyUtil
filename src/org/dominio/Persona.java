package org.dominio;

import java.io.Serializable;

public abstract class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
		private String nombre;
		private String apellido;
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellido() {
			return apellido;
		}
		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
		
}
