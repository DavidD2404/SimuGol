package tpfinal;

public class Jugador {
	private String nombre;
	private int numeroCamiseta;
	private String posicion;
	private int edad;
	
	public Jugador(String nombre, int numeroCamiseta, String posicion, int edad) {
		super();
		this.nombre = nombre;
		this.numeroCamiseta = numeroCamiseta;
		this.posicion = posicion;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroCamiseta() {
		return numeroCamiseta;
	}

	public void setNumeroCamiseta(int numeroCamiseta) {
		this.numeroCamiseta = numeroCamiseta;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
}
