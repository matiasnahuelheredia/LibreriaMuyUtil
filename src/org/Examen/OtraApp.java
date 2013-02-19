package org.Examen;

import java.util.ArrayList;

import org.dominio.Alumno;
import org.dominio.LibrUtil;



public class OtraApp {



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
		int legajo = LibrUtil.PreguntarEntero("ingrese numero de legajo que quiera borrar de la lista");
		
		listAlu = LibrUtil.EliminarObjetoLista(listAlu, "Legajo",Integer.toString(legajo));
		LibrUtil.mostrarLista(listAlu);
		
		
        }

	

}


