



/* Valdearcos Trenas, Andrés */
/* Pérez Sánchez, Pedro Daniel */
/* García Bermejo, David  */
/* Prieto Parrilla, Elías */



package data;

import java.io.File;
import java.io.*;


/**
 * Esta clase contiene la escritura de las puntuaciones del fichero. 
 * @author Andrés Valdearcos Trenas, Elías Prieto Parrilla, Pedro Daniel Pérez Sánchez
 * @version 02/06/2019
 *
 */

public class fichero {

	
	/**
	 * Método que escribe en el fichero las puntuaciones del jugador.
	 * @param jugador Nombre del jugador que ha jugado la partida.
	 * @param puntuacionStrng Puntuaciones del jugador.
	 */
	
	
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
