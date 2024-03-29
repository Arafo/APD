/**
* La clase Vertice representa un vertice en
* un grafo
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    09-01-2017
*/

package practica1.grafo;

import java.util.HashSet;
import java.util.Set;

public class Vertice {
	
	private int indice;			// Indice
	private Producto producto; 	// Informacion del producto
	private Set<Arista> aristas = new HashSet<Arista>();
	
	/**
	 * Constructor
	 * @param indice Indice del vertice
	 */
	public Vertice(int indice) {
		super();
		this.indice = indice;
	}
	
	/**
	 * Constructor
	 * @param indice Indice del vertice
	 * @param producto Informacion del producto
	 */
	public Vertice(int indice, Producto producto) {
		super();
		this.indice = indice;
		this.producto = producto;
	}
	
	/**
	 * A�ada la arista <arista> al conjunto de aristas
	 * del vertice
	 * @param arista Arista a a�adir
	 */
	public void addArista(Arista arista) {
		aristas.add(arista);	
	}
	
	/**
	 * Devuelve la arista opuesta al vertice <v>
	 * @param v Vertice
	 * @return Arista del vertice <v>
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
	 * @return Conjunto de aristas
	 */
	public Set<Arista> getAristas() {
		return aristas;
	}
	
	/**
	 * Devuelve el indice del vertice
	 * @return Indice del vertice
	 */
	public int getIndice() {
		return indice;
	}
	
	/**
	 * Reemplaza el indice del vertice por <indice>
	 * @param indice Nuevo indice
	 */
	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	/**
	 * Devuelve el producto del vertice
	 * @return Producto del vertice
	 */
	public Producto getProducto() {
		return producto;
	}
	
	/**
	 * Reemplaza el producto del vertice por <producto>
	 * @param producto Nuevo producto
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}	
}