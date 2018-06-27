package decorator;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import animation.Animation;
import services.Engine;
import services.FightChar;
import services.HitBox;
import services.Tech;

public abstract class FightCharDeco extends PlayerDec implements FightChar{

	
	
	
	public FightCharDeco(FightChar d) {
		super(d);
	}

	@Override
	public int getId() {
		
		return getDelegate().getId();
	}

	@Override
	public int getWidth() {
		
		return getDelegate().getWidth();
	}

	@Override
	public int getHeight() {
		
		return getDelegate().getHeight();
	}

	@Override
	public double getPositionX() {
		
		return getDelegate().getPositionX();
	}

	@Override
	public double getPositionY() {
		
		return getDelegate().getPositionY();
	}

	@Override
	public Engine getEngine() {
		
		return getDelegate().getEngine();
	}

	@Override
	public HitBox getHitBox() {
		
		return getDelegate().getHitBox();
	}

	@Override
	public int getLife() {
		
		return getDelegate().getLife();
	}

	@Override
	public int getSpeed() {
		
		return getDelegate().getSpeed();
	}

	@Override
	public boolean isFaceRight() {
		
		return getDelegate().isFaceRight();
	}

	@Override
	public boolean isDead() {
		
		return getDelegate().isDead();
	}

	@Override
	public void init(int id, int l, int s, boolean f, Engine e) {
		
		getDelegate().init(id, l, s, f, e);
	}

	@Override
	public void moveLeft() {
		
		getDelegate().moveLeft();
	}

	@Override
	public void moveRight() {
		getDelegate().moveRight();
		
	}

	@Override
	public void switchSide() {
		getDelegate().switchSide();
		
	}

	@Override
	public void step(int commande) {
		
		getDelegate().step(commande);
	}
	
	@Override
	public void endStep(int commande) {
		
		getDelegate().step(commande);
	}
	

	@Override
	public void setPositionX(double x) {
		
		getDelegate().setPositionX(x);
	}

	@Override
	public void setPositionY(double Y) {
		
		getDelegate().setPositionY(Y);
	}

	@Override
	public void setLife(int l) {
		
		getDelegate().setLife(l);
	}

	

	@Override
	public void setFaceRight(boolean b) {
		
		getDelegate().setFaceRight(b);
	}

	@Override
	public boolean isBlocking() {
		
		return ((FightChar) getDelegate()).isBlocking();
	}

	@Override
	public boolean isBlockStunned() {
		
		return ((FightChar) getDelegate()).isBlockStunned();
	}

	@Override
	public boolean isHitsStunned() {
		
		return ((FightChar) getDelegate()).isHitsStunned();
	}

	@Override
	public boolean isTechning() {
		
		return ((FightChar) getDelegate()).isTechning();
	}

	@Override
	public Tech isTech() {
		
		return ((FightChar) getDelegate()).isTech();
	}

	@Override
	public boolean tech() {
		
		return ((FightChar) getDelegate()).tech();
	}

	@Override
	public boolean techHasAlreadyHit() {
		
		return ((FightChar) getDelegate()).techHasAlreadyHit();
	}

	@Override
	public void startTech(Tech t) {
		
		((FightChar) getDelegate()).startTech(t);
	}

	@Override
	public boolean stopFalling() {
		
		return getDelegate().stopFalling();
	}

	@Override
	public int getFire() {
		
		return ((FightChar) getDelegate()).getFire();
	}

	@Override
	public int getMaxFire() {
		
		return ((FightChar) getDelegate()).getMaxFire();
	}




	@Override
	public Animation getAnimation() {
		// TODO Auto-generated method stub
		return ((FightChar) getDelegate()).getAnimation();
	}

	@Override
	public List<BufferedImage[]> getSprites() {
		// TODO Auto-generated method stub
		return ((FightChar) getDelegate()).getSprites();
	}

	
	@Override
	public void setCurrentAction(int i) {
		// TODO Auto-generated method stub
		((FightChar) getDelegate()).setCurrentAction(i);
	}

	@Override
	public void setAnimation(Animation a) {
		// TODO Auto-generated method stub
		((FightChar) getDelegate()).setAnimation(a);
	}

	@Override
	public void update() {
		((FightChar) getDelegate()).update();
		
	}

	@Override
	public void draw(Graphics2D g) {
		((FightChar) getDelegate()).draw(g);
		
	}

	@Override
	public void setTech(boolean b) {
		((FightChar) getDelegate()).setTech(b);
		
	}

	@Override
	public void setTechning(boolean b) {
		((FightChar) getDelegate()).setTechning(b);
		
	}

	@Override
	public void setBlockingStunned() {
		((FightChar) getDelegate()).setBlockingStunned();
	}

	@Override
	public Map<String, Integer> getActions() {
		// TODO Auto-generated method stub
		return ((FightChar) getDelegate()).getActions();
	}

	
	
}
