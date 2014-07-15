package com.opermerc.ventana;

import javax.swing.JTabbedPane;

public class PanelAdm extends JTabbedPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5429048542211878225L;

	public PanelAdm(){
		this.setTabPlacement(TOP);
		PanelAnhadir pa = new PanelAnhadir();
		PanelBorrar pb = new PanelBorrar();
		PanelListar pl = new PanelListar();
		this.addTab("Añadir", null, pa, "Añadir numero-departamento");
		this.addTab("Listar", null, pl, "Listar todos los numero-departamento");
		this.addTab("Borrar",  null, pb, "Borrar numero-departamento");
	}
	


}
