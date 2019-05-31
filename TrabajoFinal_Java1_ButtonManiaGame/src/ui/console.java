/**/
/**/
/**/
/**/


package ui;

import java.util.Scanner;

public class console {
	
	public static void main(String[] args) {
		Scanner sc= new Scanner (System.in);
		//Declaraci�nes del main
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
		
	
		
		
		
		//Bucle Partida
		do{
			
			//Reseteo el contador de golpes
			//cont = 0;
			//Compruebo que tipo de partida es
			if(opcmenu==2){ //Si es una nueva partida:
				if(cont!=0) {
					puntuacion=0.5f;
					puntuacion_total*=puntuacion;  
					calificacionNivel[nivel] *=puntuacion;
				}
				matrizTablero = service.process.generarMatriz(); 
				matrizTablero = generarNuevaPartida(nivel, matriz, matrizTableroCopia);
				cont = 0;
			}else if(opcmenu==4) {
				matrizTablero = service.process.generarMatriz();
				matrizTablero = generarNuevaPartida(nivel, matriz, matrizTableroCopia);
				cont = 0;
			}else if(opcmenu==1){ //Si se esta recomenzando: 
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
				opc = interfaz(matrizTablero, nivel, cont,true);//debe validar la entrada (opc menu o jugada valida)
				
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
						cont++;
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
				
				interfaz(matrizTablero, nivel, cont,false);
				
				
				if(cont<(nivel*3)) {
					System.out.println("Extraordinariamente  bien:  Hecho  en  "+cont+"  golpes");
					System.out.println();
				}else if(cont==(nivel*3)) {
					System.out.println("Perfecto.  Hecho  en "+cont+" golpes");
					System.out.println();
				}else if(cont>(nivel*3)){
					System.out.println("Echo  en "+cont+" golpes");
					System.out.println();
					
				}
				
				
				
				System.out.println("�Felicidades! Terminaste el tablero en "+cont+" golpes");
				System.out.println("Tu calificaci�n en este nivel es de "+puntuacion+" puntos");
				System.out.println();
				System.out.println();
			
				puntuacion_total*=puntuacion;  
				calificacionNivel[nivel] *=puntuacion;
				
				cont=0;
				
				opc = getDato("�Quieres Jugar otro Tablero? (1 para Si, 0 para No)",2);
				if(opc==1){
					opcmenu=2; //nuevo tablero
				}else if(opc==0){
					opcmenu=-2; //salir
				}
				
			}

			if(opcmenu==03){
				System.out.println("+---------------------------------------------------------------------------+");
				System.out.println("| Calificaciones:                                                           |");
				System.out.println("|                                                                           |");
				System.out.println("| 1- Aprendizaje (3 golpes): "+calificacionNivel[1]+"                                            |");
				System.out.println("| 2- Simple (6 golpes): "+calificacionNivel[2]+"                                                 |");
				System.out.println("| 3- Dificilísimo(es broma) (9 golpes): "+calificacionNivel[3]+"                                 |");
				System.out.println("| 4- Ya se va complicando (12 golpes): "+calificacionNivel[4]+"                                  |");
				System.out.println("| 5- Normal (15 golpes): "+calificacionNivel[5]+"                                                |");
				System.out.println("| 6- Yo no llego a este (18 golpes): "+calificacionNivel[6]+"                                    |");
				System.out.println("| 7- Crack (21 golpes): "+calificacionNivel[7]+"                                                 |");
				System.out.println("| 8- Puto amo (24 golpes): "+calificacionNivel[8]+"                                              |");
				System.out.println("| 9- Exámenes de Alejandro (27 golpes): "+calificacionNivel[9]+"                                 |");
				System.out.println("|                                                                           |");
				System.out.print("| ¿ Borrar todas las calificaciones ? (1 para Sí, 0 para No ):              |");
				int op =0;
				try {
					op= sc.nextInt();
				}
				catch(Exception e) {
					System.out.println("No se puede introducir otra cosa que no sea un numero.");
					
					op =0;
				}
				if(op==0) {			
					
				}
				else if(op==1) {
					service.process.borrarCalificaciones(calificacionNivel);
				}
				else {
					System.out.println("El numero solamente puede ser un 0 o un 1. Introduzca de nuevo: ");
					op= sc.nextInt();
				}
				System.out.println();
				System.out.println("+---------------------------------------------------------------------------+");
				
				
			}

			
			//en estas hay que cambiarlo.
			//en estas hay que cambiarlo.
			//en estas hay que cambiarlo.
			//en estas hay que cambiarlo.
			//en estas hay que cambiarlo.
			//en estas hay que cambiarlo.
			if(opcmenu==4){
				
				if(cont!=0) {
					
					puntuacion=0.5f;
					puntuacion_total*=puntuacion;  
					calificacionNivel[nivel] *=puntuacion;
			
				}
				//puntuacion = (float) 133.00000000;
				
				
				opcmenu = 4;
				opc = getDato("Escoge nuevo nivel (1-9), a mayor numero, m�s dificil",3);
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

	private static void guardarCalificacionTablero(float[] calificacionNivel) {
		String jugador;
		int opc;
		try {
			opc = getDato("Quieres guardar tus calificaciones?  (1 para Si, 0 para No) ",2);
			if(opc==1){
				jugador = getDato("Introduce tu nombre: ");
				service.process.guardarCalificaciones(jugador, calificacionNivel);
				System.out.println("Se ha guardado la partida correctamente");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Metodo que devuelve el string introducido por el usuario en la consola
	private static String getDato(String string) {
		
		//El metodo devuelve el string introducido por el usuario en la consola

		//Declaro el objeto Scanner
		Scanner t = new Scanner(System.in);
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
				auxs = t.next();
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
				System.out.println("�La palabra introducida no es valida!");
				t.next();
				v=false;
			}
		}while(!v);
		
		//devuelvo la palabra
		return auxs;
	}

	// Metodo de utilidad que devuelve varios enteros seg�n el parametro que se le pase
	private static int getDato(String string, int parametro) {
		
		
		/*
		 * Parametro 1: Solicita y valida una opci�n valida para la interfaz del juego
		 * menu [1||2||3||4||-2] o posici�n valida del tablero ([1][1] hasta [6][6])
		 * 
		 * Parametro 2: Valida la introduci�n de una desici�n, 1 para SI y 0 para NO, devuelve el valor en un entero
		 * 
		 * Parametro 3: Metodo devuelve un numero dado por el usuario
		 * en el rango [0-9]
		 * 
		 * 
		 * El metodo muestra por pantalla el texto que le pasemos 
		 * devuelve un string con la salida validada seg�n el parametro pasado
		 * 
		 * */
		
		//Declaro el objeto Scanner
		Scanner n = new Scanner(System.in);
		boolean v = true;
		String auxs = null;
		char auxc = '0';
		int aux = 0;
		
		switch (parametro) {
		case 1:
			/*  Parametro 1: Solocita y valida una opci�n valida para la interfaz del juego
			 * menu [1||2||3||4||-2] o posici�n valida del tablero ([1][1] hasta [6][6]) */ 
			
			do {
				try {
					v = false;
					//Mostrar texto pasado por parametro
					System.out.println(string);
					aux = n.nextInt();
					if(aux<=4&&aux>=1) {
						//Rango Correcto
						v = true;
					}else if(aux>10&&aux<67) {
						//Rango Correcto
						v = true;
					}else if(aux==-2) {
						//Rango Correcto
						v = true;
					}else {
						v = false; 
						System.out.println("�Opci�n fuera de rango!");
					}
				}catch (Exception e) {
					System.out.println("�El caracter introducido no es valido!");
					n.next();
					v=false;
				}
			} while (!v);
			break;
		
		case 2: // Parametro 2: Valida la introduci�n de una desici�n, devuelve 1 para SI y 0 para NO, devuelve el valor en un entero
			do {
				try {
					v = true;
					//Mostrar texto pasado por parametro
					System.out.println(string);
					aux = n.nextInt();
					if(aux>1||aux<0) {
						v = false; 
						System.out.println("Introduce un 1 para marcar SI o un 0 para marcar NO");
					}
				}catch (Exception e) {
					System.out.println("�El caracter introducido no es valido!");
					n.next();
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
					System.out.println(string);
					
					//Pido una Palabra y me quedo con la primera letra
					auxs = n.next();
					auxc = auxs.charAt(0);
					
					//Si el rango no es correcto, repetimos el while
					if(auxc<49||auxc>57) {
						System.out.println("�Numero fuera de rango! Debes introducir un nivel en el rango [1-9]");
						v=false;
						n.next();
					}
					
					//Nos aseguramos que el valor de auxs sea solo un caracter
					auxs = ""+auxc;
				}
				catch(Exception e){
					System.out.println("�El numero introducido no es valido!!");
					n.next();
					v=false;
				}
			}while(!v);
			
			//Pasamos de String a entero
			aux = Integer.parseInt(auxs);
			break;
		}
		
		return aux;
	}

	//Interfaz Basica del programa por consola, necesita el tablero y el nivel actual como parametro
	private static int interfaz(int[][] matrizTablero, int nivel, int cont, boolean verdadero) {
		
		//Interfaz Basica del programa por consola, necesita el tablero y el nivel actual como parametro
		String nombre_nivel=null;
		if (nivel == 1)
			nombre_nivel = "Aprendizaje";
		else if (nivel == 2)
			nombre_nivel = "Simple";
		else if (nivel == 3)
			nombre_nivel = "Dificilísimo";
		else if (nivel == 4) 
			nombre_nivel = "Ya se va complicando";
		else if (nivel == 5)
			nombre_nivel = "Normal";
		else if (nivel ==6)
			nombre_nivel = "Yo no llego a este";
		else if (nivel ==7)
			nombre_nivel = "Crack";
		else if (nivel ==8)
			nombre_nivel = "Puto amo";
		else if (nivel ==9)
			nombre_nivel = "Exámenes de Alejandro";
		
		
		System.out.println();
		System.out.println();
		System.out.println("+---------------------------------------------------------------------------+");
		System.out.println("| Recomenzar(01) -Nuevo(02) -Calificaci�n(03) -Cambiar Nivel(04) -Salir(-2) |");
		System.out.println("|                                                                           |");
		System.out.println("| Un golpe decrementar� el valor de esa casilla en 1, y tambi�n los         |");
		System.out.println("| valores de sus 4 vecinas. Objetivo: Dejar todas las casillas en '0'       |");
		System.out.println("|                                                                           |");
		System.out.println("|                     C 1   2   3   4   5   6 C                             |");
		System.out.println("|                   F +-----------------------+                             |");
		for (int i = 1; i <= 6; i++) {
			System.out.println("|                   "+i
					+ " | "+matrizTablero[i][1]
					+ " | "+matrizTablero[i][2]
					+ " | "+matrizTablero[i][3]
					+ " | "+matrizTablero[i][4]
					+ " | "+matrizTablero[i][5]
					+ " | "+matrizTablero[i][6]
					+ " |                             |");
		}
		System.out.println("|                   F +-----------------------+                             |");
		System.out.println("|                                                                           |");
		System.out.println("| Nivel de juego: "+nombre_nivel+" ("+(nivel*3)+" golpes)            Golpes realizados: "+cont);
		System.out.println("|                                                                           |");
		System.out.println("+---------------------------------------------------------------------------+");
		

		int aux=0;
		
		if(verdadero==true) {
			aux = getDato("Introduce jugada u opci�n del menu: ",1);
		}
		
		return aux;
	}

}
