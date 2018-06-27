package contract;

import services.Engine;
import services.Player;
import decorator.PlayerDec;
import errors.InvariantError;
import errors.PostCondError;
import errors.PreCondError;

public class PLayerContract extends PlayerDec{

	public PLayerContract(Player delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

	
	public void checkInvariant()
	{
		
		// \inv : getPositionX() > 0 \and getPositionX() < getEngine().getWidth()
					if(!(getPositionX() >= 0) )
					{
						System.out.println(getPositionX());
						throw new InvariantError("position x < 0");
					}
				
					if(!(getPositionX() <= getEngine().getWidth()))
					{
						throw new InvariantError("position x >= engine.getWidth");
					}
				
				// \inv : getPositionY() > 0 \and getPositionY() < getEngine().getHeight()
					
					if(!(getPositionY() >= 0) )
					{
						throw new InvariantError("position y < 0");
					}
				
					if(!(getPositionY() <= getEngine().getHeight()))
					{
						throw new InvariantError("position y > engine.getHeight");
					}
				
				// \inv : isDead() = !(getLife() > 0)
					if(!(isDead() == !(getLife() > 0)))
						throw new InvariantError("!(isDead() == !(getLife() > 0)");
							
	}
	
	
	public void init(int id, int l, int s, boolean f, Engine e) {
		// PRE : l > 0 \and s > 0
		
		if(! (l > 0))
			throw new PreCondError("! (l > 0)");
		if(! (s > 0))
			throw new PreCondError("! (s > 0)");
		
		
		// Traitement
		super.init(id, l, s, f, e);
		
		// post-init invariant
		checkInvariant();
		
		
		// \post : getId() = id
		if(!(getId() == id))
			throw new PostCondError("!(getId() == id");
		// \post : getLife() = l
		if( !(getLife() == l))
			throw new PostCondError("! (getLife() == l)");
		// \post : getSpeed() = s
		if( !(getSpeed() == s))
			throw new PostCondError("! (getSpeed() == s)");
		// \post : isFaceRight() = f
		if ( !(isFaceRight() == f))
			throw new PostCondError("!(isFaceRight() == f)");
		// \post : getEngine() = e
		if(!(getEngine() == e))
			throw new PostCondError("! (getEngine() == e)");
					
		// \post : \exist h \is HitBox {getCharBox() = h}
		
		if(!(getHitBox() != null))
			throw new PostCondError("!(getHitBox() != null)");
		

	}
	
	
	public void moveRight()
	{
		// pre: pas de precond
		
		// pre-invariant
		checkInvariant();
		
		// Captures
			int x_atPre = (int) getPositionX();
			int y_atPre = (int) getPositionY();
			int life_atPre = getLife();
			boolean faceRgh_atPre = isFaceRight();
			
			
		
		// TRAITEMENT
			super.moveRight();
		
		// post-invariant
		checkInvariant();
		
		// \post: \exist i in [1,2] { getEngine().getPlayer(i) != this \and collisionWith(getHitBox(), getEngine().getPlayer(i).getHitBox()) } \==> getPositionX() = getPostionX()@pre
		// \post: getPositionX() >= getSpeed() \and \ForAll i in [1,2] { getEngine().getPlayer(i) != this \==> !collisionWith(getHitBox(), getEngine().getPlayer(i).getHitBox())} \==> getPositionX() = getPostionX()@pre + getSpeed()
		// \post: getPositionX() < getSpeed() \and \ForAll i in [1,2] { getEngine().getPlayer(i) != this \==> !collisionWith(getHitBox(), getEngine().getPlayer(i).getHitBox())} \==> getPositionX() = 0
	
		if(!(getPositionX()== x_atPre - 5 || getPositionX() == x_atPre + getSpeed() || getPositionX() == x_atPre + getSpeed() || getPositionX() == getEngine().getWidth() / getEngine().getScale() - getWidth() / 2))
		throw new PostCondError("!(getPositionX()== x_atPre  || getPositionX() == 0 || getPositionX() == x_atPre + getSpeed())");
		
		// \post: isFacingRight() = isFaceRight()@pre
		if(!(isFaceRight() == faceRgh_atPre))
			throw new PostCondError("!(isFaceRight() == faceRgh_atPre)");
		
		// \post: getLife() = getLife()@pre
		if(!(getLife() == life_atPre))
			throw new PostCondError("!(getLife() == life_atPre)");
		
		// \post: getPositionY()  = getPositionY()@pre
		
		if(!(getPositionY() == y_atPre ) )
			throw new PostCondError("!(getPositionY() == y_atPre)");
		
	}
	
	
	public void moveLeft()
	{
		// pre: pas de precond
		
				// pre-invariant
				checkInvariant();
				
				// Captures
					int x_atPre = (int) getPositionX();
					int y_atPre = (int) getPositionY();
					int life_atPre = getLife();
					boolean faceRgh_atPre = isFaceRight();
					
					
				
				// TRAITEMENT
					super.moveLeft();
				
				// post-invariant
				checkInvariant();
				
				// \post: \exist i in [1,2] { getEngine().getPlayer(i) != this \and collisionWith(getHitBox(), getEngine().getPlayer(i).getHitBox()) } \==> getPositionX() = getPostionX()@pre
				// \post: getPositionX() <= getSpeed() \and \ForAll i in [1,2] { getEngine().getPlayer(i) != this \==> !collisionWith(getHitBox(), getEngine().getPlayer(i).getHitBox())} \==> getPositionX() = getPostionX()@pre - getSpeed()
				// \post: getPositionX() > getSpeed() \and \ForAll i in [1,2] { getEngine().getPlayer(i) != this \==> !collisionWith(getHitBox(), getEngine().getPlayer(i).getHitBox())} \==> getPositionX() = 0
			
				if(!(getPositionX()== x_atPre + 5 || getPositionX() == x_atPre + getSpeed() || getPositionX() == x_atPre - getSpeed() || getPositionX() ==  getWidth() / 2))
				throw new PostCondError("!(getPositionX()== x_atPre || getPositionX() == x_atPre + getSpeed() || getPositionX() == x_atPre - getSpeed())");
				
				// \post: isFacingRight() = isFaceRight()@pre
				if(!(isFaceRight() == faceRgh_atPre))
					throw new PostCondError("!(isFaceRight() == !faceRgh_atPre)");
				
				// \post: getLife() = getLife()@pre
				if(!(getLife() == life_atPre))
					throw new PostCondError("!(getLife() == life_atPre)");
				
				// \post: getPositionY()  = getPositionY()@pre
				if(!(getPositionY() == y_atPre))
					throw new PostCondError("!(getPositionY() == y_atPre)");
	}
	
	public void switchSide()
	{
		
		// pre: pas de precond
		
		// pre-invariant
		checkInvariant();
		
		// Captures
			int x_atPre = (int) getPositionX();
			boolean faceRgh_atPre = isFaceRight();
			
			
		
		// TRAITEMENT
			super.switchSide();
		
		// post-invariant
		checkInvariant();
		
		
			// \post: isFaceRight() != isFaceRight()@pre 
				if(!(isFaceRight() == !faceRgh_atPre))
					throw new PostCondError("!(isFaceRight() == !faceRgh_atPre)");
				 // \post : getPositionX() = getPositionX()@pre
				if(!(getPositionX() == x_atPre))
					throw new PostCondError("!(getPositionX() == x_atPre)");
		
	}
	
	
	public void step(int commande)
	{
		// \pre: !isDead()
			if(isDead())
				throw new PreCondError("is Dead");
			
				// pre-invariant
				checkInvariant();
				
				// pas de Captures
									
				
				// TRAITEMENT
					super.step(commande);
				
				// post-invariant
				checkInvariant();
				
				
			// \post : Command = LEFT \==> moveLeft()
			// \post : Command = RIGHT \==> moveRight()	
			// \post : Command = \nothing \doNothing
				
			// la vérification se fait deja dans moveLeft et moveRight
		
	}


	
	
	
	
	
}
