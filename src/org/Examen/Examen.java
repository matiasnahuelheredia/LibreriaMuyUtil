package org.Examen;




public class Examen {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Persona per = new Persona();
        try {
			per = (Persona)LibrUtil.pedirObjeto(per);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
