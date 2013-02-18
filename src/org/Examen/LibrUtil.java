package org.Examen;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;  
import java.util.Comparator;  
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * @author Matias Nahuel Heredia
 *
 */
public class LibrUtil {

	
public static boolean isDate(String fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
String strFecha = fecha;
@SuppressWarnings("unused")
Date fechaDate = null;
try {
fechaDate = formato.parse(strFecha);
return true;
} catch (ParseException ex) {
return false;
}
}

public static Date DeStringADate(String fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
String strFecha = fecha;
Date fechaDate = null;
try {
fechaDate = formato.parse(strFecha);
return fechaDate;
} catch (ParseException ex) {
ex.printStackTrace();
return fechaDate;
}
}


public static boolean isDouble(String numero){

try{
Double.parseDouble(numero);
return true;
}
catch (Exception ex)
{
return false;
}

}



public static Date PreguntarFecha(String pregunta)
{
	Date fecha;
	String linea = "";
	do
	{
		System.out.println(pregunta);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		 try {
			linea = br.readLine();
		} catch (IOException e) {
		} 
		
	}while(isDate(linea)==false);
	fecha = DeStringADate(linea);
	return fecha;
}



	
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
	public static Object GetObjetoDesdeArchivo(String nombreArchivo) throws IOException, ClassNotFoundException,FileNotFoundException
	{
		FileInputStream fis = new FileInputStream(nombreArchivo+".bin");
		ObjectInputStream in = new ObjectInputStream(fis);
		
		return in.readObject();
		}

	/**
	 * 
	 * @param cadena indica una cadena de caracteres que se transformara en un objeto
	 * @param finLinea indica un nuevo objeto
	 * @param separador separador entre propiedades del objeto
	 * @param nombreDeClase
	 * @return lista del tipo del objeto
	 * TODO la excepcion se produce cuando la cadena se ingresa mal por lo tanto se debe validar afuera del metodo y no se valida dentro de este
	 */
	@SuppressWarnings({ "rawtypes" }) 
	public static List pedirObjetosPorCadena(String cadena,String finLinea,String separador,String nombreDeClase) throws Exception
	{
		
		String methodNameSet;
		ArrayList<Object> list = new ArrayList<Object>();
		String[] filas = cadena.split(finLinea);//separa los que tienen el caracter separador que separa las filas
		Class clazz = Class.forName(LibrUtil.class.getPackage().getName()+"."+nombreDeClase);//obtengo la clase del tipo especificado por parametro
	    for (int i=0;i<filas.length;i++)//recorre las filas
	    {
	    String[] columnas = filas[i].split(separador);//en cada fila separa las columnas
	    
	    Object objec = clazz.getConstructor(null).newInstance(null);//instancia el objeto
	    
	    Method metodos[] = clazz.getMethods();// obtiene los metodos de la clase
	    int countColum=0;//cuenta posicion columnas
	    	for (int f=0;f<columnas.length*2;f++)// recorre los metodos del objeto para encontrar setters y asignarle valores de la cadena
	    	{	
	    		methodNameSet = metodos[f].getName();//nombre del metodo del objeto
	    		if (methodNameSet.substring(0, 3).contains("set"))//si el metodo es un set
	    		{	    			
	    		   String tipoDato = metodos[f].getParameterTypes()[0].getSimpleName();
	    		       
	    			if (tipoDato.equals("String"))
	    			{    		 		
	    				metodos[f].invoke(objec, columnas[countColum]);
	    			}
    		
	    			if (tipoDato.equals("int"))
	    			{    		 		
	    				metodos[f].invoke(objec, Integer.parseInt(columnas[countColum]));
	    			}
	    		      
	    		
	    		
	    		countColum++;//aumenta el contador de columnas cada vez que encuentra un metodo set
	    		}
	    		
	    	}
	    list.add(i, objec);
	    }
		
		return  list;
		
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
	public static int PreguntarEntero(String preguntar) throws IOException
	{
		String linea;
		do{
		System.out.println(preguntar);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		 linea = br.readLine();
		
		}while (isInteger(linea)==false);
		return Integer.parseInt(linea);
	}
	
	
	public static int PreguntarDouble(String preguntar) throws IOException
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
    		if (methodNameSet.substring(0,3).equals("get"))
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
    
    
    
    /**
     * obtiene un objeto a partir de 
     * @param nombre
     * @return retorna el objeto sin concatenar
     * @throws Exception
     */
    
    @SuppressWarnings("unchecked")
	public static Object pedirObjeto (String nombre) throws Exception
    {
    	System.out.println("-------------------");
        System.out.println("----"+nombre);
        System.out.println("-------------------");
		@SuppressWarnings("rawtypes")
		Class clazz = Class.forName(LibrUtil.class.getPackage().getName()+"."+nombre);//obtengo la clase del tipo especificado por parametro
        Method listaMetodos[] = clazz.getMethods();//obtengo los metodos de la clase
        Object obj;
        
        obj = clazz.getConstructor(null).newInstance(null);//instancio el objeto 
        
        for (int i=0;i<listaMetodos.length;i++)
        {
        	if(listaMetodos[i].getName().substring(0, 3).contains("set"))
        	{
        	
        
                   String tipoParametro= listaMetodos[i].getParameterTypes()[0].getSimpleName();
                   		if (tipoParametro.equals("String"))
                   		{
                   			obj.getClass().getMethod(listaMetodos[i].getName(), listaMetodos[i].getParameterTypes()[0]).invoke(obj, Preguntar("Ingrese el "+listaMetodos[i].getName().substring(3)));
                   		}
        	
                   		if (tipoParametro.equals("int"))//en caso de que el metodo set
                   		{
                   			obj.getClass().getMethod(listaMetodos[i].getName(), listaMetodos[i].getParameterTypes()[0]).invoke(obj, PreguntarEntero("Ingrese el "+listaMetodos[i].getName().substring(3)));
                   		}
                        
                   		if (tipoParametro.equals("Date"))//en caso de que el metodo set
                   		{
                   			obj.getClass().getMethod(listaMetodos[i].getName(), listaMetodos[i].getParameterTypes()[0]).invoke(obj, PreguntarFecha("Ingrese la "+listaMetodos[i].getName().substring(3)));
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
