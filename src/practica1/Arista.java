package practica1;

import java.util.ArrayList;
import java.util.List;

public class Arista {
	
	private int origen;
	private int destino;
	private int juntos;
	private String conexionOriginal;
	
	//private final List<Vertice> vertices = new ArrayList<Vertice>();
	
	public Arista(int origen, int destino) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.conexionOriginal = origen + "-" + destino;
	}
	
	public Arista(int origen, int destino, int juntos) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.juntos = juntos;
		this.conexionOriginal = origen + "-" + destino;
	}
	
	/*public Arista(Vertice origen, Vertice destino) {
		vertices.add(origen);
		vertices.add(destino);
	}

	public boolean contiene(Vertice v1, Vertice v2 ) {
		return vertices.contains(v1) && vertices.contains(v2);	
	}*/
	
	public boolean contiene(int v1, int v2) {
		return (origen == v1 && destino == v2) || (origen == v2 && destino == v1);	
	}
	
	public void reemplazarVertice(Vertice oldV, Vertice newV) {
		if (oldV.getIndice() == origen) {
			origen = newV.getIndice();
		}
		else if (oldV.getIndice() == destino) {
			destino = newV.getIndice();
		}
		else {
			throw new IllegalArgumentException( "Vertice " + oldV.getIndice());
		}	
	}
	
	public int getVerticeOpuesto(Vertice v) {
		if (v.getIndice() == origen)
			return destino;
		return origen;
	}
	
	/*public List<Vertice> getVertices() {
		return vertices;
	}*/

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
	
	public int getJuntos() {
		return juntos;
	}

	public String getConexionOriginal() {
		return conexionOriginal;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Arista: " + this.origen + "-" + this.destino;
	}
}