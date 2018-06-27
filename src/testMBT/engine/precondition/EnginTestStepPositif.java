package testMBT.engine.precondition;



import java.awt.event.KeyEvent;

import impl.EngineImpl;
import impl.PlayerImpl;

import org.junit.Assert;
import org.junit.Test;

import services.Engine;
import services.Player;

public class EnginTestStepPositif {

	@Test
	public void engineTestStepPositif()
	{
		// cas positif
		int h = 280;
		int w = 400;
		int s = 3;
		Player p1 = new PlayerImpl();
		Player p2 = new PlayerImpl();
		Engine e = new EngineImpl();

		// operation
		e.init(h, w, s, p1, p2);
		// cas positif !gameOver
		
		//preCondition
		assert(!e.isGameOver());
		//operation
		e.step(KeyEvent.VK_LEFT, KeyEvent.VK_G);
		//oracle
		// invariant
					Assert.assertTrue(e.isGameOver() == (e.getPlayer(1).isDead() || e.getPlayer(2).isDead()));
				
	}

}
