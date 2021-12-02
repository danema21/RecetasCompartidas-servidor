package presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import interfaz.Fabrica;
import interfaz.ICAdministrarUsuarios;
import interfaz.ICConsultaNovedades;
import interfaz.ICConsultaReceta;
import interfaz.ICRegistrarReceta;
import interfaz.ICRegistrarUsuario;
import publicadores.ControladorAdministrarUsuariosPublish;
import publicadores.ControladorConsultaNovedadesPublish;
import publicadores.ControladorConsultaRecetaPublish;
import publicadores.ControladorIniciarSesionPublish;
import publicadores.ControladorRegistarUsuarioPublish;
import publicadores.ControladorRegistrarRecetaPublish;

public class Principal {

	private JFrame frmRecetascompartidas;
	private RegistrarUsuario registrarUsuarioJInternalFrame;
	private RegistrarReceta registrarRecetaJInternalFrame;
	private ConsultaReceta consultaRecetaJInternalFrame;
	private ConsultaNovedades consultaNovedadesJInternalFrame;
	private AdministrarUsuarios administrarUsuariosJInternalFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmRecetascompartidas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		ControladorRegistarUsuarioPublish crup = new ControladorRegistarUsuarioPublish();
		ControladorRegistrarRecetaPublish crrp = new ControladorRegistrarRecetaPublish();
		ControladorConsultaRecetaPublish ccrp = new ControladorConsultaRecetaPublish();
		ControladorConsultaNovedadesPublish ccnp = new ControladorConsultaNovedadesPublish();
		ControladorAdministrarUsuariosPublish caup = new ControladorAdministrarUsuariosPublish();
		ControladorIniciarSesionPublish cisp = new ControladorIniciarSesionPublish();
		crup.publicar();
		crrp.publicar();
		ccrp.publicar();
		ccnp.publicar();
		caup.publicar();
		cisp.publicar();
		
		initialize();
		
		Fabrica fabrica = Fabrica.getInstance();
		ICRegistrarUsuario interfazRegistrarUsuario = fabrica.getCRegistrarUsuario();
		ICRegistrarReceta interfazRegistrarReceta = fabrica.getCRegistrarReceta();
		ICConsultaReceta interfazConsultaReceta = fabrica.getCConsultaReceta();
		ICConsultaNovedades interfazConsultaNovedades = fabrica.getCConsultaNovedades();
		ICAdministrarUsuarios interfazAdministrarUsuarios = fabrica.getCAdministrarUsuarios();
		
		registrarUsuarioJInternalFrame = new RegistrarUsuario(interfazRegistrarUsuario);
		registrarUsuarioJInternalFrame.setBounds(100, 100, 280, 248);
		registrarRecetaJInternalFrame = new RegistrarReceta(interfazRegistrarReceta);
		registrarRecetaJInternalFrame.setBounds(100, 100, 458, 389);
		consultaRecetaJInternalFrame = new ConsultaReceta(interfazConsultaReceta);
		consultaRecetaJInternalFrame.setBounds(100, 100, 432, 370);
		consultaNovedadesJInternalFrame = new ConsultaNovedades(interfazConsultaNovedades);
		administrarUsuariosJInternalFrame = new AdministrarUsuarios(interfazAdministrarUsuarios);
		frmRecetascompartidas.getContentPane().setLayout(null);
		
		frmRecetascompartidas.getContentPane().add(registrarUsuarioJInternalFrame);
		frmRecetascompartidas.getContentPane().add(registrarRecetaJInternalFrame);
		frmRecetascompartidas.getContentPane().add(consultaRecetaJInternalFrame);
		frmRecetascompartidas.getContentPane().add(consultaNovedadesJInternalFrame);
		frmRecetascompartidas.getContentPane().add(administrarUsuariosJInternalFrame);
		
		JLabel lblBkgImage = new JLabel("New label");
		lblBkgImage.setBounds(0, 0, 606, 423);
		
