package com.opermerc.ventana;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import com.opermerc.datos.TablaPpal;

public class PanelBorrar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4925911760143014902L;
	private JTextField numero, departamento;
	private JButton ok, cls, borrarTodo;
	private TablaPpal tabla;
	
	public PanelBorrar(){
		this.setLayout(new FlowLayout());
		tabla = new TablaPpal();
		setComponentes();
	}
	
	private void setComponentes(){
		
		numero = new JTextField();
		numero.setToolTipText("Numero del departamento a borrar");
		numero.setColumns(9);
		numero.setText("numero");
		numero.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				numero.setText(" ");
			}
		});

		departamento = new JTextField();
		departamento.setToolTipText("Nombre del departamento a borrar");
		departamento.setColumns(15);
		departamento.setText("departamento");
		departamento.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				departamento.setText(" ");
			}
		});
		ok = new JButton("Borrar numero");
		ok.setToolTipText("Borrar un solo numero de un departamento");
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane jop = new JOptionPane();
				if(!tabla.delNum(departamento.getText(), numero.getText())){
					jop.showMessageDialog(getParent(), "departamento no encontrado", "ERROR",  JOptionPane.ERROR_MESSAGE);
				} else{
					jop.showMessageDialog(getParent(), "numero borrado, solo contenia un numero", "OK", JOptionPane.OK_OPTION);
				}	
			}
			
		});
		cls = new JButton("Borrar campos");
		cls.setToolTipText("Borrar campos");
		cls.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				numero.setText(" ");
				departamento.setText(" ");
			}
			
		});
		borrarTodo = new JButton("Borrar todo");
		borrarTodo.setToolTipText("Borrar todo un departamento, solo hace falta el nombre");
		borrarTodo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane jop = new JOptionPane();
				if(!tabla.delDepto(departamento.getText())){
					jop.showMessageDialog(getParent(), "departamento no encontrado", "ERROR",  JOptionPane.ERROR_MESSAGE);
				} else{
					jop.showMessageDialog(getParent(), "departamento borrado", "OK", JOptionPane.OK_OPTION);
				}
			}
			
		});
		
		this.add(departamento);
		this.add(numero);
		this.add(ok);
		this.add(cls);
		this.add(borrarTodo);
		
		
	}
	

}
