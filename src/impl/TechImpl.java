package impl;

import services.FightChar;
import services.HitBox;
import services.Player;
import services.Tech;

public class TechImpl implements Tech{

	private  int dammage ;
	private  int hstun ;
	private final int bstun = 12;
	private final int sframe = 3;
	private final int hframe = 2;
	private final int rframe = 7;
	private HitBox hb;
	
	private Player player;
	
	public TechImpl(FightChar p) {
		this.setPlayer(p);
		
		this.hb = new HitBoxImpl();
	}
	
	
	@Override
	public int getDammage() {return this.dammage;}
	@Override
	public int getHStun() {	return this.hstun;}
	@Override
	public int getBStun() {	return this.bstun;}
	@Override
	public int getSFrame() { return this.sframe ;}
	@Override
	public int getHFrame() { return this.hframe; }
	@Override
	public int getRFrame() { return this.rframe; }
	@Override
	public HitBox getHitBox() { return this.hb; }
	
	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setDammage(int d)
	{
		this.dammage = d;
	}
	
	
	
	
}