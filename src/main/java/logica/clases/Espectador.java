package logica.clases;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ESPECTADOR")
public class Espectador extends Usuario {
	private boolean bloqueado;

	public Espectador() {
		super();
	}

	public Espectador(String idDeUsuario, String nombre, String apellido, String password, String imagenDePerfil, boolean bloqueado) {
		super(idDeUsuario, nombre, apellido, password, imagenDePerfil);
		this.bloqueado = bloqueado;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}	
	
}
