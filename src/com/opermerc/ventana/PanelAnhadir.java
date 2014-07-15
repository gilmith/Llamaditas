package com.opermerc.ventana;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.opermerc.datos.TablaPpal;

public class PanelAnhadir extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3683613264673690305L;
	private JTextField numero, departamento;
	private JButton ok, cls;
	private TablaPpal tablaDept;
	
	public PanelAnhadir(){
		tablaDept = new TablaPpal();
		setComponentes();
	}
	
	private void setComponentes(){
		this.setLayout(new FlowLayout());
		numero = new JTextField();
		numero.setDragEnabled(true);
		numero.setToolTipText("Numero del departamento");
		numero.setColumns(9);
		numero.setText("numero");
		numero.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				numero.setText(" ");
			}
		});

		departamento = new JTextField();
		departamento.setDragEnabled(true);
		departamento.setToolTipText("Nombre del departamento");
		departamento.setColumns(15);
		departamento.setText("deparamento");
		departamento.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				departamento.setText(" ");
			}
		});
		ok = new JButton("ok");
		ok.setToolTipText("Añadir al listado");
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				tablaDept.addHash(departamento.getText(), numero.getText());
			}
			
		});
		cls = new JButton("Borrar");
		cls.setToolTipText("Borrar campos");
		cls.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					numero.setText(" ");
					departamento.setText(" ");
			}
			
		});
		this.add(departamento);
		this.add(numero);
		this.add(ok);
		this.add(cls);
	}
	
	
	
}
