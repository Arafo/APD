package practica1.minCut;

import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Random;

import practica1.Arista;
import practica1.Grafo;
import practica1.Producto;
import practica1.Vertice;
import practica1.random.HighQualityRandom;
import practica1.random.XORShiftRandom;

/**
 * Referencia:
 * http://www.geeksforgeeks.org/kargers-algorithm-for-minimum-cut-set-1-introduction-and-implementation/
 * 
 * @author Port�til1
 *
 */
public class KargerAlgorithm implements MinCut {

	private Grafo grafoCopia;
	private int random;
	private boolean usarProbabilidad; //flag para el apartado 6

	public KargerAlgorithm(Grafo f) {
		// TODO Auto-generated constructor stub
		this(f, 0, false);
	}
	
	public KargerAlgorithm(Grafo f,boolean usarProbabilidad) {
		// TODO Auto-generated constructor stub
		this(f, 0, usarProbabilidad);
	}

	public KargerAlgorithm(Grafo f, int random) {
		this(f,random,false);
	}
	
	public KargerAlgorithm(Grafo f, int random,boolean usarProbabilidad) {
		this.grafoCopia = f.copiarGrafo();
		this.random = random;
		this.usarProbabilidad=true;
	}

	@Override
	public Grafo reducirGrafo() {
		// TODO Auto-generated method stub

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
			for (Arista a : this.grafoCopia.getAristas()) {
				
				float probability = (a.getJuntos() == 0) ? 100.0f : a.getJuntos()*100.0f/this.grafoCopia.getTotalCompras() ;
				if (!this.usarProbabilidad || r.nextFloat()*100.0f <= (100.0f-probability)) {
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
				/**
				 * Elegir una arista segun su valor junto inverso
				 */
				aristaActual = this.grafoCopia.getAristas().remove(r.nextInt(this.grafoCopia.getAristas().size()));
				System.out.println("Arista al azar: " + aristaActual.toString());
			} else {
				this.grafoCopia.getAristas().remove(tmp);
				aristaActual = tmp;
				System.out.println(aristaActual.toString());
			}

			// arista al azar
			// aristaActual = this.grafoCopia.getAristas().remove(
			// r.nextInt(this.grafoCopia.getAristas().size()));
			// vertices que contiene la arista
			// si son los mismos no vale (este caso no deberia darse??)
			// if (aristaActual.getOrigen() == aristaActual.getDestino())
			// continue;

			unir(aristaActual.getOrigen(), aristaActual.getDestino(), aristaActual);
			// System.out.println();
			System.out.println();
			System.out.println(this.grafoCopia.toString());
		}

		// Aristas en el corte
		System.out.println("MINCUT: " + this.grafoCopia.getAristas().size());
		for (Arista a : this.grafoCopia.getAristas())
			System.out.println("Arista " + a.getConexionOriginal());

		return this.grafoCopia;
	}

	/**
	 * Metodo que une dos aristas del grafo
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

		if (v1.getAristas().size() <= v2.getAristas().size()) {

			// v2.fusionarVertices(v1);
			// this.grafoCopia.getVertices().put(v2.getIndice(), v2);
		} else {
			// v1.fusionarVertices(v2);
			// this.grafoCopia.getVertices().put(v1.getIndice(), v1);

		}
	}
}