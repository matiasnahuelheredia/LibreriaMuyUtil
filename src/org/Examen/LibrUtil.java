package org.Examen;
import java.util.Collections;  
import java.util.Comparator;  
import java.io.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * @author Matias Nahuel Heredia
 *
 */
public class LibrUtil {

	public static void GuardarObjeto(Object obj,String nombreArchivo)
	{
		try {
			FileOutputStream fos = new FileOutputStream(nombreArchivo+".bin");
		    ObjectOutputStream out = new ObjectOutputStream(fos);
		    out.writeObject(obj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}

	   /**
	      * @param obj se ingresa el objeto que se quiere leer para obtener el nombre del archivo
	      * @return retorna el objeto que quiere leer
	      * @throws IOException
	      * @throws ClassNotFoundException
	      */
	public static Object GetObjeto(Object obj,String nombreArchivo) throws IOException, ClassNotFoundException,FileNotFoundException
	{
		FileInputStream fis = new FileInputStream(nombreArchivo+".bin");
		ObjectInputStream in = new ObjectInputStream(fis);
		
		return in.readObject();
		}



/**
 * 	metodo que sirve para verificar si la cadena de caracteres 
 *  es entera o no
 * @param str Cadena de caracteres
 * @return true si la cadena es entera y false si la cadena no es entera
 */
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
    /**
     * 
     * @param lista
     * @param propiedad string del metodo que por el que se quiere 
     * ordenar ejemplo getNombre ordena por el metodo getNombre una lista
     * @return Devuelve la lista ordenada
     * @throws Exception
     */
	// TODO Ordenar lista
	@SuppressWarnings({ "unchecked", "rawtypes" })  
 public static void ordena(List lista, final String propiedad) {  
  Collections.sort(lista, new Comparator() {  
     
   public int compare(Object obj1, Object obj2) {  
      
    Class clase = obj1.getClass();  
    String getter = "get" + Character.toUpperCase(propiedad.charAt(0)) + propiedad.substring(1);  
    try {  
     Method getPropiedad = clase.getMethod(getter);  
       
     Object propiedad1 = getPropiedad.invoke(obj1);  
     Object propiedad2 = getPropiedad.invoke(obj2);  
       
     if(propiedad1 instanceof Comparable && propiedad2 instanceof Comparable) {  
      Comparable prop1 = (Comparable)propiedad1;  
      Comparable prop2 = (Comparable)propiedad2;  
      return prop1.compareTo(prop2);  
     }//CASO DE QUE NO SEA COMPARABLE  
     else {  
      if(propiedad1.equals(propiedad2))  
       return 0;  
      else  
       return 1;  
  
     }  
    
    }  
    catch(Exception e) {  
     e.printStackTrace();  
    }  
    return 0;  
   }  
  });  
 }   
	
	
    /**
     * Lista los metodos getters definidos por el programador
     *  para obtener las propiedades y sus valores
     *  mostrando tambien el nombre del objeto
     * @param obj objeto que se quiere listar
     * @throws Exception
     
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
    /**
     * Pregunta mediante una cadena de string que se imprime
     * en pantalla y devuelve la respuesta en el return
     * @param preguntar pregunta formulada
     * @return respuesta
     * @throws IOException
     */
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
