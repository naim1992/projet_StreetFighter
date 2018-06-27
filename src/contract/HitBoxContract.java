package contract;


import decorator.HitBoxDeco;
import errors.InvariantError;
import errors.PostCondError;
import services.Engine;
import services.HitBox;


public class HitBoxContract extends HitBoxDeco{

	private Engine e;
	public HitBoxContract(HitBox hb, Engine e) {
		super(hb);
		this.e = e;
	}
	
	
	public void checkInvariant()
	{
		
		if(!((int) getPositionX() >= 0 && getPositionY() >= 0))
			throw new InvariantError("!((int) getPositionX() > 0 && getPositionY() > 0)");
		
	}
	
	
	public void init(int x, int y, int w, int h)
	{
		// pas de pre conditions
		
		// traitement
		super.init(x, y, w, h);
		
		// check Invariant
		checkInvariant();
		
		// \inv : collidesWith(h1, h2) = belongsTo(h1,x,y) \and belongsTo(h2,x,y)
		
	
		if(this == this.e.getPlayer(1).getHitBox() && !(collidesWith(e.getPlayer(2).getHitBox()) == belongsTo(getPositionX(), getPositionY(), getWidth(), getHeight()) && belongsTo( e.getPlayer(2).getPositionX(),   e.getPlayer(2).getPositionY(), e.getPlayer(2).getWidth(), e.getPlayer(2).getHeight())))
			throw new InvariantError("no collision with p2");
		
		
		if(this == this.e.getPlayer(2).getHitBox() && !(collidesWith(e.getPlayer(1).getHitBox()) == belongsTo( getPositionX(),  getPositionY(), getWidth(), getHeight()) && belongsTo( e.getPlayer(1).getPositionX(),  e.getPlayer(1).getPositionY(), e.getPlayer(1).getWidth(), e.getPlayer(1).getHeight())))
			throw new InvariantError("no collision with p1");
		
		
		//\inv : \forAll x, y \with x \isInt y \isInt { equalsTo(h1, h2) = belongsTo(h1,x,y) == belongsTo(h2,x,y)}  
		 
		if(this == this.e.getPlayer(1).getHitBox() 
				&& !(equalsTo(e.getPlayer(1).getHitBox()) == belongsTo( e.getPlayer(1).getPositionX(),  e.getPlayer(1).getPositionY(), e.getPlayer(1).getWidth(), e.getPlayer(1).getHeight())))
			throw new InvariantError("no equals with p1");
		
		if(this == this.e.getPlayer(2).getHitBox() 
				&& !(equalsTo(e.getPlayer(2).getHitBox()) ==  belongsTo( e.getPlayer(2).getPositionX(),  e.getPlayer(2).getPositionY(), e.getPlayer(2).getWidth(), e.getPlayer(2).getHeight())))
			throw new InvariantError("no equals with p2");
				
	}
	
	
	public void moveTo(double x, double y)
	{
		checkInvariant();
		
		//capture
		
		boolean belongsTo_centre_at_pre = belongsTo( getPositionX(), getPositionY(), getWidth(), getHeight());
		boolean belongsTo_centre_100_at_pre = belongsTo( getPositionX() + 100,  getPositionY() + 100, getWidth(), getHeight());
		int getPositionX_at_pre = (int) getPositionX();
		int getPositionY_at_pre = (int) getPositionY();
		boolean belongsTo_abs_at_pre = belongsTo(300, 0, getWidth(), getHeight());
		
		
		// traitement
		super.moveTo(x,y);
		
		//invariant
		checkInvariant();
		
		// Test du centre 
		if(! belongsTo( getPositionX(),   getPositionY(), getWidth(), getHeight()) == belongsTo_centre_at_pre)
			throw new PostCondError("belongsTo((int) getPositionX(),  (int) getPositionY()");
		
		// Test du centre + 100 
		if(! belongsTo( getPositionX() + 100,  (int) getPositionY() + 100, getWidth(), getHeight()) == belongsTo_centre_100_at_pre)
			throw new PostCondError("! belongsTo(PositionX() + 100, PositionY() + 100) == belongsTo_centre_100_at_pre");
		
		// Test d’un point absolu 
		if(! belongsTo(300 + (x - getPositionX_at_pre), 0 + (y - getPositionY_at_pre), getWidth(), getHeight()) == belongsTo_abs_at_pre)
			throw new PostCondError("! belongsTo(300 + (x - PositionX_at_Pre), 0 + (y - PositionY_at_Pre)) == belongsTo_abs_at_pre");
		
		
		
		 
		
	}

	

}
