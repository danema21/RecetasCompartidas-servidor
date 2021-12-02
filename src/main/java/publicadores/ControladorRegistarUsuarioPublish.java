package publicadores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

import excepciones.UsuarioRepetidoException;
import interfaz.Fabrica;
import interfaz.ICRegistrarUsuario;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ControladorRegistarUsuarioPublish {
	private Fabrica fabrica;
	private ICRegistrarUsuario icru;
	private Endpoint endpoint = null;
	
	public ControladorRegistarUsuarioPublish() {
		fabrica = Fabrica.getInstance();
		icru = fabrica.getCRegistrarUsuario();
	}
	
	@WebMethod(exclude = true)
	public void publicar() {
		endpoint = Endpoint.publish("http://localhost:1942/ControladorRegistrarUsuarioPublish", this);
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	//METODOS A PUBLICAR
	
	@WebMethod
	public void ingresarDatos(String idDeUsuario, String nombre, String apellido, String password, String imagenDePerfil) throws UsuarioRepetidoException {
		icru.ingresarDatos(idDeUsuario, nombre, apellido, password, imagenDePerfil);
	}
	
	@WebMethod
	public void registrar() {
		icru.registrar();
	}
}
