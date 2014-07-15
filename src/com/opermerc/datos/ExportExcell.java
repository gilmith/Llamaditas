package com.opermerc.datos;

import java.io.FileOutputStream;

import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class  ExportExcell {
	
	public static void exportar(JTable tabla, String ruta){
		 HSSFWorkbook libro = new HSSFWorkbook();
	     HSSFSheet hoja = libro.createSheet();
	     int i = 0;
	     while ((i < tabla.getRowCount()-1) && (tabla.getValueAt(i, 0) != null)){
	    	HSSFRow fila = hoja.createRow(i);          
	         if(i==0){
	        	 for (int j = 0; j <= tabla.getColumnCount()-1; j++) {
	        		 HSSFCell celda = fila.createCell(j);
	                 celda.setCellValue(new HSSFRichTextString(tabla.getValueAt(i, j).toString()));
	            }
	         }else{
	        	 for (int j = 0; j < tabla.getColumnCount(); j++) {
	        		 System.out.println(" valor de la tabla " + tabla.getValueAt(i, j).toString());
	        		 HSSFCell celda = fila.createCell(j);
	                 celda.setCellValue(new HSSFRichTextString(tabla.getValueAt(i, j).toString()));	                     
	             }
	         }
	         i++;
	    }
	    try {
	    	FileOutputStream elFichero = new FileOutputStream(ruta);
            libro.write(elFichero);
            elFichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	 }
}
	
	