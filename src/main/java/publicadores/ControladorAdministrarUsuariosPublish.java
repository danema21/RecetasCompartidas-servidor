package publicadores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

import datatypes.DtUsuario;
import interfaz.Fabrica;
import interfaz.ICAdministrarUsuarios;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ControladorAdministrarUsuariosPublish {
	private Fabrica fabrica;
    private ICAdministrarUsuarios icau;
    private Endpoint endpoint = null;

    public ControladorAdministrarUsuariosPublish() {
        fabrica = Fabrica.getInstance();
        icau = fabrica.getCAdministrarUsuarios();
    }
    
    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:1942/ControladorAdministrarUsuariosPublish", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    //METODOS A PUBLICAR
    
    @WebMethod
    public DtUsuario[] listarUsuariosNoBaneados() {
    	return icau.listarUsuariosNoBaneados();
    }
    
    @WebMethod
    public DtUsuario[] listarUsuariosBaneados() {
    	return icau.listarUsuariosBaneados();
    }
    
    @WebMethod
    public void bloquearUsuario(String idDeUsuario, String idDelAdministrador) {
    	icau.bloquearUsuario(idDeUsuario, idDelAdministrador);
    }
    
    @WebMethod
    public void desbloquearUsuario(String idDeUsuario, String idDelAdministrador) {
    	icau.desbloquearUsuario(idDeUsuario, idDelAdministrador);
    }
    
    @WebMethod
    public void eliminarUsuario(String idDeUsuario, String idDelAdministrador) {
    	icau.eliminarUsuario(idDeUsuario, idDelAdministrador);
    }
}
