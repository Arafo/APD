package practica2;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import practica2.arbolsufijos.ArbolSufijosCompacto;

public class Repeticiones {

	public static void main(String[] args) {
		
		/*LectorGenes lg = new LectorGenes("genes/12S.fasta");
		Hashtable<String, String> genes = lg.obtenerGenes();
		
		Iterator<Map.Entry<String, String>> it = genes.entrySet().iterator();
		while (it.hasNext()) { 
			Map.Entry<String, String> entry = it.next();
			System.out.println(entry.getKey());			
			System.out.println(entry.getValue());
		}*/

		String cadena_ADN = "AATAGGTTTGGTCCTAGCCTTTCTATTAGCTCTTAGTAAGATTACACATGCAAGCATCCC"
				+ "CGTTCCAGTGAGTTCACCCTCTAAATCACCACGATCAAAAGGGACAAGCATCAAGCACGC"
				+ "AGCAATGCAGCTCAAAACGCTTAGCCTAGCCACACCCCCACGGGAAACAGCAGTGATTAA"
				+ "CCTTTAGCAATAAACGAAAGTTTAACTAAGCTATACTAACCCCAGGGTTGGTCAATTTCG"
				+ "TGCCAGCCACCGCGGTCACACGATTAACCCAAGTCAATAGAAGCCGGCGTAAAGAGTGTT"
				+ "TTAGATCACCCCCTCCCCAATAAAGCTAAAACTCACCTGAGTTGTAAAAAACTCCAGTTG"
				+ "ACACAAAATAGACTACGAAAGTGGCTTTAACATATCTGAACACACAATAGCTAAGACCCA"
				+ "AACTGGGATTAGATACCCCACTATGCTTAGCCCTAAACCTCAACAGTTAAATCAACAAAA"
				+ "CTGCTCGCCAGAACACTACGAGCCACAGCTTAAAACTCAAAGGACCTGGCGGTGCTTCAT"
				+ "ATCCCTCTAGAGGAGCCTGTTCTGTAATCGATAAACCCCGATCAACCTCACCACCTCTTG"
				+ "CTCAGCCTATATACCGCCATCTTCAGCAAACCCTGATGAAGGCTACAAAGTAAGCGCAAG"
				+ "TACCCACGTAAAGACGTTAGGTCAAGGTGTAGCCCATGAGGTGGCAAGAAATGGGCTACA"
				+ "TTTTCTACCCCAGAAAACTACGATAGCCCTTATGAAACTTAAGGGTCGAAGGTGGATTTA"
				+ "GCAGTAAACTGAGAGTAGAGTGCTTAGTTGAACAGGGCCCTGAAGCGCGTACACACCGCC"
				+ "CGTCACCCTCCTCAAGTATACTTCAAAGGACATTTAACTAAAACCCCTACGCATTTATAT"
				+ "AGAGGAGACAAGTCGTAACATGGTAAGTGTACTGGAAAGTGCACTTGGACGAAC";

		ArbolSufijosCompacto arbol = new ArbolSufijosCompacto(cadena_ADN);
		System.out.println(arbol.raiz);
		System.exit(0);
		
		System.out.println("Repeticion mas larga");
		System.out.println(arbol.repeticionMasLarga());
		System.out.println("Repeticion maximales:");
		for (String s : arbol.repeticionesMaximales()) {
			System.out.println("	->" + s);
		}
	}
}