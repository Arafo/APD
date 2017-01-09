/**
* Implementacion del algoritmo de Karger-Stein
* http://research.omicsgroup.org/index.php/Karger's_algorithm#Karger.E2.80.93Stein_algorithm
* 
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    09-01-2017
*/

package practica1.minCut;

import practica1.grafo.Grafo;


public class KargerSteinAlgorithm extends MinCut {

	/**
	 * Constructor
	 * @param f Grafo
	 */
	public KargerSteinAlgorithm(Grafo f) {
		super(f);
	}
	
	/**
	 * Constructor
	 * @param f Grafo
	 * @param usarProbabilidad Flag del apartado 6
	 */
	public KargerSteinAlgorithm(Grafo f, int random) {
		super(f,random);
	}
	
	/**
	 * Constructor
	 * @param f Grafo
	 * @param random Tipo de generador a utilizar
	 */
	public KargerSteinAlgorithm(Grafo f, boolean usarprobabilidad) {
		super(f, usarprobabilidad);
	}
	
	/**
	 * Constructor
	 * @param f Grafo
	 * @param random Tipo de generador a utilizar
	 * @param usarProbabilidad Falg del apartado 6
	 */
	public KargerSteinAlgorithm(Grafo f, int random, boolean usarProbabilidad) {
		super(f, random, usarProbabilidad);
	}
	
	/**
	 * Metodo que reduce un grafo mediante el algoritmo de Karger-Stein
	 */
	@Override
	public Grafo reducirGrafo() {
		return this.FastMinCut(this.grafoCopia);
	}
	
	/**
	 * Metodo que realiza el algoritmo de Karger-Stein para un grafo g
	 * @param g Grafo
	 * @return Grafo reducido
	 */
	private Grafo FastMinCut(Grafo g) {
		if (g.getVertices().size() < 6) {
			return minCut(g, 2);
		} else {
			// Divide y venceras
			int t = 1 + (int) (g.getVertices().size() / Math.sqrt(2));
			Grafo g1 = minCut(g, t);
			Grafo g2 = minCut(g, t);
			// Eleccion del grafo con el menor numero de aristas
			if (g1.getVertices().size() > g2.getVertices().size())
				return FastMinCut(g2);
			else
				return FastMinCut(g1);
		}
	}
}