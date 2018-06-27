package animation;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import services.FightChar;
import contract.FightCharContract;



public class Byson extends FightCharContract {

	
	
private final int[]numFrames = {3,4,5,1,4,6,2,4,4,1,2,6,2,4,2,3,1,2,7,4,8,3,3,7,7,5,12,1,5,4};	
	
	
	private final int width = 110;
	private final int height = 110;
		
		
	
			
	public Byson(FightChar p) {
	
		super(p);
		
		// animation actions;
		 
		getActions().put("IDLE", 0);
		getActions().put("WALK", 1);
		getActions().put("JUMPING", 2);
		
		getActions().put("CROUCH", 3);
		
		getActions().put("LPUNCH", 4);
		getActions().put("MPUNCH", 5);
		getActions().put("FHPUNCH", 6);
		
		
		
		getActions().put("LKICK", 7);
		getActions().put("FLKICK", 8);
		
		getActions().put("BLOCKING", 9);
		getActions().put("BLOCKING_DOWN", 10);
		
		
		getActions().put("JUMP_L_PUNCH", 11);
		getActions().put("JUMP_H_PUNCH", 12);
		getActions().put("FJL_PUNCH", 13);
		getActions().put("FJH_PUNCH", 14);
		
		
		getActions().put("JUMP_LM_KICK", 15);
		getActions().put("JUMP_H_KICK", 16);
		getActions().put("FORWARD_JUMP_KICK", 17);
		getActions().put("HEAD_STOMP", 18);
		
		
		getActions().put("CROUCH_LOW_PUNCH", 19);
		getActions().put("CROUCH_M_PUNCH", 20);
		
		getActions().put("CROUCH_L_KICK", 21);
		getActions().put("CROUCH_H_KICK", 22);
		getActions().put("CROUCH_M_KICK", 23);
		
		getActions().put("TATSUMAKI_SENPU_KYAKU", 24);
		getActions().put("SHOURYUKEN", 25);
		getActions().put("SOULDER TOSS", 26);
		
		
		getActions().put("HIT", 27);
		getActions().put("KNOCKDOWN_RECOVER", 28);
		getActions().put("KO", 29);

		// load sprites
		try {
			BufferedImage spritSheet = ImageIO.read(getClass().getResource(
					"/Sprites/Player/SNES - Super Street Fighter 2 - M Bison.gif"));
			
			
			
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
