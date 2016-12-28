package practica1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

public class GestorDatos {
	
	private final static String FICHERO_PRODUCTOS = "src/practica1/productos.dat";
	private final static String FICHERO_RELACIONES = "src/practica1/relaciones.dat";
	
	// Cosas del producto
	private final int MAX_UNIDADES = 1000;
	private final int MAX_PRECIO = 1000;
	
	private int productos;
	private boolean override = true;

	/**
	 * Constructor
	 * @param productos
	 */
	public GestorDatos(int productos) {
		this.productos = productos;
	}
	
	/**
	 * Constructor
	 * @param productos
	 * @param override
	 */
	public GestorDatos(int productos, boolean override) {
		this.productos = productos;
		this.override = override;
	}
	
	/**
	 * 
	 * @param ficheroProductos
	 * @param ficheroRelaciones
	 */
	public void generarDatos(String ficheroProductos, String ficheroRelaciones) {
		
		// Si no se quiere sobreescribir los ficheros salimos
		if (!override) {
			File f1 = new File(ficheroProductos);
			File f2 = new File(ficheroRelaciones);
			
			// Si no existen los ficheros se crean aunque override sea false
			if (f1.exists() && f2.exists())
				return;
		}
		
        PrintWriter pw1 = null, pw2 = null;

        try {
            pw1 = new PrintWriter(new FileWriter(ficheroProductos));
            pw2 = new PrintWriter(new FileWriter(ficheroRelaciones));
        }
        catch (IOException e) {
            System.err.println("Error");
            System.exit(1);    
        }
        
        Random r = new Random();

        /*
         * Generaci�n de productos
         */
        
        String nombre;
        int unidades;
        double precio;
        
        for (int i = 0; i < productos; i++) {
        	nombre = "Producto" + i;
            unidades = 1 + r.nextInt(MAX_UNIDADES);
            precio = r.nextDouble() * MAX_PRECIO;
            pw1.println(nombre + " " + unidades + " " + String.format("%.2f", precio));
        }     
        pw1.flush();
        pw1.close();

        /*
         * Generaci�n de relaciones entre productos
         */
        
        //boolean conectado = false;
        boolean matriz[][] = new boolean[productos][productos];
        for (int i = 0; i < productos; i++) {
        	for (int j = 0; j < productos; j++) {
        		if (i == j) 
        			matriz[i][j] = false;
        		else if (j < i)
        			matriz[i][j] = matriz[j][i];
        		else {
        			matriz[i][j] = r.nextBoolean();
        			//conectado &= matriz[i][j];
        		}
        	}
        	// Vertice no conectado con nada
        	/*if (!conectado) {
        		int s = (productos - i) + r.nextInt(productos - 1);
        		System.out.println(s);
        		matriz[i][s] = true;
        	}*/
        }
        
    	StringBuilder relacion;
        for (int i = 0; i < productos; i++) {
        	relacion = new StringBuilder();
            for (int j = 0; j < productos; j++) {
            	relacion.append(matriz[i][j] + " ");
            }
            pw2.println(relacion);
        }
        pw2.flush();
        pw2.close();
	}
	
	/**
	 * 
	 * @return
	 */
	public Hashtable<Integer, Producto> obtenerProductos() {
		Hashtable<Integer, Producto> tabla = new Hashtable<Integer, Producto>();
		Producto p;
		int key = 0;
		
		try(BufferedReader br = new BufferedReader(new FileReader(FICHERO_PRODUCTOS))) {
		    String line = br.readLine();

		    while (line != null) {
		    	StringTokenizer st = new StringTokenizer(line);
		    	p = new Producto(st.nextToken(), 
		    			Integer.parseInt(st.nextToken()), 
		    			Double.parseDouble(st.nextToken()));
		    	tabla.put(key, p);
		    	key++;
		    	
		        line = br.readLine();
		        //System.out.println(p.getNombre() + " " + p.getUnidades() + " " + p.getPrecio());
		    }
		}
		catch(IOException e) {
            System.err.println("Error");
            System.exit(1);  
		}
		
		return tabla;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean[][] obtenerRelaciones() {
		boolean relaciones[][] = new boolean[productos][productos];
		int i = 0, j = 0;
		
		try(BufferedReader br = new BufferedReader(new FileReader(FICHERO_RELACIONES))) {
		    String line = br.readLine();

		    while (line != null) {	    	
		    	StringTokenizer st = new StringTokenizer(line);
		        while (st.hasMoreTokens()) {
		        	relaciones[i][j] = Boolean.parseBoolean(st.nextToken());
		        	//System.out.print(relaciones[i][j] + " ");
		            i++;          
		        }       
		        i = 0;
		        j++;
		        
		        //System.out.println();        
		        line = br.readLine();
		    }
		}
		catch(IOException e) {
            System.err.println("Error");
            System.exit(1);  
		}
		
		return relaciones;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Para que las comas sean puntos
		Locale.setDefault(new Locale("en", "UK"));
		
		GestorDatos gd = new GestorDatos(10, true);
		gd.generarDatos(FICHERO_PRODUCTOS, FICHERO_RELACIONES);
		Hashtable<Integer, Producto> productos = gd.obtenerProductos();
		boolean matriz[][] = gd.obtenerRelaciones();
		Grafo g = new Grafo(matriz, productos);
		System.out.println(g.toString());
		//System.out.println(g.AdjString(matriz));
	}
}