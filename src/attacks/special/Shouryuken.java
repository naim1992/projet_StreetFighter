package attacks.special;

import attacks.Attack;
import services.FightChar;
import contract.HitBoxContract;

public class Shouryuken extends Attack{

	
	private final int dist = 12;
	private final int dammage = 2;
	
	public Shouryuken(FightChar p) {
		super(p);
		setDammage(dammage);
		HitBoxContract hb = new HitBoxContract(getHitBox(), p.getEngine());
		
		if(p.isFaceRight())
			hb.init(p.getPositionX() + dist, p.getPositionY(), 32, 32);
		else
			hb.init(p.getPositionX() - dist, p.getPositionY(), 32, 32);
		
	}
}
