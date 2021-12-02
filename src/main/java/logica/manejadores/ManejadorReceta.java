package logica.manejadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import logica.clases.Receta;
import persistencia.Conexion;

public class ManejadorReceta {
	private static ManejadorReceta instancia = null;
	private List<Receta> recetas = new ArrayList<Receta>();
	
	public static ManejadorReceta getInstancia() {
		if(instancia == null) {
			instancia = new ManejadorReceta();
		}
		return instancia;
	}
	
	public Receta buscarReceta(String nombre) {
		Receta salida = null;
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		salida = em.find(Receta.class, nombre);
		return salida;
	}
	
	public List<String> listarRecetas(){
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
	    Query query = em.createQuery("from receta");
	    
		@SuppressWarnings("unchecked")
		List<Receta> lista = query.getResultList();
		
	    List<String> salida = new ArrayList<>();
	    for (Receta r: lista) {
	    	salida.add(r.getNombre());
	    }
		return salida;
	}
	
	public void agregarReceta(Receta r) {
		this.recetas.add(r);
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
			
		em.getTransaction().begin();			
		em.persist(r);			
		em.getTransaction().commit();
	}
	
	public void eliminarReceta(Receta r) {
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
			
		em.getTransaction().begin();			
		em.remove(r);			
		em.getTransaction().commit();
	}
}
