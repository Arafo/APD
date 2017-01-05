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
	private static int repeticiones = 1;
	private static int numProductos = 4;
	private static int tipoProductos = 0; // 0 -> booleanos, 1 -> enteros
	private static String tipoAlgoritmo = "k"; // k -> Karger, ks -> Karger-Stein
	
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
		}
			
		// Para que las comas sean puntos
		Locale.setDefault(new Locale("en", "UK"));
		
		GestorDatos gd = new GestorDatos(numProductos, true, tipoProductos == 0 ? false : true);
		gd.generarDatos(FICHERO_PRODUCTOS, FICHERO_RELACIONES);
		
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
		System.out.println(g.toString());
				
		// Ejecucion del algoritmo
		for (int i = 0; i < repeticiones; i++) {
			System.out.println("Ejecucion " + (i + 1));
			// Eleccion de algoritmo
			MinCut krager = null;
			if (tipoAlgoritmo.equals("k"))
				krager = new KargerAlgorithm(g);
			else if (tipoAlgoritmo.equals("ks"))
				krager = new KargerSteinAlgortihm(g);
			else {
				System.out.println("Parametro no valido, algoritmo por defecto");
				krager = new KargerAlgorithm(g);
			}
			krager.reducirGrafo();
			System.out.println();
		}
	}
}