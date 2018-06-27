package decorator;


import java.util.List;

import services.Engine;
import services.HitBox;
import services.Player;

public  abstract class PlayerDec implements Player{

	private Player delegate;
	
	public PlayerDec(Player delegate) {
		this.delegate = delegate;
	}
	
	
	public Player getDelegate()
	{
		return this.delegate;
	}
	
	@Override
	public int getId() {
		return delegate.getId();
	}

	@Override
	public double getPositionX() {
		
		return delegate.getPositionX();
	}

	@Override
	public double getPositionY() {
		
		return delegate.getPositionY();
	}

	@Override
	public Engine getEngine() {
		
		return delegate.getEngine();
	}

	@Override
	public HitBox getHitBox() {
		
		return delegate.getHitBox();
	}

	@Override
	public int getLife() {
		
		return delegate.getLife();
	}

	@Override
	public int getSpeed() {
		
		return delegate.getSpeed();
	}

	@Override
	public boolean isFaceRight() {
		
		return delegate.isFaceRight();
	}

	@Override
	public boolean isDead() {
		
		return delegate.isDead();
	}

	@Override
	public void init(int id, int l, int s, boolean f, Engine e) {
		
		delegate.init(id, l, s, f, e);
	}

	@Override
	public void moveLeft() {
		
		delegate.moveLeft();
	}

	@Override
	public void moveRight() {
		
		delegate.moveRight();
	}

	@Override
	public void switchSide() {
		
		delegate.switchSide();
	}

	@Override
	public void step(int commande) {
		
		delegate.step(commande);
	}
	@Override
	public void setPositionX(double x) {
		delegate.setPositionX(x);
		
	}
	@Override
	public void setPositionY(double Y) {
		delegate.setPositionY(Y);
		
	}
	@Override
	public void setLife(int l) {
		delegate.setLife(l);
		
	}
	
	@Override
	public void setFaceRight(boolean b) {
		delegate.setFaceRight(b);
		
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return delegate.getWidth();
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return delegate.getHeight();
	}
	@Override
	public boolean stopFalling() {
		// TODO Auto-generated method stub
		return delegate.stopFalling();
	}
	



	@Override
	public void initCommande(List<Integer> commandes) {
		delegate.initCommande(commandes);
		
	}


	@Override
	public List<Integer> getCommands() {
		// TODO Auto-generated method stub
		return delegate.getCommands();
	}


	@Override
	public void endStep(int c) {}

	@Override
	public boolean getLeft() {
		// TODO Auto-generated method stub
		return delegate.getLeft();
	}


	@Override
	public boolean getRight() {
		// TODO Auto-generated method stub
		return delegate.getRight();
	}
	
	


	
}
