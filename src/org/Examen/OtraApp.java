package org.Examen;

import java.util.ArrayList;

import org.dominio.Alumno;
import org.dominio.LibrUtil;



public class OtraApp {


	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
       
		
		/*ArrayList<Alumno> listAlu = new ArrayList<Alumno>();
		listAlu = (ArrayList<Alumno>) LibrUtil.GetObjetoDesdeArchivo("listaAlumnos");
		LibrUtil.mostrarLista(listAlu);*/
		ArrayList<Alumno> listAlu = new ArrayList<Alumno>();
		Alumno alu = new Alumno();
		alu = (Alumno) LibrUtil.pedirObjeto("Alumno");
		listAlu.add(alu);
		alu = (Alumno) LibrUtil.pedirObjeto("Alumno");
		listAlu.add(alu);
		int legajo = LibrUtil.PreguntarEntero("ingrese numero de legajo");
		
		alu = LibrUtil.buscarObjetoLista(listAlu,"Legajo",Integer.toString(legajo));
		LibrUtil.mostrarObjeto(alu);
		
		
        }

	

}


