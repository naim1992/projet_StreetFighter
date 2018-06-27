package testMBT.character.etat;

import impl.EngineImpl;
import impl.PlayerImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.Engine;
import services.Player;

public class CharacterTest {

	Player p;
	public Engine e;
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
		 Player p2 = new PlayerImpl();
		 p2.init(2, l, s, false, e);
		 e.init(480, 1000, 2, p, p2);
		
	}
	
	@Test
	public void canNotMoveRightBecauseRightBorder() {
		// couverture : etat remarquable
		// objectif : ne peut pas bouger parceque il a atteint la limite droit de terain
		// condition initiales
		p.setPositionX(e.getWidth());
		// operation
		p.moveRight();
		//oracle
		Assert.assertTrue("can't move right border", p.getPositionX() == e.getWidth() / e.getScale() - p.getWidth() / 2);
		
	}

	@Test
	public void canNotMoveLeftBecauseLeftBorder() {
		// couverture : etat remarquable
		// objectif : ne peut pas bouger parceque il a atteint la limite gauche de terain
		// condition initiales
		p.setPositionX(0);
		// operation
		p.moveLeft();
		//oracle
		Assert.assertTrue("can't move left border", p.getPositionX() == p.getWidth() / 2);
		
	}
	
	@Test
	public void canNotMoveBecauseCollidesWithPlayer() {
		// couverture : etat remarquable
		// objectif : ne peut pas continuer a avancé parceque il y a une collision avec l'autre joueur
		// condition initiales
		p.setPositionX(e.getPlayer(2).getPositionX() - e.getPlayer(2).getWidth() / 2 + 1 );
		double x = p.getPositionX();
		// operation
			p.moveRight();
		//oracle			
		Assert.assertTrue(p.getPositionX() == x - 5);
		
	}
	
}
