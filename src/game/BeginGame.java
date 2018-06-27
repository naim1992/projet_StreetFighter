package game;

import impl.FightCharImpl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;



import java.awt.event.KeyEvent;

import main.Background;
import main.GameState;
import main.GameStateManager;
import services.FightChar;



public class BeginGame extends GameState{

private Background bg;
private FightChar pl;
private FightChar pl1;
private int p1Name;
private int p2Name;

private GameStateManager gsm;
	
	public BeginGame(GameStateManager gsm, FightChar p1, FightChar p2) {
		this.gsm = gsm;
		pl =  p1;//new Ryu((FightChar) this.gsm.getEngine().getPlayer(1));
		pl1 = p2;//new Ken((FightChar) this.gsm.getEngine().getPlayer(2));
		
		init();
	}
	@Override
	public void init() {
		
	
		 p1Name = 0;
		 p2Name = gsm.getEngine().getWidth() / gsm.getEngine().getScale() -  pl1.getLife();
		
		bg = new Background("/Backgrounds/edobath.png", 0.1, gsm.getEngine());
		
	}

	@Override
	public void update() {
		//update player
	    pl.update();
		pl1.update();
		
	}

	@Override
	public void draw(Graphics2D g) {
		// clear screen
		bg.draw(g);
		// draw tileMap
		
		g.setColor(Color.GREEN);
		g.fillRect(0, 5, pl.getLife(), 10);
		
		g.drawString("Ryu", p1Name, 30);
		g.fillRect(gsm.getEngine().getWidth() / gsm.getEngine().getScale() -  pl1.getLife(), 5, pl1.getLife(), 10);
		g.drawString("Ken", p2Name, 30);
		// draw player
		pl.draw(g);
		pl1.draw(g);
		
		
		if(gsm.getEngine().getPlayer(1).isDead() || gsm.getEngine().getPlayer(2).isDead())
		{
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Century Gothics", Font.PLAIN, 28));
			
			if(gsm.getEngine().getPlayer(1).getLife() <= 0)
			{
				g.drawString("Player 2 Win", gsm.getEngine().getWidth() / gsm.getEngine().getScale() / 4, gsm.getEngine().getHeight()/ gsm.getEngine().getScale() / 3);
				
				
			}
			else
				g.drawString("Player 1 Win", gsm.getEngine().getWidth() / gsm.getEngine().getScale() / 4, gsm.getEngine().getHeight()/ gsm.getEngine().getScale() / 3);
			
			
		
			g.drawString("Game Over", gsm.getEngine().getWidth() / gsm.getEngine().getScale() / 4, gsm.getEngine().getHeight()/ gsm.getEngine().getScale() / 2);
			
			
		}
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent k) {	
		FightCharImpl.pressed.add(k.getKeyCode());
		gsm.getEngine().step(k.getKeyCode(), k.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent k) {
		gsm.getEngine().endStep(k.getKeyCode(), k.getKeyCode());
		FightCharImpl.pressed.clear();
		
	}


}
