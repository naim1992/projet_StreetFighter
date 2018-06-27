package animation;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import services.FightChar;
import contract.FightCharContract;

public class Blanka extends FightCharContract {

private final int[]numFrames = {4,5,4,1,5,3,5,5,5,1,3,6,5,3,3,3,3,3,3,8,16,6,5,9,2,5};	
	
	
	private final int width = 110;
	private final int height = 110;
		
		
	
			
	public Blanka(FightChar p) {
	
		super(p);
		
		// animation actions;
		 
		getActions().put("IDLE", 0);
		getActions().put("WALK", 1);
		getActions().put("JUMPING", 2);
		getActions().put("CROUCH", 3);
		
		getActions().put("LPUNCH", 4);
		getActions().put("MPUNCH", 5);
		getActions().put("FHPUNCH", 6);
				
		getActions().put("BLOCKING", 7);
		
		getActions().put("LKICK", 8);
		getActions().put("HKICK", 9);
		
		getActions().put("CROUCH_LOW_PUNCH", 10);
		getActions().put("CROUCH_M_PUNCH", 11);
		
		getActions().put("CROUCH_L_KICK", 12);
		getActions().put("CROUCH_H_KICK", 13);
		
		getActions().put("JUMP_L_PUNCH", 14);
		getActions().put("JUMP_H_PUNCH", 15);
		
		getActions().put("JUMP_LM_KICK", 16);
		getActions().put("JUMP_H_KICK", 17);
		
		getActions().put("ELECTRIC_SHOCK", 18);
		getActions().put("ROLLING_ATTACK", 19);
		
		getActions().put("VERTICAL_ROLLING_ATTACK", 20);
		getActions().put("BEAST_LEAP", 21);
		getActions().put("HEAD_BIT_CRASH", 22);
		
		
		getActions().put("HIT", 23);
		getActions().put("KNOCKDOWN_RECOVER", 24);
		getActions().put("KO", 25);

		// load sprites
		try {
			BufferedImage spritSheet = ImageIO.read(getClass().getResource(
					"/Sprites/Player/SNES - Super Street Fighter 2 - Blanka.gif"));
			
			
			
			for (int i = 0; i < numFrames.length ; i++)
			{
				BufferedImage[]  bi = new BufferedImage[numFrames[i]];
				
				
					for(int j =0; j < numFrames[i] ; j++)
					{
						
							
							bi[j] = spritSheet.getSubimage(j * width,
									i * height  , width  , height);
									
						
					}
				
									getSprites().add(bi);
									
									
				
					
					
					
					
					
				}
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		setAnimation(new Animation());
		setCurrentAction(getActions().get("IDLE"));
		getAnimation().setFrames(getSprites().get(getActions().get("IDLE")));
		getAnimation().setDelay(400);
	}
	
	
	
}
