package logica.controladores;

import excepciones.UsuarioRepetidoException;
import interfaz.ICRegistrarUsuario;
import logica.clases.Administrador;
import logica.clases.Espectador;
import logica.manejadores.ManejadorUsuario;

public class CRegistrarUsuario implements ICRegistrarUsuario{
	private String idDeUsuario;
	private String nombre;
	private String apellido;
	private String password;
	private String imagenDePerfil;
	
	@Override
	public void ingresarDatos(String idDeUsuario, String nombre, String apellido, String password, String imagenDePerfil) throws UsuarioRepetidoException {
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.imagenDePerfil = imagenDePerfil;
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		if(mu.buscarUsuario(idDeUsuario) != null) {
			throw new UsuarioRepetidoException("Ya hay alguien usando el nombre\nde usuario: " + idDeUsuario + ",\n elija otro nombre de usuario");
		}else {
			this.idDeUsuario = idDeUsuario;
		}
	}
	
	@Override
	public void registrar() {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		
		if(this.password.equals("damianema21admin")) {
			Administrador admin = new Administrador(this.idDeUsuario, this.nombre, this.apellido, this.password, this.imagenDePerfil);
			mu.agregarUsuario(admin);
		}else {
			Espectador esp = new Espectador(this.idDeUsuario, this.nombre, this.apellido, this.password, this.imagenDePerfil, false);
			mu.agregarUsuario(esp);
		}
	}
	
}
