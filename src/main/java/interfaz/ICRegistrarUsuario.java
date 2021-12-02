package interfaz;

import excepciones.UsuarioRepetidoException;

public interface ICRegistrarUsuario {
	
	public void ingresarDatos(String idDeUsuario, String nombre, String apellido, String password, String imagenDePerfil) throws UsuarioRepetidoException;
	
	public void registrar();
}
