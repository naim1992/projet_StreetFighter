package services;

public interface HitBox{

	
	//observateur
	public double getPositionX();
	public void setPositionX(double x);
	public int getWidth();
	public double getPositionY();
	public int getHeight();
	public void setPositionY(double y);
	public boolean belongsTo(double a, double b, int w, int h);
	public boolean collidesWith(HitBox b);
	public boolean equalsTo(HitBox b);
	
	//constructor
	public void init(double x, double y,int w, int h);
	
	//operator
	public void moveTo(double x, double y);
	
	//invariant
	/**
	 * \inv : collidesWith(h1, h2) = belongsTo(h1,x,y) \and belongsTo(h2,x,y)
	 * \inv : \forAll x, y \with x \isInt y \isInt { equalsTo(h1, h2 = belongsTo(h1,x,y) == belongsTo(h2,x,y)}  
	 */
	
	//[init]
	/**
	 * \post : positionX(init(x,y)) = x;
	 * \post : positionY(init(x,y)) = y;
	 */
	
	//[moveTo]
	/**
	 * \post : positionX(MoveTo(H, x,y)) = x
	 * \post : positionY(MoveTo(H, x, y)) = y
	 * \post : \forAll u \isInt, v \isInt { BelongsTo(u,v) = BelongsTo(u - getPostionX(), v - getPositionY()) }
	 */
	
	
	
	
}
