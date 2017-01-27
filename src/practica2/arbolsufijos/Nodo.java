/**
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    27-01-2017
*/

package practica2.arbolsufijos;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
	
	int etiqueta = -1;
	
	Nodo padre = null;
	Arista aristaPadre = null;
	
	List<Integer> cuentaTexto = new ArrayList<Integer>();
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
	 * @param numTexto
	 * @param anteriorProfundidad
	 */
	public Nodo(String etiquetaPadre, int etiqueta, int numTexto, int anteriorProfundidad) {
		this.etiqueta = etiqueta;
	    hijos = new ArrayList<Nodo>();
	    aristaPadre = new Arista(etiquetaPadre, etiqueta);
	    cuentaTexto.add(numTexto);
	    profundidadTexto = anteriorProfundidad + etiquetaPadre.length();
	}
	
	/**
	 * 
	 * @param sufijo
	 * @param indice
	 * @param numTexto
	 */
	public void addSufijo(List<String> sufijo, int indice, int numTexto) {
	    Nodo insertarEn = this;
	    insertarEn = buscar(this, sufijo, indice);
	    if (sufijo.isEmpty()) {
	    	insertarEn.cuentaTexto.add(numTexto);
	    } else {
	    	insertar(insertarEn, sufijo, indice, numTexto);
	    }    
	}
	
	/**
	 * 
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
	 * 
	 * @param insertarEn
	 * @param sufijo
	 * @param indice
	 * @param numTexto
	 */
	private void insertar(Nodo insertarEn, List<String> sufijo, int indice, int numTexto) {
		for (String x : sufijo) {
			Nodo hijo = new Nodo(x, indice, numTexto, insertarEn.profundidadTexto);
			insertarEn.hijos.add(hijo);
			insertarEn = hijo;	
		}	
	}
	
	/**
	 * 
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
	 * 
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