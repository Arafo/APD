/**
* La clase GestorDatos se ocupa de generar y leer ficheros 
* de productos y de grafos.
*
* @author  Rafael Marcen Altarriba (650435)
* @author  Jose Angel Caudevilla Casasus (649003)
* @version 1.0
* @date    09-01-2017
*/

package practica1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Random;
import java.util.StringTokenizer;

import practica1.grafo.Producto;

public class GestorDatos {
	
	private String FICHERO_PRODUCTOS = "grafos/productos.dat";
	private String FICHERO_RELACIONES = "grafos/relaciones.dat";
	
	// Datos de los prodcutos
	private final int MAX_UNIDADES = 1000;
	private final int MAX_PRECIO = 1000;
	private final int MAX_JUNTOS = 10;
	
	private int productos;
	private boolean override = true;
	private boolean enteros = false;

	/**
	 * Constructor
	 * @param productos Número de productos
	 */
	public GestorDatos(int productos) {
		this.productos = productos;
	}
	
	/**
	 * Constructor
	 * @param productos Número de productos
	 * @param override Sobreescribe los ficheros existentes
	 */
	public GestorDatos(int productos, boolean override) {
		this.productos = productos;
		this.override = override;
	}
	
	/**
	 * Constructor
	 * @param productos Número de productos
	 * @param override Sobreescribe los ficheros existentes
	 * @param enteros Matriz de enteros
	 */
	public GestorDatos(int productos, boolean override, boolean enteros) {
		this.productos = productos;
		this.override = override;
		this.enteros = enteros;
	}
	
	/**
	 * Constructor
	 * @param productos Número de productos
	 * @param ficheroRelaciones Ruta del fichero de la matriz
	 * @param ficheroProductos Ruta del fichero de los productos
	 */
	public GestorDatos(int productos, String ficheroRelaciones, String ficheroProductos) {
		this.productos = productos;
		this.FICHERO_PRODUCTOS = ficheroProductos;
		this.FICHERO_RELACIONES = ficheroRelaciones;
	}
	
	/**
	 * Genera los datos de productos y las conexiones del grafo en los ficheros
	 * <ficheroProdcutos> y <ficheroRelaciones>
	 * @param ficheroProductos Ruta del fichero de los productos
	 * @param ficheroRelaciones Ruta del fichero de la matriz
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
		String[] marcas = new String[]{"amazon", "amazonymas"};

        /*
         * Generación de productos
         */
        
        String nombre;
        int unidades;
        double precio;
        String marca;
        
        for (int i = 0; i < productos; i++) {
        	nombre = "Producto" + i;
            unidades = 1 + r.nextInt(MAX_UNIDADES);
            precio = r.nextDouble() * MAX_PRECIO;
            marca = marcas[r.nextInt(marcas.length)];
            pw1.println(nombre + " " + unidades + " " + String.format("%.2f", precio) + " " + marca);
        }     
        pw1.flush();
        pw1.close();

        /*
         * Generación de relaciones entre productos
         */
        Object matriz[][] = new Object[productos][productos];
        for (int i = 0; i < productos; i++) {
        	for (int j = 0; j < productos; j++) {
        		if (i == j) 
        			matriz[i][j] = !enteros ? false : 0;
        		else if (j < i)
        			matriz[i][j] = matriz[j][i];
        		else {
        			matriz[i][j] = !enteros ? r.nextBoolean() : r.nextInt(MAX_JUNTOS);
        		}
        	}
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
	 * Devuelve una tabla con los productos almacenados en el fichero 
	 * <FICHERO_PRODUCTOS>
	 * @return Tabla de productos
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
		    			Double.parseDouble(st.nextToken()),
		    			st.nextToken());
		    	tabla.put(key, p);
		    	key++;
		    	
		        line = br.readLine();
		    }
		}
		catch(IOException e) {
            System.err.println("Error");
            System.exit(1);  
		}
		
		return tabla;
	}
	
	/**
	 * Devuelve una tabla de booleanos con las conexiones del grafo almacenadass en
	 * el fichero <FICHERO_RELACIONES>
	 * @return Matriz de relaciones de booleanos
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
	 * Devuelve una tabla de enteros con las conexiones del grafo almacenadass en
	 * el fichero <FICHERO_RELACIONES>
	 * @return Matriz de relaciones de enteros
	 */
	public int[][] obtenerRelacionesEnteros() {
		int relaciones[][] = new int[productos][productos];
		int i = 0, j = 0;
		
		try(BufferedReader br = new BufferedReader(new FileReader(FICHERO_RELACIONES))) {
		    String line = br.readLine();

		    while (line != null) {	    	
		    	StringTokenizer st = new StringTokenizer(line);
		        while (st.hasMoreTokens()) {
		        	relaciones[i][j] = Integer.parseInt(st.nextToken());
		            i++;          
		        }       
		        i = 0;
		        j++;
		        
		        line = br.readLine();
		    }
		}
		catch(IOException e) {
            System.err.println("Error");
            System.exit(1);  
		}
		
		return relaciones;
	}
}