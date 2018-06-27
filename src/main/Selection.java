package main;

import game.BeginGame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import animation.Blanka;
import animation.Byson;
import animation.Ken;
import animation.Ryu;
import services.FightChar;

public class Selection extends GameState {

	


	private String[] players = {"ryu", "ken", "byson", "blanka"};
	
	
	
	private int choice1 = 0;
	private int choice2 = 3;
	
	
	
	private Background bg;
	
	BufferedImage ryu, ken, byson, blanka;
	
	public FightChar player1;
	public FightChar player2;
	
	
	
	
public Selection(GameStateManager gsm) {
		
		this.gsm = gsm;
		init();
	}
	
	
	public void init(){
		
		try {
			bg = new Background("/Backgrounds/street-fighter.jpg", 1, gsm.getEngine());
			BufferedImage players = ImageIO.read(getClass().getResource(
					"/players/selection.png"));
			
			ryu = players.getSubimage(0, 0, 110, 110);
			ken= players.getSubimage(110*1, 0, 110, 110);
			byson = players.getSubimage(110*2, 0, 110, 110);
			blanka = players.getSubimage(110*3, 0, 110, 110);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public void draw(Graphics2D g)
	{
		bg.draw(g);
		
		g.drawImage(ryu, 0, gsm.getEngine().getHeight() / gsm.getEngine().getScale() - 110, ryu.getWidth(), ryu.getHeight(), null);
		
		g.drawImage(ken, 110, gsm.getEngine().getHeight() / gsm.getEngine().getScale() - 110, ken.getWidth(), ken.getHeight(), null);
		
		g.drawImage(byson, 110 * 2, gsm.getEngine().getHeight() / gsm.getEngine().getScale() - 110, byson.getWidth(), byson.getHeight(), null);
		
		g.drawImage(blanka, 110 * 3, gsm.getEngine().getHeight() / gsm.getEngine().getScale() - 110, blanka.getWidth(), blanka.getHeight(), null);
		

		if(player2 == null)
		{
		g.setColor(Color.BLUE);
		g.drawRect(choice2 * 110 , gsm.getEngine().getHeight() / gsm.getEngine().getScale() - 110, 100, 105);
		}
		
		if(player1 == null)
			{
			g.setColor(Color.WHITE);
			g.drawRect(choice1 * 110 , gsm.getEngine().getHeight() / gsm.getEngine().getScale() - 110, 100, 105);
			}
			

		
		
	}
	
	
	public void select(int e)
	{
		if(e == KeyEvent.VK_ENTER)
		{
		if(choice1 == 0 && player1 == null)
		{
			player1 = new Ryu((FightChar) this.gsm.getEngine().getPlayer(1));	
		}
		else
			if(choice1 == 1 && player1 == null)
			{
				player1 = new Ken((FightChar) this.gsm.getEngine().getPlayer(1));	
			}
			else
				if(choice1 == 2 && player1 == null)
				{
					player1 = new Byson((FightChar) this.gsm.getEngine().getPlayer(1));	
				}
				else
					if(choice1 == 3 && player1 == null)
					{
					player1 = new Blanka((FightChar) this.gsm.getEngine().getPlayer(1));	
					}
		}
		
		if(e == KeyEvent.VK_SPACE)
		{
		if(choice2 == 0  &&  player2 == null)
		{
			player2 = new Ryu((FightChar) this.gsm.getEngine().getPlayer(2));	
		}
		else
			if(choice2 == 1 && player2 == null)
			{
				player2 = new Ken((FightChar) this.gsm.getEngine().getPlayer(2));	
			}
			else
				if(choice2 == 2  && player2 == null)
				{
					player2 = new Byson((FightChar) this.gsm.getEngine().getPlayer(2));	
				}
				else
					if(choice2 == 3  && player2 == null)
					{
					player2 = new Blanka((FightChar) this.gsm.getEngine().getPlayer(2));	
					}
		}
		
		if(player1 != null && player2 != null)
		{
			gsm.gameStates.add(new BeginGame(gsm, player1, player2));
			gsm.setState(GameStateManager.LEVEL1STATE);
			
		
		
		}
		
	}




	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		bg.update();
	}




	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			select(e.getKeyCode());
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && player1 == null)
		{
			choice1 ++;
			if(choice1 == players.length )
				choice1 = 0;
			
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT && player1 == null)
		{
			choice1 --;
			if(choice1 == -1)
				choice1 = players.length - 1;
			
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D  && player2 == null)
		{
			
			choice2 ++;
			if(choice2 == players.length )
				choice2 = 0;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_G  && player2 == null)
		{
			choice2 --;
			
			if(choice2 == -1)
				choice2 = players.length - 1 ;

		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}
}
