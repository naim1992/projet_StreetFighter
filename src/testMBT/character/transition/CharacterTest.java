package testMBT.character.transition;



import impl.EngineImpl;
import impl.PlayerImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.Engine;
import services.Player;

public class CharacterTest {

	

	Player p;
	Engine e;
	int l;
	int s;
	boolean f;
	
	
	@Before
	public void initOk()
	{
		 p = new PlayerImpl();
		 l = 200;
		 s = 3;
		 f = true;
		 e = new EngineImpl();
		 p.init(1, l, s, f, e);
		 e.init(480, 1000, 2, p, new PlayerImpl());
		
	}
	
	@Test
	public void moveLeft() {
		// Couverture : transitions
		// Objectif : transition move left
		// conditions initiales :
		double x = p.getPositionX();
		
		//operation
		p.moveLeft();
		// oracle
		Assert.assertTrue(p.getPositionX() == x - p.getSpeed());
		
	}
	
	@Test
	public void moveRight() {
		// Couverture : transitions
		// Objectif : transition move right
		// conditions initiales :
		double x = p.getPositionX();
				
		//operation
		p.moveRight();
		// oracle
		Assert.assertTrue(p.getPositionX() == x + p.getSpeed());
	}

}
