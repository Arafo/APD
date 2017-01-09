/**
* Clase de pruebas para el algoritmo Karger-Stein
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

public class KargerSteinAlgorithmTest {
	
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
		Producto p4 = new Producto("", 1, 1, "amazon");
		Producto p5 = new Producto("", 1, 1, "amazon");
		Producto p6 = new Producto("", 1, 1, "amazonymas");
		Producto p7 = new Producto("", 1, 1, "amazonymas");
		Producto p8 = new Producto("", 1, 1, "amazonymas");
		// Vertices
		vertices.put(0, new Vertice(0, p0));
		vertices.put(1, new Vertice(1, p1));
		vertices.put(2, new Vertice(2, p2));
		vertices.put(3, new Vertice(3, p3));
		vertices.put(4, new Vertice(4, p4));
		vertices.put(5, new Vertice(5, p5));
		vertices.put(6, new Vertice(6, p6));
		vertices.put(7, new Vertice(7, p7));
		vertices.put(8, new Vertice(8, p8));
		// Artistas
		aristas.add(new Arista(0,2,20));
		aristas.add(new Arista(0,3,10));
		aristas.add(new Arista(1,4,1000));
		aristas.add(new Arista(2,5,2000));
		aristas.add(new Arista(2,7,5467));
		aristas.add(new Arista(3,4,5424));
		aristas.add(new Arista(4,8,131));
		aristas.add(new Arista(4,6,120));
		aristas.add(new Arista(5,6,512));
		aristas.add(new Arista(6,7,51));
		aristas.add(new Arista(7,8,57));
		
		Grafo grafoTest = new Grafo(vertices, aristas);
		System.out.println(grafoTest.toString());
		MinCut minCut=new KargerSteinAlgorithm(grafoTest);
		long timei=System.currentTimeMillis();
		minCut.reducirGrafo();
		System.out.println("Tiempo krager stein. "+ (System.currentTimeMillis()-timei));
	}

}
