package attacks.special;


import impl.HitBoxImpl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import services.FightChar;
import services.HitBox;
import services.Player;
import animation.Animation;
import attacks.Attack;

public class FireBall extends Attack{


		private boolean hit;
		private boolean remove;
		private BufferedImage[] sprites;
		private BufferedImage[] hitsprites;
		private Animation animation;
		private HitBox hb;
		private Player pl;
		
		
		private double moveSpeed;
		private int x;
		private int y;
		private double dx;
		
		private int width;
		private int height;
		
		private boolean facingRight;
		
		
		
		
		public FireBall(FightChar p) {
			
			super(p);
			
			this.facingRight = p.isFaceRight();
			this.pl = p;
			
			
			moveSpeed = 3.8;
			if(facingRight) 
				dx = moveSpeed;
			else
				dx = -moveSpeed;
			
			width = 30;
			height = 30;
			
			hb = new HitBoxImpl();
			hb.init(x, y, width, height);
			
			
			// load sprits
			
			try {
				BufferedImage spriteSheet = ImageIO.read(
						getClass().getResourceAsStream("/Sprites/Player/fire.png"));
			
			
				sprites = new BufferedImage[2];
				for (int i = 0; i < sprites.length; i++)
				{
					sprites[i] = spriteSheet.getSubimage(
							i * width, 
							0, width, height);
					
				}
			
				
				hitsprites = new BufferedImage[3];
				for (int i = 0; i < hitsprites.length; i++)
				{
					hitsprites[i] = spriteSheet.getSubimage(
							i * width, 
							height, width, height);
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(70);
		
		}

		
		public void setHit(int i)
		{
			
			if (hit) return;
			hit = true;
			
			
			animation.setFrames(hitsprites);
			int l = getPlayer().getEngine().getPlayer(i).getLife();
			if(! ((FightChar) getPlayer().getEngine().getPlayer(i)).isBlockStunned())
			{
			((FightChar) getPlayer().getEngine().getPlayer(i)).setTechning(true);
			
			if(!((FightChar) getPlayer().getEngine().getPlayer(i)).techHasAlreadyHit())
				getPlayer().getEngine().getPlayer(i).setLife(l - getDammage());
			}
			
			
			System.out.println(getPlayer().getEngine().getPlayer(i).getLife() );
			
			
			dx = 0;
		}
		
		public boolean shouldRemove(){return remove;}
		
		public void update()
		{
			
			
			int i = ((pl.getId() == 2) ? 1 : 2);
			
			
			setPosition((int) ( x + dx ), y); 
			
			
			
			
			if( this.hb.collidesWith(pl.getEngine().getPlayer(i).getHitBox()))
			{
				
				this.setHit(i);
				
				
			}
				
			if(dx == 0 && !hit)
			{
				setHit(i);
			}
			
			animation.update();
			
			if(hit && animation.hasPlayedOnce())
				remove = true;
		}
		
		public void draw(Graphics2D g)
		{
			if(facingRight)
			{
				g.drawImage(
				  animation.getImage(),
				  (int) (x - width / 2 ),
				  (int) (y - height / 2),
				  
				  null);
				
				hb.setPositionX(x + width / 2);
				hb.setPositionY(y);
				
			}
			else
				{
					g.drawImage(
					  animation.getImage(),
					  (int) (x - width / 2 + width),
					  (int) (y - height / 2),
					  -width, height, null);	
					
					hb.setPositionX(x - width / 2 );
					hb.setPositionY(y);
				}
			
		}


		public int getX() {
			return x;
		}


		


		public int getY() {
			return y;
		}


		public void setPosition(int x, int y) {
			this.x = x;
			this.y = y;
		}
	

	
	
}