		ImageIcon imageIcon = new ImageIcon(Principal.class.getResource("/imagenes/RecetasCompartidasFondo.png")); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(lblBkgImage.getWidth(), lblBkgImage.getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		
		lblBkgImage.setIcon(imageIcon);
		frmRecetascompartidas.getContentPane().add(lblBkgImage);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRecetascompartidas = new JFrame();
		frmRecetascompartidas.setResizable(false);
		frmRecetascompartidas.getContentPane().setBackground(Color.BLACK);
		frmRecetascompartidas.setTitle("RECETAS COMPARTIDAS WORKSTATION");
		frmRecetascompartidas.setBounds(100, 100, 622, 484);
		frmRecetascompartidas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmRecetascompartidas.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("AGREGAR");
		menuBar.add(mnNewMenu);
		
		JMenuItem itemAgregarUsuario = new JMenuItem("USUARIO");
		itemAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dimension desktopSize = frmRecetascompartidas.getSize();
				Dimension windowSize = registrarUsuarioJInternalFrame.getSize();
				ocultarTodasLasVentanas();
				registrarUsuarioJInternalFrame.setLocation((desktopSize.width - windowSize.width)/2, ((desktopSize.height - windowSize.height)/2) - 40);
				registrarUsuarioJInternalFrame.setVisible(true);
			}
		});
		mnNewMenu.add(itemAgregarUsuario);
		
		JMenuItem itemAgregarReceta = new JMenuItem("RECETA");
		itemAgregarReceta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dimension desktopSize = frmRecetascompartidas.getSize();
				Dimension windowSize = registrarRecetaJInternalFrame.getSize();
				ocultarTodasLasVentanas();
				registrarRecetaJInternalFrame.setLocation((desktopSize.width - windowSize.width)/2, ((desktopSize.height - windowSize.height)/2) - 40);
				registrarRecetaJInternalFrame.setVisible(true);
			}
		});
		mnNewMenu.add(itemAgregarReceta);
		
		JMenu mnNewMenu_1 = new JMenu("CONSULTAR");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem itemConsultaReceta = new JMenuItem("RECETAS");
		itemConsultaReceta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dimension desktopSize = frmRecetascompartidas.getSize();
				Dimension windowSize = consultaRecetaJInternalFrame.getSize();
				ocultarTodasLasVentanas();
				consultaRecetaJInternalFrame.setLocation((desktopSize.width - windowSize.width)/2, ((desktopSize.height - windowSize.height)/2) - 40);
				consultaRecetaJInternalFrame.setVisible(true);
			}
		});
		mnNewMenu_1.add(itemConsultaReceta);
		
		JMenuItem itemConsultaNovedades = new JMenuItem("NOVEDADES");
		itemConsultaNovedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dimension desktopSize = frmRecetascompartidas.getSize();
				Dimension windowSize = consultaNovedadesJInternalFrame.getSize();
				ocultarTodasLasVentanas();
				consultaNovedadesJInternalFrame.setLocation((desktopSize.width - windowSize.width)/2, ((desktopSize.height - windowSize.height)/2) - 40);
				consultaNovedadesJInternalFrame.setVisible(true);
			}
		});
		mnNewMenu_1.add(itemConsultaNovedades);
		
		JMenu mnAdministrar = new JMenu("ADMINISTRAR");
		menuBar.add(mnAdministrar);
		
		JMenuItem itemAdministrarUsuarios = new JMenuItem("USUARIOS");
		itemAdministrarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dimension desktopSize = frmRecetascompartidas.getSize();
				Dimension windowSize = administrarUsuariosJInternalFrame.getSize();
				ocultarTodasLasVentanas();
				administrarUsuariosJInternalFrame.setLocation((desktopSize.width - windowSize.width)/2, ((desktopSize.height - windowSize.height)/2) - 40);
				administrarUsuariosJInternalFrame.setVisible(true);
			}
		});
		mnAdministrar.add(itemAdministrarUsuarios);
	}
	
	//FUNCIONES HECHAS MANUALMENTE
	public void ocultarTodasLasVentanas() {
		registrarUsuarioJInternalFrame.setVisible(false);
		registrarRecetaJInternalFrame.setVisible(false);
		consultaRecetaJInternalFrame.setVisible(false);
		consultaNovedadesJInternalFrame.setVisible(false);
		administrarUsuariosJInternalFrame.setVisible(false);
	}
}
