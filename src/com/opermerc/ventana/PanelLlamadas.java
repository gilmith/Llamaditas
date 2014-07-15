package com.opermerc.ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import com.opermerc.datos.ExportExcell;
import com.opermerc.datos.Transformacion;

public class PanelLlamadas extends JPanel implements ActionListener{
	
	/**
	 * 
	 * Para la siguiente vuelta tengo que hacer que añada solo filas si supera un limite
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 270517308792891230L;
	private JTable tabla;
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JButton mas, borrar, total, exportar;
	private Clipboard clip;
	private int numFilasOcupadas=0;
	
	public PanelLlamadas(){
		this.setLayout(new BorderLayout());
		setTable();
		setBotones();
	}
	
	private void setTable(){
		DefaultTableModel dtm = new DefaultTableModel();
		Object[] id = {"FECHA","DPTO", "NUMERO", "DURACION"};
		dtm.setColumnIdentifiers(id);
		dtm.setNumRows(5000);
		tabla = new JTable(dtm);
		tabla.setDragEnabled(true);
		KeyStroke paste = KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK,false);
		tabla.registerKeyboardAction(this,"Paste",paste,JComponent.WHEN_FOCUSED);
		clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		jsp = new JScrollPane(tabla);
		this.add(jsp, BorderLayout.NORTH);
	}
	
	private void setBotones(){
		mas = new JButton(" + ");
		mas.setToolTipText("Añadir 5000 registros mas");
		mas.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
			}
		});
		borrar = new JButton("borrar");
		borrar.setToolTipText("Borrar toda la tabla");
		borrar.setActionCommand("borrar");
		borrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i< tabla.getRowCount(); i++){
					for(int j = 0; j < tabla.getColumnCount(); j++){
						String[] vacio = {" ", " ", " ", " "};
						tabla.setValueAt(vacio[j], i, j);
					}
				}
			}
		});
		total = new JButton("total");
		total.setToolTipText("Obtener el total de llamadas");
		total.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				ArrayList<Long> arrayDepto = new ArrayList<Long>();
				ArrayList<Long> arrayNumero = new ArrayList<Long>();
				for(int row = 0; row < tabla.getRowCount(); row++){
					for(int column = 1; column < 3; column++){
						if(tabla.getValueAt(row, column) != null){
							if ((column % 2) != 0){
								String cadena = (String) tabla.getValueAt(row, column);
								if(cadena.startsWith("+")){
									arrayDepto.add(Long.parseLong(cadena.subSequence(1, cadena.length()).toString()));
								} else if(cadena.startsWith("a")){
									cadena = "00000000";
									arrayDepto.add(Long.parseLong(cadena));
								} else {
									arrayDepto.add(Long.parseLong(cadena));
								}	
							} else{
								arrayNumero.add(Long.parseLong((String) tabla.getValueAt(row, column))); 
							}
						}
					}
				}
				Transformacion tr = new Transformacion(arrayDepto);			
				//crea una nueva ventana con el contador + los departamentos
				try {
					new VentanaResultado(tr.cuentaDeptos()).setVisible(true);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		exportar = new JButton("exportar");
		exportar.setToolTipText("exportar a excel");
		exportar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ExportExcell.exportar(tabla, "c:\\" + System.getProperty("user.name")+ "\\llamadas.xls");
			}
			
		});
		JPanel panel_inferior = new JPanel();
		panel_inferior.setLayout(new FlowLayout());
		panel_inferior.add(exportar);
		panel_inferior.add(mas);
		panel_inferior.add(borrar);
		panel_inferior.add(total);
		this.add(panel_inferior, BorderLayout.SOUTH);
	}
	
	private void pasteAction(){
		
		try {
			StringTokenizer st, stTmp;
			String datosExcel = (String)clip.getData(DataFlavor.stringFlavor);
			//token por linea
			st = new StringTokenizer(datosExcel, "\n");
			int x = tabla.getSelectedColumn();
			int y = tabla.getSelectedRow();
			int numeroFilas = st.countTokens(); 
			System.out.println("posicion de la tabla " + x  + " " + y + "datos copiados" + datosExcel);
			System.out.println("numero de filas a copiar " + numeroFilas);
			//token por tabulacion ajustando los espacios con trim, corresponderia a las columnas de la tabla excel con la que lo quiero pegar
			st = new StringTokenizer(st.nextToken().trim(), "\t");
			numeroFilas = st.countTokens();
			System.out.println("el numero de token con las tabulaciones es de " + numeroFilas);
			// ahora por numer de intros
			st = new StringTokenizer(datosExcel, "\n");
			int indiceRow=0, indiceCol;
			//setados estos valores me meto un while de copia
			//primer bucle por intros para empezar a copiar linea a linea
			while(st.hasMoreTokens()){
				//separo por tabulacion
				stTmp = new StringTokenizer(st.nextToken(), "\t");
				indiceCol=0;
				while(stTmp.hasMoreTokens()){
					tabla.setValueAt(stTmp.nextToken(), indiceRow, indiceCol);
					indiceCol++;
				}
				indiceRow++;
				numFilasOcupadas++;
			}
			
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Aqui va el pasteAction
		pasteAction();
	}
	

}



