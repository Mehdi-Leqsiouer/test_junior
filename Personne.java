package p1;

import java.sql.SQLException;

public class Personne {
	private int idPersonne;
	private String nom;
	private String prenom;
	private String sexe;
	private int age;
	private int taille;
	private double poids;
	private Niveau niveau;
	private int id_resa;
	private Elements element;
	private boolean casque;
	
	public Personne(String nom, String prenom, String sexe, int age, int taille, double poids, Niveau level, Elements el,boolean casque, int resa) {
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.age = age;
		this.taille = taille;
		this.poids = poids;
		this.niveau = level;
		this.element = el;
		this.casque = casque;
		if (this.age < 10)
			this.casque = true;
		
		this.id_resa = resa;
		
		try {
			Client.InsertionBDpersonne(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "Nom : "+nom+" Prenom : "+prenom+" sexe : "+sexe+" age : "+age+" taille : "+taille+" poids : "+poids+ " niveau : "+niveau+"\n";
	}
	
	public int getIdResa() {
		return id_resa;
	}
	
	public void setIdResa(int resa) {
		this.id_resa = resa;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Elements getElement() {
		return element;
	}

	public void setElement(Elements element) {
		this.element = element;
	}

	public boolean hasCasque() {
		return casque;
	}

	public void setCasque(boolean casque) {
		this.casque = casque;
	}

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}
	
	public Niveau getNiveau() {
		return niveau;
	}
	
	public double getPoids() {
		return poids;
	}
	
	public int getTaille() {
		return taille;
	}

	public int getAge() {
		return age;
	}
	
}
