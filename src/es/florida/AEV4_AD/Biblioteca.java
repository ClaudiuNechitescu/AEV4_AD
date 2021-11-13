package es.florida.AEV4_AD;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Biblioteca {

	@SuppressWarnings({ "null", "unlikely-arg-type" })
	/**
	 * Mètode main que llig l'arxiu, l'inserta en la BBDD i mostra per defecte el llibres, l'autor dels quals, va nàixer abans de 1950 i les editorial que tenen un llibre
	 * */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader(new File("AE04_T1_4_JDBC_Dades.csv"));
		BufferedReader br = new BufferedReader(fr);
		String linea=br.readLine();
		Llibre llibre;
		List<Llibre> llibres = new ArrayList<Llibre>();
		List<String> attr=null;
		//Llegim les línies del fitxer i afegim els valors a una llista
		while((linea = br.readLine()) != null) {
			
			attr = Arrays.asList(linea.split(";"));
			String titol = attr.get(0).equals("")?"":attr.get(0);
			String autor = attr.get(1).equals("")?"":attr.get(1);
			int anyN = attr.get(2).equals("")?-1: Integer.parseInt(attr.get(2));
			int anyP = attr.get(3).equals("")?-1: Integer.parseInt(attr.get(3));
			String editorial = attr.get(4).equals("")?"":attr.get(4);
			int nomPagines = attr.get(5).equals("")?-1: Integer.parseInt(attr.get(5));
			llibre= new Llibre(titol,autor,anyN,anyP,editorial,nomPagines);
			llibres.add(llibre);
			if(anyN<1950) {
				llibre.mostrarInfo();
			}
			//Provem a connectar amb la BBDD
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aevjdbc", "root", "");
				System.out.println("Connexió correcta");
			} catch (SQLException e) {
				System.out.println("Connexió fallida");
			}
			//Comprovem que la inserció és correcta
			if (insert(con,titol,autor,anyN,anyP,editorial,nomPagines) == 1) {
			System.out.println("Inserció correcta");
		} else {
			System.out.println("Inserció fallida");
		}

		}
		//Afegim el nom de l'editorial i el nombre de llibres publicats en el S.XXI en un Map/Diccionari/Taula de Hash i els mostrem
		Map<String,Integer> editorials = new HashMap<String,Integer>();
		for(Llibre li : llibres) {
			if(li.getAnyPublicacio()>=2000) {
				
			editorials.put(li.getEditorial(),editorials.get(li.getEditorial())==null?1:editorials.get(li.getEditorial())+1);
			}
		}

		System.out.println("-------------------------------------------------------------------------------");
		for (var entry : editorials.entrySet()) {
		    System.out.println(entry.getKey() + ": " + entry.getValue() + " llibres en el S.XXI");
		}
		
	}
	/**
	 * Mètode que inserta un llibre en la base de dades
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * @param con Connexió a la BBDD
	 * @param tit El títol
	 * @param aut El autor
	 * @param anyN Any de naixement de l'autor
	 * @param anyP Any de publicació
	 * @param edi Editorial
	 * @param nomPag Nombre de pàgines
	 * @return 1 si la inserció s'ha fet correctament, o 2 si no s'ha realitzat
	 * */
	public static int insert(Connection con,String tit,String aut, int anyN,int anyP,String edi,int nomPag) throws Exception {
		@SuppressWarnings("resource")

		PreparedStatement psInsertar = con.prepareStatement("INSERT INTO llibres VALUES (DEFAULT,?,?,?,?,?,?)");
		psInsertar.setString(1, tit);
		psInsertar.setString(2, aut);
		psInsertar.setInt(3, anyN);
		psInsertar.setInt(4, anyP);
		psInsertar.setString(5, edi);
		psInsertar.setInt(6, nomPag);

		int resultadoInsertar = psInsertar.executeUpdate();
		return resultadoInsertar;
	}

}