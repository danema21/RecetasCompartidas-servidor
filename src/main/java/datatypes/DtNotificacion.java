package datatypes;

public class DtNotificacion {
	private String nombreReceta;
	private String imagenDeReceta;
	private String autorDeReceta;
	private String fechaDePublicacion;
	
	public DtNotificacion() {
		super();
	}

	public DtNotificacion(String nombreReceta, String imagenDeReceta, String autorDeReceta,
			String fechaDePublicacion) {
		super();
		this.nombreReceta = nombreReceta;
		this.imagenDeReceta = imagenDeReceta;
		this.autorDeReceta = autorDeReceta;
		this.fechaDePublicacion = fechaDePublicacion;
	}

	public String getNombreReceta() {
		return nombreReceta;
	}

	public String getImagenDeReceta() {
		return imagenDeReceta;
	}

	public String getAutorDeReceta() {
		return autorDeReceta;
	}

	public String getFechaDePublicacion() {
		return fechaDePublicacion;
	}

	public void setNombreReceta(String nombreReceta) {
		this.nombreReceta = nombreReceta;
	}

	public void setImagenDeReceta(String imagenDeReceta) {
		this.imagenDeReceta = imagenDeReceta;
	}

	public void setAutorDeReceta(String autorDeReceta) {
		this.autorDeReceta = autorDeReceta;
	}

	public void setFechaDePublicacion(String fechaDePublicacion) {
		this.fechaDePublicacion = fechaDePublicacion;
	}
	
}
