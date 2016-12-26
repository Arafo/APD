package practica1;

import java.util.HashSet;
import java.util.Set;

public class Vertice {
	
	private int indice;
	private Producto producto;
	private Set<Arista> aristas = new HashSet<Arista>();
	
	public Vertice(int indice) {
		super();
		this.indice = indice;
	}
	
	public Vertice(int indice, Producto producto) {
		super();
		this.indice = indice;
		this.producto = producto;
	}
	
	public void addArista(Arista arista) {
		aristas.add(arista);	
	}
	
	public Arista getAristaA(Vertice v) {
		for (Arista arista : aristas) {
			if (arista.contiene(this, v))
				return arista;	
		}
		return null;	
	}

	public Set<Arista> getAristas() {
		return aristas;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}