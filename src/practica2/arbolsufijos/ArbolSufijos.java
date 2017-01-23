package practica2.arbolsufijos;

public class ArbolSufijos {
	
	String texto = null;
	public Nodo raiz = null;
	
	/**
	 * 
	 * @param texto
	 */
	ArbolSufijos(String texto) {
		if (texto.length() > 0 && texto.charAt(texto.length() - 1) == '$') {
			this.texto = texto;	
		} else {
			this.texto = texto + "$";	
		}	
	}
}