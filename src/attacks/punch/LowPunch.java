package attacks.punch;

import attacks.Attack;
import contract.HitBoxContract;
import services.FightChar;

public class LowPunch extends Attack{

	private final int dist = 12;
	private final int dammage = 2;
	
	public LowPunch(FightChar p) {
		super(p);
		setDammage(dammage);
		HitBoxContract hb = new HitBoxContract(getHitBox(), p.getEngine());
		
		if(p.isFaceRight())
			hb.init(p.getPositionX() + dist, p.getPositionY(), 32, 32);
		else
			hb.init(p.getPositionX() - dist, p.getPositionY(), 32, 32);
		
	
	}
	
	
	

}
