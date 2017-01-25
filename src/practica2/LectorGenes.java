package practica2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class LectorGenes {
	
	private String fichero;
	
	public LectorGenes(String fichero) {
		this.fichero = fichero;
	}
	
	
	public Hashtable<String, String> obtenerGenes() {
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
}