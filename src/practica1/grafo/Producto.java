package practica1.grafo;

public class Producto {
	
	private String nombre;
	private int unidades;
	private double precio;
	private String marca;
	
	public Producto(String nombre, int unidades, double precio, String marca) {
		super();
		this.nombre = nombre;
		this.unidades = unidades;
		this.precio = precio;
		this.marca = marca;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getUnidades() {
		return unidades;
	}
	
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getMarca() {
		return marca;
	}
}