package contract;



import services.Engine;
import services.Player;
import decorator.EngineDeco;
import errors.InvariantError;
import errors.PostCondError;
import errors.PreCondError;

public class EngineContract extends EngineDeco{

	public EngineContract(Engine e) {
		super(e);
		// TODO Auto-generated constructor stub
	}
	
	public void checkInvariant()
	{
		
		//\inv : \exist i in [1,2] {isDead(player(i)}
		if((getPlayer(1).isDead() || getPlayer(2).isDead()) && !isGameOver())
			throw new InvariantError("game Over");
		
	}
	
	public void init(int h, int w, int s, Player p1, Player p2)
	{
		// \pre : h > 0 \and s>0 \and w > s \and p1 != p2
		if(!(h > 0 && s > 0 && p1 != p2))
			throw new PreCondError("!(h > 0 && s > 0 && p1 != p2)");
	
		//traitement
		super.init(h, w, s, p1, p2);
		
		// post-init invariant
			checkInvariant();
						
			// \post: getHeight() = h;
			if(!(getHeight() == h))
			{  System.out.println("h1 : " + getHeight());
				System.out.println("h : " + h);
				throw new PostCondError("!(getHeight() == h)");
			
			}
			
			// \post: getWidth() = w;
			if( !(getWidth() == w))
				throw new PostCondError("!(getWidth() == w)");
			
			// \post : getScale() = s
			if( !(getScale() == s))
				throw new PostCondError("!(getScale == s)");
			
			// \post: getPlayer(1) = p1;
			if ( !(getPlayer(1) == p1))
				throw new PostCondError("!(getPlayer(1) == p1)");
			
			// \post: getPlayer(2) = p2;
			if ( !(getPlayer(2) == p2))
				throw new PostCondError("!(getPlayer(2) == p2)");
			
			// \post: getPostionX(player1) = w/2 - s/2
			if(!(getPlayer(1).getPositionX() == getWidth() / getScale() / 4 - getScale() / 2))
				throw new PostCondError("!(getPlayer(1).getPositionX() == getWidth() / 2 - getScale() / 2)");
			
			// \post: PositionX(player2) = w/2 + s/2
			if(!(getPlayer(2).getPositionX() == getWidth() / getScale() / 2 + getScale() * 20))
				throw new PostCondError("!(getPlayer(2).getPositionX() == getWidth() / 2 + getScale() / 2)");
			// \post: PostionY(player1) = 0
			if(!(getPlayer(1).getPositionY() == getHeight() / getScale() - getPlayer(1).getHeight() / 2))	
			{
				System.out.println( getHeight() / getScale() - getPlayer(1).getHeight() / 2);
				System.out.println("p1 : " + getPlayer(1).getPositionY());
				throw new PostCondError("!(getPlayer(1).getPositionY())");
			}
			
			// \post: PositionY(player2) = 0
			if(!(getPlayer(2).getPositionY() == getHeight() / getScale() - getPlayer(2).getHeight() / 2))
				throw new PostCondError("!(getPlayer(2).getPositionY())");
			
			// \post: faceRight(player1) = true
			if(!(getPlayer(1).isFaceRight() == true))
				throw new PostCondError("!(getPlayer(1).isFaceRight() == true)");
			
			// \post: faceRight(Player2) = false
			if(!(getPlayer(2).isFaceRight() == false))
				throw new PostCondError("!(getPlayer(2).isFaceRight() == false)");
			
	
	}
	
	
	public void step(int c1, int c2)
	{
		// \pre : !isGameOver()
			if(isGameOver())
				throw new PreCondError("game over");
			
		//Invariants
			checkInvariant();
		
		//pas de capture
			
		// traitement
			super.step(c1, c2);
		
		//Invariants
			checkInvariant();
			
		// \post: getPlayer(1).step(c1)
		// \post: getPlayer(2).step(c2)	
		// elle seront verifier dans player 1 et 2
		
	}

	
//	public void setGameOver() {
//		super.setGameOver();
//	}
	
	
	public void run() {
		super.run();
	}
	
}
