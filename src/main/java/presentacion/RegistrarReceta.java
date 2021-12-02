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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import excepciones.RecetaRepetidaException;
import interfaz.ICRegistrarReceta;

public class RegistrarReceta extends JInternalFrame {
	private ICRegistrarReceta interfazRegistrarReceta;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextArea textAreaIngredientes;
	private JTextArea textAreaDescripcion;
	private JList<String> listAutores;
	private DefaultListModel<String> modelListAutores = new DefaultListModel<String>();
	private JButton btnListar = new JButton("listar");

	/**
	 * Create the frame.
	 */
	public RegistrarReceta(ICRegistrarReceta interfazRegistrarReceta) {
		setTitle("RegistrarReceta");
		setBounds(100, 100, 458, 389);
		
		this.interfazRegistrarReceta = interfazRegistrarReceta;
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("nombre:");
		lblNewLabel.setBounds(10, 11, 150, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ingredientes:");
		lblNewLabel_1.setBounds(10, 67, 150, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("descripcion del proceso:");
		lblNewLabel_2.setBounds(10, 168, 150, 14);
		getContentPane().add(lblNewLabel_2);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(20, 36, 253, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("autor:");
		lblNewLabel_3.setBounds(283, 11, 50, 14);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarActionPerformed();
				btnListar.setEnabled(true);
			}
		});
		btnRegistrar.setBounds(343, 331, 89, 23);
		getContentPane().add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelarActionPerformed();
				btnListar.setEnabled(true);
			}
		});
		btnCancelar.setBounds(244, 331, 89, 23);
		getContentPane().add(btnCancelar);
		
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarUsuariosActionPerformed();
				btnListar.setEnabled(false);
			}
		});
		btnListar.setBounds(343, 7, 89, 23);
		getContentPane().add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(283, 36, 149, 284);
		getContentPane().add(scrollPane);
		
		listAutores = new JList<String>();
		scrollPane.setViewportView(listAutores);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 92, 253, 65);
		getContentPane().add(scrollPane_1);
		
		textAreaIngredientes = new JTextArea();
		scrollPane_1.setViewportView(textAreaIngredientes);
		textAreaIngredientes.setLineWrap(true);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(20, 193, 253, 127);
		getContentPane().add(scrollPane_2);
		
		textAreaDescripcion = new JTextArea();
		scrollPane_2.setViewportView(textAreaDescripcion);
		textAreaDescripcion.setLineWrap(true);
	}
	
	//FUNCIONES IMPLEMENTADAS MANUALMENTE
	public void listarUsuariosActionPerformed() {
		String[] autores = interfazRegistrarReceta.listarUsuarios();
		for(int i = 0; i < autores.length; i++) {
			modelListAutores.addElement(autores[i]);
		}
		listAutores.setModel(modelListAutores);
	}
	
	public void agregarActionPerformed() {
		if(checkFormulario()) {
			try {
				interfazRegistrarReceta.ingresarDatos(this.textFieldNombre.getText(), this.listAutores.getSelectedValue(), null, this.textAreaIngredientes.getText(), this.textAreaDescripcion.getText());
				interfazRegistrarReceta.registrar();
				JOptionPane.showMessageDialog(this, "La receta se registro con exito", "Registrar receta", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				limpiarCampos();
			} catch (RecetaRepetidaException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar receta", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void cancelarActionPerformed() {
		setVisible(false);
		limpiarCampos();
	}
	
	public boolean checkFormulario() {
		if(this.textFieldNombre.getText().isEmpty() || this.textAreaIngredientes.getText().isEmpty() || this.textAreaDescripcion.getText().isEmpty() || this.listAutores.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(this, "No pueden haber campos vacios", "Registrar receta", JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			return true;
		}
	}
	
	public void limpiarCampos() {
		this.textFieldNombre.setText("");
		this.textAreaIngredientes.setText("");
		this.textAreaDescripcion.setText("");
		this.modelListAutores.clear();
		this.listAutores.setModel(this.modelListAutores);
	}
}
