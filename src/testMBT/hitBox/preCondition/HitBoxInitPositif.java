package testMBT.hitBox.preCondition;

import impl.HitBoxImpl;

import org.junit.Test;

import services.HitBox;

public class HitBoxInitPositif {

	@Test
	public void hitBoxInitTest() {
		//cas positif
		// Condition initiales
		HitBox hb = new HitBoxImpl();
		// operation
		hb.init(10, 10, 100, 100);
		// oracle
		assert(hb.getPositionX() == 10 && hb.getPositionY() == 10);
	
	}
	
	@Test
	public void hitBoxInitNegatif()
	{
		//cas positif
				// pas de precoND
				HitBox hb = new HitBoxImpl();
				// operation
				hb.init(5, 10, 100, 100);
				// oracle
				assert(hb.getPositionX() == 10 && hb.getPositionY() == 10);
	}
	
	
	

}
