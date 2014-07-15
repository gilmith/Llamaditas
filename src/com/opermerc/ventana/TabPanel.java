package com.opermerc.ventana;

import javax.swing.JTabbedPane;

public class TabPanel extends JTabbedPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6962699746719468640L;

	public TabPanel(){
		
		this.setTabPlacement(TOP);
		PanelLlamadas pl = new PanelLlamadas();
		PanelAdm pa = new PanelAdm();
		this.addTab("Llamadas", null, pl, "Registro de llamadas");
		this.addTab("Administracion", null, pa, "Administracion de numeros");
		
	}
	

	
	

}
