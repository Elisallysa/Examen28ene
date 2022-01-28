package models;

public class Alumno {
	private int id;
	private String nombre;
	private String apellidos;
	private String ciclo;
	private double media;
	private Profesor profe1;
	private Profesor profe2;
	
	public Alumno(int id, String nombre, String apellidos, String ciclo, double media) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.ciclo = ciclo;
		this.media = media;
	}

	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public Profesor getProfe1() {
		return profe1;
	}

	public void setProfe1(Profesor profe1) {
		this.profe1 = profe1;
	}

	public Profesor getProfe2() {
		return profe2;
	}

	public void setProfe2(Profesor profe2) {
		this.profe2 = profe2;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", apellidos=" + apellidos + ", ciclo=" + ciclo + ", media=" + media + "]";
	}
}
