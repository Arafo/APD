/**
* Implementacion del algoritmo de Karger
* http://www.geeksforgeeks.org/kargers-algorithm-for-minimum-cut-set-1-introduction-and-implementation/ 
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    09-01-2017
*/

package practica1.minCut;

import practica1.grafo.Grafo;


public class KargerAlgorithm extends MinCut {

	/**
	 * Constructor
	 * @param f Grafo
	 */
	public KargerAlgorithm(Grafo f) {
		super(f, 0, false);
	}
	
	/**
	 * Constructor
	 * @param f Grafo
	 * @param usarProbabilidad Flag del apartado 6
	 */
	public KargerAlgorithm(Grafo f, boolean usarProbabilidad) {
		super(f, 0, usarProbabilidad);
	}
	
	/**
	 * Constructor
	 * @param f Grafo
	 * @param random Tipo de generador a utilizar
	 */
	public KargerAlgorithm(Grafo f, int random) {
		super(f, random, false);
	}
	
	/**
	 * Constructor
	 * @param f Grafo
	 * @param random Tipo de generador a utilizar
	 * @param usarProbabilidad Falg del apartado 6
	 */
	public KargerAlgorithm(Grafo f, int random, boolean usarProbabilidad) {
		super(f, random, usarProbabilidad);
	}

	/**
	 * Metodo que reduce un grafo mediante el algoritmo de Karger
	 */
	@Override
	public Grafo reducirGrafo() {
		return minCut(this.grafoCopia, 2);
	}
}