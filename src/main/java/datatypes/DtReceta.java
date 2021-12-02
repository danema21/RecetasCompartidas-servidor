package datatypes;

public class DtReceta {
	private String nombre;
	private String autor;
	private String imagen;
	private String ingredientes;
	private String descripionDelProceso;
	
	public DtReceta() {
		super();
	}

	public DtReceta(String nombre, String autor, String imagen, String ingredientes, String descripionDelProceso) {
		super();
		this.nombre = nombre;
		this.autor = autor;
		this.imagen = imagen;
		this.ingredientes = ingredientes;
		this.descripionDelProceso = descripionDelProceso;
	}

	public String getNombre() {
		return nombre;
	}

	public String getAutor() {
		return autor;
	}

	public String getImagen() {
		return imagen;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public String getDescripionDelProceso() {
		return descripionDelProceso;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public void setDescripionDelProceso(String descripionDelProceso) {
		this.descripionDelProceso = descripionDelProceso;
	}

}
