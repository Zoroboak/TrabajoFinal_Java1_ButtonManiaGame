



/* Valdearcos Trenas, Andr�s */
/* P�rez S�nchez, Pedro Daniel */
/* Garc�a Bermejo, David  */
/* Prieto Parrilla, El�as */



package data;

import java.io.File;
import java.io.*;


/**
 * Esta clase contiene la escritura de las puntuaciones del fichero. 
 * @author Andr�s Valdearcos Trenas, El�as Prieto Parrilla, Pedro Daniel P�rez S�nchez
 * @version 02/06/2019
 *
 */

public class fichero {

	
	/**
	 * M�todo que escribe en el fichero las puntuaciones del jugador.
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
