package publicadores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

import datatypes.DtReceta;
import interfaz.Fabrica;
import interfaz.ICConsultaReceta;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ControladorConsultaRecetaPublish {
	private Fabrica fabrica;
    private ICConsultaReceta iccr;
    private Endpoint endpoint = null;

    public ControladorConsultaRecetaPublish() {
        fabrica = Fabrica.getInstance();
        iccr = fabrica.getCConsultaReceta();
    }
    
    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:1942/ControladorConsultaRecetaPublish", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    //METODOS A PUBLICAR
    
    @WebMethod
    public DtReceta[] listarRecetas() {
    	return iccr.listarRecetas();
    }
    
    @WebMethod
    public DtReceta mostrarInfo(String nombreReceta) {
    	return iccr.mostrarInfo(nombreReceta);
    }
}
