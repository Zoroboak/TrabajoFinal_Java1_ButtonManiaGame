package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class process {

	/*
	 * Entradas
	 * 		-> Void
	 * Salida
	 * 		-> int[][] matriz
	 * Descripción
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

	/*
	 * Entradas
	 * 		-> int nivel
	 * 		-> int[][] matriz
	 * Salida
	 * 		-> int[][] matriz
	 * Descripción
	 * 		-> Genera de forma aleatoria de una en una posición para hacer el juego de forma inversa según el nivel que recibe
	 * 
	 */
	
	public static int[][] generarPartida(int[][] matriz, int nivel) {
		
		int pos1 = 0, pos2 = 0;
		int golpes = nivel * 3;
		for (int i = 0; i < golpes; i++) {
			
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
	 * Descripción
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

	/*
	 * Entradas
	 * 		-> int[][] matrizTablero
	 * Salida
	 * 		-> boolean victoria
	 * Descripción
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

	public static void guardarCalificaciones(String jugador, float[] calificacionNivel) {
		
		
		//obtener la fecha a través de Gregorian calendar
		Calendar c1 = GregorianCalendar.getInstance();
		//obtengo un conversor que permite pasar calendar a String
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//le formateo para obtener un String a través de la fecha
		String fecha =sdf.format(c1.getTime());
		// esto es simplemente para comprobar que la saca bien
		//System.out.println("fecha actual: "+fecha);
		
		
		float puntuacion = 0;
		String puntuacionString = "";
		
		for (int i = 0; i < calificacionNivel.length; i++) {
			puntuacion *= calificacionNivel[i];
		}
		
		puntuacionString = 
				"Puntuación: "+puntuacion+"; "+
				"Nivel 1: "+calificacionNivel[1]+"; "+
				"Nivel 2: "+calificacionNivel[2]+"; "+
				"Nivel 3: "+calificacionNivel[3]+"; "+
				"Nivel 4: "+calificacionNivel[4]+"; "+
				"Nivel 5: "+calificacionNivel[5]+"; "+
				"Nivel 6: "+calificacionNivel[6]+"; "+
				"Nivel 7: "+calificacionNivel[7]+"; "+
				"Nivel 8: "+calificacionNivel[8]+"; "+
				"Nivel 9: "+calificacionNivel[9]+"; "+
				"Fecha: "+fecha;
		
		data.fichero.escribirFichero(jugador, puntuacionString);
		
	}
	
	

}
