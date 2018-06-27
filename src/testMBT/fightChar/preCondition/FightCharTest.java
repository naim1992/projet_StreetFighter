package testMBT.fightChar.preCondition;

import impl.EngineImpl;
import impl.FightCharImpl;
import impl.TechImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.FightChar;
import testMBT.character.precondition.CharacterTest;

public class FightCharTest extends CharacterTest {

	FightChar fc;
	
	@Before
	public void init()
	{
		fc = new FightCharImpl();
		
		e = new EngineImpl();
		fc.init(1, 100, 2, true, e);
		e.init(200, 360, 2, fc, new FightCharImpl());
	}
	
	
	@Test
	public void initTestPos() {
		// cas Positif
		super.CharacterInitPos();
	}
	
	@Test
	public void initTestNeg() {
		// cas negatif
		super.CharacterInitNeg();
	}
	
	@Test
	public void moveLeftPos() {
		CharacterMoveLeftPos();
	}
	
	@Test
	public void moveLeftNeg() {
		super.CharacterMoveLeftNeg();
	}
	
	@Test
	public void moveRightPos() {
		super.CharacterMoveRightPos();
	}
	
	@Test
	public void moveRightNeg() {
		super.CharacterMoveRightNeg();
	}
	
	@Test
	public void switchSidePos() {
		super.switchSidePos();
	}
	
	@Test
	public void switchSideNeg() {
		super.switchSideNeg();
	}
	
	@Test
	public void startTechPos()
	{
		//Couverture : precondition positif
		//Objectif : startTech
		//cas : positif
		// Conditions Initiales
		double x_pre = fc.getPositionX();
		double y_pre = fc.getPositionY();
		boolean facingRight_pre = fc.isFaceRight();
		boolean isBlocking_pre = true;
		
		
		// operations
		fc.startTech(new TechImpl(fc));
		
		//oracle
		//postConditions
		Assert.assertTrue(fc.getPositionX() == x_pre && fc.getPositionY() == y_pre && fc.isFaceRight() == facingRight_pre && fc.isBlocking() == isBlocking_pre);
		// invariants
		Assert.assertTrue(p.getPositionX() > 0
				&& p.getPositionX() < e.getWidth() && p.getPositionY() > 0
				&& p.getPositionY() < e.getHeight()
				&& p.isDead() == !(p.getLife() > 0));
		
		if(fc.isBlockStunned())
			Assert.assertTrue(fc.getLife() == 100);
		
	}
	
	@Test
	public void startTechNeg()
	{
		//Couverture : precondition negatif
		//Objectif : startTech
		//cas : negatif
		// Conditions Initiales
		double x_pre = fc.getPositionX();
		
		// operations
		fc.startTech(new TechImpl(fc));
		
		//oracle
		Assert.assertTrue("positons x changed",fc.getPositionX() != x_pre );
		
		
	}
	
	@Test
	public void fallingPos()
	{
		//Couverture : precondition positif
		//Objectif : falling
		//cas : positif
		// Conditions Initiales @Before
		double y = fc.getPositionY();
		//traitement
		fc.falling();
		//oracle
		//postCondition
		Assert.assertTrue(fc.getPositionY() < y);
		// Invariant
		Assert.assertTrue(p.getPositionX() > 0
				&& p.getPositionX() < e.getWidth() && p.getPositionY() > 0
				&& p.getPositionY() < e.getHeight()
				&& p.isDead() == !(p.getLife() > 0));
		
		
	}
	
	@Test
	public void fallingNeg()
	{
		//Couverture : precondition negatif
		//Objectif : falling
		//cas : negatif
		// Conditions Initiales @Before
		double y = fc.getPositionY();
		//traitement
		fc.falling();
		//oracle
		Assert.assertTrue(fc.getPositionY() > y);
	}
	
	@Test
	public void jumpingPos()
	{
		//Couverture : precondition positif
		//Objectif : jumping
		//cas : positif
		// Conditions Initiales @Before
		double y = fc.getPositionY();
		//traitement
		fc.jumping();
		//oracle
		//postCondition
		Assert.assertTrue(fc.getPositionY() < y);
		// Invariant
		Assert.assertTrue(p.getPositionX() > 0
						&& p.getPositionX() < e.getWidth() && p.getPositionY() > 0
						&& p.getPositionY() < e.getHeight()
						&& p.isDead() == !(p.getLife() > 0));
		
	}
	
	@Test
	public void jumpingNeg()
	{
		//Couverture : precondition negatif
		//Objectif : jumping
		//cas : negatif
		// Conditions Initiales @Before
		// Conditions Initiales @Before
		double y = fc.getPositionY();
		//traitement
		fc.jumping();
		//oracle
		Assert.assertTrue(fc.getPositionY() > y);
		
	}

}
