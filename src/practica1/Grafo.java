package practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Grafo {
	
	private final Map<Integer, Vertice> vertices = new TreeMap<Integer, Vertice>();
	private final List<Arista> aristas = new ArrayList<Arista>();
}