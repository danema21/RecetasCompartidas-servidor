package logica.manejadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import logica.clases.Usuario;
import persistencia.Conexion;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public static ManejadorUsuario getInstancia() {
		if(instancia == null) {
			instancia = new ManejadorUsuario();
		}
		return instancia;
	}
	
	public Usuario buscarUsuario(String idDeUsuario) {
		Usuario salida = null;
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		salida = em.find(Usuario.class, idDeUsuario);
		
		return salida;
	}
	
	public List<String> listarUsuarios(){
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
	    Query query = em.createQuery("from usuario");
	    
		@SuppressWarnings("unchecked")
		List<Usuario> lista = query.getResultList();
		
	    List<String> salida = new ArrayList<>();
	    for (Usuario u: lista) {
	    	salida.add(u.getIdDeUsuario());
	    }
	    return salida;
	}
	
	public void agregarUsuario(Usuario u) {
		this.usuarios.add(u);
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
			
		em.getTransaction().begin();			
		em.persist(u);			
		em.getTransaction().commit();
	}
	
	public void eliminarUsuario(Usuario u) {
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
			
		em.getTransaction().begin();			
		em.remove(u);			
		em.getTransaction().commit();
	}
}
