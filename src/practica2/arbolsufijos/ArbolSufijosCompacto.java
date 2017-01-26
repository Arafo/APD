package practica2.arbolsufijos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class ArbolSufijosCompacto extends ArbolSufijos {
	
	public ArbolSufijosCompacto(String texto) {
		super(texto);
		crearArbol();
		Nodo raiz = compactarNodos(super.raiz, 0);
		super.raiz = comprimirEtiquetas(raiz, 20);
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
				//for (Nodo nietoAux : hijo.hijos)
					//nietoAux.padre = nodo;	
			}
			hijo = compactarNodos(hijo, profundidadNodo + 1);	
		}
		return nodo;		
	}
	
	private Nodo comprimirEtiquetas(Nodo nodo, int alfabeto) {
		for (int i = 0; i < nodo.hijos.size(); i++) {
			Nodo hijo = nodo.hijos.get(i);
			
			/* Comprueba si la etiqueta es demasiado grande (length >= Log(n - |E|))*/
			if (hijo.aristaPadre.etiqueta.length() >= Math.log(super.texto.length() - alfabeto)) {
				int oldLabelLength = hijo.aristaPadre.etiqueta.length();
				hijo.aristaPadre.etiqueta = Integer.toString(hijo.aristaPadre.sufijos.get(0) + nodo.profundidadTexto) + ",";
				hijo.aristaPadre.etiqueta += Integer.toString(hijo.aristaPadre.sufijos.get(0) + nodo.profundidadNodo + oldLabelLength -1);
			}
			hijo = comprimirEtiquetas(hijo, alfabeto);
		}
		return nodo;	
	}
	
	
	/**
	 * Encuentra la repeticion mas larga dentro del arbol de sufijos
	 * @return
	 */
	public String repeticionMasLarga() {
		int N = super.texto.length();
		String[] sufijos = new String[N];
		for (int i = 0; i < N; i++) {
			sufijos[i] = super.texto.substring(i, N);
		}
		// sort them
		Arrays.sort(sufijos);
		// find longest repeated substring by comparing adjacent sorted suffixes
		String lrs = "";
		for (int i = 0; i < N - 1; i++) {
			String x = repeticionMasLarga(sufijos[i], sufijos[i + 1]);
			if (x.length() > lrs.length())
				lrs = x;
		}
		return lrs;
	}
	
	private  String repeticionMasLarga(String s, String t){
		int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i))
                return s.substring(0, i);
        }
        return s.substring(0, n);		
	}
	
	
	public ArrayList<String> repeticionesMaximales(){
		LinkedHashMap<String, String> maximales=new LinkedHashMap<String, String>();
		//ArrayList<String> maximales=new ArrayList<String>();
		int N = super.texto.length();
		String[] sufijos = new String[N];
		for (int i = 0; i < N; i++) {
			sufijos[i] = super.texto.substring(i, N);
		}
		// sort them
		Arrays.sort(sufijos);
		// find maximal > 2
		for (int i = 0; i < N - 1; i++) {
			String x = repeticionMasLarga(sufijos[i], sufijos[i + 1]);
			if (x.length() >= 2)
				maximales.put(x, x);
		}
		return new ArrayList<String>(maximales.keySet());
	}	
}