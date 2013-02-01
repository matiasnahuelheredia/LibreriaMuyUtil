package org.Examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class LibrUtil {

	
	public static boolean isInteger(String str) {
	    try {
	        Integer.parseInt(str);
	        return true;
	    } catch (NumberFormatException nfe) {}
	    return false;
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
    /*public static Object OrdenarLista (Object lista,String propiedad) throws Exception
	{
    	@SuppressWarnings("unchecked")
		ArrayList<Object> list = (ArrayList<Object>) lista;
        Object per = list.get(0);
        String methodNameSet;
        Method metodos[] = per.getClass().getMethods();
        int countname=0;
        for (int i=0;i<metodos.length;i++)
        {
        	if(propiedad.equals(metodos[i].getName()))
        	{
        		
        	}
        }
        methodNameSet = metodos[3].getName().replaceFirst("get", "set");
        System.out.println(methodNameSet);
        Method metodoSet = per.getClass().getMethod(propiedad, metodos[3].getReturnType());
        String valor = metodos[3].invoke(per, new Object[0]).toString();
		System.out.println(valor);
		return list;
	}
    */
    /**
     * Lista los metodos getters definidos por el programador
     *  para obtener las propiedades y sus valores
     *  mostrando tambien el nombre del objeto
     * @param obj objeto que se quiere listar
     * @throws Exception
     * @author Matias Nahuel Heredia
     */
    public static void mostrarObjeto (Object obj) throws Exception
	{
    	String methodNameSet;
    	Method metodos[] = obj.getClass().getMethods();
    	System.out.println("----------------------------");
    	System.out.println(obj.getClass().getSimpleName());
    	System.out.println("----------------------------");
    	for (int i=0;i<metodos.length-9;i++)
    	{
    		
    		methodNameSet = metodos[i].getName();
    		if (methodNameSet.contains("get"))
    		{
    		System.out.println(methodNameSet.replaceFirst("get", ""));
    		
    		String valor = metodos[i].invoke(obj, new Object[0]).toString();
    		System.out.println(valor);
    		}
    	}
	}
    
    public static String Preguntar(String preguntar) throws IOException
	{
		System.out.println(preguntar);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String linea = br.readLine(); 
		return linea;
	}
    
    public static Object pedirObjeto (Object obj) throws Exception
	{
    	String methodNameSet;
    	Method metodos[] = obj.getClass().getMethods();
    	System.out.println("----------------------------");
    	System.out.println(obj.getClass().getSimpleName());
    	System.out.println("----------------------------");
    	for (int i=0;i<metodos.length-9;i++)
    	{
    		
    		methodNameSet = metodos[i].getName();
    		if (methodNameSet.contains("set"))
    		{
    		
    		String tipoDato = metodos[i].getParameterTypes()[0].getSimpleName();
    		    if (tipoDato.equals("String"))
    		    {
    		       String respuesta = Preguntar("ingrese "+ methodNameSet.replaceFirst("set",""));    		 		
    		       metodos[i].invoke(obj, respuesta);
    		    }
    		
    		    if (tipoDato.equals("int"))
    		    {
    		      int respuesta = PreguntaEntero("ingrese "+ methodNameSet.replaceFirst("set",""));    		 		
    		      metodos[i].invoke(obj, respuesta);
    		    }
    		
    		
    		
    		}
    	}
    	return obj;
	}
    
    
    @SuppressWarnings("unchecked")
	public static void mostrarLista (Object list) throws Exception
    {
    	ArrayList<Object> lisObj = null;
    	try
    	{
    		lisObj = (ArrayList<Object>) list;
    	}
    	catch(ClassCastException ex)
    	{
    	System.out.println("no hay nada en la lista para mostrar");	
    	}
    	finally
    	{
    	
    	if (lisObj!=null)
    	{
    	System.out.println("----------------------");
    	System.out.print("Listado de  ");
    	System.out.println(lisObj.get(0).getClass().getSimpleName());
    	
    	    for( Iterator<Object> it = lisObj.iterator(); it.hasNext(); ) { 
    	    	Object ProvisObjeto = (Object)it.next();
    	    	mostrarObjeto(ProvisObjeto);
		     }
    	
    	
    	}
    	}
    	
    	
    	
    	
    	
    }

}
