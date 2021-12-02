package logica.clases;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "notificacion")
@javax.persistence.Table(name = "notificacion")
public class Notificacion {
	@Id
	private String nombreReceta;
	private String imagenDeReceta;
	private String autorDeReceta;
	private String fechaDePublicacion;
	
	public Notificacion() {
		super();
	}

	public Notificacion(String nombreReceta, String imagenDeReceta, String autorDeReceta, String fechaDePublicacion) {
		super();
		this.nombreReceta = nombreReceta;
		this.imagenDeReceta = imagenDeReceta;
		this.autorDeReceta = autorDeReceta;
		this.fechaDePublicacion = fechaDePublicacion;
	}

	public String getNombreReceta() {
		return nombreReceta;
	}

	public void setNombreReceta(String nombreReceta) {
		this.nombreReceta = nombreReceta;
	}

	public String getImagenDeReceta() {
		return imagenDeReceta;
	}

	public void setImagenDeReceta(String imagenDeReceta) {
		this.imagenDeReceta = imagenDeReceta;
	}

	public String getAutorDeReceta() {
		return autorDeReceta;
	}

	public void setAutorDeReceta(String autorDeReceta) {
		this.autorDeReceta = autorDeReceta;
	}

	public String getFechaDePublicacion() {
		return fechaDePublicacion;
	}

	public void setFechaDePublicacion(String fechaDePublicacion) {
		this.fechaDePublicacion = fechaDePublicacion;
	}
	
}
