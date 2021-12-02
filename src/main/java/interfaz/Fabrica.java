package interfaz;

import logica.controladores.*;

public class Fabrica {
	private static Fabrica instancia= null;
	
	private Fabrica() {}
	
	public static Fabrica getInstance() {
		if (instancia == null) {
			instancia = new Fabrica();
	    }
	    return instancia;
	 }
	
	public ICRegistrarUsuario getCRegistrarUsuario() {
		CRegistrarUsuario icru= new CRegistrarUsuario();
		return icru;
	}
	
	public ICRegistrarReceta getCRegistrarReceta() {
		CRegistrarReceta icrr = new CRegistrarReceta();
		return icrr;
	}
	
	public ICConsultaReceta getCConsultaReceta() {
		CConsultaReceta iccr = new CConsultaReceta();
		return iccr;
	}
	
	public ICConsultaNovedades getCConsultaNovedades() {
		CConsultaNovedades iccn = new CConsultaNovedades();
		return iccn;
	}
	
	public ICAdministrarUsuarios getCAdministrarUsuarios() {
		CAdminitrarUsuarios icau = new CAdminitrarUsuarios();
		return icau;
	}
	
	public ICIniciarSesion getCIniciarSesion() {
		CIniciarSesion icis = new CIniciarSesion();
		return icis;
	}
}
