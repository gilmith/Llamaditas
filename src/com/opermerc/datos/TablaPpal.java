package com.opermerc.datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Revisar si el value se puede dejar como un StringBuffer y añadir los datos 
 * @author xe02761
 *
 */
public class TablaPpal {

	private Hashtable<String, String> hash;
	private Scanner sc;
	private StringTokenizer st;
	
	public TablaPpal(){
		hash = new Hashtable<String, String>();
		//Lee el txt
		sc = readTxt("c:\\"+ System.getProperty("user.name")+"\\deptos.txt");
		//enu = new Enumeration<String>();
		//Analiza linea a linea
		while(sc.hasNext()){
			st = new StringTokenizer(sc.nextLine(), "=");
			//asigna al hash
			while(st.hasMoreTokens()){
				Object o1 = st.nextToken(); //key
				Object o2 = st.nextToken(); //value
				hash.put((String)o1, (String) o2);
			}
		}
	}
	
	private Scanner readTxt(String ruta){
		
		try {
			Scanner scan = new Scanner(new FileInputStream(ruta));
			return scan;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addHash(String key, String value){
		System.out.println(key.trim());
		if(hash.containsKey(key.trim())){
			System.out.println("exite, extraigo y modifico");
			System.out.println(hash.get(key.trim()));
			if(hash.get(key.trim()).contains(",")){
				String[] cadena = hash.get(key.trim()).split(",");
				StringBuilder sb = new StringBuilder();
				for(String s : cadena){
					sb.append(s+",");
				}
				sb.append(value.trim() + ",");
				hash.remove(key.trim());
				hash.put(key.trim(), sb.toString());
				write(hash);
			}		
		} else {
			value += ",";
			hash.put(key.trim(), value.trim());
			write(hash);
		}
		
		
	}
	
	
	public boolean delDepto(String key){
		System.out.println(hash.get(key.trim()));
		if(hash.containsKey(key.trim())){
			hash.remove(key.trim());
			write(hash);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean delNum(String key, String value){
			if(hash.containsKey(key.trim())){
				//analiza los numeros y comprueba
				if(hash.get(key.trim()).contains(",")){
					String[] cadena = hash.get(key.trim()).split(",");
					StringBuilder sb = new StringBuilder();
					for(String s : cadena){
						if(!s.equals(value.trim())){
							sb.append(s+",");
						}
					}
					hash.remove(key.trim());
					hash.put(key.trim(), sb.toString());
					write(hash);
				}else{
					hash.remove(key.trim());
					write(hash);
				}
				return true;
			} else {
				return false;
			}
				
	}
	
	public int size(){
		return hash.size();
	}
	
	public Enumeration<String> getDept(){
		return hash.keys();
	}
	
	public ArrayList<String> getNum(){
		ArrayList<String> al = new ArrayList(hash.values());
		return al;
		
	}
	
	public String contains(String busqueda){
		if(hash.containsKey(busqueda)){
			return busqueda + " " + hash.get(busqueda);
		} else {
			return "no encontrado";
		}
	}
	
	private void write(Hashtable<String, String> dispersion){
		File file = new File("c:\\"+ System.getProperty("user.name")+"\\deptos.txt");
		try {
			PrintWriter pw = new PrintWriter(file);
			Map<String, String> map = dispersion;
			for(Map.Entry<String, String> mapa : dispersion.entrySet()){
				pw.println(mapa.getKey()+"="+mapa.getValue());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
