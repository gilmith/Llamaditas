package com.opermerc.datos;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Transformacion {
	
	private List<Long> listaNumeros;
	private TablaPpal tabla;
	private ArrayList<String> numerosDepto;
	private int[] contador;
	
	public Transformacion(ArrayList<Long> array){
		listaNumeros = array;
		tabla = new TablaPpal();
		numerosDepto = tabla.getNum();
		Collections.sort(listaNumeros);
		contador = new int[tabla.size()];
	}
	
	/**
	 * VERSION NO TOTALMENTE FUNCIONAL
	 * @return
	 * @throws FileNotFoundException
	 */
	/*public int[] cuentaDeptos() throws FileNotFoundException{
		 int indice;
		 boolean esta;
		//contador de resutaldo tal que soporte algorithmics = 10...
		System.setOut(new PrintStream("C:\\" + System.getProperty("user.name") + "\\Gestion\\Log\\" + "log" +  ".txt" ));
		 for(Long l : listaNumeros){
			 indice = 0;
			 esta = false;
			 while((indice <= numerosDepto.size()-1) && !esta){
				 if(numerosDepto.get(indice).indexOf(String.valueOf(l)) >= 0){
					 System.out.println("el numero " + l + " esta contenido en el " + numerosDepto.get(indice));
					 contador[indice]++;
					 esta = true;
				 } else {
					 System.out.println("el numero " + l + " no esta contenido en " + numerosDepto.get(indice));
					 indice++; 
				 }
			 }
		 }
		 return contador;
	}*/
	
	public int[] cuentaDeptos() throws FileNotFoundException{
		int indice;
		System.setOut(new PrintStream("C:\\" + System.getProperty("user.name") + "\\Gestion\\Log\\" + "log" +  ".txt" ));
		for(Long l : listaNumeros){
			indice = 0;
			//rompo cada elemento del numerosDepto en un String[]
			while(indice <= numerosDepto.size()-1){
				String[] numerosDeptoRoto = numerosDepto.get(indice).split(",");
				int indice2 = 0;
				while(indice2 < numerosDeptoRoto.length){
					if(numerosDeptoRoto[indice2].equals(String.valueOf(l).trim())){
						System.out.println("el numero " + l + " esta contenido en el " + numerosDepto.get(indice));
						contador[indice]++;
					}
					indice2++;
				}
				indice++;
			}
		}
		return contador;
		
	}
	
	

}
