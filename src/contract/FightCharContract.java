package contract;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import animation.Animation;
import services.FightChar;
import services.Tech;
import errors.InvariantError;
import errors.PostCondError;
import errors.PreCondError;

public class FightCharContract extends PLayerContract implements FightChar {

	public FightCharContract(FightChar d) {
		super(d);
		
	}
	
	public FightChar getDelegate()
	{
		return (FightChar) super.getDelegate();
	}
	
	
	public void checkInvariant()
	{
		
		
		super.checkInvariant();
		// \inv: !isDead()
			if(getDelegate().isDead())
				throw new InvariantError("isDead");
		
			int life = getDelegate().getLife();
			
		 // \inv: isBlockingStunned \==> getLife() = getLife()@pre
			if(!(getDelegate().isBlockStunned()) && !(getDelegate().getLife() == life))	
			{	
				throw new InvariantError("isBlockStunned() && getLife() == life");	
			}
	}
	
	 @Override
	public void startTech(Tech t)
	{
		// \pre: 
		if(isTechning())
			throw new PreCondError("isTechning");
		
		// preInvariant
		checkInvariant();
		 
		//capture
		double x_pre = getPositionX();
		double y_pre = getPositionY();
		boolean facingRight_pre = isFaceRight();
		boolean isBlocking_pre = true;
		
		//traitement
		getDelegate().startTech(t);
		
		//post Invariant
		checkInvariant();
		
		// \post: getPositionX() = getPositionX()@pre 
		// \post: getPositionY() = getPositionY()@pre
		// \post: isFacingRight() = isFacingRight@pre
		// \post: isBlocking() = false
		
		if(!(getPositionX() == x_pre))
			throw new PostCondError("getPositionX() == x_pre)");
		
		if(!(getPositionY() == y_pre))
			throw new PostCondError("getPositionY() == y_pre)");
		
		if(!(isFaceRight() == facingRight_pre))
			throw new PostCondError("isFaceRight() == facingRight_pre");
		
		if(!(isBlocking() != isBlocking_pre))
		{
			throw new PostCondError("isBlocking");
		}
		
	}
	
	 
		@Override
		public void falling() {
			
			// \pre: isFalling() 
			if(!getDelegate().isFalling())
				throw new PreCondError("is falling");
			// pre invariant
			checkInvariant();
			
			// capture
			double y = getDelegate().getPositionY();
			
			// traitement
			getDelegate().falling();
			
			// post invaraint
			checkInvariant();

			// \post : getPositionY() != getPositionY()@pre
			if(!(getDelegate().getPositionY() != y))
				throw new PostCondError("falling position y");
			
			
		}

		

		@Override
		public void jumping() {
			
			// \pre: isJumping() 
					if(!getDelegate().isJumping())
						throw new PreCondError("is jumping");
					// pre invariant
					checkInvariant();
					
					// capture
					double y = getDelegate().getPositionY();
					
					// traitement
					getDelegate().jumping();
					
					// post invaraint
					checkInvariant();

					// \post : getPositionY() != getPositionY()@pre
					if(!(getDelegate().getPositionY() != y))
						throw new PostCondError("jumping position y");
			
		}

		@Override
		public void shouryuken() {
			// \pre: isShouryuken() 
					if(!getDelegate().isShouryuken())
						throw new PreCondError("shouryuken");
					// pre invariant
					checkInvariant();
					
					// capture
					double x = getDelegate().getPositionX();
					
					// traitement
					getDelegate().shouryuken();
					
					// post invaraint
					checkInvariant();

					// \post : getPositionY() != getPositionY()@pre
					if(!(getDelegate().getPositionX() != x))
						throw new PostCondError("falling position y");
		} 
	 
	
	public void moveLeft()
	{
		getDelegate().moveLeft();
	}
	
	public void moveRight()
	{
		getDelegate().moveRight();
	}
	
	
	public void step(int commande) {
		
		super.step(commande);
		getDelegate().step(commande);
	}
	
	public void endStep(int commande) {
		
		super.endStep(commande);
		getDelegate().endStep(commande);
	}
	
	@Override
	public Map<String,Integer> getActions()
	{
		return getDelegate().getActions();
	}
	
	@Override
	public boolean isBlocking() {
		
		return getDelegate().isBlocking();
	}
	@Override
	public boolean isBlockStunned() {
		
		return getDelegate().isBlockStunned();
	}
	@Override
	public boolean isHitsStunned() {
		
		return getDelegate().isHitsStunned();
	}
	@Override
	public boolean isTechning() {
		
		return getDelegate().isTechning();
	}
	@Override
	public Tech isTech() {
		
		return getDelegate().isTech();
	}
	@Override
	public boolean tech() {
		
		return getDelegate().tech();
	}
	@Override
	public boolean techHasAlreadyHit() {
		
		return getDelegate().techHasAlreadyHit();
	}

	@Override
	public int getFire() {
		
		return ((FightChar) getDelegate()).getFire();
	}

	@Override
	public int getMaxFire() {
		
		return ((FightChar) getDelegate()).getMaxFire();
	}

	
	@Override
	public Animation getAnimation() {return getDelegate().getAnimation();}
	@Override
	public List<BufferedImage[]> getSprites() {return getDelegate().getSprites();}
	
	
	@Override
	public void setAnimation(Animation a) {	getDelegate().setAnimation(a);}
	@Override
	public void update() {getDelegate().update(); }
	@Override
	public void draw(Graphics2D g) { getDelegate().draw(g);}
	@Override
	public void setCurrentAction(int i) {getDelegate().setCurrentAction(i);}

	@Override
	public void setTech(boolean b) {
		getDelegate().setTech(b);
		
	}

	@Override
	public void setTechning(boolean b) {
		getDelegate().setTechning(b);
		
	}

	@Override
	public void setBlockingStunned() {
		getDelegate().setBlockingStunned();
		
	}



	@Override
	public boolean isFalling() {
		// TODO Auto-generated method stub
		return getDelegate().isFalling();
	}

	@Override
	public boolean isShouryuken() {
		// TODO Auto-generated method stub
		return getDelegate().isShouryuken();
	}

	@Override
	public boolean isJumping() {
		// TODO Auto-generated method stub
		return getDelegate().isJumping();
	}



	

	
	
}
