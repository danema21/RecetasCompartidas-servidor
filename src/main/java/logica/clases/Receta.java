package logica.clases;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "receta")
@javax.persistence.Table(name = "receta")
public class Receta {
	@Id
	private String nombre;
	private String autor;
	private String imagen;
	private String ingredientes;
	private String descripcionDelProceso;
	
	public Receta() {
		super();
	}

	public Receta(String nombre, String autor, String imagen, String ingredientes, String descripcionDelProceso) {
		super();
		this.nombre = nombre;
		this.autor = autor;
		this.imagen = imagen;
		this.ingredientes = ingredientes;
		this.descripcionDelProceso = descripcionDelProceso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getDescripcionDelProceso() {
		return descripcionDelProceso;
	}

	public void setDescripcionDelProceso(String descripcionDelProceso) {
		this.descripcionDelProceso = descripcionDelProceso;
	}
	
}
