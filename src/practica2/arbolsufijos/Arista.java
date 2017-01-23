package practica2.arbolsufijos;

import java.util.ArrayList;
import java.util.List;

public class Arista {
	
	String etiqueta;
	List<Integer> sufijos = new ArrayList<Integer>();
	
	/**
	 * 
	 * @param etiqueta
	 * @param indiceSufijo
	 */
	public Arista(String etiqueta, int indiceSufijo) {
		this.etiqueta = etiqueta;
		this.sufijos.add(indiceSufijo);
	}
}