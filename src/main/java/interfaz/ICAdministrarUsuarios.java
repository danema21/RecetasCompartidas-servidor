package interfaz;

import datatypes.DtUsuario;

public interface ICAdministrarUsuarios {

	public DtUsuario[] listarUsuariosNoBaneados();
	
	public DtUsuario[] listarUsuariosBaneados();
	
	public void bloquearUsuario(String idDeUsuario, String idDelAdministrador);
	
	public void desbloquearUsuario(String idDeUsuario, String idDelAdministrador);

	void eliminarUsuario(String idDeUsuario, String idDelAdministrador);
}
