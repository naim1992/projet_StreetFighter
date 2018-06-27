package impl;



import java.awt.Rectangle;


import services.HitBox;


public class HitBoxImpl implements HitBox{
	
	private double x;
	private double y;
	private int width;
	private int height;
	
	@Override
	public double getPositionX() {	return this.x;}

	@Override
	public double getPositionY() {	return this.y;}

	@Override
	public boolean belongsTo(double a, double b, int w, int h) {
		
		return new Rectangle((int) x, (int) y,width, height).intersects(a, b, w, h);
		
		
	}

	@Override
	public boolean collidesWith(HitBox b) {return belongsTo((int)x, (int) y, width, height) && belongsTo((int) b.getPositionX(), (int) b.getPositionY(), b.getWidth(), b.getHeight());}

	@Override
	public boolean equalsTo(HitBox b) {	return belongsTo((int) x, (int) y, width, height) == belongsTo((int) b.getPositionX(),(int) b.getPositionY(),b.getWidth(), b.getHeight());}

	@Override
	public void init(double x, double y, int w, int h) {	
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		
	}

	@Override
	public void moveTo(double x, double y) {
		this.x = x;
		this.y = y;
		
	}

	@Override
	public void setPositionX(double x) {
		this.x = x;
		
	}

	@Override
	public void setPositionY(double y) {
		this.y = y;
		
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public void setHeight(int h)
	{
		this.height = h;
	}

}
