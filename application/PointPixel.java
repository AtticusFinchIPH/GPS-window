package application;

public class PointPixel {
	private double xPixel, yPixel;
	
	public PointPixel() {

	}
	
	public PointPixel(double xPixel, double yPixel) {
		this.xPixel = xPixel;
		this.yPixel = yPixel;
	}
	
	public void setXPixel(double xPixel) {
		this.xPixel = xPixel;
	}
	
	public void setYPixel(double yPixel) {
		this.yPixel = yPixel;
	}
	
	public double getXPixel() {
		return xPixel;
	}
	
	public double getYPixel() {
		return yPixel;
	}
}
