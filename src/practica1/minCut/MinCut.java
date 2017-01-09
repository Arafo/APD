/**
* Clase abtraca que implementa las funciones necesarias para usar
* algoritmos de corte minimo sobre grafos
*
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

public abstract class MinCut {

	protected Grafo grafoCopia; // copia del grafo original
	protected int random; // eleccion de metodo de generacion de numeros
							// aleatorios
	protected boolean usarProbabilidad; // flag para el apartado 6
	private boolean debug = false;

	/**
	 * Constructor
	 * 
	 * @param f
	 */
	public MinCut(Grafo f) {
		this(f, 0, false);
	}

	/**
	 * Constructor
	 * 
	 * @param f
	 * @param usarProbabilidad
	 */
	public MinCut(Grafo f, boolean usarProbabilidad) {
		this(f, 0, usarProbabilidad);
	}

	/**
	 * Constructor
	 * 
	 * @param f
	 * @param random
	 */
	public MinCut(Grafo f, int random) {
		this(f, random, false);
	}

	/**
	 * Constructor
	 * 
	 * @param f
	 * @param random
	 * @param usarProbabilidad
	 */
	public MinCut(Grafo f, int random, boolean usarProbabilidad) {
		this.grafoCopia = f.copiarGrafo();
		this.random = random;
		this.usarProbabilidad = usarProbabilidad;
	}

	/**
	 * Metodo que devuelve el grafo copia
	 * 
	 * @return
	 */
	public Grafo getGrafoCopia() {
		return grafoCopia;
	}

	/**
	 * Metodo que realiza el algoritmo de corte minimo reduciendo el grafo g a t
	 * vertices
	 * 
	 * @param g
	 * @param t
	 * @return
	 */
	public Grafo minCut(Grafo g, int t) {
		Arista aristaActual = null;

		Random r = elegirGeneradorAleatorio();
		while (g.getVertices().size() > t) {
			// Se busca una arista con productos con la misma marca
			Arista tmp = null;
			// si no se usan los valores enteros de la matriz
			if (!this.usarProbabilidad) {
				for (int i = 0; i < g.getAristas().size(); i++) {
					Arista a = g.getAristas().get(r.nextInt(g.getAristas().size() - 1));
					Producto p1 = g.getVertices().get(a.getOrigen()).getProducto();
					Producto p2 = g.getVertices().get(a.getDestino()).getProducto();

					if (p1.getMarca().equals(p2.getMarca())) {
						tmp = a;
						break;
					}
				}

				// Si no se encuentra ninguna arista se elige una al azar
				if (tmp == null) {
					aristaActual = g.getAristas().remove(r.nextInt(g.getAristas().size() - 1));
					if (debug)
						System.out.println("Arista al azar: " + aristaActual.toString());
				} else {
					g.getAristas().remove(tmp);
					aristaActual = tmp;
					if (debug)
						System.out.println(aristaActual.toString());
				}
			} else {
				// busca productor por numero de compras y de la misma marca
				for (Arista a : g.getAristas()) {
					float probability = (a.getJuntos() == 0) ? 0.0f : a.getJuntos() * 100.0f / g.getTotalCompras();
					if (!usarProbabilidad || r.nextFloat() * 100.0f <= (100.0f - probability)) {
						Producto p1 = g.getVertices().get(a.getOrigen()).getProducto();
						Producto p2 = g.getVertices().get(a.getDestino()).getProducto();
						if (p1.getMarca().equals(p2.getMarca())) {
							tmp = a;
							break;
						}
						// seleciono la arista pero busco mejores candidatos
						else {
							tmp = a;
						}
					}
				}
				if (tmp == null) {
					aristaActual = g.getAristas().remove(r.nextInt(g.getAristas().size()));
					if (debug)
						System.out.println("Arista al azar: " + aristaActual.toString());
				} else {
					g.getAristas().remove(tmp);
					aristaActual = tmp;
					if (debug)
						System.out.println(aristaActual.toString());
				}
			}

			unir(g, aristaActual.getOrigen(), aristaActual.getDestino(), aristaActual);
		}
		
		// Aristas en el corte
		if (debug) {
			// System.out.println("MINCUT: " + g.getAristas().size());
			for (Arista a : g.getAristas())
				System.out.println("Arista: " + a.getConexionOriginal());
		}

		return g;
	}

	/**
	 * Metodo que elige un generador aleatorio en funcion del valor de random
	 * 
	 * @return
	 */
	private Random elegirGeneradorAleatorio() {
		switch (random) {
		case 0:
			return new Random();
		case 1:
			return new SecureRandom();
		case 2:
			return new HighQualityRandom();
		case 3:
			return new XORShiftRandom();
		default:
			return new Random();
		}
	}

	/**
	 * Metodo que une dos aristas del grafo
	 */
	protected void unir(Grafo g, int vertice1, int vertice2, Arista arista) {
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
	}

	/**
	 * Metodo abstracto que reduce un grafo mediante el problema del corte
	 * minimo
	 */
	public abstract Grafo reducirGrafo();
}