package com.opermerc.ventana;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;




import com.opermerc.datos.*;

public class VentanaResultado extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5722190199023064116L;
	private JTable tabla;
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JButton export;
	
	public VentanaResultado(int[] resultado, TablaPpal tp){
		setVentana(resultado, tp);
	}

	public VentanaResultado(int[] cuentaDeptos) {
		TablaPpal tp = new TablaPpal();
		setVentana(cuentaDeptos, tp);
		export = new JButton("exportar");
		export.setToolTipText("exportar la tabla a excel");
		export.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//metodo private para exportar a excel 
				ExportExcell.exportar(tabla, "c:\\" + System.getProperty("user.name")+ "\\prueba.xls");
			}
			
		});
		this.add(export, BorderLayout.SOUTH);
	}

	private void setVentana(int[] resultado, TablaPpal tp) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500, 600);
		dtm = new DefaultTableModel();
		Object[] id = {"DEPARTAMENTO","LLAMADAS"};
		dtm.setColumnIdentifiers(id);
		dtm.setNumRows(tp.size());
		tabla = new JTable(dtm);
		jsp = new JScrollPane(tabla);
		setContenido(resultado, tp);
		this.add(jsp);
	}

	private void setContenido(int[] resultado, TablaPpal tp) {
		Enumeration<String> depto = tp.getDept();
		int x=0, y=0, indice=0;
		while(depto.hasMoreElements() && (indice < resultado.length)){
			tabla.setValueAt(depto.nextElement(), x, y);
			tabla.setValueAt(resultado[indice], x++, ++y);
			indice++;
			y=0;
		}
	}
}
