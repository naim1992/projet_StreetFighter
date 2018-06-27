package attacks.special;


import attacks.Attack;
import services.FightChar;
import contract.HitBoxContract;

public class TatsumakiSenpuKyaku extends Attack {

	
	private final int dist = 12;
	private final int dammage = 2;
	
	
	public TatsumakiSenpuKyaku(FightChar p) {
		super(p);
		setDammage(dammage);
		HitBoxContract hb = new HitBoxContract(getHitBox(), p.getEngine());
		
		if(p.isFaceRight())
			hb.init(p.getPositionX() + dist, p.getPositionY(), 32, 32);
		else
			hb.init(p.getPositionX() - dist, p.getPositionY(), 32, 32);
		
	}
	@Override
	public void setHit(int i)
	{
		
		int l = getPlayer().getEngine().getPlayer(i).getLife();
		
		if(! ((FightChar) getPlayer().getEngine().getPlayer(i)).isBlockStunned())
		{
			
			
			((FightChar) getPlayer().getEngine().getPlayer(i)).setTechning(true);
			getPlayer().getEngine().getPlayer(i).setLife(l - getDammage());

		}
		
		System.out.println("p" + i + " life : "+  getPlayer().getEngine().getPlayer(i).getLife() );
		
	}
	@Override
	public void update()
	{
		int i = ((getPlayer().getId() == 2) ? 1 : 2);
		if( getHitBox().collidesWith(getPlayer().getEngine().getPlayer(i).getHitBox()))
		{
			setHit(i);
		}
			

	}
	
}
