package logica.controladores;

import interfaz.ICIniciarSesion;
import logica.clases.Usuario;
import logica.manejadores.ManejadorUsuario;

public class CIniciarSesion implements ICIniciarSesion{

	@Override
	public String iniciarSesion(String idUsuario, String password) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
        Usuario u = mU.buscarUsuario(idUsuario);
        if(u != null && u.getPassword().equals(password)) {
        	return u.getIdDeUsuario();
        }else {
        	return null;
        }
	}
}