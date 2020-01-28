package p1;

public class Personne {
	private String nom;
	private String prenom;
	private String sexe;
	private int age;
	private int taille;
	private double poids;
	private String niveau;
	private int id_resa;
	
	
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
		this.id_resa = -1;
	}
	
	public Personne(String nom, String prenom, String sexe, int age, int taille, double poids, String level, int resa) {
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
		this.id_resa = resa;
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
	
	

}
