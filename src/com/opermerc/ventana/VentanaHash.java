package com.opermerc.ventana;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import com.opermerc.datos.*;

public class VentanaHash extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8172096661752688470L;
	private JTable tabla;
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JButton export;
	
	public VentanaHash(TablaPpal tp){
		setVentana(tp);
		
	}
	
	private void setVentana(TablaPpal tp){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("departamentos registrados en el hash");
		this.setSize(500, 600);
		dtm = new DefaultTableModel();
		Object[] id = {"DEPARTAMENTO", "NUMERO"};
		dtm.setColumnIdentifiers(id);
		dtm.setNumRows(tp.size());
		tabla = new JTable(dtm);
		jsp = new JScrollPane(tabla);	
		setContenido(tp);
		export = new JButton("export");
		export.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ExportExcell.exportar(tabla, "c:\\" + System.getProperty("user.name") + "\\exceldepartamentos.xls");				
			}
			
		});
		this.add(jsp);
		this.add(export, BorderLayout.SOUTH);
	}
	
	private void setContenido(TablaPpal tp){
		Enumeration<String> depto = tp.getDept();
		//Enumeration<String> numero = tp.getNum();
		ArrayList<String> array = tp.getNum();
		int x=0, y=0;
		while(depto.hasMoreElements()){
			String copia1 = depto.nextElement();
			tabla.setValueAt(copia1, x, y);
			String copia2 = array.get(x);
			tabla.setValueAt(copia2, x++, ++y);
			y=0;
		}
		
		
	}
}
