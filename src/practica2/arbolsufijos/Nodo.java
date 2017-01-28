/**
* Clase que representa un nodo del arbol. Un nodo esta compuesto por
* su arista padre, el nodo padre, una lista de hijos, la profundidad
* del nodo en el arbol y la longitud del string resultante desde 
* la raiz del arbol hasta el nodo.
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    28-01-2017
*/

package practica2.arbolsufijos;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
	
	int etiqueta = -1;
	
	Nodo padre = null;
	Arista aristaPadre = null;
	
	List<Nodo> hijos = null;
	
	int profundidadNodo = -1;
	int profundidadTexto;
	
	/**
	 * Constructor
	 * Nodo raiz del arbol
	 */
	public Nodo() {
	    hijos = new ArrayList<Nodo>();
	    etiqueta = 0;
	}
	
	/**
	 * Constructor
	 * Nodo del arbol
	 * @param etiquetaPadre
	 * @param etiqueta
	 * @param anteriorProfundidad
	 */
	public Nodo(String etiquetaPadre, int etiqueta, int anteriorProfundidad) {
		this.etiqueta = etiqueta;
	    hijos = new ArrayList<Nodo>();
	    aristaPadre = new Arista(etiquetaPadre, etiqueta);
	    profundidadTexto = anteriorProfundidad + etiquetaPadre.length();
	}
	
	/**
	 * Anade un nuevo sufijo como rama del arbol cuya raiz es este nodo
	 * @param sufijo
	 * @param indice
	 */
	public void addSufijo(List<String> sufijo, int indice) {
	    Nodo insertarEn = this;
	    insertarEn = buscar(this, sufijo, indice);
	    insertar(insertarEn, sufijo, indice);
	}
	
	/**
	 * Busca el nodo del arbol donde debe empezar la nueva rama
	 * @param nodoInicio
	 * @param sufijo
	 * @param indice
	 * @return
	 */
	private Nodo buscar(Nodo nodoInicio, List<String> sufijo, int indice) {
	    for (Nodo hijo : nodoInicio.hijos) {
	        if (hijo.aristaPadre.etiqueta.equals(sufijo.get(0))) {
	        	hijo.aristaPadre.sufijos.add(indice);
	        	sufijo.remove(0);
	            if (sufijo.isEmpty()) {
	                return hijo;
	            }
	            return buscar(hijo, sufijo, indice);
	        }
	    }
	    return nodoInicio;    
	}
	
	/**
	 * Inserta un nodo en la posicion dada del arbol
	 * @param insertarEn
	 * @param sufijo
	 * @param indice
	 */
	private void insertar(Nodo insertarEn, List<String> sufijo, int indice) {
		for (String x : sufijo) {
			Nodo hijo = new Nodo(x, indice, insertarEn.profundidadTexto);
			insertarEn.hijos.add(hijo);
			insertarEn = hijo;	
		}	
	}
	
	/**
	 * Representacion de un nodo
	 */
	public String toString() {
		StringBuilder resultado = new StringBuilder();
		String etiqueta = this.profundidadTexto == 0 ? "" : 
			this.etiqueta + ":" + this.aristaPadre.etiqueta 
			/*+ ":" + this.aristaPadre.sufijos + ":" + "/" + this.cuentaTexto*/;

		resultado.append("(" + etiqueta + ")\n");
	    for (Nodo hijo : hijos) {
	    	resultado.append(tabularArbol(hijo) + hijo.toString());
	    }
	    return resultado.toString();
	}
	
	/**
	 * Tabula el arbol segun la profunidad de nodo
	 * @param nodo
	 * @return
	 */
	private String tabularArbol(Nodo nodo) {
		StringBuilder tabulado = new StringBuilder();
		for (int i = 0; i < nodo.profundidadNodo; i++)
			tabulado.append("\t");
		return tabulado.toString();
	}
}