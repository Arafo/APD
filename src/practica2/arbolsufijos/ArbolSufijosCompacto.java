/**
* Clase que representa un arbol de sufijos compacto donde sus ramas
* estan compactadas y sus etiquetas comprimidas utilizando los 
* indices de inicio y final del  texto
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    28-01-2017
*/

package practica2.arbolsufijos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ArbolSufijosCompacto extends ArbolSufijos {
	
	/**
	 * Constructor
	 * @param texto
	 */
	public ArbolSufijosCompacto(String texto) {
		super(texto);
		crearArbol();
		Nodo raiz = compactarNodos(super.raiz, 0);
		super.raiz = comprimirEtiquetas(raiz, 28);
		//super.raiz = raiz;
	}
	
	/**
	 * Crea un arbol de sufijos
	 */
	private void crearArbol() {
		super.raiz = new Nodo();
	    String texto = super.texto;
	    
	    for (int i = 0; i < texto.length(); i++) {

	    	// Crear una rama para nueva para la lista de sufijos
	    	List<String> listaSufijos = new ArrayList<String>();
	    	for (int j = i; j < texto.length(); j++) {
	    		listaSufijos.add(texto.charAt(j) + "");
		            
	    		if (texto.charAt(j) == '$') {
		            break;      
	    		}   		
	    	}
	    	
	    	// Incluir la nueva lista de sufijos en el arbol
	    	super.raiz.addSufijo(listaSufijos, i + 1);
	    }    
	}
	
	/**
	 * Compacta el arbol de sufijos
	 * @param nodo
	 * @param profundidadNodo
	 * @return
	 */
	private Nodo compactarNodos(Nodo nodo, int profundidadNodo) {
		//nodo.profundidadNodo = profundidadNodo;
		for (Nodo hijo : nodo.hijos) {
			while (hijo.hijos.size() == 1) {
				Nodo nieto = hijo.hijos.iterator().next();
				hijo.aristaPadre.etiqueta += ", " + nieto.aristaPadre.etiqueta;
				hijo.profundidadTexto += nieto.aristaPadre.etiqueta.length();
				hijo.hijos = nieto.hijos;
	            //hijo.cuentaTexto = nieto.cuentaTexto;

				//for (Nodo nietoAux : hijo.hijos)
					//nietoAux.padre = nodo;	
			}
			hijo = compactarNodos(hijo, profundidadNodo + 1);	
		}
		return nodo;		
	}
	
	/**
	 * Comprime las etiquetas de las aristas para definirlas por los indices
	 * inicial y final del texto
	 * @param nodo
	 * @param alfabeto
	 * @return
	 */
	private Nodo comprimirEtiquetas(Nodo nodo, int alfabeto) {
		for (int i = 0; i < nodo.hijos.size(); i++) {
			Nodo hijo = nodo.hijos.get(i);
			
			// Comprueba si la etiqueta es demasiado grande 
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
	 * Devuelve la repeticion mas larga dentro del arbol de sufijos
	 * @return
	 */
	public String repeticionMasLarga(){
		return repeticionMasLargaRec(super.raiz, "");	
	}
	
	/**
	 * Busca la repeticion mas larga de forma recursiva en el arbol, 
	 * en este caso, el nodo mas profundo con dos hijos o mas
	 * @param nodo
	 * @param texto
	 * @return
	 */
	private String repeticionMasLargaRec(Nodo nodo, String texto) {
		
		// Si tiene menos de dos hijos no es una repeticion
		if (nodo.hijos.size() < 2) {
			return texto;      
		}
		
		String mejor = "";
			
		if (nodo.aristaPadre != null) {		
			String etiquetaHijo = nodo.aristaPadre.etiqueta;
			// Si es una etiqueta comprimida se pasa a la real
			if (nodo.aristaPadre.etiqueta.contains(",")) {
				String[] fromTo = etiquetaHijo.split(",");
				
				try {
					int from = Integer.parseInt(fromTo[0]);
					int to = Integer.parseInt(fromTo[1]);
					etiquetaHijo = super.texto.substring(from-1, to);	
				}
				catch (NumberFormatException e) {
					etiquetaHijo = etiquetaHijo.replace(", ", "");
				}
			}
			texto = texto + etiquetaHijo;
		}
		
		// Se busca en los hijos el mayor string repetido
		for (Nodo hijo : nodo.hijos) {
			String nuevo = repeticionMasLargaRec(hijo, texto);

			if (nuevo != null && nuevo.length() > mejor.length()) {
				mejor = nuevo;
			}		
		}	
		return mejor;
	}
	
	/**
	 * Encuentra la repeticion mas larga dentro del arbol de sufijos
	 * @return
	 */
	/*public String repeticionMasLarga() {
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
	}*/
	
	/*private  String repeticionMasLarga(String s, String t){
		int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i))
                return s.substring(0, i);
        }
        return s.substring(0, n);		
	}*/
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> repeticionesMaximales(){
		LinkedHashMap<String, String> maximales=new LinkedHashMap<String, String>();
		//ArrayList<String> maximales=new ArrayList<String>();
		/*int N = super.texto.length();
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
		}*/
		return new ArrayList<String>(maximales.keySet());
	}	
}