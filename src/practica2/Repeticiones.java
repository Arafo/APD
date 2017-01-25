package practica2;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import practica2.arbolsufijos.ArbolSufijosCompacto;

public class Repeticiones {

	public static void main(String[] args) {
		LectorGenes lg = new LectorGenes("genes/12S.fasta");
		Hashtable<String, String> genes = lg.obtenerGenes();
		
		Iterator<Map.Entry<String, String>> it = genes.entrySet().iterator();
		while (it.hasNext()) { 
			Map.Entry<String, String> entry = it.next();
			System.out.println(entry.getKey());			
			System.out.println(entry.getValue());
		}
		
		ArbolSufijosCompacto arbol = new ArbolSufijosCompacto("bananas");
		System.out.println(arbol.raiz);
	}
}