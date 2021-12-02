package publicadores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

import excepciones.RecetaRepetidaException;
import interfaz.Fabrica;
import interfaz.ICRegistrarReceta;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ControladorRegistrarRecetaPublish {
	private Fabrica fabrica;
	private ICRegistrarReceta icrr;
	private Endpoint endpoint = null;
	
	public ControladorRegistrarRecetaPublish() {
		fabrica = Fabrica.getInstance();
		icrr = fabrica.getCRegistrarReceta();
	}
	
	@WebMethod(exclude = true)
	public void publicar() {
		endpoint = Endpoint.publish("http://localhost:1942/ControladorRegistrarRecetaPublish", this);
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	//METODOS A PUBLICAR
	@WebMethod
	public String[] listarUsuarios() {
		return icrr.listarUsuarios();
	}
	
	@WebMethod
	public void ingresarDatos(String nombre, String autor, String imagen, String ingredientes, String descripcionDelProceso) throws RecetaRepetidaException {
		icrr.ingresarDatos(nombre, autor, imagen, ingredientes, descripcionDelProceso);
	}
	
	@WebMethod
	public void registrar() {
		icrr.registrar();
	}
}
