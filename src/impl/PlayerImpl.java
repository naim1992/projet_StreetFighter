package impl;



import java.awt.event.KeyEvent;







import java.util.List;

import contract.HitBoxContract;
import services.Engine;
import services.HitBox;
import services.Player;




public class PlayerImpl implements Player{

	private int id;
	private double x;

	private double y;
	private int life;
	private int speed;

	
	private int width;
	private int height;
	private List<Integer> commands;
	
	private Engine e;
	private HitBox hb;
	
	private boolean faceRight;

	
	private boolean left;
	private boolean right;
	
	@Override
	public boolean getLeft(){return this.left;}
	@Override
	public boolean getRight(){return this.right;}
	@Override
	public int getId() { return this.id;}
	@Override
	public double getPositionX() {return this.x; }
	@Override
	public double getPositionY() {return this.y;}
	@Override
	public void setPositionX(double x) {
		this.x = x; 
		
		if(this.hb == null)
		{
			this.hb = new HitBoxImpl();
		}
		this.hb.moveTo(this.x, y);
		}
	@Override
	public void setPositionY(double y) {this.y = y; this.hb.moveTo(x, this.y); }
	@Override
	public void setLife(int life) {	this.life = life;}
	
	@Override
	public Engine getEngine() {return this.e;}
	@Override
	public HitBox getHitBox() { return this.hb;}
	@Override
	public int getLife() {return this.life;}
	@Override
	public int getSpeed() {return this.speed;}
	@Override
	public boolean isFaceRight() { return this.faceRight;}
	@Override
	public boolean isDead() { 
		if(life > 0) 
			return false; 
		else 
			return true;
	}
	
	public void setHitBox(HitBox hb){this.hb = hb;}

	
	/****************************************************/
	
	
	@Override
	public List<Integer> getCommands(){return this.commands;}
	
	
	
	/****************************************************/
	
	
	
	@Override
	public void init(int id, int l, int s, boolean f, Engine e) {
		
		this.id = id;
		// \post : getLife() = l
		this.life = l;
		// \post : getSpeed() = s
		this.speed = s;
		// \post : isFaceRight() = f
		this.faceRight = f;
		// \post : getEngine() = e
		this.e = e;
		// \post : \exist h \is HitBox {getCharBox() = h}
		
		
		// additiona work
		
			this.width = 70; 
			this.height = 70;
		
		
		
		HitBox h = new HitBoxImpl();
		this.hb = new HitBoxContract(h, this.e);
		hb.init(this.x, this.y, this.width / 2, this.height / 2);
		
		
				
	}

	@Override
	public void moveLeft() {
		
		int i = ((id == 2) ? 1 : 2);
		
		if(isFaceRight())
			switchSide();
		
		if( this.getHitBox().collidesWith(getEngine().getPlayer(i).getHitBox()))
		{
			
			this.x = this.getPositionX() + 5;
		}
		
		if((this.getPositionX() < this.getSpeed()) && !this.getHitBox().collidesWith(this.getEngine().getPlayer(i).getHitBox()))
		{
			this.x = this.getPositionX() + this.getSpeed();
		}
	
		if((this.getPositionX() >= this.getSpeed()) && !this.getHitBox().collidesWith(this.getEngine().getPlayer(i).getHitBox()))
		{
			this.x = this.getPositionX() - this.getSpeed();
		}
		
		this.hb.setPositionX((int) x );
		
		if((this.hb.getPositionX() - this.width / 2) < 0)
		{
			this.x = this.width / 2;
		}
		
		this.faceRight = isFaceRight();
		this.life = getLife();
		this.y = getPositionY();
		
		
	}

	@Override
	public void moveRight() {
		
		
		
		int i = ((id == 2) ? 1 : 2);
		if(!isFaceRight())
			switchSide();
		

		if( this.getHitBox().collidesWith(getEngine().getPlayer(i).getHitBox()))
		{
			
			this.x = this.getPositionX() - 5;
		}
		
		if((this.getPositionX() >= this.getSpeed()) && !this.getHitBox().collidesWith(this.getEngine().getPlayer(i).getHitBox()))
		{
			this.x = this.getPositionX() + this.getSpeed();
			
		}
		
		if((this.getPositionX() < this.getSpeed()) && !this.getHitBox().collidesWith(this.getEngine().getPlayer(i).getHitBox()))
		{
			this.x = this.getPositionX() - this.getSpeed();
		}
		
		
		this.hb.setPositionX((int) x );
		
		if((this.hb.getPositionX() + this.width / 2) > this.e.getWidth() / this.e.getScale())
		{
			this.x = this.e.getWidth() / this.e.getScale() - this.width / 2;
		}
	
		
		this.faceRight = this.isFaceRight();
				
		this.life = getLife();
		
		this.y = getPositionY();
	}

	@Override
	public void switchSide() {
		
		this.faceRight = !isFaceRight();
		this.x = getPositionX();
	}

	@Override
	public void step(int commande) {
		
		
		// \post : Command = LEFT \==> moveLeft()
			if(commands.contains(commande) && (commande == KeyEvent.VK_LEFT || commande == KeyEvent.VK_G) )
			{
				this.left = true;
			//	this.moveLeft();
			}
			else
		// \post : Command = RIGHT \==> moveRight()
			if(commands.contains(commande) && (commande == KeyEvent.VK_RIGHT || commande == KeyEvent.VK_D))
			{
				this.right = true;
			//	this.moveRight();
			}
		// \post : Command = \nothing \doNothing
		
	}
	
	
	
	@Override
	public void endStep(int commande) 
	{
		
					if(commands.contains(commande) && (commande == KeyEvent.VK_LEFT || commande == KeyEvent.VK_G) )
					{
						this.left = false;
						
					}
					else
					if(commands.contains(commande) && (commande == KeyEvent.VK_RIGHT || commande == KeyEvent.VK_D))
					{
						this.right = false;
						
					
					}
	}
	
	
	
	@Override
	public boolean stopFalling()
	{
		this.hb.setPositionY(this.y + this.height / 2);
		
		if(this.hb.getPositionY() >= this.e.getHeight() / this.e.getScale())
		{
			this.setPositionY(this.e.getHeight() / this.e.getScale() - this.height / 2);
			
			return true;
		}
		return false;
		
	}
	
	
	@Override
	public void setFaceRight(boolean b) {
		this.faceRight = b;
		
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
/************************ additiona work *********************************************/	
	

	@Override
	public void initCommande(List<Integer> commandes) {
		this.commands = commandes;
		
	}

	
	

}
