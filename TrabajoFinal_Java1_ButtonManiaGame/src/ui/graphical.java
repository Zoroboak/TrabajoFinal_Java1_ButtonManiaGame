/* Valdearcos Trenas, Andrés */
/* Pérez Sánchez, Pedro Daniel */
/* Prieto Parrilla, Elías */
/*David*/



package ui;

import java.awt.Image;
import java.lang.reflect.GenericArrayType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Esta clase contiene la parte de interfaz gráfica.
 * @author  Andrés Valdearcos Trenas, Elías Prieto Parrilla, David García Bermejo, Pedro Daniel Pérez Sánchez
 * @version 03/06/2019
 */

@SuppressWarnings("serial")
public class graphical extends javax.swing.JFrame {
	
	
	private JButton reComenzar, nuevoTablero, calificaciones, cambiarNivel, salir;
	private ArrayList<JButton> Tablero = new ArrayList<JButton>();
	private int fichas[] = new int[37];
	private JLabel instruciones, c123, f123;
	private JLabel niveljuego, puntuacionnivel, golpesrealizados, golpefilacolumna;
	private double puntuacionPartida = 0;
	private int golpesRealizados = 0;
	private String golpeFilaColumna = "0 0";
	
	
	private int nivel = 5;
	private int[][] matriz = service.process.generarMatriz();

	
	public graphical(){
		//setSize(500, 512);
		setBounds(350, 150, 600, 500);
		setTitle("Button Mania Game");
	    ImageIcon ImageIcon = new ImageIcon(getClass().getResource("logo.png"));
        Image Image = ImageIcon.getImage();
        this.setIconImage(Image);
		componentes();
		
		nuevaPartida();
		
	}
	
	//Metodos que gestionan la partida
	private void nuevaPartida() {
		
		service.process.generarPartida(matriz, nivel);
		
		actualizarTablero();
		
	}
	
	
	private void actualizarTablero() {
		
		int i = 1;
		int j = 1;
		int I = 1;
		
		for (i = 1; i <= 6; i++) {
			for (j = 1; j <= 6; j++) {
				Tablero.get(I).setText(""+matriz[i][j]);
				I++;
			}
		}
	}

	//Metodos para generar la parte grafica
	
