package main;


import javax.swing.JFrame;

import contract.EngineContract;
import contract.FightCharContract;

import services.Engine;
import services.FightChar;

import services.Player;
import impl.EngineImpl;
import impl.FightCharImpl;


public class Main {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 240;
	private static final int SCALE = 2;
	
	public static void main(String[] args) {
		
		Engine e  = new EngineImpl();
		
		
		Player p1 = new FightCharImpl();
		Player p2 = new FightCharImpl();
			
		FightCharContract plc1 = new FightCharContract((FightChar) p1);
		FightCharContract plc2 = new FightCharContract((FightChar) p2);
		
		
		EngineContract ec = new EngineContract(e);
		plc1.init(1, 100, 2, true, e);
		plc2.init(2, 100, 2, false, e);
		ec.init(HEIGHT * SCALE, WIDTH * SCALE, SCALE, plc1, plc2);
		
	
		JFrame window = new JFrame("Street Fighter");
		window.setContentPane((EngineImpl) e);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		
		
		Thread thread = new Thread((Runnable) ec);
		thread.run();
	}
	
	
	
	
	
}
