/**
* Implementacion del algoritmo de Karger
* http://www.geeksforgeeks.org/kargers-algorithm-for-minimum-cut-set-1-introduction-and-implementation/ 
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @since   07-01-2017
*/

package practica1.minCut;

import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Random;

import practica1.grafo.Arista;
import practica1.grafo.Grafo;
import practica1.grafo.Producto;
import practica1.grafo.Vertice;
import practica1.random.HighQualityRandom;
import practica1.random.XORShiftRandom;

public class KargerAlgorithm implements MinCut {
	
	private Grafo grafoCopia;
	private int random;
	private boolean usarProbabilidad; // flag para el apartado 6
	private boolean debug = false;

	/**
	 * Constructor
	 * @param f
	 */
	public KargerAlgorithm(Grafo g) {
		this(g, 0, false);
	}

	/**
	 * Constructor
	 * @param g
	 * @param usarProbabilidad
	 */
	public KargerAlgorithm(Grafo g, boolean usarProbabilidad) {
		this(g, 0, usarProbabilidad);
	}

	/**
	 * Constructor
	 * @param g
	 * @param random
	 */
	public KargerAlgorithm(Grafo g, int random) {
		this(g, random, false);
	}
	
	/**
	 * Constructor
	 * @param g
	 * @param random
	 * @param usarProbabilidad
	 */
	public KargerAlgorithm(Grafo g, int random, boolean usarProbabilidad) {
		this.grafoCopia = g.copiarGrafo();
		this.random = random;
		this.usarProbabilidad = usarProbabilidad;
	}

	@Override
	public Grafo reducirGrafo() {
		
		Arista aristaActual = null;

		Random r = null;
		switch (random) {
		case 0:
			// System.out.println("RANDOM");
			r = new Random();
			break;
		case 1:
			// System.out.println("SECURERANDOM");
			r = new SecureRandom();
			break;
		case 2:
			// System.out.println("HIGHQUALITYRANDOM");
			r = new HighQualityRandom();
			break;
		case 3:
			// System.out.println("XORSHIFTRANDOM");
			r = new XORShiftRandom();
			break;
		default:
			// System.out.println("RANDOM");
			r = new Random();
		}

		while (this.grafoCopia.getVertices().size() > 2) {

			// Se busca una arista con productos con la misma marca
			Arista tmp = null;
			/*for (Arista a : this.grafoCopia.getAristas()) {
					Producto p1 = this.grafoCopia.getVertices().get(a.getOrigen()).getProducto();
					Producto p2 = this.grafoCopia.getVertices().get(a.getDestino()).getProducto();

					if (p1.getMarca().equals(p2.getMarca())) {
						tmp = a;
						break;
					}
			}*/

			// Si no se encuentra ninguna arista se elige una al azar
			if (tmp == null) {
				if (this.usarProbabilidad) {
					for (Arista a : this.grafoCopia.getAristas()) {
						float probability = (a.getJuntos() == 0) ? 100.0f
								: a.getJuntos() * 100.0f / this.grafoCopia.getTotalCompras();
						if (!usarProbabilidad || r.nextFloat() * 100.0f <= (100.0f - probability)) {
							aristaActual = this.grafoCopia.getAristas().remove(this.grafoCopia.getAristas().indexOf(a));
							break;
						}
					}
				} else {
					aristaActual = this.grafoCopia.getAristas().remove(r.nextInt(this.grafoCopia.getAristas().size()));
					if (debug)
						System.out.println("Arista al azar: " + aristaActual.toString());
				}	
			} else {
				this.grafoCopia.getAristas().remove(tmp);
				aristaActual = tmp;
				if (debug)
					System.out.println(aristaActual.toString());
			}

			unir(aristaActual.getOrigen(), aristaActual.getDestino(), aristaActual);
			if (debug) {
				// System.out.println();
				System.out.println();
				System.out.println(this.grafoCopia.toString());
			}
		}

		// Aristas en el corte
		if (debug) {
			System.out.println("MINCUT: " + this.grafoCopia.getAristas().size());
			for (Arista a : this.grafoCopia.getAristas())
				System.out.println("Arista " + a.getConexionOriginal());
		}
		
		return this.grafoCopia;
	}

	/**
	 * Metodo que une dos aristas del grafo
	 * @param vertice1
	 * @param vertice2
	 * @param arista
	 */
	private void unir(int vertice1, int vertice2, Arista arista) {
		// vertices a partir arista
		Vertice v1 = this.grafoCopia.getVertices().remove(vertice1);
		v1.getAristas().remove(arista);
		Vertice v2 = this.grafoCopia.getVertices().remove(vertice2);
		v2.getAristas().remove(arista);

		Vertice unido = new Vertice(v1.getIndice(), v1.getProducto());

		// Redirigir las aristas de v1 al vertice unido
		for (Iterator<Arista> it = v1.getAristas().iterator(); it.hasNext();) {
			Arista a = it.next();
			it.remove();
			if (a.getVerticeOpuesto(v1) == unido.getIndice()) {
				// Quitar bucles
				unido.getAristas().remove(a);
				this.grafoCopia.getAristas().remove(a);
			} else {
				// Reemplaza v1 por unido
				a.reemplazarVertice(v1, unido);
				unido.addArista(a);
			}
		}

		// Redirigir las aristas de v2 al vertice unido
		for (Iterator<Arista> it = v2.getAristas().iterator(); it.hasNext();) {
			Arista a = it.next();
			it.remove();
			if (a.getVerticeOpuesto(v2) == unido.getIndice()) {
				// Quitar bucles
				unido.getAristas().remove(a);
				this.grafoCopia.getAristas().remove(a);
			} else {
				// Reemplaza v2 por unido
				a.reemplazarVertice(v2, unido);
				unido.addArista(a);
			}
		}

		this.grafoCopia.getVertices().put(unido.getIndice(), unido);
	}
}