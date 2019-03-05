

import application.PointGPS;
import fr.ensma.a3.ia.InfoWifi.ReseauOverTime;
import fr.ensma.a3.ia.InfoWifi.Routeur;



public class App 
{
	public static void main( String[] args )
	{
		String cheminCSV = "/home/tranv/Documents/BE/simulation2.csv";
		ReseauOverTime r = new ReseauOverTime(cheminCSV);
		r.update();
		
        
		System.out.println(r.toString() + r.getNbReseau());
	}
}
