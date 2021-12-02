package logica.controladores;

import java.util.List;

import datatypes.DtReceta;
import interfaz.ICConsultaReceta;
import logica.clases.Receta;
import logica.manejadores.ManejadorReceta;

public class CConsultaReceta implements ICConsultaReceta{
	
	@Override
	public DtReceta[] listarRecetas() {
		ManejadorReceta mr = ManejadorReceta.getInstancia();
		List<String> listaRecetas = mr.listarRecetas();
		DtReceta[] salida = new DtReceta[listaRecetas.size()];
		int i = 0;
		for(String s : listaRecetas) {
			Receta r = mr.buscarReceta(s);
			salida[i] = new DtReceta(r.getNombre(), r.getAutor(), r.getImagen(), null, null);
			i++;
		}
		
		return salida;
	}
	
	@Override
	public DtReceta mostrarInfo(String nombreReceta) {
		ManejadorReceta mr = ManejadorReceta.getInstancia();
		Receta r = mr.buscarReceta(nombreReceta);
		DtReceta salida = new DtReceta(r.getNombre(), r.getAutor(), r.getImagen(), r.getIngredientes(), r.getDescripcionDelProceso());
		return salida;
	}
}
