package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import datatypes.DtReceta;
import interfaz.ICConsultaReceta;

public class ConsultaReceta extends JInternalFrame {
	ICConsultaReceta interfazConsultaReceta;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textAreaIngredientes;
	private JTextArea textAreaProcedimiento;
	private JList<String> listRecetas;
	private DefaultListModel<String> modelListRecetas = new DefaultListModel<String>();
	private JButton btnListar = new JButton("listar");

	/**
	 * Create the frame.
	 */
	public ConsultaReceta(ICConsultaReceta interfazConsultaReceta) {
		setTitle("Consulta receta");
		setBounds(100, 100, 432, 370);

		this.interfazConsultaReceta = interfazConsultaReceta;
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("elejir receta:");
		lblNewLabel.setBounds(10, 11, 76, 14);
		getContentPane().add(lblNewLabel);
		
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarActionPerformed();
				btnListar.setEnabled(false);
			}
		});
		btnListar.setBounds(92, 7, 89, 23);
		getContentPane().add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 396, 50);
		getContentPane().add(scrollPane);
		
		listRecetas = new JList<String>();
		listRecetas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				mostrarInfoActionPerformed();
			}
		});
		scrollPane.setViewportView(listRecetas);
		
		JLabel lblNewLabel_1 = new JLabel("Ingredientes:");
		lblNewLabel_1.setBounds(10, 97, 89, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Procedimiento: ");
		lblNewLabel_2.setBounds(135, 97, 89, 14);
		getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 122, 115, 175);
		getContentPane().add(scrollPane_1);
		
		textAreaIngredientes = new JTextArea();
		textAreaIngredientes.setLineWrap(true);
		textAreaIngredientes.setEditable(false);
		scrollPane_1.setViewportView(textAreaIngredientes);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(135, 122, 271, 175);
		getContentPane().add(scrollPane_2);
		
		textAreaProcedimiento = new JTextArea();
		textAreaProcedimiento.setLineWrap(true);
		textAreaProcedimiento.setEditable(false);
		scrollPane_2.setViewportView(textAreaProcedimiento);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarActionPerformed();
			}
		});
		btnCerrar.setBounds(317, 308, 89, 23);
		getContentPane().add(btnCerrar);
	}
	
	//FUNCIONES IMPLEMENTADAS MANUALMENTE
	public void listarActionPerformed(){
		DtReceta[] listaRecetas = interfazConsultaReceta.listarRecetas();
		for(DtReceta dtr : listaRecetas) {
			modelListRecetas.addElement(dtr.getNombre() + " | Autor: " + dtr.getAutor());
		}
		listRecetas.setModel(modelListRecetas);
	}
	
	public void mostrarInfoActionPerformed() {
		if(this.listRecetas.getSelectedValue() != null) {
			String valorSeleccionado = this.listRecetas.getSelectedValue();
			int i = 0;
			while(valorSeleccionado.charAt(i) != '|') {
				i++;
			}
			
			DtReceta dtr = interfazConsultaReceta.mostrarInfo(this.listRecetas.getSelectedValue().substring(0, i-1));
			this.textAreaIngredientes.setText(dtr.getIngredientes());
			this.textAreaProcedimiento.setText(dtr.getDescripionDelProceso());
		}
	}
	
	public void cancelarActionPerformed() {
		setVisible(false);
		limpiarCampos();
		btnListar.setEnabled(true);
	}
	
	public void limpiarCampos() {
		this.textAreaIngredientes.setText("");
		this.textAreaProcedimiento.setText("");
		this.modelListRecetas.clear();
		this.listRecetas.setModel(this.modelListRecetas);
	}
}
