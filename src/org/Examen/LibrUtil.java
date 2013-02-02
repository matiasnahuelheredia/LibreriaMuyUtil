package org.Examen;

import java.io.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
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
	public static Object OrdenarLista (Object lista,String propiedad) throws Exception
	{
    	@SuppressWarnings("unchecked")
		ArrayList<Object> list = (ArrayList<Object>) lista;
    	String PropAnterior= null;
        for (int f=0;f<list.size();f++)
        {
        	
        	Object objeto = ((ArrayList<Object>) lista).get(f);      	
        	
        	String methodNameSet;
        	Method metodos[] = objeto.getClass().getMethods();
        
        		for (int i=0;i<metodos.length;i++)
        		{
        			if(propiedad.equals(metodos[i].getName()))
        			{
        				methodNameSet = metodos[i].getName();
        				System.out.println(methodNameSet);
        				String valor = metodos[i].invoke(objeto, new Object[0]).toString();
        				
        				System.out.println("valor antes"+PropAnterior);
        				System.out.println("valor antes"+valor);
        				if(PropAnterior!=null)
        				{
        				System.out.println(""+valor.compareTo(PropAnterior));
        				}
        	            PropAnterior = valor.toString();
        				
        			}
        		}
        }
        
        //Method metodoSet = per.getClass().getMethod(propiedad, metodos[3].getReturnType());
        //String valor = metodos[3].invoke(per, new Object[0]).toString();
		//System.out.println(valor);
		return list;
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
