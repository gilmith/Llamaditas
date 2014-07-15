package com.opermerc.ventana;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;



public class VentanaMain extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6010845401183130471L;
	public VentanaMain() {
	
		this.setTitle("Llamadas Operacion CIB");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 600);
		TabPanel tp = new TabPanel();
		this.getContentPane().add(tp, BorderLayout.CENTER);
		
	}
		public static void main(String[] args) {

		
			EventQueue.invokeLater(new Runnable(){

				@Override
				public void run() {
					new VentanaMain();
				}
			});
					
		}
		
		
}


