package interfaz;

import datatypes.DtNotificacion;
import datatypes.DtReceta;

public interface ICConsultaNovedades {

	public DtNotificacion[] listarNotificaciones();
	
	public DtReceta verReceta(String nombreReceta);
}
