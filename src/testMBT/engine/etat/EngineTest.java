package testMBT.engine.etat;



import impl.EngineImpl;
import impl.PlayerImpl;

import org.junit.Assert;
import org.junit.Test;

import services.Engine;
import services.Player;

public class EngineTest {

	@Test
	public void isGameOver(){
		// couverture : etat remarquable
		// objectif : gameOver
		// condition initiales
		Player p = new PlayerImpl();
		Player p1 = new PlayerImpl();
		Engine e = new EngineImpl();
		p.init(1, 100, 2, true, e);
		p1.init(2, 100, 2, false, e);
		e.init(200, 400, 2, p, p1);
		
		//operations:
		p.setLife(0);
		// oracle
		Assert.assertTrue(e.isGameOver());
		
		
		
		
	}

}
