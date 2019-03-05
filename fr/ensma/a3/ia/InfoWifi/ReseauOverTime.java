package fr.ensma.a3.ia.InfoWifi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReseauOverTime {

	private List<Reseau> reseauOverTime = new ArrayList<Reseau>();
	private String chemin;

	public ReseauOverTime(String ch) {
		this.chemin = ch;
	}
	
	public Reseau getReseau(int i) {
		return reseauOverTime.get(i);
	}
	
	public int getNbReseau() {
		return reseauOverTime.size();
	}

	public void addReseau(long time, String[] infos) {
		Reseau r = new Reseau(time);
		for (int i = 0; i < infos.length/5; i++) {
			int j = 5*i;
			Routeur ap = new Routeur(infos[j],Float.parseFloat(infos[j+1]),Float.parseFloat(infos[j+2]),Integer.parseInt(infos[j+3]),Integer.parseInt(infos[j+4]));
			r.addRouteur(ap);
		}
		this.reseauOverTime.add(r);
	}

	public void update() {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(chemin));
			while ((line = br.readLine()) != null) {
				String[] info = line.split(cvsSplitBy);
				this.addReseau(Long.parseLong(info[0]), Arrays.copyOfRange(info, 1, info.length));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String toString() {
		return reseauOverTime.get(reseauOverTime.size()-1).toString();
	}
	
}
