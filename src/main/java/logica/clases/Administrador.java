package logica.clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario{
	@OneToMany
	private List<Usuario> baneados = new ArrayList<Usuario>();
	
	public Administrador() {
		super();
	}

	public Administrador(String idDeUsuario, String nombre, String apellido, String password, String imagenDePerfil) {
		super(idDeUsuario, nombre, apellido, password, imagenDePerfil);
	}
	
	public void bloquearUsuario(Usuario u) {
		this.baneados.add(u);
	}
	
	public void desbloquearUsuario(Usuario u) {
		this.baneados.remove(u);
	}
	
}
