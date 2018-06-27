package testMBT.hitBox.transition;

import impl.HitBoxImpl;

import org.junit.Assert;
import org.junit.Test;

import services.HitBox;

public class HitBoxTest {

	@Test
	public void test() {
		// Couverture : transition
		// Objectif : transition moveTo
		// Conditions initiales:
		HitBox hb = new HitBoxImpl();
		hb.init(32, 32, 32, 32);
		// Operation:
		hb.moveTo(100, 32);
		//	Oracle:
		Assert.assertTrue(hb.getPositionX() == 100 && hb.getPositionY() == 32);
		Assert.assertTrue(hb.belongsTo(hb.getPositionX(), hb.getPositionY(), 32, 32));
	}

}
