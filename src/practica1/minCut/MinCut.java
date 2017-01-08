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

/**
 * Interfaz algoritmos minimun cut sobre grafos
 *
 */
public abstract class MinCut {
	
	protected Grafo grafoCopia;
	protected int random;
	protected boolean usarProbabilidad; // flag para el apartado 6
	private boolean debug = false;
	
	public MinCut(Grafo f) {
		this(f, 0, false);
	}

	public MinCut(Grafo f, boolean usarProbabilidad) {
		this(f, 0, usarProbabilidad);
	}

	public MinCut(Grafo f, int random) {
		this(f, random, false);
	}

	public MinCut(Grafo f, int random, boolean usarProbabilidad) {
		this.grafoCopia = f.copiarGrafo();
		this.random = random;
		this.usarProbabilidad = true;
	}
	
	public Grafo getGrafoCopia() {
		return grafoCopia;
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
			// Se busca una arista con productos con la misma marca
			Arista tmp = null;
			// si no uso valores enteros matriz
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
					float probability = (a.getJuntos() == 0) ? 0.0f	: a.getJuntos() * 100.0f / g.getTotalCompras();
					if (!usarProbabilidad || r.nextFloat() * 100.0f <= (100.0f - probability)) {
						Producto p1 = g.getVertices().get(a.getOrigen()).getProducto();
						Producto p2 = g.getVertices().get(a.getDestino()).getProducto();
						if (p1.getMarca().equals(p2.getMarca())) {
							tmp = a;
							break;
						}
						//seleciono la arista pero busco mejores candidatos
						else {						
							tmp = a;
						}
					}
				}
				if (tmp == null) {
					aristaActual = g.getAristas().remove(r.nextInt(g.getAristas().size()));
					if (debug)			
						System.out.println("Arista al azar: " + aristaActual.toString());
				} 
				else {
					g.getAristas().remove(tmp);
					aristaActual = tmp;
					if (debug)			
						System.out.println(aristaActual.toString());
				}
			}

			unir(g, aristaActual.getOrigen(), aristaActual.getDestino(), aristaActual);
			
			if (debug) {
				// System.out.println();
				System.out.println();
				System.out.println(g.toString());
			}
		}

		// Aristas en el corte
		if (debug) {
			//System.out.println("MINCUT: " + g.getAristas().size());
			for (Arista a : g.getAristas())
				System.out.println("Arista: " + a.getConexionOriginal());
		}
		
		return g;
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
	
	public abstract Grafo reducirGrafo();
}