package application;

public class DeterminerGPS {
	private PointGPS pGPS1, pGPS2;
	private PointPixel pPixel1, pPixel2;
	
	private PointPixel pXPixel;
	
	private double xGPSSurPixel, yGPSSurPixel;
	
	public DeterminerGPS() {
		
	}
	
	public DeterminerGPS(PointGPS pGPS1, PointGPS pGPS2, 
			PointPixel pPixel1, PointPixel pPixel2,PointPixel pXPixel) {
		this.pGPS1 = pGPS1;
		this.pGPS2 = pGPS2;
		this.pPixel1 = pPixel1;
		this.pPixel2 = pPixel2;
		this.pXPixel = pXPixel;
	}
	
	public double getX_GPS() throws NullPointerException{
		xGPSSurPixel = (pGPS2.getXGPS() - pGPS1.getXGPS())/(pPixel2.getXPixel() - pPixel1.getXPixel());
		//System.out.println(pGPS2.getXGPS() +"," + pGPS1.getXGPS()+","+ pPixel2.getXPixel() +","+ pPixel1.getXPixel()+","+xGPSSurPixel +"");
		System.out.println("gps/pixelX"+xGPSSurPixel);
		return pGPS1.getXGPS() + (pXPixel.getXPixel() - pPixel1.getXPixel())*xGPSSurPixel;
	}
	
	public double getY_GPS() throws NullPointerException{
		yGPSSurPixel = (pGPS2.getYGPS() - pGPS1.getYGPS())/(pPixel2.getYPixel() - pPixel1.getYPixel());
		System.out.println("gps/pixelY"+yGPSSurPixel);
		return pGPS1.getYGPS() + (pXPixel.getYPixel() - pPixel1.getYPixel())*yGPSSurPixel;
	}
	
	//a ajouter
	
	public PointGPS getpGPS1() {
		return this.pGPS1;
	}
	public PointGPS getpGPS2() {
		return this.pGPS2;
	}
	public PointPixel getpPixel1() {
		return this.pPixel1;
	}
	public PointPixel getpPixel2() {
		return this.pPixel2;
	}
	
	public PointPixel GPSToPixel(final PointGPS ptGPS) throws NullPointerException {
		if(xGPSSurPixel==0 || yGPSSurPixel==0) {
			System.out.println("fraction nulle");
		}
		PointPixel ppixel = new PointPixel((ptGPS.getXGPS()-pGPS1.getXGPS())*1/xGPSSurPixel+pPixel1.getXPixel(), (ptGPS.getYGPS()-pGPS1.getYGPS())*1/yGPSSurPixel+pPixel1.getYPixel());
		return ppixel;			
	}
}
