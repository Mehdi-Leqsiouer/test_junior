package p1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Reservation {
	
	private int id_resa;
	private int nb_personnes;
	private int duree;
	private Map<String,Personne> liste_personne;
	
	public Reservation(int id, int nb_personnes, int duree) {
		this.id_resa = id;
		this.nb_personnes = nb_personnes;
		this.duree = duree;
		this.liste_personne = new HashMap<String,Personne>();
	}
	
	public Reservation(int id,int nb_personnes, int duree,Map<String,Personne> liste) {
		this.id_resa = id;
		this.nb_personnes = nb_personnes;
		this.duree = duree;
		if (liste != null)
			liste_personne = liste;
		else 
			this.liste_personne = new HashMap<String,Personne>();
	}
	
	public Personne RecherchePersonne(String key) {
		if (liste_personne.containsKey(key))
			return liste_personne.get(key);
		return null;
	}
	
	public void AjoutPersonne(Personne p) {
		String key = p.getNom()+p.getPrenom();
		if (!liste_personne.containsKey(key))
			liste_personne.put(key, p);
	}
	
	public void SupprPersonne(Personne p) {
		String key = p.getNom()+p.getPrenom();
		if (liste_personne.containsKey(key))
			liste_personne.remove(key,p);
	}
	
	@Override
	public String toString() {
		String msg = "----RESERVATION----\nNombre de personnes : "+nb_personnes+" \n durée : "+duree+" jours\n";
		Set<String> key = liste_personne.keySet();
		Iterator<String> it = key.iterator();
		while(it.hasNext()) {
			String cle = (String)it.next();
			msg += liste_personne.get(cle).toString();
		}
		return "\n"+msg;
	}

	public int getNb_personnes() {
		return nb_personnes;
	}

	public void setNb_personnes(int nb_personnes) {
		this.nb_personnes = nb_personnes;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Map<String,Personne> getListe_personne() {
		return liste_personne;
	}

	public void setListe_personne(Map<String,Personne> liste_personne) {
		this.liste_personne = liste_personne;
	}
	

}
