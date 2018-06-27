package animation;




import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import contract.FightCharContract;
import services.FightChar;



public class Ken extends FightCharContract {

	
	

	private final int[]numFrames = {4,5,7,7,1,1,1,3,5,3,7,5,3,5,3,5,5,3,4,5,3,3,5,3,2,3,2,3,4,9,6,5,11,2,8,8};	
	
	
	private final int width = 110;
	private final int height = 110;
		
		
	
			
	public Ken(FightChar p) {
	
		super(p);
		
		// animation actions;
		 
		getActions().put("IDLE", 0);
		getActions().put("WALK", 1);
		getActions().put("JUMPING", 2);
		getActions().put("FJUMP", 3);
		getActions().put("CROUCH", 4);
		
		getActions().put("BLOCKING", 5);
		getActions().put("BLOCKING_DOWN", 6);
		
		getActions().put("LPUNCH", 7);
		getActions().put("MPUNCH", 8);
		getActions().put("FPUNCH", 9);
		getActions().put("FMPUNCH", 10);
		getActions().put("FHPUNCH", 11);
		
		getActions().put("LKICK", 12);
		getActions().put("HKICK", 13);
		getActions().put("FLKICK", 14);
		getActions().put("FMKICK", 15);
		getActions().put("FHKICK", 16);
		
		getActions().put("CROUCH_LOW_PUNCH", 17);
		getActions().put("CROUCH_M_PUNCH", 18);
		getActions().put("CROUCH_H_PUNCH", 19);
		
		getActions().put("CROUCH_L_KICK", 20);
		getActions().put("CROUCH_M_KICK", 21);
		getActions().put("CROUCH_H_KICK", 22);
		
		getActions().put("JUMP_L_PUNCH", 23);
		getActions().put("JUMP_LM_KICK", 24);
		getActions().put("JUMP_H_KICK", 25);
		getActions().put("FJL_PUNCH", 26);
		getActions().put("FORWARD_JUMP_KICK", 27);
		
		getActions().put("HADUKEN", 28);
		getActions().put("TATSUMAKI_SENPU_KYAKU", 29);
		getActions().put("SHOURYUKEN", 30);
		
		
		
		getActions().put("SOULDER TOSS", 31);
		getActions().put("BACKROLL", 32);
		getActions().put("HIT", 33);
		getActions().put("KNOCKDOWN_RECOVER", 34);
		getActions().put("KO", 35);

		// load sprites
		try {
			BufferedImage spritSheet = ImageIO.read(getClass().getResource(
					"/Sprites/Player/SNES - Super Street Fighter 2 - Ken.gif"));
			
			
			
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
