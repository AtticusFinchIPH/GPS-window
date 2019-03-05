package fr.ensma.a3.ia.InfoWifi;

import java.util.ArrayList;
import java.util.List;

public class Reseau {
	
	private long time;
	private List<Routeur> reseau;

	public Reseau(long t) {
		this.time = t;
		this.reseau = new ArrayList<Routeur>();
	}
	
	public void setTime(long t) {
		this.time = t;
	}
	
	public long getTime() {
		return this.time;
	}
	
	public void addRouteur(Routeur r) {
		reseau.add(r);
	}
	
	public Routeur getRouteur(int i) {
		return reseau.get(i);
	}
	
	public int getNbRouteurs() {
		return this.reseau.size();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Reseau /time=" + time +"\n");
		for (Routeur routeur : reseau) {
			sb.append("     " + routeur.toString() + "\n");
		}
		return sb.toString();
	}
	
}
