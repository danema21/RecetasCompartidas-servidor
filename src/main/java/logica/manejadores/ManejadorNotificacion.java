package logica.manejadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import logica.clases.Notificacion;
import persistencia.Conexion;

public class ManejadorNotificacion {
	private static ManejadorNotificacion instancia = null;
	private List<Notificacion> notificaciones = new ArrayList<Notificacion>();
	
	public static ManejadorNotificacion getInstancia() {
		if(instancia == null) {
			instancia = new ManejadorNotificacion();
		}
		return instancia;
	}
	
	public Notificacion buscarNotificacion(String nombreReceta) {
		Notificacion salida = null;
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		salida = em.find(Notificacion.class, nombreReceta);
		
		return salida;
	}
	
	public List<String> listarNotificaciones(){
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
	    Query query = em.createQuery("from notificacion");
	    
		@SuppressWarnings("unchecked")
		List<Notificacion> lista = query.getResultList();
		
	    List<String> salida = new ArrayList<>();
	    for (Notificacion n: lista) {
	    	salida.add(n.getNombreReceta());
	    }
	    return salida;
	}
	
	public void agregarNotificacion(Notificacion n) {
		this.notificaciones.add(n);
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
			
		em.getTransaction().begin();			
		em.persist(n);			
		em.getTransaction().commit();
	}
	
	public void eliminarNotificacion(Notificacion n) {
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
			
		em.getTransaction().begin();			
		em.remove(n);			
		em.getTransaction().commit();
	}
}
