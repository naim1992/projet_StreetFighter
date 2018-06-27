package animation;


import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

import contract.FightCharContract;
import services.FightChar;



public class Ryu extends FightCharContract{
	

	private final int[]numFrames = {4,4,7,7,1,1,1,3,3,7,5,3,5,3,5,5,3,5,3,3,5,3,2,3,2,3,7,9,5,5,7,4,8,5};	
	
	private final int width = 110;
	private final int height = 110;
			
	public Ryu(FightChar p) {
	
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

		getActions().put("FPUNCH", 8);
		getActions().put("FMPUNCH", 9);
		getActions().put("FHPUNCH", 10);
		
		getActions().put("LKICK", 11);
		getActions().put("HKICK", 12);
		getActions().put("FLKICK", 13);
		getActions().put("FMKICK", 14);
		getActions().put("FHKICK", 15);
		
		getActions().put("CROUCH_LOW_PUNCH", 16);
		getActions().put("CROUCH_H_PUNCH", 17);
		getActions().put("CROUCH_L_KICK", 18);
		getActions().put("CROUCH_M_KICK", 19);
		getActions().put("CROUCH_H_KICK", 20);
		
		getActions().put("JUMP_L_PUNCH", 21);
		getActions().put("JUMP_LM_KICK", 22);
		getActions().put("JUMP_H_KICK", 23);
		getActions().put("FJL_PUNCH", 24);
		getActions().put("FORWARD_JUMP_KICK", 25);
	
		getActions().put("SHOURYUKEN", 26);
		getActions().put("TATSUMAKI_SENPU_KYAKU", 27);
		getActions().put("HADUKEN", 28);
		getActions().put("SOULDER TOSS", 29);
		getActions().put("BACKROLL", 30);
		
		getActions().put("HIT", 31);
		getActions().put("KNOCKDOWN_RECOVER", 32);
		getActions().put("KO", 33);
		
		
			
		
		
		// load sprites
		try {
			BufferedImage spritSheet = ImageIO.read(getClass().getResource(
					"/Sprites/Player/SNES - Super Street Fighter 2 - Ryu.gif"));
			
			
			
			for (int i = 0; i < numFrames.length ; i++)
			{
				BufferedImage[]  bi = new BufferedImage[numFrames[i]];
				
				
					for(int j =0; j < numFrames[i] ; j++)
					{	
						/**
						if(i == getActions().get("HADUKEN") || i == getActions().get("HIT"))
							bi[j] = spritSheet.getSubimage(j * width + 10,
									i * (height - 5) + 40  , width - 20 , height );
						else */
							bi[j] = spritSheet.getSubimage(j * width + 10,
									i * height  , width - 20 , height);
							
							
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
