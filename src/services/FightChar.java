package services;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import animation.Animation;

public interface FightChar extends /*raffine*/ Player{

	// observators
	public boolean isBlocking();
	public boolean isBlockStunned();
	public boolean isHitsStunned();
	public boolean isTechning();
	public boolean isFalling();
	public boolean isShouryuken();
	public boolean isJumping();
	
	// \pre: isTeching()
	public Tech isTech();
	
	// \pre: isTeching()
	public boolean tech();
	
	// \pre: isTeching();
	public boolean techHasAlreadyHit();
	
	/******************************************************************************/
	// \pre: isTeching();
	public int getFire();
	// \pre: isTeching();
	public int getMaxFire();
		
	public Animation getAnimation();
	public List<BufferedImage[]> getSprites();
	public Map<String, Integer> getActions();
	
	
	
	/********************************************************************************/
	
	
	// operators
	// \pre: !isTeching
	public void startTech(Tech t);
	// \pre: !isTeching
	public void setTech(boolean b);
	// \pre: !isTeching
	public void setTechning(boolean b);
	/***************************************/

	public void setCurrentAction(int i);
	//[setCurrentAction]:
	//\post: getCurrentAction() = i
	
	public void setAnimation(Animation a);
	//[setAnimation]
	//\post : getAnimation() = a
	
	// \pre: isFalling() 
	public void falling();

	
	// \pre: isJumping
	public void jumping();
	// \post : getPositionY() != getPositionY()@pre
	
	// \pre: isShouryouken()
	public void shouryuken();
	// \post : getPositionX() != getPositionX()@pre
	
	
	public void update();
	public void draw(Graphics2D g);
	public void setBlockingStunned();

	/**************************************/
	
	
	//Observation:
	// \inv: !isDead()
	// \inv: isBlockingStunned \==> getLife() = getLife()@pre
	
	
	
	//[startTech]:
	// \post: getPositionX() = getPositionX()@pre 
	// \post: getPositionY() = getPositionY()@pre
	// \post: isFacingRight() = isFacingRight@pre
	// \post: isBlocking() = true
	// \post: Tech::getRFrame() \==> !isBlocking()
	
	
	
	
}
