package testMBT.engine.precondition;



import impl.EngineImpl;
import impl.PlayerImpl;

import java.awt.event.KeyEvent;

import org.junit.Test;

import services.Engine;


public class EngineTestStepNegatif {

	
	
	
	@Test
	public void engineTestStepNegatif()
	{
		Engine e = new EngineImpl();	
		e.init(280, 400, 3, new PlayerImpl(), new PlayerImpl());
		
		// cas positif !gameOver
		e.getPlayer(1).setLife(0);
	
		//preCondition
		assert(!e.isGameOver());
		//operation
		e.step(KeyEvent.VK_LEFT, KeyEvent.VK_G);
		//oracle
		// [exception player 1 dead]
	
		
	}

}
