/* Valdearcos Trenas, Andrés */
/* Pérez Sánchez, Pedro Daniel */
/* Prieto Parrilla, Elías */
/*David*/


package ui;

import java.util.Scanner;
import java.text.DecimalFormat;


/**
 * Esta clase contiene el método principal del programa y otros métodos.
 * @author  Andrés Valdearcos Trenas, Elías Prieto Parrilla, Pedro Daniel Pérez Sánchez
 * @version 03/06/2019
 */

public class PedroPerez {
	public static Scanner sc= new Scanner (System.in);
	
	
	/**
	 * Este método es el principal en el que transcurre la ejecución del programa.
	 * @param args : argumentos que se le puede pasar. En este caso, no le pasamos.
	 */
	
	
	public static void main(String[] args) {
		
		//Declaraciones del main
		int nivel =  5;  //por defecto nivel normal [1-9]
		int[][] matriz = service.process.generarMatriz(); //Generar matriz todo a 0 por defecto 
		//String jugador = "Player";
		float puntuacion_total=0f;
		//Iniciando Partida
		int cont = 0;
		float calificacionNivel[] = {1,1,1,1,1,1,1,1,1,1,1}; //Vector que almacena los las puntuaciones de la partida
		int opcmenu = 2; //variable temporal que usamos para transportar valores del menu, por defecto su valor es "2"
		/* opcmenu = 1 //Se esta recomenzando
		   opcmenu = 2 //Se ha iniciado una nueva partida
		   opcmenu = 3 //Se ha solicitado la calificaci�n
		   opcmenu = 4 //Se ha solicitado cambio de nivel
		   opcmenu = -2 //se sale 
		*/

		//Variables auxiliares
		
		boolean victoria = false; //por defecto la victoria es falsa
		boolean menu = false;
		int[][] matrizTablero = new int[8][8];
		int[][] matrizTableroCopia = new int[8][8];
		int opc = 0; // Variable auxiliar que se usa para menus
		float puntuacion = 0; //Variable que usamos para almacenar la puntuaci�n
		String ultimaJugada = "0 0"; // Variable donde almacenamos la ultima jugada del tablero
		boolean[]nivelJugado= {false,false,false,false,false,false,false,false,false,false,false}; 
		float calificacionZero = 0.00f;
		float []calificacionVisual = new float[9];
		DecimalFormat formateador = new DecimalFormat("0.00");
		
		//Bucle Partida
		do{
			
			//Reseteo el contador de golpess
			//cont = 0;
			//Compruebo que tipo de partida es
			if(opcmenu==2){ //Si es una nueva partida:
				if(cont!=0) {
					puntuacion=0.5f;
					puntuacion_total*=puntuacion;  
					calificacionNivel[nivel] *=puntuacion;
					
				}
				calificacionVisual[nivel]= 0;
				matrizTablero = service.process.generarMatriz(); 
				matrizTablero = generarNuevaPartida(nivel, matriz, matrizTableroCopia);
				cont = 0;
			}else if(opcmenu==4) {
				matrizTablero = service.process.generarMatriz();
				matrizTablero = generarNuevaPartida(nivel, matriz, matrizTableroCopia);
				cont = 0;
			}else if(opcmenu==1){ //Si se esta recomenzando: 
				
				calificacionVisual[nivel]= 0;
				System.out.println("Recomenzando");
				//matrizTablero = matrizTableroCopia.clone();
				for (int i = 0; i < matrizTableroCopia.length; i++) {
					for (int j = 0; j < matrizTableroCopia.length; j++) {
						matrizTablero[i][j] = matrizTableroCopia[i][j];
					}
				}
				cont = 0;
			}

			//Bucle Tablero
			do{ 
				victoria = false; //por defecto la victoria es falsa
				menu=false; //por defecto la victoria es falsa
				
				
				//Mostramos tablero
				opc = interfaz(matrizTablero, nivel, cont,true,calificacionVisual,ultimaJugada);//debe validar la entrada (opc menu o jugada valida)
				
				ultimaJugada=opc/10+" "+opc%10;
				//Comprobamos si el caracter introducido es entrada de menu
				if ((opc==1)||(opc==2)||(opc==3)||(opc==4)||(opc==-2)){
					menu=true;
					switch(opc) {
						case 1: //ReComenzar
						opcmenu = 1;
						break;

						case 2: //Nuevo Tablero
						opcmenu = 2;
						break;

						case 3: //Calificaci�n
						opcmenu = 3;
						break;

						case 4: //Cambiar Nivels
						opcmenu = 4;
						break;
						
						case -2: //Salir
						opcmenu = -2;
						break;
						}
							
				}else{
					//Si no es un menu, solo puede ser una jugada
					try{
						
						
						
						service.process.jugarFicha(opc,matrizTablero);
						if(nivelJugado[nivel] == false) {
							calificacionNivel[nivel]=1.00f;
							
						}nivelJugado[nivel] = true;
						cont++;
						puntuacion = ((float)nivel*3)/(float)cont;
						calificacionVisual[nivel]= puntuacion;
						nivelJugado[nivel] = true;
						if(service.process.comprobarVictoria(matrizTablero)==true){
							victoria=true;
						}
						
					}catch(Exception e){
						System.out.println("Algo ha fallado durante la partida, se empezara una nueva");
					}
				}
				
			}while(victoria==false && menu==false);

			//Victoria 
			if(victoria==true){
				puntuacion = ((float)nivel*3)/(float)cont;
				calificacionVisual[nivel]= puntuacion;
				puntuacion_total*=puntuacion;  
				calificacionNivel[nivel]*=puntuacion;
				interfaz(matrizTablero, nivel, cont,false,calificacionVisual, ultimaJugada);
				
				
				if(cont<(nivel*3)) {
					System.out.println("Extraordinariamente  bien:  Hecho  en  "+cont+"  golpes");
					System.out.println();
				}else if(cont==(nivel*3)) {
					System.out.println("Perfecto.  Hecho  en "+cont+" golpes");
					System.out.println();
				}else if(cont>(nivel*3)){
					System.out.println("Hecho  en "+cont+" golpes");
					System.out.println();
					
				}
				
				
				
				System.out.println("¡Felicidades! Terminaste el tablero en "+cont+" golpes");
				System.out.println("Tu calificación en este nivel es de "+puntuacion+" puntos");
				System.out.println();
				System.out.println();
			
				
				
				cont=0;
				
				opc = getDato("¿Quieres seguir jugando ? (1 para Si, -1 para No) : ",2);
				if(opc==1){
					ultimaJugada="0 0";
					opcmenu=2; //nuevo tablero
				}else if(opc==-1){
					opcmenu=-2; //salir
				}
				
			}

			if(opcmenu==03){
				
				for(int j = 0; j < nivelJugado.length;j++) {
					if(nivelJugado[j] == false)
						calificacionNivel[j] = 0.00f;

				}
				
				
				System.out.println("+---------------------------------------------------------------------------+");
				System.out.println("| Calificaciones:                                                           |");
				System.out.println("|                                                                           |");
				System.out.println("| 1- Para bebés (3 golpes): "+formateador.format(calificacionNivel[1])+"                                            |");
				System.out.println("| 2- Aprendizaje (6 golpes): "+formateador.format(calificacionNivel[2])+"                                            |");
				System.out.println("| 3- Simple (9 golpes): "+formateador.format(calificacionNivel[3])+"                                                 |");
				System.out.println("| 4- Casi normal (12 golpes): "+formateador.format(calificacionNivel[4])+"                                           |");
				System.out.println("| 5- Normal (15 golpes): "+formateador.format(calificacionNivel[5])+"                                                |");
				System.out.println("| 6- Difícil (18 golpes): "+formateador.format(calificacionNivel[6])+"                                               |");
				System.out.println("| 7- Puto amo (21 golpes): "+formateador.format(calificacionNivel[7])+"                                              |");
				System.out.println("| 8- Imposible (24 golpes): "+formateador.format(calificacionNivel[8])+"                                             |");
				System.out.println("| 9- Diez en progra (27 golpes): "+formateador.format(calificacionNivel[9])+"                                 |");
				System.out.println("|                                                                           |");
				System.out.println("+---------------------------------------------------------------------------+");
				
				System.out.print(" ¿ Borrar todas las calificaciones ? (1 para Sí, 0 para No ):");
				int op =0;
				try {
					op= sc.nextInt();
				}
				catch(Exception e) {
					System.out.println("No se puede introducir otra cosa que no sea un número.");
					
					op =0;
				}
				if(op==0) {			
					
				}
				else if(op==1) {
					service.process.borrarCalificaciones(calificacionNivel);
				}
				else {
					System.out.println("El número solamente puede ser un 0 o un 1. Introduzca de nuevo: ");
					op= sc.nextInt();
				}
				System.out.println();
				System.out.println("+---------------------------------------------------------------------------+");
				
				
			}

		
			if(opcmenu==4){
				
				if(cont!=0) {
					
					puntuacion=0.5f;
					puntuacion_total*=puntuacion;  
					calificacionNivel[nivel] *=puntuacion;
			
				}
			
				calificacionVisual[nivel]= 0.00f;
				
				opcmenu = 4;
				opc = getDato("Escoge nuevo nivel (1-9), a mayor numero, más difícil: ",3);
				nivel=opc; 
				System.out.println("Se ha cambiado a nivel "+nivel+", correctamente");
				System.out.println();
				cont=0;
			}

			if(opcmenu==-2){
				if(cont!=0) {
					
					puntuacion=0.5f;
					puntuacion_total*=puntuacion;  
					calificacionNivel[nivel] *=puntuacion;
					
				}
				

				
				System.out.println("Saliendo del juego");
				
				guardarCalificacionTablero(calificacionNivel);
				
				System.out.println("Gracias por Jugar :D");
				System.out.println();
				System.out.println("Juego cerrado.");
			}
			
		}while(opcmenu!=-2);
		
	
	}

	
	/**
	 * Método que genera una nueva partida llamando al método generarPartida.
	 * @param nivel Lo pasamos para que genere una nueva partida de dicho nivel.
	 * @param matriz con esta matriz hacemos que se establezca a 0 todas las filas y columnas de la matriz.
	 * @param matrizTableroCopia para copiar en la matriz la nueva matriz obtenida aleatoriamente.
	 * @return la matriz obtenida aleatoriamente para trabajar sobre ella.
	 */
	
	
	private static int[][] generarNuevaPartida(int nivel, int[][] matriz, int[][] matrizTableroCopia) {
		int[][] matrizTablero;
		matrizTablero = service.process.generarPartida(matriz, nivel);
		//matrizTableroCopia = matrizTablero.clone();
		for (int i = 0; i < matrizTableroCopia.length; i++) {
			for (int j = 0; j < matrizTableroCopia.length; j++) {
				matrizTableroCopia[i][j] = matrizTablero[i][j];
			}
		}
		return matrizTablero;
	}

	
	/**
	 * Guarda (o no) todas las calificaciones de todos los niveles obtenidas durante la partida. 
	 * @param calificacionNivel Las calificaciones obtenidas durante la partida.
	 */
	
	
	private static void guardarCalificacionTablero(float[] calificacionNivel) {
		String jugador;
		int opc;
		try {
			opc = getDato("Quieres guardar tus calificaciones?  (1 para Si, 0 para No): ",4);
			if(opc==1){
				jugador = getDato("Introduce tu nombre: ");
				service.process.guardarCalificaciones(jugador, calificacionNivel);
				System.out.println("Se ha guardado la partida correctamente");
			}
			else {
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Método que comprueba si el dato que recibe (el nombre para guardar la partida) es correcto.
	 * @param string El nombre para guardar la partida.
	 * @return El nombre para poder guardar las calificaciones. 
	 */
	
	
	private static String getDato(String string) {
		
		
		boolean v = true;
		String auxs = null;
		
		//Valor a solicitar: Palabra introducida por el usuario
		do {
			try {
				//Doy por supuesto que el valor introducido es correcto
				v = true; 
				//Mostrar texto pasado por parametro
				System.out.println(string);
				//Pido una Palabra entre 3 y 15 caracteres
				auxs = sc.next();
				//Si el rango no es correcto, repetimos el while
				if(auxs.length()<3||auxs.length()>15) {
					System.out.println("Debes introducir un nombre entre 3 y 15 caracteres");
					v=false;
				}
				//Si hay un numero, lo volvemos a pedir
				for (int i = 0; i < auxs.length(); i++) {
					if((auxs.charAt(i)>=48)&&(auxs.charAt(i)<=57)) {
						i=auxs.length();
						System.out.println("Debes introducir un nombre entre 3 y 15 caracteres, no numeros");
						v=false;
					}
				}
			}
			catch(Exception e){
				System.out.println("¡La palabra introducida no es valida!");
				sc.next();
				v=false;
			}
		}while(!v);
		
		//devuelvo la palabra
		return auxs;
	}

	
	/**
	 * Método que según el parámetro que se pase, valida para la interfaz del juego.
	 * @param string Palabras que recibe la línea de texto para cada caso.
	 * @param parametro Cuando es un 1, verifica si se introduce bien o no la fila y columna del golpe realizado.
	 * 					Cuando es un 2, verifica que si se introduce bien el rango de salir o no. 
	 * 					Cuando es un 3, verifica que el rango del cambio de nivel es correcto.
	 * 					Cuando es un 4, verifica para que se meta bien el rango de guardar o no guardar. 
	 * @return La opción ya verificada en su rango correcto. 
	 */

	
	private static int getDato(String string, int parametro) {
		
		

		//Declaro el objeto Scanner
	
		boolean v = true;
		String auxs = null;
		char auxc = '0';
		int aux = 0;
		String text = "";
		char[] opc1 = {'0','0'};
		String myString = "0";
		
		
		
		switch (parametro) {
		case 1:
	 
			
			do {
				try {
					v = false;
							try {
							//Seteo todas las variables (Forma rapida)
							auxc = '0';
							aux = 0;
							text = "";
							opc1[0] = '0';
							opc1[1] = '0';
							myString = "0";
							
							//Mostrar texto pasado por parametro
							System.out.print(string);
							//aux = sc.nextInt();
							text = sc.nextLine();
							text+=text+"   ";//Me aseguro que el string nunca sea de menos de 3 caracteres
							opc1[0] = text.charAt(0);
							opc1[1] = text.charAt(2);
							
							myString = String.valueOf(opc1);
							aux = Integer.parseInt(myString);
							
							
							if(text.charAt(1)!=' '){
								v = false; 
								System.out.println("¡Opción fuera de rango! debes introducir: (fila columna)");
							}else if (text.charAt(0)=='0'&&text.charAt(1)=='-'&&text.charAt(2)=='2') {
								aux = -2;
								//Rango Correcto
								v = true;
							}else {
								if(aux<=4&&aux>=1) {
									//Rango Correcto
									v = true;
								}else if(aux>10&&aux<67) {
									//Rango Correcto
									v = true;
								}else {
									v = false; 
									System.out.println("¡Opción fuera de rango! debes introducir: (fila columna)");
								}
							}
					}catch(Exception j) {
						v = false; 
						System.out.println(j.getMessage());
					}
				}catch (Exception e) {
					System.out.println("¡Los caracteres introducidos no son validos!");
					System.out.println(e.getMessage());
					sc.next();
					v=false;
				}
			} while (!v);
			break;
		
		case 2: // Parametro 2: Valida la introduci�n de una desici�n, devuelve 1 para SI y -1 para NO, devuelve el valor en un entero
			do {
				try {
					v = true;
					//Mostrar texto pasado por parametro
					System.out.print(string);
					aux = sc.nextInt();
					if(aux>1||aux<-1 || aux ==0) {
					 
						v = false; 
						System.out.println("Introduce un 1 para marcar SI o un -1 para marcar NO");
					}
				}catch (Exception e) {
					System.out.println("¡El caracter introducido no es valido!");
					sc.next();
					v=false;
				}
			} while (!v);
			break;
	
		case 3: //Parametro 3: Metodo devuelve un numero dado por el usuario en el rango [1-9]
			do {
				try {
					//Doy por supuesto que el valor introducido es correcto
					v = true; 
					//Mostrar texto pasado por parametro
					System.out.print(string);
					
					//Pido una Palabra y me quedo con la primera letra
					auxs = sc.next();
					auxc = auxs.charAt(0);
					
					//Si el rango no es correcto, repetimos el while
					if(auxc<49||auxc>57) {
						System.out.println("¡Número fuera de rango! Debes introducir un nivel en el rango [1-9]");
						v=false;
						sc.next();
					}
					
					//Nos aseguramos que el valor de auxs sea solo un caracter
					auxs = ""+auxc;
				}
				catch(Exception e){
					System.out.println("¡El número introducido no es valido!!");
					sc.next();
					v=false;
				}
			}while(!v);
			
			//Pasamos de String a entero
			aux = Integer.parseInt(auxs);
			break;
			
			
		case 4: // Parametro 4: Valida la introduci�n de una desici�n, devuelve 1 para SI y 0 para NO, devuelve el valor en un entero
			do {
				try {
					v = true;
					//Mostrar texto pasado por parametro
					System.out.print(string);
					aux = sc.nextInt();
					if(aux>1||aux<0) {
					 
						v = false; 
						System.out.println("Introduce un 1 para marcar SI o un 0 para marcar NO");
					}
				}catch (Exception e) {
					System.out.println("¡El caracter introducido no es valido!");
					sc.next();
					v=false;
				}
			} while (!v);
			break;
	
			
		}
		
		return aux;
	}

	
	/**
	 * Método que muestra la interfaz en consola.
	 * @param matrizTablero Pinta el tablero del juego.
	 * @param nivel El que estamos jugando actualmente en el tablero.
	 * @param cont El número de golpes que llevamos durante el tablero. 
	 * @param verdadero Es un true cuando es un golpe y es un false cuando se ha ganado. 
	 * @param cal_level La calificación que llevamos en el tablero.
	 * @param ultimaJugada El último golpe realizado en el anterior turno. 
	 * @return El golpe realizado u la opción.
	 */
	
	
	private static int interfaz(int[][] matrizTablero, int nivel, int cont, boolean verdadero, float []cal_level, String ultimaJugada) {
		
		DecimalFormat formateador = new DecimalFormat("0.00");


		
		System.out.println();
		System.out.println();
		System.out.println("+----------------------------------------------------------------------------+");
		System.out.println("| Recomenzar(01)  Nuevo(02)  Calificación(03)  Cambiar Nivel(04)  Salir(0-2) |");
		System.out.println("|                                                                            |");
		System.out.println("| Un golpe decrementar el valor de esa casilla en 1, y también los           |");
		System.out.println("| valores de sus 4 vecinas. Objetivo: Dejar todas las casillas en '0'        |");
		System.out.println("|                                                                            |");
		System.out.println("|                     C 1   2   3   4   5   6 C                              |");
		System.out.println("|                   F +-----------------------+                              |");
		for (int i = 1; i <= 6; i++) {
			System.out.println("|                   "+i
					+ " | "+matrizTablero[i][1]
					+ " | "+matrizTablero[i][2]
					+ " | "+matrizTablero[i][3]
					+ " | "+matrizTablero[i][4]
					+ " | "+matrizTablero[i][5]
					+ " | "+matrizTablero[i][6]
					+ " |                              |");
		}
		System.out.println("|                   F +-----------------------+                              |");
		System.out.println("|                                                                            |");
		System.out.println("| Nivel de juego: "+NombreNivel(nivel)+"          Puntuación en el nivel: "+formateador.format(cal_level[nivel])+"   |");
		System.out.println("|                                                                            |");
		System.out.println("| Golpes realizados: "+cont+"                        Golpe (fila columna): "+ultimaJugada+"      |");
		System.out.println("|                                                                            |");
		System.out.println("+----------------------------------------------------------------------------+");
		

		int aux=0;
		
		if(verdadero==true) {
			aux = getDato("Introduce jugada u opción del menú: ",1);
		}
		
		return aux;
	}
	
	
	/**
	 * Método que contiene los nombres de cada nivel.
	 * @param level El nivel actual sobre el que estamos jugando.
	 * @return El nombre del nombre en el que estamos jugando. 
	 */
	
	
	private static String NombreNivel (int level) {
		
		String nombre_nivel=null;
		
		if (level == 1)
			nombre_nivel = "Para bebés (3 golpes)";
		else if (level == 2)
			nombre_nivel = "Aprendizaje (6 golpes)";
		else if (level == 3)
			nombre_nivel = "Simple (9 golpes)";
		else if (level == 4) 
			nombre_nivel = "Casi normal (12 golpes)";
		else if (level == 5)
			nombre_nivel = "Normal (15 golpes)";
		else if (level ==6)
			nombre_nivel = "Difícil (18 golpes)";
		else if (level ==7)
			nombre_nivel = "Puto amo (21 golpes)";
		else if (level ==8)
			nombre_nivel = "Imposible (24 golpes)";
		else if (level ==9)
			nombre_nivel = "Diez en Progra (27 golpes)";
		
		return nombre_nivel; 
		
	}

}