	private void componentes() {
		//botones
		reComenzar = new JButton();
		nuevoTablero = new JButton();
		calificaciones = new JButton();
		cambiarNivel = new JButton();
		salir = new JButton();
		
		for (int i = 0; i < 37; i++) {
			fichas[i] = 0;
			Tablero.add(i,new JButton(""+fichas[i]));
		}
		
		//campos de texto
		instruciones = new JLabel();
		c123 = new JLabel();
		f123 = new JLabel();
		
		niveljuego = new JLabel();
		puntuacionnivel = new JLabel();
		golpesrealizados = new JLabel();
		golpefilacolumna = new JLabel();
		
		//Accion de cierre de ventana
		getContentPane().setLayout(null);
		addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt){
				exitForm(evt);
			}
		});
		
		
		// BOTÓN Recomenzar
		reComenzar.setText("Recomenzar");
		reComenzar.setMnemonic('R');
		getRootPane().setDefaultButton(reComenzar);
		getContentPane().add(reComenzar);
		reComenzar.setBounds(10, 10, 100, 30);
		reComenzar.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt){
				lista_ActionPerformed(evt, 0);
			}
		});
		
		// BOTÓN Nuevo Tablero
		nuevoTablero.setText("Nuevo Tablero");
		nuevoTablero.setMnemonic('N');
		getRootPane().setDefaultButton(nuevoTablero);
		getContentPane().add(nuevoTablero);
		nuevoTablero.setBounds(120, 10, 120, 30);
		nuevoTablero.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt){
				lista_ActionPerformed(evt, 1);
			}
		});
		

		// BOTÓN Calificaciones
		calificaciones.setText("Calificaciones");
		calificaciones.setMnemonic('C');
		getRootPane().setDefaultButton(calificaciones);
		getContentPane().add(calificaciones);
		calificaciones.setBounds(250, 10, 100, 30);
		calificaciones.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt){
				lista_ActionPerformed(evt, 2);
			}
		});
		
		// BOTÓN Cambiar Nivel
		cambiarNivel.setText("Cambiar Nivel");
		cambiarNivel.setMnemonic('C');
		getRootPane().setDefaultButton(cambiarNivel);
		getContentPane().add(cambiarNivel);
		cambiarNivel.setBounds(360, 10, 100, 30);
		cambiarNivel.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt){
				lista_ActionPerformed(evt, 3);
			}
		});
		
		// BOTÓN Salir
		salir.setText("Salir");
		salir.setMnemonic('S');
		getRootPane().setDefaultButton(salir);
		getContentPane().add(salir);
		salir.setBounds(470, 10, 100, 30);
		salir.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt){
				lista_ActionPerformed(evt, 4);
			}
		});
		
		//Campo de texto para instruciones
		instruciones.setText("<html><body>"
				+ "Un golpe decrementar el valor de esa casilla en 1, y también los "
				+ "valores de sus 4 vecinas. "
				+ "<br> <strong> Objetivo: Dejar todas las casillas en '0' <strong>"
				+ "</body></html>");
		getContentPane().add(instruciones);
		instruciones.setBounds(20, 40, 560, 50);
		
		//Botones Tablero
		int index = 1;
		int x = 150;
		int y = 100;
		
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				crearBoton(index, fichas[index], x, y);
				index++;
				x+=40;
			}
			x-=240;
			y+=40;
		}
		
		//Campo de texto para el nivel
		niveljuego.setText("<html><body>"
				+ "<strong>Nivel del Juego: <strong>"+nivel
				+ "</body></html>");
		getContentPane().add(niveljuego);
		niveljuego.setBounds(50, 330, 560, 80);	
		
		//Campo de texto para la puntuación según el nivel
		puntuacionnivel.setText("<html><body>"
				+ "<strong>Puntuación Actual: <strong>"+puntuacionPartida
				+ "</body></html>");
		getContentPane().add(puntuacionnivel);
		puntuacionnivel.setBounds(350, 330, 560, 80);
		
		//Campo de texto para los golpes realizados
		golpesrealizados.setText("<html><body>"
				+ "<strong>Golpes Realizados: <strong>"+golpesRealizados
				+ "</body></html>");
		getContentPane().add(golpesrealizados);
		golpesrealizados.setBounds(50, 350, 560, 80);
		
		//Campo de texto para la ultima jugada
		golpefilacolumna.setText("<html><body>"
				+ "<strong>Golpes (Fila Columna): <strong>"+golpeFilaColumna
				+ "</body></html>");
		getContentPane().add(golpefilacolumna);
		golpefilacolumna.setBounds(350, 350, 560, 80);
		
		
	}

	private void exitForm(java.awt.event.WindowEvent evt){
		System.exit(0);
	}
	
	//Indice, Contenido, Eje x, Eje Y
	private void crearBoton(int i, int c, int x, int y) {
		// Tablero
		Tablero.get(i).setText(""+c);
		Tablero.get(i).setMnemonic(c);
		getRootPane().setDefaultButton(Tablero.get(i));
		getContentPane().add(Tablero.get(i));
		Tablero.get(i).setBounds(x, y, 40, 40);
		Tablero.get(i).addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt){
				jugada_tablero(evt, i);
			}
		});
	}
	
	//Metodos que ofrecen funcionalidad a la parte grafica
	private void lista_ActionPerformed(java.awt.event.ActionEvent evt, int opc){
		switch(opc){
			case 0:	// Recomenzar
				System.out.println("Recomenzando: ");
				
			break;
			
			case 1:	// Nuevo
				nuevaPartida();
			break;
			
			case 2:	// Calificacion
				System.out.println("Calificaciones: ");
			
			break;
			
			case 3:	// Cambiar Nivel
				String myLevel = JOptionPane.showInputDialog("Introduce un nuevo nivel");
				try {
					nivel = Integer.parseInt(myLevel);
				} catch (Exception e) {
					nivel = 5;
					nuevaPartida();
				}
			break;
			
			case 4:	// Salir
				System.exit(0);
			break;
		}
	}
	
	private void jugada_tablero(java.awt.event.ActionEvent evt, int pos) {
		
		pos+=2;
		pos+=30;
		
		service.process.jugarFicha(pos, matriz);
		
		golpesRealizados++;
		golpesrealizados.setText("<html><body>"
				+ "<strong>Golpes Realizados: <strong>"+golpesRealizados
				+ "</body></html>");
		
		loop();
		
	}
	
	private void loop() {
		
		actualizarTablero();
		
		if(service.process.comprobarVictoria(matriz)==true){
			JOptionPane.showMessageDialog(null, "¡Ganaste!");
		}

	}
	
	public static void main(String[] args) {
		
		try{
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			System.out.println("No se pudo establecer el aspecto deseado: " + e);
		}
		
		new graphical().setVisible(true);
		
		
	}
	
}
