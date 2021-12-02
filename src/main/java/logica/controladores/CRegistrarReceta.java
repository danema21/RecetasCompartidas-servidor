package logica.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import excepciones.RecetaRepetidaException;
import interfaz.ICRegistrarReceta;
import logica.clases.Notificacion;
import logica.clases.Receta;
import logica.clases.Usuario;
import logica.manejadores.ManejadorNotificacion;
import logica.manejadores.ManejadorReceta;
import logica.manejadores.ManejadorUsuario;
import persistencia.Conexion;

public class CRegistrarReceta implements ICRegistrarReceta {
	private String nombre;
	private String autor;
	private String imagen;
	private String ingredientes;
	private String descripcionDelProceso;
	
	@Override
	public String[] listarUsuarios() {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		List<String> listaUsuarios = mu.listarUsuarios();
		
		String[] salida = new String[listaUsuarios.size()];
		int i = 0;
		for(String u : listaUsuarios) {
			salida[i] = u;
			i++;
		}
		
		return salida;
	}
	
	@Override
	public void ingresarDatos(String nombre, String autor, String imagen, String ingredientes, String descripcionDelProceso) throws RecetaRepetidaException {
		this.autor = autor;
		this.imagen = imagen;
		this.ingredientes = ingredientes;
		this.descripcionDelProceso = descripcionDelProceso;
		
		ManejadorReceta mr = ManejadorReceta.getInstancia();
		if(mr.buscarReceta(nombre) != null) {
			throw new RecetaRepetidaException("Esa receta ya existe en la pagina, \npruebe con otro nombre o haga otra receta");
		}else {
			this.nombre = nombre;
		}
	}
	
	@Override
	public void registrar() {
		ManejadorReceta mr = ManejadorReceta.getInstancia();
		Receta r = new Receta(this.nombre, this.autor, this.imagen, this.ingredientes, this.descripcionDelProceso);
		mr.agregarReceta(r);
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Usuario u = mu.buscarUsuario(autor);
		u.agregarReceta(r);
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
		em.getTransaction().begin();			
		em.persist(u);			
		em.getTransaction().commit();
		
		ManejadorNotificacion mn = ManejadorNotificacion.getInstancia();
		LocalDate fechaP = LocalDate.now();
		String fechaParseada = fechaP.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		Notificacion n = new Notificacion(this.nombre, this.imagen, this.autor, fechaParseada);
		mn.agregarNotificacion(n);
		Query query = em.createQuery("from usuario");
		
		@SuppressWarnings("unchecked")
		List<Usuario> lista = query.getResultList();
		for (Usuario usu: lista) {
	    	usu.agregarNotificacion(n);
	    	em.getTransaction().begin();
	    	em.persist(usu);
	    	em.getTransaction().commit();
	    }
	}
}
