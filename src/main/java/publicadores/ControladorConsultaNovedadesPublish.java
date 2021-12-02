package publicadores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

import datatypes.DtNotificacion;
import datatypes.DtReceta;
import interfaz.Fabrica;
import interfaz.ICConsultaNovedades;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ControladorConsultaNovedadesPublish {
	private Fabrica fabrica;
    private ICConsultaNovedades iccn;
    private Endpoint endpoint = null;

    public ControladorConsultaNovedadesPublish() {
        fabrica = Fabrica.getInstance();
        iccn = fabrica.getCConsultaNovedades();
    }
    
    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:1942/ControladorConsultaNovedadesPublish", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    //METODOS A PUBLICAR
    
    @WebMethod
    public DtNotificacion[] listarNotificaciones() {
    	return iccn.listarNotificaciones();
    }
    
    @WebMethod
    public DtReceta verReceta(String nombreReceta) {
    	return iccn.verReceta(nombreReceta);
    }
}
