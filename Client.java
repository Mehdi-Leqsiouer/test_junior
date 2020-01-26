package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Client {

	public static void main(String[] args) throws SQLException {		
		
		// CONNEXION BD
		String url = "jdbc:mysql://localhost/bdjunior";
		String utilisateur = "root";
		String motDePasse = "";
		Connection connexion = null;
		connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		
		
		// REQUETE
		Statement statement = connexion.createStatement();
		ResultSet resultat = statement.executeQuery( "SELECT Niveau nom  FROM reservations;" );
		String niveau ="";
		while ( resultat.next() ) {
		    niveau = resultat.getString(1);

		}
		resultat.close();
		
		System.out.println(niveau.toString());
		statement.close();
		connexion.close();
	}

}
