package org.Examen;

import java.util.ArrayList;




public class Examen {

	/**
	 * @param args
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Persona> lisPer = new ArrayList<Persona>();
		Persona per = null;
		for (int i=0;i<2;i++)
        {
		per = new Persona();
        per = (Persona) LibrUtil.pedirObjeto(per);
        
        lisPer.add(per);        
        }
        
		LibrUtil.OrdenarLista(lisPer, "getEdad");
	}

}
