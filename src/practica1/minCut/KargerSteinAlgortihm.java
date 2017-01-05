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

/**
 * Reference
 * http://research.omicsgroup.org/index.php/Karger's_algorithm#Karger.E2.80.93Stein_algorithm
 * 
 * @author Port�til1
 *
 */
public class KargerSteinAlgortihm implements MinCut {

	private Grafo grafoCopia;
	private int random = 0;

	public KargerSteinAlgortihm(Grafo f) {
		this.grafoCopia = f.copiarGrafo();
	}

	public KargerSteinAlgortihm(Grafo f, int random) {
		this.grafoCopia = f.copiarGrafo();
		this.random = random;
	}

	@Override
	public Grafo reducirGrafo() {
		return this.FastMinCut(this.grafoCopia);
	}

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

	public Grafo minCut(Grafo g, int t) {
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

		while (g.getVertices().size() > t) {

			// Se busca una arista con productos con la misma marca
			Arista tmp = null;
			for (Arista a : this.grafoCopia.getAristas()) {
				float probability = (a.getJuntos() == 0) ? 100.0f
						: a.getJuntos() * 100.0f / this.grafoCopia.getTotalCompras();
				if (r.nextFloat() * 100.0f <= (100.0f - probability)) {
					Producto p1 = this.grafoCopia.getVertices().get(a.getOrigen()).getProducto();
					Producto p2 = this.grafoCopia.getVertices().get(a.getDestino()).getProducto();

					if (p1.getMarca().equals(p2.getMarca())) {
						tmp = a;
						break;
					}
				}
			}

			// Si no se encuentra ninguna arista se elige una al azar
			if (tmp == null) {
				aristaActual = g.getAristas().remove(r.nextInt(g.getAristas().size()));
				System.out.println("Arista al azar: " + aristaActual.toString());
			} else {
				g.getAristas().remove(tmp);
				aristaActual = tmp;
				System.out.println(aristaActual.toString());
			}

			// arista al azar
			// aristaActual = this.grafoCopia.getAristas().remove(
			// r.nextInt(this.grafoCopia.getAristas().size()));
			// vertices que contiene la arista

			unir(g, aristaActual.getOrigen(), aristaActual.getDestino(), aristaActual);
			// System.out.println();
			System.out.println();
			System.out.println(g.toString());
		}

		// Aristas en el corte
		System.out.println("MINCUT: " + g.getAristas().size());
		for (Arista a : g.getAristas())
			System.out.println("Arista " + a.getConexionOriginal());

		return g;
	}

	/**
	 * Metodo que une dos aristas del grafo
	 */
	private void unir(Grafo g, int vertice1, int vertice2, Arista arista) {
		// vertices a partir arista
		Vertice v1 = g.getVertices().remove(vertice1);
		v1.getAristas().remove(arista);
		Vertice v2 = g.getVertices().remove(vertice2);
		v2.getAristas().remove(arista);

		Vertice unido = new Vertice(v1.getIndice(), v1.getProducto());

		// Redirigir las aristas de v1 al vertice unido
		for (Iterator<Arista> it = v1.getAristas().iterator(); it.hasNext();) {
			Arista a = it.next();
			it.remove();
			if (a.getVerticeOpuesto(v1) == unido.getIndice()) {
				// Quitar bucles
				unido.getAristas().remove(a);
				g.getAristas().remove(a);
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
				g.getAristas().remove(a);
			} else {
				// Reemplaza v2 por unido
				a.reemplazarVertice(v2, unido);
				unido.addArista(a);
			}
		}

		g.getVertices().put(unido.getIndice(), unido);

		if (v1.getAristas().size() <= v2.getAristas().size()) {

			// v2.fusionarVertices(v1);
			// this.grafoCopia.getVertices().put(v2.getIndice(), v2);
		} else {
			// v1.fusionarVertices(v2);
			// this.grafoCopia.getVertices().put(v1.getIndice(), v1);

		}
	}

}
