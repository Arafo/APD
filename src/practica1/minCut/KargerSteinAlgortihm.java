package practica1.minCut;

import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Random;

import practica1.Arista;
import practica1.Grafo;
import practica1.Vertice;
import practica1.random.HighQualityRandom;
import practica1.random.XORShiftRandom;

public class KargerSteinAlgortihm implements MinCut {

	private Grafo grafoCopia;
	private int random = 0;

	public KargerSteinAlgortihm(Grafo f) {
		// TODO Auto-generated constructor stub
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
			return minCut(g,2);
		} else {
			// divide y venceras
			int t = 1 + (int) (this.grafoCopia.getVertices().size() / Math.sqrt(2));
			Grafo g1 = minCut(g,t);
			Grafo g2 = minCut(g,t);
			if (g1.getVertices().size() > g2.getVertices().size())
				return FastMinCut(g2);
			else
				return FastMinCut(g1);

		}
	}

	public Grafo minCut(Grafo g,int t) {
		// TODO Auto-generated method stub
		Arista aristaActual = null;
		Random r = null;
		switch (random) {
		case 0:
			System.out.println("RANDOM");
			r = new Random();
			break;
		case 1:
			System.out.println("SECURERANDOM");
			r = new SecureRandom();
			break;
		case 2:
			System.out.println("HIGHQUALITYRANDOM");
			r = new HighQualityRandom();
			break;
		case 3:
			System.out.println("XORSHIFTRANDOM");
			r = new XORShiftRandom();
			break;
		default:
			System.out.println("RANDOM");
			r = new Random();
		}

		while (g.getVertices().size() > t) {
			// arista al azar
			aristaActual = g.getAristas().remove(r.nextInt(g.getAristas().size()));
			System.out.println("Arista al azar: " + aristaActual.toString());
			// vertices que contiene la arista
			// si son los mismos no vale (este caso no deberia darse??)
			if (aristaActual.getOrigen() == aristaActual.getDestino())
				continue;

			unir(g,aristaActual.getOrigen(), aristaActual.getDestino(), aristaActual);
			// System.out.println();
			System.out.println();
			System.out.println(g.toString());

		}

		// Aristas en el corte
		System.out.println("MINCUT: " + g.getAristas().size());
		for (Arista a : g.getAristas())
			System.out.println("Arista " + a.getConexionOriginal());

		return this.grafoCopia;
	}

	/**
	 * Metodo que une dos aristas del grafo
	 */
	private void unir(Grafo g,int vertice1, int vertice2, Arista arista) {
		// vertices a partir arista
		Vertice v1 = g.getVertices().remove(vertice1);
		v1.getAristas().remove(arista);
		Vertice v2 = g.getVertices().remove(vertice2);
		v2.getAristas().remove(arista);

		Vertice unido = new Vertice(v1.getIndice());

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
				this.grafoCopia.getAristas().remove(a);
			} else {
				// Reemplaza v2 por unido
				a.reemplazarVertice(v2, unido);
				unido.addArista(a);
			}
		}

		this.grafoCopia.getVertices().put(unido.getIndice(), unido);

		if (v1.getAristas().size() <= v2.getAristas().size()) {

			// v2.fusionarVertices(v1);
			// this.grafoCopia.getVertices().put(v2.getIndice(), v2);
		} else {
			// v1.fusionarVertices(v2);
			// this.grafoCopia.getVertices().put(v1.getIndice(), v1);

		}

	}

}
