package practica2.SuffixTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
* Clase que implementa una tabla de sufijos
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    28-01-2017
*/
public class SuffixTable {
	
	private String texto;
	
	public SuffixTable(String s){
		this.texto=s;
	}
	
	
	/**
	 * Encuentra la repeticion mas larga dentro del arbol de sufijos
	 * @return
	 */
	public String repeticionMasLarga() {
		int N = texto.length();
		String[] sufijos = new String[N];
		for (int i = 0; i < N; i++) {
			sufijos[i] = texto.substring(i, N);
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
		int N = texto.length();
		String[] sufijos = new String[N];
		for (int i = 0; i < N; i++) {
			sufijos[i] = texto.substring(i, N);
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
