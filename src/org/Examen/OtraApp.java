package org.Examen;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;

public class OtraApp {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static boolean isInteger(String str) {
	    try {
	        Integer.parseInt(str);
	        return true;
	    } catch (NumberFormatException nfe) {}
	    return false;
	}
	
    public static String Preguntar(String preguntar) throws IOException
	{
		System.out.println(preguntar);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String linea = br.readLine(); 
		return linea;
	}
    
    public static int PreguntaEntero(String preguntar) throws IOException
	{
		String linea;
		do{
		System.out.println(preguntar);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		 linea = br.readLine();
		
		}while (isInteger(linea)==false);
		return Integer.parseInt(linea);
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        Class clazz = Class.forName(OtraApp.class.getPackage().getName()+".Alumno");
        Method listaMetodos[] = clazz.getMethods();
        Object obj;
        
        obj = clazz.getConstructor(null).newInstance(null);
        
        for (int i=0;i<listaMetodos.length;i++)
        {
        	if(listaMetodos[i].getName().substring(0, 3).contains("set"))
        	{
        	
        
            String tipoParametro= listaMetodos[i].getParameterTypes()[0].getSimpleName();
        	if (tipoParametro.equals("String"))
        	{
        	obj.getClass().getMethod(listaMetodos[i].getName(), listaMetodos[i].getParameterTypes()[0]).invoke(obj, Preguntar("Ingrese el "+listaMetodos[i].getName().substring(3)));
        	}
        	
        	if (tipoParametro.equals("int"))
        	{
        	obj.getClass().getMethod(listaMetodos[i].getName(), listaMetodos[i].getParameterTypes()[0]).invoke(obj, PreguntaEntero("Ingrese el "+listaMetodos[i].getName().substring(3)));
        	}
        	
        	
        	
        	}
        }

	}

}
