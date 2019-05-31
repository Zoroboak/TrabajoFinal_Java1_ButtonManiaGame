/**/
/**/
/**/
/**/

package service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DecimalFormat;
public class process {

	/*
	 * Entradas
	 * 		-> Void
	 * Salida
	 * 		-> int[][] matriz
	 * Descripci�n
	 * 		-> Genera un tablero relleno de 0
	 * 
	 */
	
	public static int[][] generarMatriz() {

		int[][] matriz = new int[8][8];
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = 0;
			}
		}
		
		return matriz;
	}
	
	private static int[][] generarMatriz(int[][] matriz) {
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = 0;
			}
		}
		
		return matriz;
	}

	/*
	 * Entradas
	 * 		-> int nivel
	 * 		-> int[][] matriz
	 * Salida
	 * 		-> int[][] matriz
	 * Descripci�n
	 * 		-> Genera de forma aleatoria de una en una posici�n para hacer el juego de forma inversa seg�n el nivel que recibe
	 * 
	 */
	
	public static int[][] generarPartida(int[][] matriz, int nivel) {
		
		generarMatriz(matriz);
		
		int pos1 = 0, pos2 = 0;
		//System.out.println(nivel);
		int golpes = nivel * 3;
		for (int i = 0; i < golpes; i++) {
			//System.out.println("Generando jugada inversa "+i);
			
			pos1 = (int) ((Math.random()*6)+1);
			pos2 = (int) ((Math.random()*6)+1);
			
			if (matriz[pos1][pos2] == 3) {
				matriz[pos1][pos2] = 0;
			} else {
				matriz[pos1][pos2]++;
			}
			
			if (matriz[pos1+1][pos2] == 3) {
				matriz[pos1+1][pos2] = 0;
			} else {
				matriz[pos1+1][pos2]++;
			}
			
			if (matriz[pos1-1][pos2] == 3) {
				matriz[pos1-1][pos2] = 0;
			} else {
				matriz[pos1-1][pos2]++;
			}
			
			if (matriz[pos1][pos2+1] == 3) {
				matriz[pos1][pos2+1] = 0;
			} else {
				matriz[pos1][pos2+1]++;
			}
			
			if (matriz[pos1][pos2-1] == 3) {
				matriz[pos1][pos2-1] = 0;
			} else {
				matriz[pos1][pos2-1]++;
			}
			
		}
		
		return matriz;
		
	}
	
	/*
	 * Entradas
	 * 		-> int posicion jugada
	 * Salida
	 * 		-> void
	 * Descripci�n
	 * 		-> Simula la jugada del jugador sobre el tablero
	 * 
	 */
	
	

	/*
	 * Entradas
	 * 		-> int[][] matrizTablero
	 * Salida
	 * 		-> boolean victoria
	 * Descripci�n
	 * 		-> Indica si el juego ha finalizado o no
	 * 
	 */
	
	public static boolean comprobarVictoria(int [][] matrizTablero) {
		
		boolean victoria = true;
		
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				if (matrizTablero[i][j] != 0) {
					victoria = false;
				}
			}
		}
		
		return victoria;
		
	}

	/*
	 * Entradas
	 * 		-> int posicion jugada
	 * Salida
	 * 		-> void
	 * Descripci�n
	 * 		-> Simula la jugada del jugador sobre el tablero
	 * 
	 */
	
	public static void jugarFicha(int opc, int[][] matrizTablero) {
			
			
			int a = opc/10;
			int c = opc%10;
			
			for ( int i = 0; i < 7; i++) {
				for ( int j = 0; j < 7; j++) {
					if( (i == a) && (j == c)) {
						if(matrizTablero[i][j]==0)
							matrizTablero[i][j]=3;
						else
						matrizTablero[i][j]--;
					}
					else if ((i == a-1) && (j == c)) {
						
						if(matrizTablero[i][j]==0)
							matrizTablero[i][j]=3;
						else
						matrizTablero[i][j]--;
						
					}
					
					else if ((i == a+1)&&(j == c)) {
						if(matrizTablero[i][j]==0)
							matrizTablero[i][j]=3;
						else
						matrizTablero[i][j]--;
					}
					
					else if ((i == a)&&(j == c-1)) {
						
						if(matrizTablero[i][j]==0)
							matrizTablero[i][j]=3;
						else
						matrizTablero[i][j]--;
					}
					
					else if ((i == a)&&(j == c+1)) {
						if(matrizTablero[i][j]==0)
							matrizTablero[i][j]=3;
						else
						matrizTablero[i][j]--;
					}
				}
			}
		}

	
	public static void borrarCalificaciones (float[] calificacionNivel) {
		
		for(int i=0; i<calificacionNivel.length;i++) {
			
			calificacionNivel[i]=1f;
			
		}
		
	}
	
	public static void guardarCalificaciones(String jugador, float[] calificacionNivel) {
		
		
		//obtener la fecha a trav�s de Gregorian calendar
		Calendar c1 = GregorianCalendar.getInstance();
		//obtengo un conversor que permite pasar calendar a String
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//le formateo para obtener un String a trav�s de la fecha
		String fecha =sdf.format(c1.getTime());
		// esto es simplemente para comprobar que la saca bien
		//System.out.println("fecha actual: "+fecha);
		
		DecimalFormat formateador = new DecimalFormat("#.###");
		float puntuacion = 1;
		String puntuacionString = "";
		
		for (int i = 0; i < calificacionNivel.length; i++) {
			puntuacion *= calificacionNivel[i];
		}
		
		puntuacionString = 
				"Puntuaci�n: "+formateador.format(puntuacion)+"; "+
				"Nivel 1: "+formateador.format(calificacionNivel[1])+"; "+
				"Nivel 2: "+formateador.format(calificacionNivel[2])+"; "+
				"Nivel 3: "+formateador.format(calificacionNivel[3])+"; "+
				"Nivel 4: "+formateador.format(calificacionNivel[4])+"; "+
				"Nivel 5: "+formateador.format(calificacionNivel[5])+"; "+
				"Nivel 6: "+formateador.format(calificacionNivel[6])+"; "+
				"Nivel 7: "+formateador.format(calificacionNivel[7])+"; "+
				"Nivel 8: "+formateador.format(calificacionNivel[8])+"; "+
				"Nivel 9: "+formateador.format(calificacionNivel[9])+"; "+
				"Fecha: "+fecha;
		
		data.fichero.escribirFichero(jugador, puntuacionString);
		
	}
	
	

}
