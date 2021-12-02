package interfaz;

import excepciones.RecetaRepetidaException;

public interface ICRegistrarReceta {
	
	public String[] listarUsuarios();
	
	public void ingresarDatos(String nombre, String autor, String imagen, String ingredientes, String descripcionDelProceso) throws RecetaRepetidaException;

	public void registrar();
}
