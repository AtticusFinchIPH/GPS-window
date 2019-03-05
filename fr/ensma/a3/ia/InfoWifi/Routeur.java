package fr.ensma.a3.ia.InfoWifi;

public class Routeur {

	private String nom;
	private float latitude;
	private float longitude;
	private int distanceMin;
	private int distanceMax;
	
	public Routeur(String nom, float latitude, float longitude, int distanceMin, int distanceMax) {
		this.nom = nom;
		this.latitude = latitude;
		this.longitude = longitude;
		this.distanceMin = distanceMin;
		this.distanceMax = distanceMax;
	}

	public String getNom() {
		return nom;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public int getDistanceMin() {
		return distanceMin;
	}

	public int getDistanceMax() {
		return distanceMax;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(this.nom + "\n");
		sb.append("     latitude : " + latitude + "\n");
		sb.append("     longitude : " + longitude + "\n");
		sb.append("     distanceMin : " + distanceMin + "\n");
		sb.append("     distanceMax : " + distanceMax + "\n");
		return sb.toString();
	}
	

}
