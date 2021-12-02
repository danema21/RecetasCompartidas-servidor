package logica.controladores;

import java.util.List;

import javax.persistence.EntityManager;

import datatypes.DtUsuario;
import interfaz.ICAdministrarUsuarios;
import logica.clases.Administrador;
import logica.clases.Espectador;
import logica.clases.Notificacion;
import logica.clases.Receta;
import logica.clases.Usuario;
import logica.manejadores.ManejadorNotificacion;
import logica.manejadores.ManejadorReceta;
import logica.manejadores.ManejadorUsuario;
import persistencia.Conexion;

public class CAdminitrarUsuarios implements ICAdministrarUsuarios{
	
	@Override
	public DtUsuario[] listarUsuariosNoBaneados() {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		List<String> listaUsuarios = mu.listarUsuarios();
		for(String s : mu.listarUsuarios()) {
			Usuario usu = mu.buscarUsuario(s);
			if(usu instanceof Administrador) {
				listaUsuarios.remove(s);
				
			}else if(((Espectador)usu).isBloqueado()) {
				listaUsuarios.remove(s);
			}
		}
		
		DtUsuario[] salida = new DtUsuario[listaUsuarios.size()];
		int i = 0;
		for(String s : listaUsuarios) {
			Espectador esp = (Espectador)mu.buscarUsuario(s);
			salida[i] = new DtUsuario(esp.getIdDeUsuario(), esp.getNombre(), esp.getApellido(), esp.getImagenDePerfil(), esp.isBloqueado());
			i++;
		}
		
		return salida;
	}
	
	@Override
	public DtUsuario[] listarUsuariosBaneados() {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		List<String> listaUsuarios = mu.listarUsuarios();
		for(String s : mu.listarUsuarios()) {
			Usuario usu = mu.buscarUsuario(s);
			if(usu instanceof Administrador) {
				listaUsuarios.remove(s);
				
			}else if(!((Espectador)usu).isBloqueado()) {
				listaUsuarios.remove(s);
			}
		}
		
		DtUsuario[] salida = new DtUsuario[listaUsuarios.size()];
		int i = 0;
		for(String s : listaUsuarios) {
			Espectador esp = (Espectador)mu.buscarUsuario(s);
			salida[i] = new DtUsuario(esp.getIdDeUsuario(), esp.getNombre(), esp.getApellido(), esp.getImagenDePerfil(), esp.isBloqueado());
			i++;
		}
		
		return salida;
	}
	
	@Override
	public void bloquearUsuario(String idDeUsuario, String idDelAdministrador) {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Usuario u = mu.buscarUsuario(idDeUsuario);
		((Espectador)u).setBloqueado(true);
		((Administrador)mu.buscarUsuario(idDelAdministrador)).bloquearUsuario(u);
	}
	
	@Override
	public void desbloquearUsuario(String idDeUsuario, String idDelAdministrador) {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Usuario u = mu.buscarUsuario(idDeUsuario);
		((Espectador)u).setBloqueado(false);
		((Administrador)mu.buscarUsuario(idDelAdministrador)).desbloquearUsuario(u);
	}
	
	@Override
	public void eliminarUsuario(String idDeUsuario, String idDelAdministrador) {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		ManejadorReceta mr = ManejadorReceta.getInstancia();
		ManejadorNotificacion mn= ManejadorNotificacion.getInstancia();
		
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
		
		Usuario u = mu.buscarUsuario(idDeUsuario);
		if(((Espectador)u).isBloqueado()) {
			for(String s : mr.listarRecetas()) {
				Receta r = mr.buscarReceta(s);
				if(r.getAutor().equals(u.getIdDeUsuario())) {
					for(String s2 : mu.listarUsuarios()) {
						Usuario u2 = mu.buscarUsuario(s2);
						u2.eliminarReceta(r);
						em.getTransaction().begin();			
						em.persist(u2);			
						em.getTransaction().commit();
					}
					mr.eliminarReceta(r);
				}
			}
			
			for(String s : mn.listarNotificaciones()) {
				Notificacion n = mn.buscarNotificacion(s);
				if(n.getAutorDeReceta().equals(u.getIdDeUsuario())) {
					for(String s2 : mu.listarUsuarios()) {
						Usuario u2 = mu.buscarUsuario(s2);
						u2.eliminarNotificacion(n);
						em.getTransaction().begin();
						em.persist(u2);
						em.getTransaction().commit();
					}
					mn.eliminarNotificacion(n);
				}
			}
			
			Administrador admin = (Administrador)mu.buscarUsuario(idDelAdministrador);
			admin.desbloquearUsuario(u);
			em.getTransaction().begin();
			em.persist(admin);
			em.getTransaction().commit();
			
			mu.eliminarUsuario(u);
		}
	}
}
