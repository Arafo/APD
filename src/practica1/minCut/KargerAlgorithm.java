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