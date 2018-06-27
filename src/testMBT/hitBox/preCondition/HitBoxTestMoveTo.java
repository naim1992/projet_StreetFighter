package testMBT.hitBox.preCondition;


import impl.HitBoxImpl;

import org.junit.Assert;
import org.junit.Test;

import services.HitBox;

public class HitBoxTestMoveTo {

	@Test
	public void MoveToTestPos() {
		// cas Positif
		
		HitBox hb = new HitBoxImpl();
		hb.init(10, 10, 200, 200);
		// pas de preCond
		boolean belongsTo_centre_at_pre = hb.belongsTo( hb.getPositionX(), hb.getPositionY(), hb.getWidth(), hb.getHeight());
		boolean belongsTo_centre_100_at_pre = hb.belongsTo( hb.getPositionX() + 100,  hb.getPositionY() + 100, hb.getWidth(), hb.getHeight());
		int getPositionX_at_pre = (int) hb.getPositionX();
		int getPositionY_at_pre = (int) hb.getPositionY();
		boolean belongsTo_abs_at_pre = hb.belongsTo(300, 0, hb.getWidth(), hb.getHeight());
		
		
		// operations
		
		hb.moveTo(15, 15);
		// oracle
		Assert.assertTrue(hb.getPositionX() == 15 && hb.getPositionY() == 15);
		// Test du centre 
		Assert.assertTrue(hb.belongsTo( hb.getPositionX(),  hb.getPositionY(), hb.getWidth(), hb.getHeight()) == belongsTo_centre_at_pre);
		// Test du centre + 100 
		Assert.assertTrue(hb.belongsTo( hb.getPositionX() + 100,  (int) hb.getPositionY() + 100, hb.getWidth(), hb.getHeight()) == belongsTo_centre_100_at_pre);
		// Test d’un point absolu 
		Assert.assertTrue(hb.belongsTo(300 + (hb.getPositionX() - getPositionX_at_pre), 0 + (hb.getPositionY() - getPositionY_at_pre), hb.getWidth(), hb.getHeight()) == belongsTo_abs_at_pre);
					
		
		
	}
	
	
	@Test
	public void MoveToTestNeg() {
		// cas Negatif
		
		HitBox hb = new HitBoxImpl();
		hb.init(10, 10, 200, 200);
		// pas de preCond
		
		// operations
		
		hb.moveTo(-15, 15);
	
		// oracle
		Assert.assertTrue(hb.getPositionX() > 0);
			
	}
	

}
