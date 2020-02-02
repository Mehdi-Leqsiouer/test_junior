package p1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Client {
	
	// CONNEXION BD
	static String url = "jdbc:mysql://localhost/bdjunior";
	static String utilisateur = "root";
	static String motDePasse = "";
	static Connection conn;
	

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws SQLException, IOException, ParseException{		
		
		// REQUETE
		conn = DriverManager.getConnection( url, utilisateur, motDePasse );
		AgenceSki agence = new AgenceSki();

		JFileChooser dialogue = new JFileChooser();		        
		
        
		String saisie = "";
		String saisie2 = "";
		int nbpersonne = 0;
		int duree = 0;
		String prenom = "";
		String nom = "";
		String sexe = "";
		int age = 0;
		int taille = 0;
		double poids = 0.0;
		Niveau niveau = Niveau.D�butant;
		Elements element = Elements.Ski;
		boolean casque = false;
		Reservation resa = null;
		Scanner sc = new Scanner(System.in);
		do {
			
			//menu
			System.out.println();
			System.out.println("----- Saisir 1 : faire une r�servation manuelle -----");
			System.out.println("----- Saisir 2 : faire une r�servation automatique via .JSON -----");
			System.out.println("----- Saisir 3 : afficher toutes les r�servations -----");
			System.out.println("----- Saisir q pour quitter -----");
			saisie = sc.nextLine();
			switch (saisie) {
			
				//saisie manuelle
				case "1":
					System.out.println("Cr�ation d'une r�servation: ");
					System.out.println("Saisir le nombre de personne de la r�servation : ");
					nbpersonne = sc.nextInt();
					System.out.println("Saisir la dur�e de la r�servation : ");
					duree = sc.nextInt();
					resa = new Reservation(nbpersonne,duree);
					for(int i = 0; i < nbpersonne; i++) {
						System.out.println("----- Saisir la personne n�"+(i+1) +"-----");
						System.out.println("Saisir le pr�nom : ");
						sc.nextLine();
						prenom = sc.nextLine();
						System.out.println("Saisir le nom : ");
						nom = sc.nextLine();
						System.out.println("Saisir le sexe : H ou F : ");
						sexe = sc.nextLine();
						System.out.println("Saisir l'age : ");
						age = sc.nextInt();
						System.out.println("Saisir la taille : ");
						taille = sc.nextInt();
						System.out.println("Saisir le poids : ");
						poids = sc.nextDouble();
						sc.nextLine();
						do {
							System.out.println("Saisir le niveau : d�butant/confirm�/expert : ");
							System.out.println("1 : D�butant");
							System.out.println("2 : Confirm�");
							System.out.println("3 : Expert");
							saisie2 = sc.nextLine();
						}while (!saisie2.equalsIgnoreCase("1") && !saisie2.equalsIgnoreCase("2") && !saisie2.equalsIgnoreCase("3"));
						switch(saisie2) {
							case "1":
								niveau = Niveau.D�butant;
								break;
							case "2":
								niveau = Niveau.Confirm�;
								break;
							case "3":
								niveau = Niveau.Expert;
								break;
							default:
								niveau = Niveau.D�butant;
								break;
						}
						saisie2 = "";
						do {
							System.out.println("Saisir l'�lement � louer : Ski/Monoski/Surf/Luge/ChaussuresSki/ChausuresMonoski/ChaussuresSurf : ");
							System.out.println("1 : Ski");
							System.out.println("2 : Monoski");
							System.out.println("3 : Surf");
							System.out.println("4 : Luge");
							System.out.println("5 : Chaussures de ski");
							System.out.println("6 : Chaussures monoski");
							System.out.println("7 : Chaussures de surf");
							saisie2 = sc.nextLine();
						}while(!saisie2.equalsIgnoreCase("1") && !saisie2.equalsIgnoreCase("2") && !saisie2.equalsIgnoreCase("3") && !saisie2.equalsIgnoreCase("4") && !saisie2.equalsIgnoreCase("5") &&  !saisie2.equalsIgnoreCase("6") && !saisie2.equalsIgnoreCase("7") );
						
						switch(saisie2) {
							case "1":
								element = Elements.Ski;
								break;
							case "2":
								element = Elements.Monoski;
								break;
							case "3":
								element = Elements.Surf;
								break;
							case "4":
								element = Elements.Luge;
								break;
							case "5":
								element = Elements.ChaussureSki;
								break;
							case "6":
								element = Elements.ChaussureMonoSki;
								break;
							case "7":
								element = Elements.ChaussureSurf;
								break;
							default:
								element = Elements.Ski;
								break;
						}
						if (element.equals(Elements.Ski))
							System.out.println("Baton r�serv� d'office");
						saisie2 = "";
						do {
							System.out.println("Saisir si il y a un casque ou non (O/N) : ");
							saisie2 = sc.nextLine();
						}while (!saisie2.equalsIgnoreCase("O") && !saisie2.equalsIgnoreCase("N"));
						if (age < 10 && !casque)
							System.out.println("Moins de 10 ans donc casque r�server d'office");
						casque = (saisie2.equalsIgnoreCase("O")) ? true : false;
						resa.AjoutPersonne(new Personne(prenom,nom,sexe,age,taille,poids,niveau,element,casque,resa.getId_resa()));
					}
					agence.AjoutResa(resa);
					System.out.println("R�servation ajout�e avec succ�s, la voici : ");
					System.out.println(resa);
					break;
					
				//JSON	
				case "2":
					//saisie fichier
					System.out.println("Choisir le fichier .json");
					
					File file1 = null;
					//File workingDirectory = new File(System.getProperty("user.dir"));
					
					do {
						dialogue.showOpenDialog(null);
						//dialogue.setCurrentDirectory(workingDirectory); 
				        file1 = dialogue.getSelectedFile();      
					}while (!file1.toString().contains(".json") && !file1.toString().contains(".JSON"));
					       
					FileReader file = new FileReader(file1.toString());
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(file);
		            JSONObject jsonObject =  (JSONObject) obj;
		            
					nbpersonne = (int) (long) jsonObject.get("nb_personne");
					duree = (int) (long) jsonObject.get("duree");
					
					resa = new Reservation(nbpersonne,duree);
					
					
					JSONArray personnes = (JSONArray) jsonObject.get("personnes");
					Iterator<?> iterateur = personnes.iterator();
					Map<String,Object> map = new HashMap<String, Object>();
					while(iterateur.hasNext()) {
						map = (Map<String, Object>) iterateur.next();
						prenom = (String) map.get("prenom");
						nom =  (String) map.get("nom");
						sexe = (String) map.get("sexe");
						age = (int) (long) map.get("age");
						taille = (int) (long) map.get("taille");
						poids = (double) (long) map.get("poids");
						saisie2 = (String) map.get("niveau");
						switch(saisie2) {
							case "debutant":
								niveau = Niveau.D�butant;
								break;
							case "confirme":
								niveau = Niveau.Confirm�;
								break;
							case "expert":
								niveau = Niveau.Expert;
								break;
							default:
								niveau = Niveau.D�butant;
								break;
						}
						saisie2 = (String) map.get("element");						
						switch(saisie2) {
							case "Ski":
								element = Elements.Ski;
								break;
							case "Monoski":
								element = Elements.Monoski;
								break;
							case "Surf":
								element = Elements.Surf;
								break;
							case "Luge":
								element = Elements.Luge;
								break;
							case "ChaussureSki":
								element = Elements.ChaussureSki;
								break;
							case "ChaussureMonoSki":
								element = Elements.ChaussureMonoSki;
								break;
							case "ChaussureSurf":
								element = Elements.ChaussureSurf;
								break;
							default:
								element = Elements.Ski;
								break;
						}
					
						saisie2 = (String) map.get("casque");
						casque = (saisie2.equalsIgnoreCase("true")) ? true : false;
						resa.AjoutPersonne(new Personne(prenom,nom,sexe,age,taille,poids,niveau,element,casque,resa.getId_resa()));
						//System.out.println(map);
					}
					agence.AjoutResa(resa);
					System.out.println("R�servation ajout�e avec succ�s, la voici : ");
					System.out.println(resa);
					break;
					
				case "3":
					AffichageReservations();
					break;
				
			}
			
			
		}while(!saisie.equalsIgnoreCase("q"));
		
		System.out.println("Fin du programme");
		sc.close();
		conn.close();
	}
	
	public static void AffichageReservations() throws SQLException {
		String sql = "select * from reservations";
		String sql2 = "";
		Statement stmt = conn.createStatement();
		Statement stmt2 = conn.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		ResultSet res2 = null;
		ResultSetMetaData resultMeta = res.getMetaData();
		ResultSetMetaData resultMeta2 = null;
		int x = 1;
		int y = 1;
		int id = 0;
		while (res.next()) {
			System.out.println("R�servation num�ro : "+x);
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		        System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + " ");
		       System.out.println();  
		     for(int i = 1; i <= resultMeta.getColumnCount(); i++) 
		    	 System.out.print("\t" + res.getObject(i).toString() + "\t |");
		     
		     id = (int) res.getObject("idReservation");
		     sql2 = "select * from personne where idReservation = "+id;
		     res2 = stmt2.executeQuery(sql2);
		     resultMeta2 = res2.getMetaData();
		     y = 1;
		     while(res2.next()) {
		    	 System.out.println();
		    	 System.out.println("Personne num�ro : "+y);
			     for(int i = 1; i <= resultMeta2.getColumnCount(); i++)
				        System.out.print("\t" + resultMeta2.getColumnName(i).toUpperCase() + "\t ");
				       System.out.println();
				       
		         for(int i = 1; i <= resultMeta2.getColumnCount(); i++) 
			    	 System.out.print("\t" + res2.getObject(i).toString() + "\t |");
		         y++;
		     }
		     
		     System.out.println(); 
		     System.out.println("\n---------------------------------");
		     x++;
		}
		stmt.close();
		stmt2.close();
		res.close();
		res2.close();
	}

	public static void InsertionBDpersonne(Personne p) throws SQLException {
		String sql = "INSERT INTO personne (nom, prenom, age, niveau, poids, taille, element, casque, idReservation) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,p.getNom());
		pstmt.setString(2,p.getPrenom());
		pstmt.setInt(3, p.getAge());
		pstmt.setString(4, p.getNiveau().toString());
		pstmt.setDouble(5, p.getPoids());
		pstmt.setInt(6, p.getTaille());
		pstmt.setString(7, p.getElement().toString());
		pstmt.setBoolean(8, p.hasCasque());
		pstmt.setInt(9, p.getIdResa());
		pstmt.executeUpdate();
		pstmt.close();
		
		String sql2 = "select max(idPersonne) from personne";
		Statement stmt = conn.createStatement();
		ResultSet resultat = stmt.executeQuery(sql2);
		int id = -1;
		while (resultat.next())
			id = resultat.getInt(1);
		p.setIdPersonne(id);
		stmt.close();
		resultat.close();
	}
	
	public static void InsertionBDresa(Reservation r ) throws SQLException {
		String sql = "INSERT INTO reservations (duree, nombrePersonne) VALUES (?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,r.getDuree());
		pstmt.setInt(2,r.getNb_personnes());
		pstmt.executeUpdate();
		pstmt.close();
		
		String sql2 = "select max(idReservation) from reservations";
		Statement stmt = conn.createStatement();
		ResultSet resultat = stmt.executeQuery(sql2);
		int id = -1;
		while (resultat.next())
			id = resultat.getInt(1);
		r.setId_resa(id);
		stmt.close();
		resultat.close();
	}

}
