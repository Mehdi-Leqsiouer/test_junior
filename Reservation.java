package p1;

public class Reservation {
	
	private int nb_personnes;
	private String sexe;
	private int age;
	private int taille;
	private double poids;
	private String niveau;
	private int duree;
	
	public Reservation(int nb_personnes, String sexe, int age, int taille, double poids, String niveau, int duree) {
		this.nb_personnes = nb_personnes;
		this.sexe = sexe;
		this.age = age;
		this.taille = taille;
		this.poids = poids;
		this.niveau = niveau;
		this.duree = duree;
	}

}
