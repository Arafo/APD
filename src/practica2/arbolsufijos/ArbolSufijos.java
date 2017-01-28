/**
* Clase que representa un arbol de sufijos.
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    28-01-2017
*/

package practica2.arbolsufijos;

public class ArbolSufijos {
	
	String texto = null;
	public Nodo raiz = null;
	
	/**
	 * Constructor
	 * Incluye el simbolo $ al  final de texto si no
	 * esta incluido
	 * @param texto
	 */
	ArbolSufijos(String texto) {
		if (texto.length() > 0 && texto.charAt(texto.length() - 1) == '$') {
			this.texto = texto;	
		} else {
			this.texto = texto + "$";	
		}	
	}
}