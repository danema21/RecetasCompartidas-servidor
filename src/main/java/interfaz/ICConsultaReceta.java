package interfaz;

import datatypes.DtReceta;

public interface ICConsultaReceta {
	
	public DtReceta[] listarRecetas();
	
	public DtReceta mostrarInfo(String nombreReceta);
}
