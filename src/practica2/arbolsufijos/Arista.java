/**
* Clase que representa una arista del arbol. Una arista esta 
* compuesta por una una etiqueta y por una lista de los sufijos 
* que pasan por esa arista.
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    28-01-2017
*/

package practica2.arbolsufijos;

import java.util.ArrayList;
import java.util.List;

public class Arista {
	
	String etiqueta;
	List<Integer> sufijos = new ArrayList<Integer>();
	
	/**
	 * Constructor
	 * @param etiqueta
	 * @param indiceSufijo
	 */
	public Arista(String etiqueta, int indiceSufijo) {
		this.etiqueta = etiqueta;
		this.sufijos.add(indiceSufijo);
	}
}