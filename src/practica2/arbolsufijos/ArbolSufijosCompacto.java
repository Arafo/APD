package practica2.arbolsufijos;

import java.util.ArrayList;
import java.util.List;

public class ArbolSufijosCompacto extends ArbolSufijos {
	
	public ArbolSufijosCompacto(String texto) {
		super(texto);
		crearArbol();
		super.raiz = compactarNodos(raiz, 0);	
	}
	
	/**
	 * 
	 */
	private void crearArbol() {
		super.raiz = new Nodo();
	    String texto = super.texto;
	    int numTexto = 1;
	    
	    for (int i = 0; i < texto.length(); i++) {
	    	// Nueva palabra incluida en el arbol
	    	if (texto.charAt(i) == '$') {
	    		numTexto++;
	    	} else {
	    		// Crear una rama para nueva para el sufijo i..n
		        List<String> listaSufijos = new ArrayList<String>();
		        for (int j = i; j < texto.length(); j++) {
		        	listaSufijos.add(texto.charAt(j) + "");
		            
		            if (texto.charAt(j) == '$') {
		            	break;
		            }
		        }
		        
		        // Incluir la nueva lista de sufijos en el arbol
		        super.raiz.addSufijo(listaSufijos, i + 1, numTexto);
	    	}
	    }    
	}
	
	/**
	 * 
	 * @param nodo
	 * @param profundidadNodo
	 * @return
	 */
	private Nodo compactarNodos(Nodo nodo, int profundidadNodo) {
		nodo.profundidadNodo = profundidadNodo;
		for (Nodo hijo : nodo.hijos) {
			while (hijo.hijos.size() == 1) {
				Nodo nieto = hijo.hijos.iterator().next();
				hijo.aristaPadre.etiqueta += ", " + nieto.aristaPadre.etiqueta;
				hijo.profundidadTexto += nieto.aristaPadre.etiqueta.length();
				hijo.hijos = nieto.hijos;
				for (Nodo nietoAux : hijo.hijos)
					nietoAux.padre = nodo;	
			}
			hijo = compactarNodos(hijo, profundidadNodo + 1);	
		}
		return nodo;		
	}
}