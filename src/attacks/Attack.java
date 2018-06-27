package attacks;


import impl.TechImpl;
import services.FightChar;



public class Attack extends TechImpl {

	
   private boolean hit;
   
   
	
   public Attack(FightChar p) {
		super(p);	
	}
	
	public boolean isHit() {
		return hit;
	}

	public void setHit(int i)
	{
		if (hit) return;
		
		
		int l = getPlayer().getEngine().getPlayer(i).getLife();
		
		if(! ((FightChar) getPlayer().getEngine().getPlayer(i)).isBlockStunned())
		{
		((FightChar) getPlayer().getEngine().getPlayer(i)).setTechning(true);
		
		if(!((FightChar) getPlayer().getEngine().getPlayer(i)).techHasAlreadyHit())
			getPlayer().getEngine().getPlayer(i).setLife(l - getDammage());
		}
		
		System.out.println("p" + i + " life : "+  getPlayer().getEngine().getPlayer(i).getLife() );
		
	}
	
	
	
	public void update()
	{
		int i = ((getPlayer().getId() == 2) ? 1 : 2);
		if( getHitBox().collidesWith(getPlayer().getEngine().getPlayer(i).getHitBox()))
		{
			this.setHit(i);		
		}
			

	}

	

	

	
	
	

	

}
