package practica1;

import java.util.ArrayList;

public class Vertice {

	private int indice;
	// en un vertice hay varios indices segun el algoritmo
	private ArrayList<Vertice> verticesInternos;
	private ArrayList<Arista> aristas;

	public Vertice(int indice) {
		super();
		this.indice = indice;
		this.verticesInternos=new ArrayList<Vertice>();
		this.aristas = new ArrayList<Arista>();
	}

	public void fusionarVertices(Vertice v) {
			System.out.println("Fusionando "+this.indice+"--"+v.getIndice());
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
			this.verticesInternos.add(v);
			
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public ArrayList<Vertice> getVerticesInternos() {
		return this.verticesInternos;
	}

	public ArrayList<Arista> getAristas() {
		return this.aristas;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String resultado = "Vertice " + this.indice;
		return resultado;
	}
}