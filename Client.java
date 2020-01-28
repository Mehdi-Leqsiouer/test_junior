package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Client {
	
	// CONNEXION BD
	static String url = "jdbc:mysql://localhost/bdjunior";
	static String utilisateur = "root";
	static String motDePasse = "";
	static Connection conn;
	

	public static void main(String[] args) throws SQLException {		
		
		// REQUETE
		conn = DriverManager.getConnection( url, utilisateur, motDePasse );
		
		Reservation resa = new Reservation(5,3);
		Personne p1 = new Personne("Boukhatem", "Tarik", "H", 19, 190, 70, Niveau.Confirmé, Elements.Luge,true,false,1);//getResa
		resa.AjoutPersonne(p1);
		
		
		
		conn.close();
	}
	
	public static void InsertionBDpersonne(Personne p) throws SQLException {
		String sql = "INSERT INTO personne (nom, prenom, age, niveau, poids, taille, element, chaussure, casque, idReservation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,p.getNom());
		pstmt.setString(2,p.getPrenom());
		pstmt.setInt(3, p.getAge());
		pstmt.setString(4, p.getNiveau().toString());
		pstmt.setDouble(5, p.getPoids());
		pstmt.setInt(6, p.getTaille());
		pstmt.setString(7, p.getElement().toString());
		pstmt.setBoolean(8, p.hasChaussures());
		pstmt.setBoolean(9, p.hasCasque());
		pstmt.setInt(10, p.getIdResa());
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
