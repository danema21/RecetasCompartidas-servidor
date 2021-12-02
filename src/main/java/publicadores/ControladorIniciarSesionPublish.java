package publicadores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

import interfaz.Fabrica;
import interfaz.ICIniciarSesion;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ControladorIniciarSesionPublish {
	private Fabrica fabrica;
    private ICIniciarSesion icis;
    private Endpoint endpoint = null;

    public ControladorIniciarSesionPublish() {
        fabrica = Fabrica.getInstance();
        icis = fabrica.getCIniciarSesion();
    }
    
    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:1942/ControladorIniciarSesionPublish", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    //METODOS A PUBLICAR
    
    public String iniciarSesion(String idUsuario, String password) {
    	return icis.iniciarSesion(idUsuario, password);
    }
}