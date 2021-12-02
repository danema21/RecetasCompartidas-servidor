package logica.clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name = "usuario")
@javax.persistence.Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Usuario {
	@Id
	private String idDeUsuario;
	private String nombre;
	private String apellido;
	private String password;
	private String imagenDePerfil;
	
	@OneToMany
	private List<Receta> recetas = new ArrayList<Receta>();
	
	@ManyToMany
	private List<Notificacion> notificaciones = new ArrayList<Notificacion>();

	public Usuario() {
		super();
	}

	public Usuario(String idDeUsuario, String nombre, String apellido, String password, String imagenDePerfil) {
		super();
		this.idDeUsuario = idDeUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.imagenDePerfil = imagenDePerfil;
	}

	public String getIdDeUsuario() {
		return idDeUsuario;
	}

	public void setIdDeUsuario(String idDeUsuario) {
		this.idDeUsuario = idDeUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagenDePerfil() {
		return imagenDePerfil;
	}

	public void setImagenDePerfil(String imagenDePerfil) {
		this.imagenDePerfil = imagenDePerfil;
	}
	
	public void agregarReceta(Receta r) {
		this.recetas.add(r);
	}
	
	public void eliminarReceta(Receta r) {
		this.recetas.remove(r);
	}
	
	public void agregarNotificacion(Notificacion n) {
		this.notificaciones.add(n);
	}
	
	public void eliminarNotificacion(Notificacion n) {
		this.notificaciones.remove(n);
	}
}
