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
	
	
	
	/*
	public void fusionarVertices(Vertice v) {
		
			for(int i=0;i<v.getAristas().size();i++){
				Arista aux=v.getAristas().get(i);
				//si el grafo no conecta los dos vertices 
				//lo añadimos a las aristas del vertice actual
				if(!((aux.getOrigen()==this.indice || aux.getDestino() ==this.indice) && 
						(aux.getOrigen()==v.getIndice() || aux.getDestino() ==v.getIndice()))){
					
					
					if(!this.aristas.contains(aux)){
						System.out.println("añadiendo...."+aux.toString());
						aristas.add(aux);
					}
				}
			}
			//this.verticesInternos.add(v);
			
	}
	*/
	
}