package com.opermerc.ventana;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.opermerc.datos.TablaPpal;

public class PanelListar extends JPanel {
	
	/**
	 * 
	 * La parte de la TablaPpal la tendre que borrar al dejar la version definitiva, esto es
	 * una prueba de concepto
	 * 
	 * 
	 */
	private static final long serialVersionUID = -850548297871805362L;
	private JTextField departamento;
	private JButton ok, todo;
	private TablaPpal tablaDept;
	
	
	public PanelListar(){
		this.setLayout(new FlowLayout());
		tablaDept = new TablaPpal();
		setComponentes();
	}
	
	private void setComponentes(){
		departamento = new JTextField();
		departamento.setColumns(15);
		departamento.setToolTipText("Nombre del departamento listara todos los numeros registrados");
		departamento.setText("nombre departamento");
		departamento.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				departamento.setText(" ");
			}
		});
		ok = new JButton("ok");
		ok.setToolTipText("Listar los numeros registrados con un departamento");
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getParent(), tablaDept.contains(departamento.getText().trim()), "numeros del departamento", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		todo = new JButton("todo");
		todo.setToolTipText("Listar todos los registros");
		todo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// Genera una nueva ventana con la tabla de 2. La meto en otra clase que llame directamente al hash.
				new VentanaHash(tablaDept).setVisible(true);
				
			}
		});
		this.add(departamento);
		this.add(ok);
		this.add(todo);
	}

}
