/**
* Clase de pruebas para el algoritmo karger
* http://www.geeksforgeeks.org/kargers-algorithm-for-minimum-cut-set-1-introduction-and-implementation/ 
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    09-01-2017
*/

package practica1.minCut;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import practica1.grafo.Arista;
import practica1.grafo.Grafo;
import practica1.grafo.Producto;
import practica1.grafo.Vertice;

public class KargerAlgorithmTest {
	
	/*
	 * test graph
	 * 0------1
	 * | \    |
	 * |   \  |
	 * |     \|
	 * 2------3   
    */
	public static void main(String[] args) {
		
		Map<Integer, Vertice> vertices = new TreeMap<Integer, Vertice>();
		List<Arista> aristas = new ArrayList<Arista>();
		
		/*
		 * Datos del grafo
		 */
		Producto p0 = new Producto("", 1, 1, "amazon");
		Producto p1 = new Producto("", 1, 1, "amazon");
		Producto p2 = new Producto("", 1, 1, "amazonymas");
		Producto p3 = new Producto("", 1, 1, "amazonymas");
		// Vertices
		vertices.put(0, new Vertice(0, p0));
		vertices.put(1, new Vertice(1, p1));
		vertices.put(2, new Vertice(2, p2));
		vertices.put(3, new Vertice(3, p3));
		// Artistas
		aristas.add(new Arista(0, 1,2));
		aristas.add(new Arista(0, 2,2));
		aristas.add(new Arista(0, 3,2));
		aristas.add(new Arista(1, 3,2));
		aristas.add(new Arista(2, 3,2));
		
		Grafo grafoTest = new Grafo(vertices, aristas);
		System.out.println(grafoTest.toString());
		KargerAlgorithm minCut=new KargerAlgorithm(grafoTest,true);
		minCut.reducirGrafo();	
	}
}