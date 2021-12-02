package presentacion;

import javax.swing.JInternalFrame;

import interfaz.ICConsultaNovedades;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;

import datatypes.DtNotificacion;
import javax.swing.event.ListSelectionEvent;

public class ConsultaNovedades extends JInternalFrame {
	private ICConsultaNovedades interfazConsultaNovedades;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> list;
	private DefaultListModel<String> modelListNotificaciones = new DefaultListModel<String>();
	private JButton btnlistar = new JButton("Ver novedades");

	/**
	 * Create the frame.
	 */
	public ConsultaNovedades(ICConsultaNovedades interfazConsultaNovedades) {
		setTitle("Consulta novedades");
		setBounds(100, 100, 363, 327);
		
		this.interfazConsultaNovedades = interfazConsultaNovedades;
		getContentPane().setLayout(null);
		
		btnlistar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarNovedadesActionPerformed();
				btnlistar.setEnabled(false);
			}
		});
		btnlistar.setBounds(10, 11, 327, 23);
		getContentPane().add(btnlistar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 327, 206);
		getContentPane().add(scrollPane);
		
		list = new JList<String>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				verRecetaActionPerformed();
			}
		});
		scrollPane.setViewportView(list);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarActionPerformed();
			}
		});
		btnCerrar.setBounds(248, 262, 89, 23);
		getContentPane().add(btnCerrar);
	}
	
	//FUNCIONES IMPLEMENTADAS MANUALMENTE
	public void listarNovedadesActionPerformed(){
		DtNotificacion[] listaNotificaciones = interfazConsultaNovedades.listarNotificaciones();
		for(DtNotificacion dtn : listaNotificaciones) {
			modelListNotificaciones.addElement(dtn.getAutorDeReceta() + " publico " + dtn.getNombreReceta() + ". El " + dtn.getFechaDePublicacion());
		}
		list.setModel(modelListNotificaciones);
	}
	
	public void verRecetaActionPerformed() {
		//Opcional, en la web va a andar de todas formas
	}
	
	public void cerrarActionPerformed() {
		setVisible(false);
		limpiarCampos();
		btnlistar.setEnabled(true);
	}
	
	public void limpiarCampos() {
		this.modelListNotificaciones.clear();
		this.list.setModel(this.modelListNotificaciones);
	}
}
