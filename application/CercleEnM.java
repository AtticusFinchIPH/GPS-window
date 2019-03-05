package application;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


//classe qui cr�e un cercle � partir de distance en m et de coordonn�es GPS
public class CercleEnM extends Circle{
	
	public CercleEnM(PointGPS centre, float rayonenm, DeterminerGPS lechelle, Paint fill) {
		super(lechelle.GPSToPixel(centre).getXPixel(),lechelle.GPSToPixel(centre).getYPixel(),rayonenm*fraction(centre, rayonenm,lechelle),fill);
		System.out.println(centre.getXGPS());
		System.out.println(centre.getYGPS());
		System.out.println("pixel du centre" +lechelle.GPSToPixel(centre).getXPixel());
		System.out.println("pixel du centre"+ lechelle.GPSToPixel(centre).getYPixel());		
	}
	


	private static double fraction(PointGPS centre, float rayonenm, DeterminerGPS lechelle) {
		//int R = 6200000;//Rayon de la terre en m�tre
		//double lat_a = convertRad(lechelle.getpGPS1().getXGPS());
		//double lon_a = convertRad(lechelle.getpGPS1().getYGPS());
		//double lat_b = convertRad(lechelle.getpGPS2().getXGPS());
		//double lon_b = convertRad(lechelle.getpGPS2().getYGPS());
	    double latitude1 = lechelle.getpGPS1().getYGPS() * Math.PI / 180;
	    double latitude2 = lechelle.getpGPS2().getYGPS() * Math.PI / 180;
	    double longitude1 = lechelle.getpGPS1().getXGPS() * Math.PI / 180;
	    double longitude2 = lechelle.getpGPS2().getXGPS() * Math.PI / 180; 
	    double R = 6378137d;

	 

	    
	    double echellem = R * Math.acos(Math.cos(latitude1) * Math.cos(latitude2) *Math.cos(longitude1) *Math.cos(longitude2) +Math.cos(latitude1) * Math.sin(longitude1)*Math.sin(longitude2)* Math.cos(latitude2) + Math.sin(latitude1) * Math.sin(latitude2));
	    System.out.println("echellem :"+echellem);
	    //double echellem=R * (Math.PI/2 - Math.asin( Math.sin(lat_b) * Math.sin(lat_a) + Math.cos(lon_b - lon_a) * Math.cos(lat_b) * Math.cos(lat_a)));
		double echellepx=Math.sqrt(Math.pow(lechelle.getpPixel2().getXPixel()-lechelle.getpPixel1().getXPixel(),2)+Math.pow(lechelle.getpPixel2().getYPixel()-lechelle.getpPixel1().getYPixel(),2));
		System.out.println("echellepx :"+echellepx);
		return echellepx/echellem;


		
	}
	
	//convertion degr�s en radins
	private static double convertRad(double input){
        return (Math.PI * input)/180;
}
 
}
