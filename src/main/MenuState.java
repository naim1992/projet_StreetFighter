package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;



public class MenuState extends GameState{

	private Background bg;
	
	private String[] options = 
		{
			"Start",
			"Help",
			"Quit"
		};
	
	private int currentChoice = 0;
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		
		bg = new Background("/Backgrounds/street-fighter.jpg", 1, gsm.getEngine());
		//bg.setVector(-0.1, 0);
		
		titleColor = Color.GREEN;
		titleFont = new Font("Century Gothics", Font.PLAIN, 28);
		
		font = new Font("Arial", Font.PLAIN, 14);
		
	}
	
	@Override
	public void init() {		
	}

	@Override
	public void update() {
		bg.update();
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Street Fighter", 80, 70);
		
		g.setFont(font);
		
		for(int i = 0; i < options.length; i++)
		{
			if(i == currentChoice)
			{
				
				g.setColor(Color.WHITE);
				
			}
			else
				g.setColor(Color.RED);
			
			g.drawString(options[i], 145, 140 +i *15);
		}
		
	}

	private void select()
	{
		if(currentChoice == 0)
		{
			gsm.setState(GameStateManager.SELECTION);
			
		}
		
		if(currentChoice == 1)
		{
			//help
		}
		
		if(currentChoice == 2)
		{
			System.exit(0);
		}
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_ENTER)
		{
			select();
		}
		
		if(k.getKeyCode() == KeyEvent.VK_UP)
		{
			currentChoice --;
			
			if(currentChoice == -1)
				currentChoice = options.length - 1;
			
		}
		
		if(k.getKeyCode() == KeyEvent.VK_DOWN)
		{
			currentChoice ++;
			
			if(currentChoice == options.length)
				currentChoice = 0;			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
		
	}

}
