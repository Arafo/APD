package practica2;

import practica2.arbolsufijos.ArbolSufijosCompacto;

public class Repeticiones {

	public static void main(String[] args) {
		ArbolSufijosCompacto arbol = new ArbolSufijosCompacto("bananas");
		System.out.println(arbol.raiz);
		System.out.println("Repeticion mas larga");
		System.out.println(arbol.repeticionMasLarga());
		System.out.println("Repeticion maximales:");
		for(String s:arbol.repeticionesMaximales()){
			System.out.println("	->"+s);
		}
	}
}