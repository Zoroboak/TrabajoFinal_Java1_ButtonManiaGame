/* Valdearcos Trenas, Andrés */
/* Pérez Sánchez, Pedro Daniel */
/* Prieto Parrilla, Elías */
/*David*/


package service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DecimalFormat;


/**
 * Esta clase contiene métodos de comprobación, guardado, generar y el de jugadas. 
 * @author Andrés Valdearcos Trenas, Elías Prieto Parrilla, David García Bermejo, Pedro Daniel Pérez Sánchez
 * @version 03/06/2019
 *
 */
public class process {
	
	
	/**
	 * Método que genera el tablero relleno de 0s.
	 * @return El tablero con la matriz rellena de 0s.
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
	
	
	/**
	 * Método que resetea todos los valores del tablero a 0.
	 * @param matriz El tablero a resetear.
	 * @return El tablero con la matriz reseteada.
	 */
	
	
	private static int[][] generarMatriz(int[][] matriz) {
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = 0;
			}
		}
		
		return matriz;
	}

	
	/**
	 * Método que genera de forma inversa (dado un golpe incrementa el valor de las casillas) una partida con los mismos golpes siempre.
	 * Dichos golpes son los siguientes: 
	 * 			int nivel_fil[] = {1,5,6,3,3,3,3,1,2,3,5,6,1,2,3,4,5,6,1,2,4,6,5,2,3,4,1};
				int nivel_col[] = {3,4,2,4,4,4,4,3,4,6,3,2,1,4,5,6,3,1,2,3,4,5,6,3,2,6,5};
	 * @param matriz El tablero a rellenar.
	 * @param nivel El nivel de juego del tablero actual.
	 * @return El tablero generado de forma NO aleatoria dependiendo del nivel recibido.
	 */
	
	
	public static int[][] generarPartidaPruebas(int[][] matriz, int nivel) {
		
		generarMatriz(matriz);
		int nivel_fil[] = {1,5,6,3,3,3,3,1,2,3,5,6,1,2,3,4,5,6,1,2,4,6,5,2,3,4,1};
		int nivel_col[] = {3,4,2,4,4,4,4,3,4,6,3,2,1,4,5,6,3,1,2,3,4,5,6,3,2,6,5};
		
		int golpes = nivel * 3;
		
		
			
			for (int i = 0; i < golpes; i++) {

				if (matriz[nivel_fil[i]][nivel_col[i]] == 3) {
					matriz[nivel_fil[i]][nivel_col[i]] = 0;
				} else {
					matriz[nivel_fil[i]][nivel_col[i]]++;
				}
				
				if (matriz[nivel_fil[i]+1][nivel_col[i]] == 3) {
					matriz[nivel_fil[i]+1][nivel_col[i]] = 0;
				} else {
					matriz[nivel_fil[i]+1][nivel_col[i]]++;
				}
				
				if (matriz[nivel_fil[i]-1][nivel_col[i]] == 3) {
					matriz[nivel_fil[i]-1][nivel_col[i]] = 0;
				} else {
					matriz[nivel_fil[i]-1][nivel_col[i]]++;
				}
				
				if (matriz[nivel_fil[i]][nivel_col[i]+1] == 3) {
					matriz[nivel_fil[i]][nivel_col[i]+1] = 0;
				} else {
					matriz[nivel_fil[i]][nivel_col[i]+1]++;
				}
				
				if (matriz[nivel_fil[i]][nivel_col[i]-1] == 3) {
					matriz[nivel_fil[i]][nivel_col[i]-1] = 0;
				} else {
					matriz[nivel_fil[i]][nivel_col[i]-1]++;
				}
				
				
				
			}
			
			return matriz;
		
		
	}
	
	
	/**
	 * Método que genera de forma inversa (dado un golpe incrementa el valor de las casillas) una partida de forma aleatoria.
	 * @param matriz El tablero a rellenar.
	 * @param nivel El nivel de juego del tablero actual.
	 * @return El tablero generado de forma aleatoria dependiendo del nivel recibido.
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
	

	/**
	 * Método que comprueba si se ha ganado o no.
	 * @param matrizTablero El tablero con el golpe realizado.
	 * @return Devuelve un booleano que sí es true, ha ganado y sí es false, no ha ganado todavía.
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


	/**
	 * Método que simula la jugada del usuario sobre el tablero.
	 * @param opc El golpe realizado.
	 * @param matrizTablero El tablero con el golpe realizado.
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

	
	/**
	 * Método que borra las calificaciones de todos los niveles. 
	 * @param calificacionNivel Todas las calificaciones de todos los niveles.
	 */
	
	public static void borrarCalificaciones (float[] calificacionNivel) {
		
		for(int i=0; i<calificacionNivel.length;i++) {
			
			calificacionNivel[i]=0.00f;
			
		}
		
	}
	
	
	/**
	 * Método que guarda todas las calificaciones de todos los niveles en el fichero.
	 * @param jugador El nombre del usuario que ha jugado la partida.
	 * @param calificacionNivel La califación obtenida en cada nivel.
	 */
	
	
	public static void guardarCalificaciones(String jugador, float[] calificacionNivel) {
		
		
		Calendar c1 = GregorianCalendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String fecha =sdf.format(c1.getTime());

		DecimalFormat formateador = new DecimalFormat("0.000");
		float puntuacion = 1;
		String puntuacionString = "";
		
		for (int i = 0; i < calificacionNivel.length; i++) {
			puntuacion *= calificacionNivel[i];
		}
		
		puntuacionString = 
				"Puntuación: "+formateador.format(puntuacion)+"; "+
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
