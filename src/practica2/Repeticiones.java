/**
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    27-01-2017
*/

package practica2;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import practica2.arbolsufijos.ArbolSufijosCompacto;

public class Repeticiones {
		
	private static boolean debug = false;
	private static String FICHERO_GENES = "genes/12S.fasta";
	
	public static void main(String[] args) {
		
		/* Lectura de parametros */
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-f"))
				FICHERO_GENES = args[i + 1];
			else if (args[i].equals("-d"))
				debug = args[i + 1].equals("true") ? true : false;
		}
		
		System.out.println("Fichero: " + FICHERO_GENES);
		System.out.println("--------------------------------");
		LectorGenes lg = new LectorGenes(FICHERO_GENES);
		Hashtable<String, String> genes = lg.obtenerGenes();
		
		long[] tiemposCreacion = new long[genes.size()];
		long[] tiemposRepeticion = new long[genes.size()];
		long[] tiemposMaximal = new long[genes.size()];
		int indice = 0;
		
		Iterator<Map.Entry<String, String>> it = genes.entrySet().iterator();
		while (it.hasNext()) { 
			Map.Entry<String, String> entry = it.next();
			
			/* Arbol compacto */
			if (debug)
				System.out.print("Creando arbol compacto "+ entry.getKey() + "...");
			
			long tiempo = System.currentTimeMillis();
			ArbolSufijosCompacto arbol = new ArbolSufijosCompacto(entry.getValue());
			tiempo = System.currentTimeMillis() - tiempo;
			tiemposCreacion[indice] = tiempo;

			if (debug) {
				System.out.println(" HECHO");
				//System.out.println(arbol.raiz);
				System.out.println("Tiempo: " + tiempo + " ms");
				System.out.println("--------------------------------");
			}

			/* Repeticion mas larga */
			if (debug)
				System.out.print("Repeticion mas larga: ");
			
			tiempo = System.currentTimeMillis();
			if (debug)
				System.out.println(arbol.repeticionMasLarga());
			tiempo = System.currentTimeMillis() - tiempo;
			tiemposRepeticion[indice] = tiempo;
			
			if (debug) {
				System.out.println("Tiempo: " + tiempo + " ms");
			}
			
			/* Repeticiones maximales*/
			if (debug) {
				System.out.println("--------------------------------");
				System.out.println("Repeticion maximales:");
			}
			
			tiempo = System.currentTimeMillis();
			for (String s : arbol.repeticionesMaximales()) {
				if (debug)
					System.out.println("	->" + s);
			}
			tiempo = System.currentTimeMillis() - tiempo;
			tiemposMaximal[indice] = tiempo;

			if (debug) {
				System.out.println("Tiempo: " + tiempo + " ms");
				System.out.println("--------------------------------");
			}		
			
			indice++;
		}
		
		/* Tiempos medios */
		long tiempoCreacionMedio = 0, tiempoRepeticionMedio = 0, tiempoMaximalMedio = 0;
		for (int i = 0; i < genes.size(); i++) {
			tiempoCreacionMedio += tiemposCreacion[i];
			tiempoRepeticionMedio += tiemposRepeticion[i];
			tiempoMaximalMedio += tiemposMaximal[i];
		}
		System.out.println("TIEMPO MEDIO CREACION ARBOL: " + tiempoCreacionMedio / genes.size() + " ms");
		System.out.println("TIEMPO MEDIO REPETICION MAS LARGA: " + tiempoRepeticionMedio / genes.size() + " ms");
		System.out.println("TIEMPO MEDIO REPETICION MAXIMAL: " + tiempoMaximalMedio / genes.size() + " ms");
		System.out.println("--------------------------------");
	}
}