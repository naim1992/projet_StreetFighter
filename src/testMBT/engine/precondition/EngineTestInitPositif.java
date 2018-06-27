package testMBT.engine.precondition;


import impl.EngineImpl;
import impl.PlayerImpl;
import org.junit.Assert;
import org.junit.Test;

import services.Engine;
import services.Player;

public class EngineTestInitPositif {

	private Engine e;
	
	
	//cas de test Engine::init
	@Test
	public void engineTestInitPositif()
	{
		// cas positif
		int h = 280;
		int w = 400;
		int s = 3;
		Player p1 = new PlayerImpl();
		Player p2 = new PlayerImpl();
		this.e = new EngineImpl();
		
		// test des preCondition
		Assert.assertTrue(h>0 && s>0 && w>s && p1 != p2);
		// operation
		this.e.init(h, w, s, p1, p2);
		//oracle
			// post cond
			Assert.assertTrue(e.getHeight() == h && e.getWidth() == w);
			Assert.assertTrue(e.getPlayer(1) == p1 && e.getPlayer(2) == p2);
			Assert.assertTrue(e.getPlayer(1).getPositionX() == w/s/4 - s/2 );
			Assert.assertTrue(e.getPlayer(2).getPositionX() == w/s/2 + s * 20);
			// invariant
			Assert.assertTrue(e.isGameOver() == (e.getPlayer(1).isDead() || e.getPlayer(2).isDead()));
		
		// rapport	
			Assert.assertTrue(e.getHeight() != h && e.getWidth() != w);
			Assert.assertTrue( e.getPlayer(1) != p1 && e.getPlayer(2) != p2);
			Assert.assertTrue( e.getPlayer(1).getPositionX() != w/s/4 - s/2);
			Assert.assertTrue(e.getPlayer(2).getPositionX() != w/s/2 + s * 20);
		
				
	}
	
	

	

	
	
	

}
