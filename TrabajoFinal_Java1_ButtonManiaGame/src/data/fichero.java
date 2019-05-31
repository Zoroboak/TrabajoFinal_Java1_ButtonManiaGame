/**/
/**/
/**/
/**/



package data;

import java.io.File;
import java.io.*;


public class fichero {

	public static void escribirFichero(String jugador, String puntuacionString) {
			
	
		//nombre del fichero
		String  nombrefichero ="calificaciones.txt";
		FileWriter fw = null;
		PrintWriter pw = null;
		File file = new File (nombrefichero);
		
		
		try {
			fw=new FileWriter(nombrefichero,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw=new PrintWriter (fw);
		pw.println("Jugador: "+jugador+"; "+puntuacionString);
		
	
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
