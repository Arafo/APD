package practica2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class LectorGenes {
	
	private String fichero;
	
	public LectorGenes(String fichero) {
		this.fichero = fichero;
	}
	
	public Hashtable<String, String> obtenerGenes() {
		if (new File(fichero).isFile()) {
			return obtenerGenesFichero();
		}
		else if (new File(fichero).isDirectory()) {
			return obtenerGenesDirectorio();
		}
		return null;
	}

	private Hashtable<String, String> obtenerGenesFichero() {
		Hashtable<String, String> genes = new Hashtable<String, String>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(fichero))) {
		    String line = br.readLine();
		    String key = null;
		    StringBuilder sb = new StringBuilder();

		    while (line != null) {
		    	if (line.startsWith(">")) {

		    		if (sb.length() != 0)
		    			genes.put(key, sb.toString());

		    		key = line;
		    		line = "";
		    		
		    		if (sb.length() != 0) {
		    			sb = new StringBuilder();
		    			continue;
		    		}
		    	}

		    	sb.append(line);    	
		        line = br.readLine();
		    }
		    
    		if (sb.length() != 0)
    			genes.put(key, sb.toString());
		    
		}
		catch(IOException e) {
            System.err.println("Error");
            System.exit(1);  
		}
		
		return genes;
	}
	
	private Hashtable<String, String> obtenerGenesDirectorio() {
		Hashtable<String, String> genes = new Hashtable<String, String>();
		
		File directorio = new File(fichero);
		File[] ficheros = directorio.listFiles();
		
		for (File fichero : ficheros) {
			try(BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			    String line = br.readLine();
			    String key = null;
			    StringBuilder sb = new StringBuilder();

			    while (line != null) {
			    	if (line.startsWith(">")) {

			    		if (sb.length() != 0)
			    			genes.put(key, sb.toString());

			    		key = line;
			    		line = "";
			    		
			    		if (sb.length() != 0) {
			    			sb = new StringBuilder();
			    			continue;
			    		}
			    	}

			    	sb.append(line);    	
			        line = br.readLine();
			    }
			    
	    		if (sb.length() != 0)
	    			genes.put(key, sb.toString());
			    
			}
			catch(IOException e) {
	            System.err.println("Error");
	            System.exit(1);  
			}
		}
		
		return genes;
	}
}