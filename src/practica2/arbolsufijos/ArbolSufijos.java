/**
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    27-01-2017
*/

package practica2.arbolsufijos;

public class ArbolSufijos {
	
	String texto = null;
	public Nodo raiz = null;
	
	/**
	 * 
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