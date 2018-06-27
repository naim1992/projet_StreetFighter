package testMBT.character.precondition;


import impl.EngineImpl;
import impl.PlayerImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.Engine;
import services.Player;

public class CharacterTest {

	public Player p;
	public Engine e;
	public int l;
	public int s;
	public boolean f;
	
	@Before
	public void CharacterInit()
	{
		 p = new PlayerImpl();
		 l = 200;
		 s = 3;
		 f = true;
		 e = new EngineImpl();
		 e.init(200, 360, 2, p, new PlayerImpl());
	}
	
	@Test
	public void CharacterInitPos() {
		// cas pos
		// condition initales vide
		// operations
		p.init(1, l, s, f, e);
		// oracle
		// postCondition
		Assert.assertTrue(p.getLife() == l && p.getSpeed() == s
				&& p.isFaceRight() == f && p.getEngine() == e);
		//invariant
				Assert.assertTrue(p.getPositionX() > 0
						&& p.getPositionX() < e.getWidth() && p.getPositionY() > 0
						&& p.getPositionY() < e.getHeight()
						&& p.isDead() == !(p.getLife() > 0));

	}

	@Test
	public void CharacterInitNeg() {
		// cas Neg
		//Cond initiales vide
		// operations
		p.init(1, l, -3, f, e);
		// oracle
		Assert.assertTrue("s negatif", l > 0 && p.getSpeed() > 0);
		

	}

	@Test
	public void CharacterMoveLeftPos() {
		// cas pos

		// cond initiales 
		p.init(1, l, s, f, e);
		double x = p.getPositionX();

		p.moveLeft();
		// oracle
		// post condition
		Assert.assertTrue((p.getPositionX() == x + 5
				|| p.getPositionX() == x + p.getSpeed()
				|| p.getPositionX() == x - p.getSpeed()
				|| p.getPositionX() == e
				.getWidth() / e.getScale() - p.getWidth() / 2));
		// invariant
		Assert.assertTrue(p.getPositionX() > 0
				&& p.getPositionX() < e.getWidth() && p.getPositionY() > 0
				&& p.getPositionY() < e.getHeight()
				&& p.isDead() == !(p.getLife() > 0));

	}

	@Test
	public void CharacterMoveLeftNeg() {
		// cas pos

		// cond initiales
		p.init(1, l, s, f, e);
		double x = p.getPositionX();

		p.moveLeft();
		// oracle
		// post condition
		Assert.assertTrue("x inchanged ", p.getPositionX() == x);
				
		
	}
	
	@Test
	public void CharacterMoveRightPos() {
		// cas pos

		// cond initiales
		p.init(1, l, s, f, e);
		double x = p.getPositionX();

		p.moveLeft();
		// oracle
		// post condition
		Assert.assertTrue((p.getPositionX() == x - 5
				|| p.getPositionX() == x + p.getSpeed()
				|| p.getPositionX() == x - p.getSpeed()
						|| p.getPositionX() == e
				.getWidth() / e.getScale() - p.getWidth() / 2));
		// invariant
		Assert.assertTrue(p.getPositionX() > 0
				&& p.getPositionX() < e.getWidth() && p.getPositionY() > 0
				&& p.getPositionY() < e.getHeight()
				&& p.isDead() == !(p.getLife() > 0));

	}

	@Test
	public void CharacterMoveRightNeg() {
		// cas pos
		// cond initiales
		p.init(1, l, s, f, e);
		double x = p.getPositionX();

		p.moveLeft();
		// oracle
		Assert.assertTrue("position x inchanged ", p.getPositionX() == x);
				
		

	}
	
	
	@Test
	public void switchSidePos()
	{
		// cas positif
		//cond initiales
		p.init(1, 200, 3, true, new EngineImpl());
		int x_atPre = (int) p.getPositionX();
		boolean faceRgh_atPre = p.isFaceRight();
		
		// operation
		p.switchSide();
		
		//oracle
			// post Condition
			Assert.assertTrue(p.isFaceRight() == !faceRgh_atPre);
			Assert.assertTrue(p.getPositionX() == x_atPre);
		
		
		
	}
	
	@Test
	public void switchSideNeg()
	{
		// cas negatif
				//cond initiales
				p.init(1, 200, 3, true, new EngineImpl());
				
				boolean faceRgh_atPre = p.isFaceRight();
				
				// operation
				p.switchSide();
				
				//oracle
					// post Condition
					Assert.assertTrue("Post Condition Error faceRight inchanged",p.isFaceRight() == faceRgh_atPre);

	}
	
	
	
	

}
