package org.Examen;

import java.awt.List;
import java.util.ArrayList;




public class Examen {

	/**
	 * @param args
	 * @throws Exception 
	 */
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		Alumno alu= new Alumno();
		ArrayList<Alumno> listalu = new ArrayList<Alumno>();
		for (int i=0;i<3;i++)
		{
			alu = (Alumno) LibrUtil.pedirObjeto("Alumno");
			listalu.add(alu);
		}
		
        LibrUtil.mostrarLista(listalu);
        
	}

}
