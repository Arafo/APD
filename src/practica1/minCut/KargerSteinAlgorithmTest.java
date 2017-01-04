package practica1.minCut;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import practica1.Arista;
import practica1.Grafo;
import practica1.Producto;
import practica1.Vertice;

public class KargerSteinAlgorithmTest {
	
	
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
		Producto p0 = new Producto("", 1, 1, "amazon");
		Producto p1 = new Producto("", 1, 1, "amazon");
		Producto p2 = new Producto("", 1, 1, "amazonymas");
		Producto p3 = new Producto("", 1, 1, "amazonymas");
		Producto p4 = new Producto("", 1, 1, "amazon");
		Producto p5 = new Producto("", 1, 1, "amazon");
		Producto p6 = new Producto("", 1, 1, "amazonymas");
		Producto p7 = new Producto("", 1, 1, "amazonymas");
		Producto p8 = new Producto("", 1, 1, "amazonymas");
		//vertices
		vertices.put(0, new Vertice(0, p0));
		vertices.put(1, new Vertice(1, p1));
		vertices.put(2, new Vertice(2, p2));
		vertices.put(3, new Vertice(3, p3));
		vertices.put(4, new Vertice(4, p4));
		vertices.put(5, new Vertice(5, p5));
		vertices.put(6, new Vertice(6, p6));
		vertices.put(7, new Vertice(7, p7));
		vertices.put(8, new Vertice(8, p8));
		//artistas
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
		MinCut minCut=new KargerSteinAlgortihm(grafoTest);
		long timei=System.currentTimeMillis();
		minCut.reducirGrafo();
		System.out.println("Tiempo krager stein. "+ (System.currentTimeMillis()-timei));
		
		
		minCut=new KargerAlgorithm(grafoTest);
		timei=System.currentTimeMillis();
		minCut.reducirGrafo();
		System.out.println("Tiempo krager. "+ (System.currentTimeMillis()-timei));
	}

}
