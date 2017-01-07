/**
* La clase Producto representa un producto
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @since   07-01-2017
*/

package practica1.grafo;

public class Producto {
	
	private String nombre;
	private int unidades;
	private double precio;
	private String marca;
	
	/**
	 * Constructor
	 * @param nombre
	 * @param unidades
	 * @param precio
	 * @param marca
	 */
	public Producto(String nombre, int unidades, double precio, String marca) {
		super();
		this.nombre = nombre;
		this.unidades = unidades;
		this.precio = precio;
		this.marca = marca;
	}
	
	/**
	 * Devuelve el nombre del producto
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Reemplaza el nombre del producto por <nombre>
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Devuelve las unidades del producto
	 * @return
	 */
	public int getUnidades() {
		return unidades;
	}
	
	/**
	 * Reemplaza la unidades del producto por <unidades>
	 * @param unidades
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	/**
	 * Devuelve el precio del producto
	 * @return
	 */
	public double getPrecio() {
		return precio;
	}
	
	/**
	 * Reemplaza el precio del producto por <precio>
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Devuelve la marca del producto
	 * @return
	 */
	public String getMarca() {
		return marca;
	}
}