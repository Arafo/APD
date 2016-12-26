package practica1;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Grafo {
	
	private final Map<Integer, Vertice> vertices = new TreeMap<Integer, Vertice>();
	private final List<Arista> aristas = new ArrayList<Arista>();
	
	public Grafo(boolean[][] matriz, Hashtable<Integer, Producto> productos) {
		//System.out.println(matriz.length);
		for (int i = 0; i < matriz.length; i++) {
			Vertice v1 = getVertice(i, productos);
			for (int j = 0; j < matriz.length; j++) {
                Vertice v2 = getVertice(j, productos);
                Arista a;
                if (matriz[i][j] == true && (a = v2.getAristaA(v1)) == null) {
                    a = new Arista(v1, v2);
                    aristas.add(a);
                    v1.addArista(a);
                    v2.addArista(a);
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Vertice v1 : vertices.values()) {
			sb.append(v1.getIndice() + " (" + v1.getProducto().getNombre() + "): ");
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