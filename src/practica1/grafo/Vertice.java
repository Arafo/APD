/**
* La clase Vertice representa un vertice en
* un grafo
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @since   07-01-2017
*/

package practica1.grafo;

import java.util.HashSet;
import java.util.Set;

public class Vertice {
	
	private int indice;
	private Producto producto;
	private Set<Arista> aristas = new HashSet<Arista>();
	
	/**
	 * Constructor
	 * @param indice
	 */
	public Vertice(int indice) {
		super();
		this.indice = indice;
	}
	
	/**
	 * Constructor
	 * @param indice
	 * @param producto
	 */
	public Vertice(int indice, Producto producto) {
		super();
		this.indice = indice;
		this.producto = producto;
	}
	
	/**
	 * Añada la arista <arista> al conjunto de aristas
	 * del vertice
	 * @param arista
	 */
	public void addArista(Arista arista) {
		aristas.add(arista);	
	}
	
	/**
	 * Devuelve la arista opuesta al vertice <v>
	 * @param v
	 * @return
	 */
	public Arista getAristaA(Vertice v) {
		for (Arista arista : aristas) {
			if (arista.contiene(this.getIndice(), v.getIndice()))
				return arista;	
		}
		return null;	
	}
	
	/**
	 * Devuelve el conjunto de aristas del vertice
	 * @return
	 */
	public Set<Arista> getAristas() {
		return aristas;
	}
	
	/**
	 * Devuelve el indice del vertice
	 * @return
	 */
	public int getIndice() {
		return indice;
	}
	
	/**
	 * Reemplaza el indice del vertice por <indice>
	 * @param indice
	 */
	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	/**
	 * Devuelve el producto del vertice
	 * @return
	 */
	public Producto getProducto() {
		return producto;
	}
	
	/**
	 * Reemplaza el producto del vertice por <producto>
	 * @param producto
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}	
}