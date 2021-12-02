package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import datatypes.DtUsuario;
import interfaz.ICAdministrarUsuarios;

public class AdministrarUsuarios extends JInternalFrame {
	private ICAdministrarUsuarios interfazAdministrarUsuarios;
	private JList<String> listBaneados;
	private JList<String> listNoBaneados;
	private DefaultListModel<String> modelListNoBaneados = new DefaultListModel<String>();
	private DefaultListModel<String> modelListBaneados = new DefaultListModel<String>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public AdministrarUsuarios(ICAdministrarUsuarios interfazAdministrarUsuarios) {
		setTitle("Administrar usuarios");
		setBounds(100, 100, 349, 300);
		
		this.interfazAdministrarUsuarios = interfazAdministrarUsuarios;
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 89, 214);
		getContentPane().add(scrollPane);
		
		listNoBaneados = new JList<String>();
		scrollPane.setViewportView(listNoBaneados);
		
		JButton btnlistar = new JButton("listar");
		btnlistar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarActionPerformed();
				btnlistar.setEnabled(false);
			}
		});
		btnlistar.setBounds(109, 43, 115, 23);
		getContentPane().add(btnlistar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(234, 45, 89, 214);
		getContentPane().add(scrollPane_1);
		
		listBaneados = new JList<String>();
		scrollPane_1.setViewportView(listBaneados);
		
		JButton btnBloquear = new JButton("bloquear");
		btnBloquear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bloquearActionPerformed();
			}
		});
		btnBloquear.setBounds(109, 77, 115, 23);
		getContentPane().add(btnBloquear);
		
		JButton btnDesbloquear = new JButton("desbloquear");
		btnDesbloquear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desbloquearActionPerformed();
			}
		});
		btnDesbloquear.setBounds(109, 111, 115, 23);
		getContentPane().add(btnDesbloquear);
		
		JButton btnEliminar = new JButton("eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarActionPerformed();
			}
		});
		btnEliminar.setBounds(109, 145, 115, 23);
		getContentPane().add(btnEliminar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarActionPerformed();
				btnlistar.setEnabled(true);
			}
		});
		btnCerrar.setBounds(109, 236, 115, 23);
		getContentPane().add(btnCerrar);
		
		JLabel lblNewLabel = new JLabel("NO BLOQUEADOS");
		lblNewLabel.setBounds(10, 20, 109, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BLOQUEADOS");
		lblNewLabel_1.setBounds(234, 20, 89, 14);
		getContentPane().add(lblNewLabel_1);
	}
	
	//FUNCIONES HECHAS MANUALMENTE
	public void listarActionPerformed() {
		limpiarCampos();
		DtUsuario[] listaNoBaneados = interfazAdministrarUsuarios.listarUsuariosNoBaneados();
		for(DtUsuario dtu : listaNoBaneados) {
			modelListNoBaneados.addElement(dtu.getIdDeUsuario());
		}
		listNoBaneados.setModel(modelListNoBaneados);
		
		DtUsuario[] listaBaneados = interfazAdministrarUsuarios.listarUsuariosBaneados();
		for(DtUsuario dtu : listaBaneados) {
			modelListBaneados.addElement(dtu.getIdDeUsuario());
		}
		listBaneados.setModel(modelListBaneados);
	}
	
	public void bloquearActionPerformed(){
		String u = listNoBaneados.getSelectedValue();
		if(u != null) {
			interfazAdministrarUsuarios.bloquearUsuario(u, "d-dalto");
			listarActionPerformed();
			JOptionPane.showMessageDialog(this, "El usuario " + u + " ha sido bloqueado", "Bloquear usuario", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "No ha seleccionado ningun usuario para bloquear", "Bloquear usuario", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void desbloquearActionPerformed() {
		String u = listBaneados.getSelectedValue();
		if(u != null) {
			interfazAdministrarUsuarios.desbloquearUsuario(u, "d-dalto");
			listarActionPerformed();
			JOptionPane.showMessageDialog(this, "El usuario " + u + " ha sido desbloqueado", "Desbloquear usuario", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "No ha seleccionado ningun usuario para desbloquear", "Desbloquear usuario", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void eliminarActionPerformed() {
		String u = listBaneados.getSelectedValue();
		if(u != null) {
			interfazAdministrarUsuarios.eliminarUsuario(u, "d-dalto");
			listarActionPerformed();
			JOptionPane.showMessageDialog(this, "El usuario " + u + " ha sido eliminado", "Eliminar usuario", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario bloqueado antes de eliminarlo", "Eliminar usuario", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cerrarActionPerformed() {
		setVisible(false);
		limpiarCampos();
	}
	
	public void limpiarCampos() {
		modelListNoBaneados.clear();
		modelListBaneados.clear();
		
		listNoBaneados.setModel(modelListNoBaneados);
		listBaneados.setModel(modelListBaneados);
	}
}
