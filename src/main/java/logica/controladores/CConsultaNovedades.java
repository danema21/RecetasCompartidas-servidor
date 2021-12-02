package logica.controladores;

import java.util.List;

import datatypes.DtNotificacion;
import datatypes.DtReceta;
import interfaz.ICConsultaNovedades;
import logica.clases.Notificacion;
import logica.clases.Receta;
import logica.manejadores.ManejadorNotificacion;
import logica.manejadores.ManejadorReceta;

public class CConsultaNovedades implements ICConsultaNovedades {
	
	@Override
	public DtNotificacion[] listarNotificaciones() {
		ManejadorNotificacion mn = ManejadorNotificacion.getInstancia();
		List<String> listaNotificaciones = mn.listarNotificaciones();
		DtNotificacion[] salida = new DtNotificacion[listaNotificaciones.size()];
		int i = 0;
		for(String s : listaNotificaciones) {
			Notificacion n = mn.buscarNotificacion(s);
			salida[i] = new DtNotificacion(n.getNombreReceta(), n.getImagenDeReceta(), n.getAutorDeReceta(), n.getFechaDePublicacion());
			i++;
		}
		
		return salida;
	}
	
	@Override
	public DtReceta verReceta(String nombreReceta) {
		ManejadorReceta mr = ManejadorReceta.getInstancia();
		Receta r = mr.buscarReceta(nombreReceta);
		DtReceta salida = new DtReceta(r.getNombre(), r.getAutor(), r.getImagen(), r.getIngredientes(), r.getDescripcionDelProceso());
		return salida;
	}
}
