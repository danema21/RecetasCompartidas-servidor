package datatypes;

public class DtUsuario {
	private String idDeUsuario;
	private String nombre;
	private String apellido;
	private String imagenDePerfil;
	private boolean bloqueado;
	
	public DtUsuario() {
		super();
	}

	public DtUsuario(String idDeUsuario, String nombre, String apellido, String imagenDePerfil, boolean bloqueado) {
		super();
		this.idDeUsuario = idDeUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.imagenDePerfil = imagenDePerfil;
		this.bloqueado = bloqueado;
	}

	public String getIdDeUsuario() {
		return idDeUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getImagenDePerfil() {
		return imagenDePerfil;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setIdDeUsuario(String idDeUsuario) {
		this.idDeUsuario = idDeUsuario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setImagenDePerfil(String imagenDePerfil) {
		this.imagenDePerfil = imagenDePerfil;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
}
