package p1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AgenceSki {
	
	List<Reservation> liste;
	static int NbResa;
	
	public AgenceSki() {
		liste = new ArrayList<Reservation>();
		NbResa++;
	}
	
	public AgenceSki(List<Reservation> liste) {
		this.liste = liste;
		NbResa++;
	}
	
	public void AjoutResa(Reservation r) {
		if (!liste.contains(r))
			liste.add(r);
	}
	
	public void SupprResa(Reservation r) {
		if (liste.contains(r))
			liste.remove(r);
	}
	
	@Override
	public String toString() {
		String msg = "---- AGENCE X----";
		Iterator<Reservation> i = liste.iterator();
		while(i.hasNext()) {
			Reservation r = (Reservation)i.next();
			msg += r.toString();
		}
		return msg;
	}
}
