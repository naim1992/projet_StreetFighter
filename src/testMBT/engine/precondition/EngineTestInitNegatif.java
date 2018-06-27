package testMBT.engine.precondition;


import impl.EngineImpl;
import impl.PlayerImpl;

import org.junit.Assert;
import org.junit.Test;


import services.Engine;
import services.Player;

public class EngineTestInitNegatif {

	@Test
	public void engineTestInitNegatifH()
	{
		// cas negatif avec h negatif
		int h = -1;
		int w = 400;
		int s = 3;
		Player p1 = new PlayerImpl();
		Player p2 = new PlayerImpl();
		Engine e = new EngineImpl();
		
		// test des preCondition
		Assert.assertTrue(h>0 && s>0 && w>s && p1 != p2);
		// operation
		e.init(h, w, s, p1, p2);
		//oracle
		// [exception h negatif] 
				
	}

}
