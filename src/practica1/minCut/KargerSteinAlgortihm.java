/**
* Implementacion del algoritmo de Karger-Stein
* http://research.omicsgroup.org/index.php/Karger's_algorithm#Karger.E2.80.93Stein_algorithm
* 
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @since   07-01-2017
*/

package practica1.minCut;

import practica1.grafo.Grafo;


public class KargerSteinAlgortihm extends MinCut {
	private boolean debug = false;

	public KargerSteinAlgortihm(Grafo f) {
		// TODO Auto-generated constructor stub
		super(f);
	}

	public KargerSteinAlgortihm(Grafo f, int random) {
		super(f,random);
	}

	public KargerSteinAlgortihm(Grafo f, boolean usarprobabilidad) {
		super(f, usarprobabilidad);
	}
	public KargerSteinAlgortihm(Grafo f, int random, boolean usarProbabilidad) {
		super(f, random, usarProbabilidad);
	}

	@Override
	public Grafo reducirGrafo() {
		return this.FastMinCut(this.grafoCopia);
	}
	
	/**
	 * 
	 * @param g
	 * @return
	 */
	private Grafo FastMinCut(Grafo g) {
		if (g.getVertices().size() < 6) {
			return minCut(g, 2);
		} else {
			// divide y venceras
			int t = 1 + (int) (g.getVertices().size() / Math.sqrt(2));
			Grafo g1 = minCut(g, t);
			Grafo g2 = minCut(g, t);
			if (g1.getVertices().size() > g2.getVertices().size())
				return FastMinCut(g2);
			else
				return FastMinCut(g1);
		}
	}
	
}