package testMBT.scenario;

import impl.EngineImpl;
import impl.FightCharImpl;

import org.junit.Assert;
import org.junit.Test;

import attacks.kick.FHkick;
import services.Engine;
import services.FightChar;
import services.Player;
import services.Tech;
import contract.EngineContract;


public class Game {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 240;
	private static final int SCALE = 2;
	
	@Test
	public void scenarios() {
		// cond initiales
		Engine e  = new EngineImpl();
		
		
		Player p1 = new FightCharImpl();
		Player p2 = new FightCharImpl();
			
		
		
		
		EngineContract ec = new EngineContract(e);
		p1.init(1, 100, 2, true, e);
		p2.init(2, 100, 2, false, e);
		ec.init(HEIGHT * SCALE, WIDTH * SCALE, SCALE, p1, p2);
		
		// operations
		while(!p1.getHitBox().collidesWith(p2.getHitBox()))
		{
			double x = p1.getPositionX();
			p1.moveRight();
			Assert.assertTrue(p1.getPositionX() == x + p1.getSpeed());
		}
		while(!p2.isDead())
		{
			
		double life = p2.getLife();
		
		Tech tch = new FHkick((FightChar) p1);
		((FightChar) p1).startTech(tch);
		((FHkick) tch).update();
		
		Assert.assertTrue(p2.getLife() != life);
		
		}
		
		// oracle
		Assert.assertTrue("p2 is dead", !p2.isDead());		
		
	}

}
