package practica1.minCut;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import practica1.Arista;
import practica1.Grafo;
import practica1.Vertice;

public class KargerAlgorithmTest {
	
	
	 /*test graph
    0------1
    | \    |
    |   \  |
    |     \|
    2------3   */
	public static void main(String[] args) {
		
		Map<Integer, Vertice> vertices = new TreeMap<Integer, Vertice>();
		List<Arista> aristas = new ArrayList<Arista>();
		
		/*
		 * datos del grafo
		 */
		//vertices
		vertices.put(0, new Vertice(0));
		vertices.put(1, new Vertice(1));
		vertices.put(2, new Vertice(2));
		vertices.put(3, new Vertice(3));
		//artistas
		aristas.add(new Arista(0, 1));
		aristas.add(new Arista(0, 2));
		aristas.add(new Arista(0, 3));
		aristas.add(new Arista(1, 3));
		aristas.add(new Arista(2, 3));
		
		
		
		Grafo grafoTest = new Grafo(vertices, aristas);
		System.out.println(grafoTest.toString());
		KargerAlgorithm minCut=new KargerAlgorithm(grafoTest);
		minCut.reducirGrafo();
		
	}

}
