/**
* La clase Arista representa una arista en
* un grafo
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    09-01-2017
*/

package practica1.grafo;

public class Arista {

	private int origen; // 0rigen de la arista
	private int destino; // Destino de la arista
	private int juntos = -1; // Numero de articulos comprados juntos
	private String conexionOriginal;

	/**
	 * Constructor
	 * @param origen Indice del vertice de origen
	 * @param destino Indice del vertice de destino
	 */
	public Arista(int origen, int destino) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.conexionOriginal = origen + "-" + destino;
	}

	/**
	 * Constructor
	 * @param origen Indice del vertice de origen
	 * @param destino Indice del vertice de destino
	 * @param juntos Peso de la arista
	 */
	public Arista(int origen, int destino, int juntos) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.juntos = juntos;
		this.conexionOriginal = origen + "-" + destino;
	}

	/**
	 * Devuelve true si los vertices correspondientes a los vertices <v1> y <v2>
	 * corresponden con los vertices de origen y destino
	 * @param v1 Indice del vertice 
	 * @param v2 Indice del vertice
	 * @return True si la arista tiene como vertices <v1> y <v2>
	 */
	public boolean contiene(int v1, int v2) {
		return (origen == v1 && destino == v2) || (origen == v2 && destino == v1);
	}

	/**
	 * Reemplaza el vertice <oldV> por el vertice <newV>
	 * @param oldV Vertice a reemplazar
	 * @param newV Nuevo vertice
	 */
	public void reemplazarVertice(Vertice oldV, Vertice newV) {
		if (oldV.getIndice() == origen) {
			origen = newV.getIndice();
		} else if (oldV.getIndice() == destino) {
			destino = newV.getIndice();
		} else {
			throw new IllegalArgumentException("Vertice " + oldV.getIndice());
		}
	}

	/**
	 * Devuelve el vertice opuesto al vertice <v>
	 * @param v Vertice
	 * @return Vertice opuesto a <v>
	 */
	public int getVerticeOpuesto(Vertice v) {
		if (v.getIndice() == origen)
			return destino;
		return origen;
	}

	/**
	 * Devuele el indice del vertice origen
	 * @return Indice del vertice origen
	 */
	public int getOrigen() {
		return origen;
	}

	/**
	 * Reemplaza el indice del vertice origen por <origen>
	 * @param origen Indice del nuevo vertice origen
	 */
	public void setOrigen(int origen) {
		this.origen = origen;
	}

	/**
	 * Devuele el indice del vertice destino
	 * @return Indice del vertice destino
	 */
	public int getDestino() {
		return destino;
	}

	/**
	 * Reemplaza el indice del vertice destino por <destino>
	 * @param origen Indice del nuevo vertice destino
	 */
	public void setDestino(int destino) {
		this.destino = destino;
	}

	/**
	 * Devuelve el numero de veces que han sido comprados los productos a la vez
	 * @return Numero de veces que los productos se ha comprado juntos
	 */
	public int getJuntos() {
		return juntos;
	}

	/**
	 * Devuelve la cadena correspondiente a la conexion original 
	 * @return Cadena correspondiente a la conexion original
	 */
	public String getConexionOriginal() {
		return conexionOriginal;
	}

	@Override
	public String toString() {
		return "Arista: " + this.origen + " - " + this.destino;
	}
}