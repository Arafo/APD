package practica1;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Grafo {
	
	private Map<Integer, Vertice> vertices = new TreeMap<Integer, Vertice>();
	private List<Arista> aristas = new ArrayList<Arista>();
	
	/**
	 * 
	 * @param matriz
	 * @param productos
	 */
	public Grafo(boolean[][] matriz, Hashtable<Integer, Producto> productos) {
		//System.out.println(matriz.length);
		for (int i = 0; i < matriz.length; i++) {
			Vertice v1 = getVertice(i, productos);
			for (int j = 0; j < matriz.length; j++) {
                Vertice v2 = getVertice(j, productos);
                Arista a;
                if (matriz[i][j] == true && (a = v2.getAristaA(v1)) == null) {
                    a = new Arista(v1.getIndice(), v2.getIndice());
                    aristas.add(a);
                    v1.addArista(a);
                    v2.addArista(a);
                }
            }
		}
	}
	
	/**
	 * 
	 * @param matriz
	 * @param productos
	 */
	public Grafo(int[][] matriz, Hashtable<Integer, Producto> productos) {
		//System.out.println(matriz.length);
		for (int i = 0; i < matriz.length; i++) {
			Vertice v1 = getVertice(i, productos);
			for (int j = 0; j < matriz.length; j++) {
                Vertice v2 = getVertice(j, productos);
                Arista a;
                if (matriz[i][j] > 0 && (a = v2.getAristaA(v1)) == null) {
                    a = new Arista(v1.getIndice(), v2.getIndice(), matriz[i][j]);
                    aristas.add(a);
                    v1.addArista(a);
                    v2.addArista(a);
                }
            }
		}
	}
	
	public Grafo(Map<Integer, Vertice> vertices, List<Arista> aristas) {
		// TODO Auto-generated constructor stub
		this.vertices =  new TreeMap<Integer, Vertice>(vertices);
		this.aristas = new ArrayList<Arista>(aristas);
		for (Vertice v0 : this.vertices.values()) {
			for (Vertice v1 : this.vertices.values()) {
				for (Arista a : this.aristas) {
					if (a.contiene(v0.getIndice(), v1.getIndice())) {
						v0.addArista(a);
	                    v1.addArista(a);
					}
				}
			}
		}
	}
	
	public Vertice getVertice(int indice, Hashtable<Integer, Producto> productos) {
        Vertice v;
        if ((v = vertices.get(indice)) == null) {
        	v = new Vertice(indice, productos.get(indice));
        	vertices.put(indice, v);        	
        }
        return v;    
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Vertice v1 : vertices.values()) {
			sb.append(v1.getIndice() + " (" + /*v1.getProducto().getNombre() +*/ "): ");
			for (Vertice v2 : vertices.values()) {
				if (v1.getIndice() != v2.getIndice() && v2.getAristaA(v1) != null) {
					sb.append(" " + v2.getIndice());
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public String AdjString(boolean[][] matriz) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(matriz.length + "\n");
		for (int i = 0; i < matriz.length; i++) {
			sb.append(i);
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] == true)
					sb.append(" " + j);	
			}
			sb.append("\n");		
		}
		return sb.toString();
	}
}