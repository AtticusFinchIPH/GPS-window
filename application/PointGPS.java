package application;

public class PointGPS {
	private double xGPS, yGPS;
	
	public PointGPS() {
	}
	
	public PointGPS(double xGPS, double yGPS) {
		this.xGPS = xGPS;
		this.yGPS = yGPS;
	}
	
	public void setXGPS(double xGPS) {
		this.xGPS = xGPS;
	}
	
	public void setYGPS(double yGPS) {
		this.yGPS = yGPS;
	}
	
	public double getXGPS() {
		return xGPS;
	}
	
	public double getYGPS() {
		return yGPS;
	}
	
}
