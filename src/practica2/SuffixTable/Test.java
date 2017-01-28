/**
* Programa de pruebas para las tablas de suffijos
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    28-01-2017
*/
package practica2.SuffixTable;

public class Test {
	
	
	public static void main(String[] args) {
		SuffixTable st=new SuffixTable("BANANAS");
		System.out.println(st.repeticionMasLarga());
		System.out.println("Repeticiones maximas");
		for(String s:st.repeticionesMaximales())
			System.out.println("	->"+s);
	}

}
