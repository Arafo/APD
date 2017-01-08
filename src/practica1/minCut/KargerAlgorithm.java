/**
* Implementacion del algoritmo de Karger
* http://www.geeksforgeeks.org/kargers-algorithm-for-minimum-cut-set-1-introduction-and-implementation/ 
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @since   07-01-2017
*/

package practica1.minCut;

import practica1.grafo.Grafo;


public class KargerAlgorithm extends MinCut {

	public KargerAlgorithm(Grafo f) {
		// TODO Auto-generated constructor stub
		super(f, 0, false);
	}

	public KargerAlgorithm(Grafo f, boolean usarProbabilidad) {
		// TODO Auto-generated constructor stub
		super(f, 0, usarProbabilidad);
	}

	public KargerAlgorithm(Grafo f, int random) {
		super(f, random, false);
	}

	public KargerAlgorithm(Grafo f, int random, boolean usarProbabilidad) {
		super(f, random, usarProbabilidad);
	}

	@Override
	public Grafo reducirGrafo() {
		// TODO Auto-generated method stub
		return minCut(this.grafoCopia, 2);
	}

}