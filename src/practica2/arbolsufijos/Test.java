package practica2.arbolsufijos;

public class Test {
	
	
	public static void main(String[] args) {
		ArbolSufijosCompacto compacto=new ArbolSufijosCompacto("BANANAS");
		System.out.println(compacto.repeticionMasLarga());
		System.out.println("Repeticiones maximas");
		for(String s:compacto.repeticionesMaximas())
			System.out.println("	->"+s);
	}

}
