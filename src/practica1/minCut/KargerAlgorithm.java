package practica1.minCut;

import java.util.Iterator;
import java.util.Random;

import practica1.Arista;
import practica1.Grafo;
import practica1.Vertice;

/**
 * Referencia:
 * http://www.geeksforgeeks.org/kargers-algorithm-for-minimum-cut-set-1-introduction-and-implementation/
 * 
 * @author Portátil1
 *
 */
public class KargerAlgorithm implements MinCut {

	private Grafo grafoCopia;

	public KargerAlgorithm(Grafo f) {
		// TODO Auto-generated constructor stub
		this.grafoCopia = f.copiarGrafo();
	}

	@Override
	public Grafo reducirGrafo() {
		// TODO Auto-generated method stub

		Arista aristaActual = null;
		Random r = new Random();
		while (this.grafoCopia.getVertices().size() > 2) {
			// arista al azar
			aristaActual = this.grafoCopia.getAristas().remove(
					r.nextInt(this.grafoCopia.getAristas().size()));
			System.out.println("Arista al azar: " + aristaActual.toString());
			//vertices que contiene la arista
			//si son los mismos no vale (este caso no deberia darse??)
			if (aristaActual.getOrigen() == aristaActual.getDestino())
				continue;

			unir(aristaActual.getOrigen(), aristaActual.getDestino());
			System.out.println();
			System.out.println();
			System.out.println(this.grafoCopia.toString());
			

		}
		System.out.println("MINCUT: " + this.grafoCopia.getAristas().size());
		return this.grafoCopia;
	}
	
	/**
	 * Metodo que une dos aristas del grafo
	 */
	private void unir(int vertice1, int vertice2) {
		//vertices a partir arista
		Vertice v1 = this.grafoCopia.getVertices().remove(vertice1);
		Vertice v2 = this.grafoCopia.getVertices().remove(vertice2);
		
		Vertice unido = new Vertice(v1.getIndice());
        
        for (Iterator<Arista> it = v1.getAristas().iterator(); it.hasNext();) {
        	Arista a = it.next();
        	it.remove();
        	if (a.getVerticeOpuesto(v1) == unido.getIndice()) {
        		unido.getAristas().remove(a);
        		this.grafoCopia.getAristas().remove(a);
        	} else {
        		a.reemplazarVertice(v1, unido);
        		unido.addArista(a);
        	}
        }
        
        for (Iterator<Arista> it = v2.getAristas().iterator(); it.hasNext();) {
        	Arista a = it.next();
        	it.remove();
        	if (a.getVerticeOpuesto(v2) == unido.getIndice()) {
        		unido.getAristas().remove(a);
        		this.grafoCopia.getAristas().remove(a);
        	} else {
        		a.reemplazarVertice(v2, unido);
        		unido.addArista(a);
        	}
        }
        
        this.grafoCopia.getVertices().put(unido.getIndice(), unido);
        		
		if(v1.getAristas().size() <= v2.getAristas().size())
		{
			
			//v2.fusionarVertices(v1);
			//this.grafoCopia.getVertices().put(v2.getIndice(), v2);
		}
		else
		{
			//v1.fusionarVertices(v2);
			//this.grafoCopia.getVertices().put(v1.getIndice(), v1);
			
		}
		
	}
	
	

}
