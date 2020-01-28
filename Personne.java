package p1;

public class Personne {
	private String nom;
	private String prenom;
	private String sexe;
	private int age;
	private int taille;
	private double poids;
	private String niveau;
	
	
	public Personne(String nom, String prenom, String sexe, int age, int taille, double poids, String level) {
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.age = age;
		this.taille = taille;
		this.poids = poids;
		if (!level.equals("debutant") && !level.equals("confirme") && !level.equals("expert"))
			this.niveau = "debutant";
		else
			this.niveau = level;
	}
	
	@Override
	public String toString() {
		return "Nom : "+nom+" Prenom : "+prenom+" sexe : "+sexe+" age : "+age+" taille : "+taille+" poids : "+poids+ " niveau : "+niveau+"\n";
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
	
	

}
