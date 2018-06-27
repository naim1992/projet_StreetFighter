package main;



import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import services.Engine;





public class Background {

	
	private BufferedImage image;
	
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	private Engine engine;
	
	private double moveScale;
	
	public Background(String s, double msg, Engine engine) {
		
		try {
			this.engine = engine;
			image  = ImageIO.read(getClass().getResource(s));
			
			moveScale = msg;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y)
	{
		this.x = (x * moveScale) % engine.getWidth();
		this.y = (y * moveScale) % engine.getHeight() ;
	}
	
	public void setVector(double dx, double dy)
	{
		
		this.dx = dx;
		this.dy = dy;
	}
	
	
	public void update()
	{
		
		x += dx;
		y += dy;
		
	}
	
	
	public void draw(Graphics2D g)
	{
		
		
		
		g.drawImage(image, (int) x, (int) y, null);
		
		if(x < 0)
		{
			g.drawImage(image ,
					(int) x + engine.getWidth(),
					(int) y,
					null);
			
		}
		
		if(x > 0)
		{
			g.drawImage(image ,
					(int) x - engine.getWidth(),
					(int) y,
					null);
			
		}
		
	}
	
	
	
	
}
