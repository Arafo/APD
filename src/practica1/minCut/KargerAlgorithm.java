package practica1.minCut;

import java.util.Random;

import practica1.Arista;
import practica1.Grafo;

/**
 * Referencia:
 * http://www.geeksforgeeks.org/kargers-algorithm-for-minimum-cut-set-1-introduction-and-implementation/
 * 
 * @author Portátil1
 *
 */
public class KargerAlgorithm implements MinCut {

	private Grafo grafoCopia;

	public KargerAlgorithm(Grafo f) {
		// TODO Auto-generated constructor stub
		this.grafoCopia = f.copiarGrafo();
	}

	@Override
	public Grafo reducirGrafo() {
		// TODO Auto-generated method stub

		Arista aristaActual = null;
		Random r = new Random();
		while (this.grafoCopia.getVertices().size() > 2) {
			// arista al azar
			aristaActual = this.grafoCopia.getAristas().get(
					r.nextInt(this.grafoCopia.getAristas().size()-1));
			//vertices que contiene la arista

		}
		return null;
	}

}
