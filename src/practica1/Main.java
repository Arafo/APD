/**
* Clase principal
* 
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @since   07-01-2017
*/

package practica1;

import java.util.Hashtable;
import java.util.Locale;

import practica1.grafo.Grafo;
import practica1.grafo.Producto;
import practica1.minCut.KargerAlgorithm;
import practica1.minCut.KargerSteinAlgortihm;
import practica1.minCut.MinCut;

public class Main {
	
	private final static String FICHERO_PRODUCTOS = "src/practica1/productos.dat";
	private final static String FICHERO_RELACIONES = "src/practica1/relaciones.dat";
	private static int repeticiones = 10;
	private static int numProductos = 40;
	private static int tipoProductos = 0; // 0 -> booleanos, 1 -> enteros
	private static String tipoAlgoritmo = "k"; // k -> Karger, ks -> Karger-Stein
	private static int random = 3; // 0 -> random, 1 -> SecureRandom, 2 -> HighQualityRandom, 3 -> XORShiftRandom
	private static int usarProbabilidad = 0; // Flag para el apartado 6: 0 -> No, 1 -> Si
	private static int debug = 1;
	private static String ficheroMatriz = "src/practica1/relaciones.dat";
	private static String ficheroProductos = "src/practica1/productos.dat";
	
	public static void main(String[] args) {
		
		// Lectura de parametros
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-p")) 
				numProductos = Integer.parseInt(args[i + 1]);
			else if (args[i].equals("-t"))
				tipoProductos = Integer.parseInt(args[i + 1]);
			else if (args[i].equals("-r"))
				repeticiones = Integer.parseInt(args[i + 1]);
			else if (args[i].equals("-a"))
				tipoAlgoritmo = args[i + 1];	
			else if (args[i].equals("-n"))
				random = Integer.parseInt(args[i + 1]);
			else if (args[i].equals("-u"))
				usarProbabilidad = Integer.parseInt(args[i + 1]);
			else if (args[i].equals("-d"))
				debug = Integer.parseInt(args[i + 1]);
			else if (args[i].equals("-fm"))
				ficheroMatriz = args[i + 1];			
			else if (args[i].equals("-fp"))
				ficheroProductos = args[i + 1];
		}
		
		/*
		 * Comprobacion de parametros
		 */
		
		// Algoritmo
		if (tipoAlgoritmo.equals("k")) System.out.print("Karger - ");
		else if (tipoAlgoritmo.equals("ks")) System.out.print("Karger-Stein - ");

		// Random
		if (random == 0) System.out.print("Random - ");
		else if (random == 1) System.out.print("SecureRandom - ");
		else if (random == 2) System.out.print("HighQualityRandom - ");
		else if (random == 3) System.out.print("XORShiftRandom - ");
		else random = 0;
		
		// Repeticiones
		System.out.print(repeticiones + " repeticiones - ");
		
		// Tipo matriz
		if (usarProbabilidad == 0) System.out.print("matriz booleanos");
		else if (usarProbabilidad == 1) System.out.print("matriz enteros");
		else usarProbabilidad = 0; 
		System.out.println();
			
		// Para que las comas sean puntos
		Locale.setDefault(new Locale("en", "UK"));
		
		GestorDatos gd = new GestorDatos(numProductos, true, tipoProductos == 0 ? false : true);
		if (ficheroProductos.isEmpty() || ficheroMatriz.isEmpty()) {
			gd.generarDatos(FICHERO_PRODUCTOS, FICHERO_RELACIONES);
		}
		else {
			gd = new GestorDatos(numProductos, ficheroMatriz, ficheroProductos);
		}
		
		// Generacion de productos
		System.out.print("Generando " + numProductos + " productos...");
		Hashtable<Integer, Producto> productos = gd.obtenerProductos();
		System.out.println(" HECHO");

		// Generacion de la matriz de relaciones
		boolean[][] matriz = null;
		int[][] matrizEnteros = null;
		Grafo g = null;
		System.out.print("Generando matriz " + numProductos + "x" + numProductos + "...");
		if (tipoProductos == 0) {
			matriz = gd.obtenerRelaciones();
			g = new Grafo(matriz, productos);
		}
		else if (tipoProductos == 1) {
			matrizEnteros = gd.obtenerRelacionesEnteros();
			g = new Grafo(matrizEnteros, productos);
		}
		System.out.println(" HECHO");
		
		// Matriz
		if (debug == 1)
			System.out.println(g.toString());
			
		long[] tiempos = new long[repeticiones];
		int[] cortes = new int[repeticiones];
		// Ejecucion del algoritmo
		for (int i = 0; i < repeticiones; i++) {
			if (debug == 1)
				System.out.println("Ejecucion " + (i + 1));
			long tiempo = System.currentTimeMillis();
			// Eleccion de algoritmo
			MinCut krager = null;
			if (tipoAlgoritmo.equals("k"))
				krager = new KargerAlgorithm(g, random, usarProbabilidad == 1 ? true : false);
			else if (tipoAlgoritmo.equals("ks"))
				krager = new KargerSteinAlgortihm(g, random, usarProbabilidad == 1 ? true : false);
			else {
				System.out.println("Parametro no valido, algoritmo por defecto");
				krager = new KargerAlgorithm(g);
			}
			
			Grafo minCut = krager.reducirGrafo();
			
			cortes[i] = minCut.getAristas().size();
			tiempo = System.currentTimeMillis() - tiempo;
			tiempos[i] = tiempo;
			
			if (debug == 1) {
				System.out.println("MINCUT: " + cortes[i]);
				System.out.println("TIEMPO: " + tiempo + " ms");
				System.out.println();
			}
		}
		
		long tiempoMedio = 0;
		double corteMedio = 0;
		for (int i = 0; i < repeticiones; i++) {
			corteMedio += cortes[i];
			tiempoMedio += tiempos[i];
		}
		System.out.println("CORTE MEDIO: " + corteMedio / repeticiones + " aristas");
		System.out.println("TIEMPO MEDIO: " + tiempoMedio / repeticiones + " ms");
	}
}