package testMBT.fightChar.etat;

import impl.EngineImpl;
import impl.FightCharImpl;
import impl.TechImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import attacks.kick.FHkick;
import services.FightChar;
import services.Tech;
import testMBT.character.etat.CharacterTest;

public class FightCharTest extends CharacterTest{

	FightChar fc;
	FightChar fc1;
	
	@Before
	public void init()
	{
		fc = new FightCharImpl();
		fc1 = new FightCharImpl();
		
		e = new EngineImpl();
		fc.init(1, 100, 2, true, e);
		fc1.init(2, 100, 2, false, e);
		e.init(480, 1000, 2, fc, fc1);
	}
	
	@Test
	public void blockingWhileAttack() {
		// couverture : etat remarquable
		// objectif : ne peut pas bouger parceque il attack
		// condition initiales : @Before
		
		// operation
		fc.startTech(new TechImpl(fc));
		// oracle
		Assert.assertTrue(fc.isBlocking());
		
	}
	
	@Test
	public void blockingWhileIsTouch() {
		// couverture : etat remarquable
		// objectif : ne peut pas bouger parceque il attack
		// condition initiales : @Before
		fc.setPositionX(e.getPlayer(2).getPositionX() - e.getPlayer(2).getWidth() / 2 + 1 );
		Tech tch = new FHkick(fc1);
		// operation
		fc1.startTech(tch);
		((FHkick) tch).update();
		// oracle
		Assert.assertTrue(fc.isBlocking());
	}
	
	@Test
	public void lifeWhileBlockingStunned()
	{
		// couverture : etat remarquable
		// objectif : le joueur ne subit pas de degat l'orseque il bloque l'attaque
		// condition initiales : @Before
		
		fc.setPositionX(e.getPlayer(2).getPositionX() - e.getPlayer(2).getWidth() / 2 + 1 );
		
		fc1.setBlockingStunned();
		Tech tch = new FHkick(fc);
		double life = fc1.getLife();
		// operations
		fc.startTech(tch);
		((FHkick) tch).update();
		
		// oracle
		Assert.assertTrue(fc1.getLife() == life);
		
		
		
	}

}
