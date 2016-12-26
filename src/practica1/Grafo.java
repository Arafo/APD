package practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Grafo {
	
	private Map<Integer, Vertice> vertices = new TreeMap<Integer, Vertice>();
	private List<Arista> aristas = new ArrayList<Arista>();
	

	
	public Grafo(final Map<Integer, Vertice> vertices,final List<Arista> aristas) {
		// TODO Auto-generated constructor stub
		this.vertices=vertices;
		this.aristas=aristas;
	}
	
	public List<Arista> getAristas() {
		return this.aristas;
	}
	
	public Map<Integer, Vertice> getVertices() {
		return this.vertices;
	}
	
	public Grafo copiarGrafo(){
		return new Grafo(this.vertices, this.aristas);
	}
}