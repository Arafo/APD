package practica1;

import java.util.Set;

public class Vertice {
	
	private int indice;
	private Set<Arista> aristas;
	
	public Vertice(int indice) {
		super();
		this.indice = indice;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
}