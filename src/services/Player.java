package services;


import java.util.List;



public interface Player {

	//observator:
	
	public int getId();
	public int getWidth();
	public int getHeight();
	public double getPositionX();
	public double getPositionY();
	public Engine getEngine();
	public HitBox getHitBox();
	public int getLife();
	public int getSpeed();
	public boolean isFaceRight();
	public boolean isDead();
	
	
	/***************************** additional observators **********************************/
	public List<Integer> getCommands();
	public boolean stopFalling();
	public boolean getLeft();
	public boolean getRight();
	
	/********************************************************************************/
	
	
	// constructor
	// \pre: l > 0 \and s > 0
	public void init(int id, int l, int s, boolean f, Engine e);
	
	
	//operators:
	public void moveLeft();
	public void moveRight();
	
	/************************************************************/
	
	public void initCommande(List<Integer> commandes);
	
	/**********************************************************/
	
	public void switchSide();
	
	// \pre: !isDead()
	public void step(int c1);
	// \pre: !isDead()
	public void endStep(int c1);
	
	
	public void setPositionX(double x);
	
	public void setPositionY(double Y);
	
	
	public void setLife(int l);

	public void setFaceRight(boolean b);
	
	
	
	
	
	//observation:
	// \inv : getPositionX() > 0 \and getPositionX() < getEngine().getWidth()
	// \inv : getPositionY() > 0 \and getPositionY() < getEngine().getHeight()
	// \inv : isDead() = !(getLife() > 0)
	
	//[init]:
	// \post : getLife() = l
	// \post : getSpeed() = s
	// \post : isFaceRight() = f
	// \post : getEngine() = e
	// \post : \exist h \is HitBox {getCharBox() = h} 
	
	//[moveLeft]:
	// \post: \exist i in [1,2] { getEngine().getPlayer(i) != this \and collisionWith(getHitBox(), getEngine().getPlayer(i).getHitBox()) } \==> getPositionX() = getPostionX()@pre
	// \post: getPositionX() <= getSpeed() \and \ForAll i in [1,2] { getEngine().getPlayer(i) != this \==> !collisionWith(getHitBox(), getEngine().getPlayer(i).getHitBox())} \==> getPositionX() = getPostionX()@pre - getSpeed()
	// \post: getPositionX() > getSpeed() \and \ForAll i in [1,2] { getEngine().getPlayer(i) != this \==> !collisionWith(getHitBox(), getEngine().getPlayer(i).getHitBox())} \==> getPositionX() = 0
	// \post: isFacingRight() = false
	// \post: getLife() = getLife()@pre
	// \post: getPositionY()  = getPositionY()@pre
	
	
	//[moveRight]:
	// \post: \exist i in [1,2] { getEngine().getPlayer(i) != this \and collisionWith(getHitBox(), getEngine().getPlayer(i).getHitBox()) } \==> getPositionX() = getPostionX()@pre
	// \post: getPositionX() >= getSpeed() \and \ForAll i in [1,2] { getEngine().getPlayer(i) != this \==> !collisionWith(getHitBox(), getEngine().getPlayer(i).getHitBox())} \==> getPositionX() = getPostionX()@pre + getSpeed()
	// \post: getPositionX() < getSpeed() \and \ForAll i in [1,2] { getEngine().getPlayer(i) != this \==> !collisionWith(getHitBox(), getEngine().getPlayer(i).getHitBox())} \==> getPositionX() = 0
	// \post: isFacingRight() = true
	// \post: getLife() = getLife()@pre
	// \post: getPositionY()  = getPositionY()@pre
		
	//[switchSide]:
	 // \post: isFaceRight() != isFaceRight()@pre 
	 // \post : getPositionX() = getPositionX()@pre

	
	//[step]:
	// \post : Command = LEFT \==> moveLeft()
	// \post : Command = RIGHT \==> moveRight()
	// \post : Command = \nothing \doNothing
	
	// [setPositionX(x)]:
	// \post : getPositionX() = getPositionX()@pre + x
	
	// [setPositionY(y)]:
	// \post : getPositionY() = getPositionY()@pre + y
	
	// [setLife()]:
		// \post : getLife() = getLife()@pre - l;
	
	//[setDead()]:
		// \post: isDead() = !isDead()@pre
	
	//[setFaceRight]
		// \post: isFaceRight() != isFaceRight()@pre
	
	

}


