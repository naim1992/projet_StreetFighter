package attacks.kick;

import services.FightChar;
import contract.HitBoxContract;
import attacks.Attack;

public class FHkick extends Attack{

	
	private final int dist = 12;
	private final int dammage = 2;
	
	public FHkick(FightChar p) {
		super(p);
		setDammage(dammage);
		HitBoxContract hb = new HitBoxContract(getHitBox(), p.getEngine());
		
		if(p.isFaceRight())
			hb.init(p.getPositionX() + dist, p.getPositionY(), 32, 32);
		else
			hb.init(p.getPositionX() - dist, p.getPositionY(), 32, 32);
		
	}
	
}
