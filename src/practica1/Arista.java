package practica1;

import java.util.ArrayList;
import java.util.List;

public class Arista {
	
	private int origen;
	private int destino;
	
	private final List<Vertice> vertices = new ArrayList<Vertice>();
	
	public Arista(int origen, int destino) {
		super();
		this.origen = origen;
		this.destino = destino;
	}
	
	public Arista(Vertice origen, Vertice destino) {
		vertices.add(origen);
		vertices.add(destino);
	}

	public boolean contiene(Vertice v1, Vertice v2 ) {
		return vertices.contains(v1) && vertices.contains(v2);	
	}
	
	public List<Vertice> getVertices() {
		return vertices;
	}

	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}
}