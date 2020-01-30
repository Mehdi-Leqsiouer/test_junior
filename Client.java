package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Client {
	
	// CONNEXION BD
	static String url = "jdbc:mysql://localhost/bdjunior";
	static String utilisateur = "root";
	static String motDePasse = "";
	static Connection conn;
	

	public static void main(String[] args) throws SQLException {		
		
		// REQUETE
		conn = DriverManager.getConnection( url, utilisateur, motDePasse );
		AgenceSki agence = new AgenceSki();
		/*Reservation resa = new Reservation(5,3);
		Personne p1 = new Personne("Boukhatem", "Tarik", "H", 19, 190, 70, Niveau.Confirmé, Elements.Luge,false,1);//getResa
		resa.AjoutPersonne(p1);
		agence.AjoutResa(resa);
		System.out.println(agence.toString());*/
		
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
		Niveau niveau = Niveau.Débutant;
		Elements element = Elements.Ski;
		boolean casque = false;
		Reservation resa = null;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("----- Saisir 1 : faire une réservation ");
			System.out.println("----- Saisir q pour quitter -----");
			saisie = sc.nextLine();
			switch (saisie) {
				case "1":
					System.out.println("Création d'une réservation: ");
					System.out.println("Saisir le nombre de personne de la réservation : ");
					nbpersonne = sc.nextInt();
					System.out.println("Saisir la durée de la réservation : ");
					duree = sc.nextInt();
					resa = new Reservation(nbpersonne,duree);
					for(int i = 0; i < nbpersonne; i++) {
						System.out.println("----- Saisir la personne n°"+(i+1) +"-----");
						System.out.println("Saisir le prénom : ");
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
							System.out.println("Saisir le niveau : débutant/confirmé/expert : ");
							System.out.println("1 : Débutant");
							System.out.println("2 : Confirmé");
							System.out.println("3 : Expert");
							saisie2 = sc.nextLine();
						}while (!saisie2.equalsIgnoreCase("1") && !saisie2.equalsIgnoreCase("2") && !saisie2.equalsIgnoreCase("3"));
						switch(saisie2) {
							case "1":
								niveau = Niveau.Débutant;
								break;
							case "2":
								niveau = Niveau.Confirmé;
								break;
							case "3":
								niveau = Niveau.Expert;
								break;
						}
						saisie2 = "";
						do {
							System.out.println("Saisir l'élement à louer : Ski/Monoski/Surf/Luge/ChaussuresSki/ChausuresMonoski/ChaussuresSurf : ");
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
						}
						if (element.equals(Elements.Ski))
							System.out.println("Baton réservé d'office");
						saisie2 = "";
						do {
							System.out.println("Saisir si il y a un casque ou non (O/N) : ");
							saisie2 = sc.nextLine();
						}while (!saisie2.equalsIgnoreCase("O") && !saisie2.equalsIgnoreCase("N"));
						if (age < 10 && !casque)
							System.out.println("Moins de 10 ans donc casque réserver d'office");
						casque = (saisie2.equalsIgnoreCase("O")) ? true : false;
						resa.AjoutPersonne(new Personne(prenom,nom,sexe,age,taille,poids,niveau,element,casque,resa.getId_resa()));
					}
					break;
				
			}
			agence.AjoutResa(resa);
			
		}while(!saisie.equalsIgnoreCase("q"));
		
		System.out.println("Fin du programme");
		sc.close();
		conn.close();
	}
	
	public static String SaisitUtilisateur(String msg, Scanner sc) {
		System.out.println(msg);
		String saisie = sc.nextLine();
		return saisie;
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
