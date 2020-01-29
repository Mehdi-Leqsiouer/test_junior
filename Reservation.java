package p1;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Reservation {
	
	private int id_resa;
	private int nb_personnes;
	private int duree;
	private Map<Integer,Personne> liste_personne;
	
	public Reservation(int nb_personnes, int duree) {
		this.nb_personnes = nb_personnes;
		this.duree = duree;
		this.liste_personne = new HashMap<Integer,Personne>();
		
		try {
			Client.InsertionBDresa(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Personne RecherchePersonne(Integer key) {
		if (liste_personne.containsKey(key))
			return liste_personne.get(key);
		return null;
	}
	
	public void AjoutPersonne(Personne p) {
		Integer key = p.getIdPersonne();
		if (!liste_personne.containsKey(key) && liste_personne.size() < nb_personnes)
			liste_personne.put(key, p);
	}
	
	public void SupprPersonne(Personne p) {
		Integer key = p.getIdPersonne();
		if (liste_personne.containsKey(key))
			liste_personne.remove(key,p);
	}
	
	@Override
	public String toString() {
		String msg = "----RESERVATION----\nNombre de personnes : "+nb_personnes+" \n durée : "+duree+" jours\n";
		Set<Integer> key = liste_personne.keySet();
		Iterator<Integer> it = key.iterator();
		while(it.hasNext()) {
			Integer cle = (Integer)it.next();
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

	public Map<Integer,Personne> getListe_personne() {
		return liste_personne;
	}

	public void setListe_personne(Map<Integer,Personne> liste_personne) {
		this.liste_personne = liste_personne;
	}

	public int getId_resa() {
		return id_resa;
	}

	public void setId_resa(int id_resa) {
		this.id_resa = id_resa;
	}
	

}
