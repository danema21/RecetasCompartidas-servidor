package presentacion;

import javax.swing.JInternalFrame;

import interfaz.ICRegistrarUsuario;
import javax.swing.JTextField;

import excepciones.UsuarioRepetidoException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegistrarUsuario extends JInternalFrame {
	
	private ICRegistrarUsuario interfazRegistrarUsuario;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIdUsuario;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JPasswordField textFieldPassword;

	/**
	 * Create the frame.
	 */
	public RegistrarUsuario(ICRegistrarUsuario interfazRegistrarUsuario) {
		setTitle("RegistrarUsuario");
		setBounds(100, 100, 280, 248);
		getContentPane().setLayout(null);
		
		this.interfazRegistrarUsuario = interfazRegistrarUsuario;
		
		textFieldIdUsuario = new JTextField();
		textFieldIdUsuario.setToolTipText("");
		textFieldIdUsuario.setBounds(80, 33, 176, 20);
		getContentPane().add(textFieldIdUsuario);
		textFieldIdUsuario.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarActionPerformed();
			}
		});
		btnRegistrar.setBounds(166, 184, 90, 23);
		getContentPane().add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarActionPerformed();
			}
		});
		btnCancelar.setBounds(66, 184, 90, 23);
		getContentPane().add(btnCancelar);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 70, 74, 14);
		getContentPane().add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(80, 64, 176, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		lblNewLabel_1.setBounds(10, 101, 74, 14);
		getContentPane().add(lblNewLabel_1);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(80, 95, 176, 20);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setBounds(10, 132, 74, 14);
		getContentPane().add(lblNewLabel_2);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(80, 126, 176, 20);
		getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(10, 39, 74, 14);
		getContentPane().add(lblNewLabel);

	}
	//FUNCIONES IMPLEMENTADAS MANUALMENTE
	public void cancelarActionPerformed() {
		setVisible(false);
		limpiarCampos();
	}
	
	public boolean checkFormulario() {
		if(textFieldIdUsuario.getText().isEmpty() || textFieldNombre.getText().isEmpty() || textFieldApellido.getText().isEmpty() || textFieldPassword.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "No pueden haber campos vacios", "Registrar usuario", JOptionPane.ERROR_MESSAGE);
        	return false;
		}else {
			return true;
		}
	}
	
	public void agregarActionPerformed() {
		if(checkFormulario()) {
			try {
				interfazRegistrarUsuario.ingresarDatos(textFieldIdUsuario.getText(), textFieldNombre.getText(), textFieldApellido.getText(), String.valueOf(textFieldPassword.getPassword()) , null);
				this.interfazRegistrarUsuario.registrar();
				JOptionPane.showMessageDialog(this, "El usuario se registro con exito", "Registrar usuario", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				limpiarCampos();
			}catch(UsuarioRepetidoException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar usuario", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void limpiarCampos() {
		this.textFieldIdUsuario.setText("");
		this.textFieldNombre.setText("");
		this.textFieldApellido.setText("");
		char[] passwd = this.textFieldPassword.getPassword();
		for(int i = 0; i < passwd.length; i++) {
			passwd[i] = '\0';
		}
		this.textFieldPassword.setText("");
	}
	
}
