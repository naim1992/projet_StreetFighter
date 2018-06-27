package impl;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;



import java.util.Set;

import animation.Animation;
import attacks.crouch.CrouchHKick;
import attacks.crouch.CrouchLKick;
import attacks.crouch.CrouchMKick;
import attacks.kick.FHkick;
import attacks.kick.FLkick;
import attacks.kick.FMkick;
import attacks.kick.Hkick;
import attacks.kick.Lkick;
import attacks.punch.FHPunch;
import attacks.punch.FMPunch;
import attacks.punch.LowPunch;
import attacks.punch.MPunch;
import attacks.special.BeastLeap;
import attacks.special.ElectricShock;
import attacks.special.FireBall;
import attacks.special.HeadStomp;
import attacks.special.HitBeatCrash;
import attacks.special.RollingAttack;
import attacks.special.Shouryuken;
import attacks.special.SoulderToss;
import attacks.special.TatsumakiSenpuKyaku;
import attacks.special.VerticalRollingAttack;
import services.Engine;
import services.FightChar;
import services.HitBox;
import services.Tech;

public class FightCharImpl extends PlayerImpl implements FightChar{

	
	public static Set<Integer> pressed = new HashSet<Integer>();
	
	private boolean blocking;
	private boolean blockStunned;
	private boolean hitStunned;
	private boolean Teching;
	private boolean tech;
	private boolean techHit;
	private Map<String, Integer> actions = new HashMap<String, Integer>();
	
	
	
	/************************ pour les directions ***********************************************/
	
	private Tech tch;
	
	// les vecteur
	
	private double dy;
	
	

		
		
	private Animation animation;
	private int currentAction;	
	
	
	private boolean left;
	private boolean right;
	//fireBall
	private int fire = 2500;
	private final int maxFire = 2500;
	private boolean firing;
	private final int fireCost = 200;

	private ArrayList<FireBall> fireBalls = new ArrayList<FireBall>();
	
	private boolean fliching;
	private long flinchTime;
	
	
	// scratch;
	private boolean punching;	
	// gliding
	private boolean down;
	private boolean jumping;
	private boolean falling;
	private boolean kicking;
	private boolean heightKiking;
	private boolean iscombo;
	
	private boolean fjumping;
	private boolean bloocking_down;
	
	private boolean m_punch;
	private boolean fpunch;
	private boolean fmpunch;
	private boolean fhpunch;
	
	private boolean flkick;
	private boolean fmkick;
	private boolean fhkick;
	
	
	
	private boolean crouch_l_kick;
	private boolean crouch_m_kick;
	private boolean crouch_h_kick;
	private boolean jump_l_punch;
	private boolean jump_lm_kick;
	private boolean	jump_h_kick;
	private boolean fjl_punch;
	private boolean forward_jump_kick;
	private boolean shouryuken;
	private boolean tatsumaki_senpu_kyaku;
	private boolean soulder_toss;
	
	private boolean electric_shock;     
	private boolean rolling_attack;	
	private boolean vertical_rolling_attack;
	private boolean beast_leap;	
	private boolean head_bit_crash;	

	private boolean head_stomp; 
	
	public boolean ko;
	public boolean knockDown_recover;
	
	
	//movement attribute
	protected final double moveSpeed = 0.3;
	protected final double maxSpeed = 1.6;
	protected final double stopSpeed = 0.4;
	protected final double fallSpeed = 0.15;
	protected final double maxFallSpeed = 4.0;
	protected final double stopJumpSpeed = 0.3;
	protected final double jumpStart = -5.8;
	
	
	

	
	private double delay;

	private List<BufferedImage[]> sprites;

	public FightCharImpl() {
		this.animation = new Animation();
		sprites = new ArrayList<BufferedImage[]>();
	}
	
	
	
	
	
	public Animation getAnimation(){return this.animation;}
	public List<BufferedImage[]> getSprites(){return this.sprites;} 
	
	

	
	
	public void setAnimation(Animation a){this.animation = a;}
	public void setCurrentAction(int i){this.currentAction = i;}
	
	
	
	
	
	
	/*******************************************************************************************/
	
	@Override
	public int getId() {return super.getId();}

	@Override
	public int getWidth() {return super.getWidth();}

	@Override
	public int getHeight() {return super.getHeight();}

	@Override
	public double getPositionX() { return super.getPositionX(); }

	@Override
	public double getPositionY() {return super.getPositionY(); }

	@Override
	public Engine getEngine() { return super.getEngine();}

	@Override
	public HitBox getHitBox() { return super.getHitBox(); }

	@Override
	public int getLife() { return super.getLife();}

	@Override
	public int getSpeed() { return super.getSpeed(); }

	@Override
	public boolean isFaceRight() { return super.isFaceRight();}

	@Override
	public boolean isDead() {return super.isDead();	}

	@Override
	public void init(int id, int l, int s, boolean f, Engine e) { super.init(id, l, s, f, e); }

	@Override
	public void moveLeft() { super.moveLeft(); }
	@Override
	public void moveRight() { super.moveRight();}

	@Override
	public void switchSide() { super.switchSide();}

	@Override
	public void step(int commande ) 
	{	
		
		
		
		super.step(commande);
		
		
	
		/******** punching **********************/
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_P || commande == KeyEvent.VK_NUMPAD0))
		{
			fpunch = true;
		}
		
		if ( getCommands().contains(commande) && (commande == KeyEvent.VK_A || (pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_NUMPAD0)))
		{
			
			fmpunch = true;
		}
		
		if (( getCommands().contains(commande)) && (commande == KeyEvent.VK_Z ||  commande == KeyEvent.VK_NUMPAD1))
		{
			fhpunch = true;
		}
			
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_E || (pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_NUMPAD1)))
		{
			
			this.punching = true;
		}
		
			
		/*********************************** kick ********************************************/
		
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_Q || commande == KeyEvent.VK_NUMPAD2) )
		{
			this.kicking = true;
		}
		
		if ( getCommands().contains(commande) && (commande == KeyEvent.VK_S ||(pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_NUMPAD2)))
		{
			heightKiking= true;
		}
		
		if ( getCommands().contains(commande) && (commande == KeyEvent.VK_X || commande == KeyEvent.VK_NUMPAD3))
		{
			flkick = true;
		//	beast_leap = true;
		}
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_V|| (pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_NUMPAD3)))
		{
			fmkick = true;
		}
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_H || commande == KeyEvent.VK_NUMPAD4))
		{
			fhkick = true;
		}
		
		/******************* HADUKEN + TATSUMAKI_SENPU_KYAKU +  SHOURYUKEN**************************/
		
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_F || commande == KeyEvent.VK_NUMPAD5) )
		{
			this.firing = true;
		//	this.head_stomp = true;
		//	this.electric_shock = true;
		}
	
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_M || commande == KeyEvent.VK_NUMPAD6) )
		{
		 shouryuken = true;
		// head_bit_crash = true;
		 
		}
		
		
		if ( getCommands().contains(commande) && (commande == KeyEvent.VK_T || ((pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande)  && commande == KeyEvent.VK_NUMPAD6))))
		{
			
			tatsumaki_senpu_kyaku = true;
		//	vertical_rolling_attack = true;
		}
		
		/************************************************************************************************************/
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_B || commande == KeyEvent.VK_NUMPAD7) )
		{
			this.blockStunned = true;
		}
		
		/************************************************* crouch **************************************/
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_C || commande == KeyEvent.VK_DOWN) )
		{
			this.down = true;
		}
		
		if( getCommands().contains(commande) &&  (commande == KeyEvent.VK_U || (pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_DOWN)))
		{
			this.crouch_h_kick = true;
		}
		
		if ( getCommands().contains(commande) && (commande == KeyEvent.VK_J ||  (pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_NUMPAD7)))
		{
			crouch_m_kick = true;
		}
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_N || commande == KeyEvent.VK_NUMPAD8))
		{
			crouch_l_kick = true;
		}
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_I || (pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_NUMPAD8)))
		{
			rolling_attack = true;
		}
		
		
		/***********************************jumping ********************************************************************/
		
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_W || commande == KeyEvent.VK_UP) )
		{
			this.jumping = true;
		}

		if( getCommands().contains(commande) &&  (commande == KeyEvent.VK_L || commande == KeyEvent.VK_ADD))
		{
			jump_l_punch = true;
		}
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_O || commande == KeyEvent.VK_NUMPAD9))
		{
			jump_lm_kick = true;
		}
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_ALT || commande == KeyEvent.VK_SUBTRACT))
		{
			jump_h_kick = true;
		}
		
	
	}
	
	
	@Override
	public void endStep(int commande) 
	{	
		super.endStep(commande);
		
		
		/******** punching **********************/
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_P || commande == KeyEvent.VK_NUMPAD0))
		{
			fpunch = false;
		}
		
		if ( getCommands().contains(commande) && (commande == KeyEvent.VK_A || (pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_NUMPAD0)))
		{
			
			fmpunch = false;
		}
		
		if (( getCommands().contains(commande)) && (commande == KeyEvent.VK_Z ||  commande == KeyEvent.VK_NUMPAD1))
		{
			fhpunch = false;
		}
			
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_E || (pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_NUMPAD1)))
		{
			this.punching = false;
		}
		
			
		/*********************************** kick ********************************************/
		
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_Q || commande == KeyEvent.VK_NUMPAD2) )
		{
			this.kicking = false;
		}
		
		if ( getCommands().contains(commande) && (commande == KeyEvent.VK_S ||(pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_NUMPAD2)))
		{
			heightKiking= false;
		}
		
		if ( getCommands().contains(commande) && (commande == KeyEvent.VK_X || commande == KeyEvent.VK_NUMPAD3))
		{
			flkick = false;
		//	beast_leap = false;
		}
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_V|| (pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_NUMPAD3)))
		{
			fmkick = false;
		}
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_H || commande == KeyEvent.VK_NUMPAD4))
		{
			fhkick = false;
		}
		
		/******************* HADUKEN + TATSUMAKI_SENPU_KYAKU +  SHOURYUKEN**************************/
		
		
		
		
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_F || commande == KeyEvent.VK_NUMPAD5) )
		{
			this.firing = false;
	//		head_stomp = false;
	//		electric_shock = false;
		}
	
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_M || commande == KeyEvent.VK_NUMPAD6) )
		{
		 shouryuken = false;
	//	 head_bit_crash = false;
	//	 beast_leap = false;
		}
		
		
		if ( getCommands().contains(commande) && (commande == KeyEvent.VK_T || ((pressed.size() > 1 && pressed.contains(KeyEvent.CTRL_MASK) && pressed.contains(commande)  && commande == KeyEvent.VK_NUMPAD6))))
		{
			tatsumaki_senpu_kyaku = false;
			System.out.println("two key pressed");
		//	vertical_rolling_attack = false;
		}
		
		
		
		
		
		
		/************************************************************************************************************/
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_B || commande == KeyEvent.VK_NUMPAD7) )
		{
			this.blockStunned = false;
		}
		
		/************************************************* crouch **************************************/
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_C || commande == KeyEvent.VK_DOWN) )
		{
			this.down = false;
		}
		
		if( getCommands().contains(commande) &&  (commande == KeyEvent.VK_U || (pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_DOWN)))
		{
			this.crouch_h_kick = false;
		}
		
		if ( getCommands().contains(commande) && (commande == KeyEvent.VK_J ||  (pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_NUMPAD7)))
		{
			crouch_m_kick = false;
		}
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_N || commande == KeyEvent.VK_NUMPAD8))
		{
			crouch_l_kick = false;
		}
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_I || (pressed.size() > 1 && pressed.contains(17) && pressed.contains(commande) && commande == KeyEvent.VK_NUMPAD8)))
		{
			rolling_attack  = false;
		}
		
		
		
		/***********************************jumping ********************************************************************/
		
		if(getCommands().contains(commande) && (commande == KeyEvent.VK_W || commande == KeyEvent.VK_UP) )
		{
			this.jumping = false;
		}

		if( getCommands().contains(commande) &&  (commande == KeyEvent.VK_L || commande == KeyEvent.VK_ADD))
		{
			jump_l_punch = false;
		}
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_O || commande == KeyEvent.VK_NUMPAD9))
		{
			jump_lm_kick = false;
		}
		
		if (getCommands().contains(commande) && (commande == KeyEvent.VK_ALT || commande == KeyEvent.VK_SUBTRACT))
		{
			jump_h_kick = false;
		}
		
		
		
	}
	
	
	
	@Override
	public void setPositionX(double x) {super.setPositionX(x);}

	@Override
	public void setPositionY(double Y) {super.setPositionY(Y);}

	@Override
	public void setLife(int l) { super.setLife(l); }
	

	

	@Override
	public void setFaceRight(boolean b) {super.setFaceRight(b);}
	
	
	@Override
	public void setBlockingStunned() {
		
	
		if(tch == null)
			tch = new TechImpl(this);
		
		long debut = System.nanoTime();
		this.blockStunned = true;
		long end = System.nanoTime();
		delay += (end - debut) / 1000000;
		
		
		
				
		if(delay < tch.getHFrame() + tch.getSFrame() + tch.getSFrame()) return;
		blockStunned = false;
		delay = 0;
		
	}
	
	@Override
	public boolean isBlocking() { 		
		return blocking;
	}

	@Override
	public boolean isBlockStunned() { return blockStunned; }

	@Override
	public boolean isHitsStunned() { return hitStunned;}

	@Override
	public boolean isTechning() {  return Teching; }

	@Override
	public Tech isTech() {if(isTechning()) return tch; else return null;}

	@Override
	public boolean tech() {	if(isTechning()) return tech; else return !tech;}

	@Override
	public boolean techHasAlreadyHit() 	{return techHit;}

	private double cantMove;
	@Override
	public void startTech(Tech t) {
		
		blocking = true;
	
	}

	

	@Override
	public void setTech(boolean b) {
		
		blocking = true;
	}
	
	@Override
	public void setTechning(boolean b) {
		
			this.Teching = b;
			setTech(true);
	}
	
	@Override
	public void falling()
	{
		if(dy > 0 && shouryuken)
		{
			dy += fallSpeed * 0.1;
			
		}
		else
			dy += fallSpeed;
		
		setPositionY(getPositionY() + dy);
		
		
		if(dy > 0 && jumping) 
			{
			jumping = false;
			}
		
		if(dy < 0 && !jumping )
			{
			dy += stopJumpSpeed;
			setPositionY(getPositionY() + dy);
			
			}
		
		if(dy > maxFallSpeed)
			{ 
			dy = maxFallSpeed;
			setPositionY(getPositionY() + dy);
			
			}
		
		
		
		if(stopFalling())
			falling = false;
		
	
	}
	
	@Override
	public void jumping()
	{
		dy = jumpStart;
		
		setPositionY(getPositionY() + dy);
		
		falling = true;
	}
	
	
	
	@Override
	public void shouryuken()
	{
		dy += -1;
		
		setPositionY(getPositionY() + dy);
		
		if(dy < jumpStart)
		 falling = true;
	}
	
	public void tatsumaki_senpu_kyaku()
	{
		int i = (getId() == 1 ? 2 : 1);
		if(isFaceRight())
		moveRight();
		else
		moveLeft();
		
		if(this.getHitBox().collidesWith(getEngine().getPlayer(i).getHitBox()))
			tatsumaki_senpu_kyaku = false;
		
		
			
	}
	
	private void getNextPosition()
	{
		if(getLeft() )
		{
			moveLeft();
		}
		else
			if(getRight())
			{
				moveRight();
				
			}
		
		// jumping 
		if(jumping && !falling)	{jumping();	}
		// shouryuken
		if(shouryuken && !falling){	shouryuken(); }
		
		// falling
		if(falling)	{falling();	}
		
		if(tatsumaki_senpu_kyaku){tatsumaki_senpu_kyaku();}
				
	}
	
	public void update()
	{
		// update position
		
		if(blocking)
		{
			if(tch == null)
				tch = new TechImpl(this);
			
			cantMove += 1;
			if(cantMove < tch.getHFrame() + tch.getSFrame() + tch.getSFrame() * 10) return;
				
			cantMove = 0;
			blocking = false;
		}
		
		getNextPosition();
	
		//check attack has stopped
	
		if(actions.get("LPUNCH") != null && currentAction == actions.get("LPUNCH"))
		{
			if(animation.hasPlayedOnce()) punching = false;
		}
		
		
		if(actions.get("FPUNCH") != null && currentAction == actions.get("FPUNCH"))
		{
			if(animation.hasPlayedOnce()) fpunch = false;
		}
		
		if(actions.get("MPUNCH") != null &&  currentAction == actions.get("MPUNCH"))
		{
			if(animation.hasPlayedOnce()) m_punch = false;
		}
		
	
		
		
		
		if(actions.get("HADUKEN") != null &&  currentAction == actions.get("HADUKEN"))
		{
			if(animation.hasPlayedOnce()) firing = false;
		}
		
		if(actions.get("LKICK") != null && currentAction == actions.get("LKICK"))
		{
			if(animation.hasPlayedOnce()) kicking = false;
		}
		
		if(actions.get("HKICK") != null && currentAction == actions.get("HKICK"))
		{
			if(animation.hasPlayedOnce()) heightKiking = false;
		}
		
		if(actions.get("FMPUNCH") != null && currentAction == actions.get("FMPUNCH"))
		{
			if(animation.hasPlayedOnce()) iscombo = false;
		}
		
		if(actions.get("HIT") != null && currentAction == actions.get("HIT"))
		{
			if(animation.hasPlayedOnce())
				{
				Teching = false;
				techHit = false;
				}
		}
		
		
		if(actions.get("FLKICK") != null && currentAction == actions.get("FLKICK"))
		{
			if(animation.hasPlayedOnce())  flkick = false;
		}
		
		if(actions.get("FMKICK") != null && currentAction == actions.get("FMKICK"))
		{
			if(animation.hasPlayedOnce())  fmkick = false;
		}
		
		if(actions.get("CROUCH_L_KICK") != null && currentAction == actions.get("CROUCH_L_KICK"))
		{
			if(animation.hasPlayedOnce())  crouch_l_kick = false;
		}
		
		if(actions.get("CROUCH_M_KICK") != null && currentAction == actions.get("CROUCH_M_KICK"))
		{
			if(animation.hasPlayedOnce())  crouch_m_kick = false;
		}
		
		
		if(actions.get("CROUCH_H_KICK") != null && currentAction == actions.get("CROUCH_H_KICK"))
		{
			if(animation.hasPlayedOnce())  crouch_h_kick = false;
		}
		
		
		if(actions.get("JUMP_L_PUNCH") != null && currentAction == actions.get("JUMP_L_PUNCH"))
		{
			if(animation.hasPlayedOnce())  jump_l_punch = false;
		}
		
		
		if(actions.get("JUMP_LM_KICK") != null && currentAction == actions.get("JUMP_LM_KICK"))
		{
			if(animation.hasPlayedOnce())  jump_lm_kick = false;
		}
		
		
		if(actions.get("JUMP_H_KICK") != null && currentAction == actions.get("JUMP_H_KICK"))
		{
			if(animation.hasPlayedOnce())  jump_h_kick = false;
		}
		
		
		if(actions.get("TATSUMAKI_SENPU_KYAKU") != null && currentAction == actions.get("TATSUMAKI_SENPU_KYAKU"))
		{
			if(animation.hasPlayedOnce())  tatsumaki_senpu_kyaku = false;
		}
		
		
		if(actions.get("SHOURYUKEN") != null && currentAction == actions.get("SHOURYUKEN"))
		{
			if(animation.hasPlayedOnce())  shouryuken = false;
		}
		
		if(actions.get("HEAD_STOMP") != null && currentAction == actions.get("HEAD_STOMP"))
		{
			if(animation.hasPlayedOnce())  head_stomp = false;
		}
		
		if(actions.get("SOULDER TOSS") != null && currentAction == actions.get("SOULDER TOSS"))
		{
			if(animation.hasPlayedOnce())  soulder_toss = false;
		}
		
		if(actions.get("ELECTRIC_SHOCK") != null && currentAction == actions.get("ELECTRIC_SHOCK"))
		{
			if(animation.hasPlayedOnce())  electric_shock = false;
		}
		
		if(actions.get("ROLLING_ATTACK") != null && currentAction == actions.get("ROLLING_ATTACK"))
		{
			if(animation.hasPlayedOnce())  rolling_attack = false;
		}
		
		if(actions.get("VERTICAL_ROLLING_ATTACK") != null && currentAction == actions.get("VERTICAL_ROLLING_ATTACK"))
		{
			if(animation.hasPlayedOnce())  vertical_rolling_attack = false;
		}
		
		if(actions.get("BEAST_LEAP") != null && currentAction == actions.get("BEAST_LEAP"))
		{
			if(animation.hasPlayedOnce())  beast_leap = false;
		}
		
		if(actions.get("HEAD_BIT_CRASH") != null && currentAction == actions.get("HEAD_BIT_CRASH"))
		{
			if(animation.hasPlayedOnce())  head_bit_crash = false;
		}
			
		//update punch
		if(punching && actions.get("LPUNCH") != null &&  currentAction != actions.get("LPUNCH"))		 					
		{
			 
			tch = new LowPunch(this);
		    (this).startTech(tch);
		    ((LowPunch) tch).update();	
		}
		
		if(m_punch && actions.get("MPUNCH") != null && currentAction != actions.get("MPUNCH"))
		{
			tch = new MPunch(this);
			this.startTech(tch);
			((MPunch) tch).update();
			
		}
		
		if(fmpunch && actions.get("FMPUNCH") != null && currentAction != actions.get("FMPUNCH"))
		{
			tch = new FMPunch(this);
			this.startTech(tch);
			((FMPunch) tch).update();
		}
		
		if(fhpunch && actions.get("FHPUNCH") != null && currentAction != actions.get("FHPUNCH"))
		{
			tch = new FHPunch(this);
			this.startTech(tch);
			((FHPunch) tch).update();
		}
		
		// update Kick
		if(kicking && actions.get("LKICK") != null &&  currentAction != actions.get("LKICK"))
		{
			 
			tch = new Lkick(this);
		    this.startTech(tch);
		    ((Lkick) tch).update();
			
			
		}
		
		if(heightKiking && actions.get("HKICK") != null &&  currentAction != actions.get("HKICK"))
		{
			tch = new Hkick(this);
		    this.startTech(tch);
		    ((Hkick) tch).update();
		}
		
		if(flkick && actions.get("FLKICK") != null && currentAction != actions.get("FLKICK"))
		{
			tch = new FLkick(this);
		    this.startTech(tch);
		    ((FLkick) tch).update();
		}
		
		if(fmkick && actions.get("FMKICK") != null && currentAction != actions.get("FMKICK"))
		{
			tch = new FMkick(this);
		    this.startTech(tch);
		    ((FMkick) tch).update();
		}
		
		if(fhkick &&  actions.get("FHKICK") != null && currentAction != actions.get("FHKICK"))
		{
			tch = new FHkick(this);
		    this.startTech(tch);
		    ((FHkick) tch).update();
		}
		
		
		//update crouch attack
		if(crouch_l_kick && actions.get("CROUCH_L_KICK") != null && currentAction != actions.get("CROUCH_L_KICK")) 
		{
			
			tch = new CrouchLKick(this);
		    this.startTech(tch);
		    ((CrouchLKick) tch).update();
			
		}
		
		if(crouch_m_kick && actions.get("CROUCH_M_KICK") != null && currentAction != actions.get("CROUCH_M_KICK"))
		{
			tch = new CrouchMKick(this);
		    this.startTech(tch);
		    ((CrouchMKick) tch).update();
		}
		
		if(crouch_h_kick && actions.get("CROUCH_H_KICK") != null && currentAction != actions.get("CROUCH_H_KICK"))
		{
			tch = new CrouchHKick(this);
		    this.startTech(tch);
		    ((CrouchHKick) tch).update();
		}
		
		// update tastsumaki and shouryuken
		if(tatsumaki_senpu_kyaku && actions.get("TATSUMAKI_SENPU_KYAKU") != null &&currentAction != actions.get("TATSUMAKI_SENPU_KYAKU"))
		{
							 
			tch = new TatsumakiSenpuKyaku(this);
			this.startTech(tch);
			((TatsumakiSenpuKyaku) tch).update();
		
			/**
			int i = (getId() == 1 ? 2 : 1);
			((FightCharImpl) getEngine().getPlayer(i)).ko = true;
				*/	
		}
		
		if(shouryuken && actions.get("SHOURYUKEN") != null && currentAction != actions.get("SHOURYUKEN"))
		{
		 					 
			tch = new Shouryuken(this);
			this.startTech(tch);
			((Shouryuken) tch).update();
				
		}
		
		
		
		if(head_stomp && actions.get("HEAD_STOMP") != null && currentAction != actions.get("HEAD_STOMP"))
		{
			tch = new HeadStomp(this);
			(this).startTech(tch);
			((HeadStomp) tch).update();
		}
		
		if(soulder_toss && actions.get("SOULDER TOSS") != null && currentAction != actions.get("SOULDER TOSS"))
		{
			
			tch = new SoulderToss(this);
			(this).startTech(tch);
			((SoulderToss) tch).update();
	
		}
		
		if(electric_shock && actions.get("ELECTRIC_SHOCK") != null && currentAction != actions.get("ELECTRIC_SHOCK"))
		{
			
			tch = new ElectricShock(this);
			(this).startTech(tch);
			((ElectricShock) tch).update();
		
		}
		
		if(rolling_attack && actions.get("ROLLING_ATTACK") != null && currentAction != actions.get("ROLLING_ATTACK"))
		{
			
			tch = new RollingAttack(this);
			(this).startTech(tch);
			((RollingAttack) tch).update();
		
		}
		
		if(vertical_rolling_attack && actions.get("VERTICAL_ROLLING_ATTACK") != null && currentAction != actions.get("VERTICAL_ROLLING_ATTACK"))
		{
			
			tch = new VerticalRollingAttack(this);
			(this).startTech(tch);
			((VerticalRollingAttack) tch).update();
	
		}
		
		if(beast_leap && actions.get("BEAST_LEAP") != null && currentAction != actions.get("BEAST_LEAP"))
		{
			
			tch = new BeastLeap(this);
			
			(this).startTech(tch);
			((BeastLeap) tch).update();
		
		}
		
		if(head_bit_crash && actions.get("HEAD_BIT_CRASH") != null && currentAction != actions.get("HEAD_BIT_CRASH"))
		{
			tch = new HitBeatCrash(this);
			(this).startTech(tch);
			((HitBeatCrash) tch).update();
		
		}
		
		
		
		//fireBall attack
		fire += 1;
		if(fire > maxFire) fire = maxFire;
		
		if(firing && actions.get("HADUKEN") != null && currentAction != actions.get("HADUKEN"))
		{
			if(fire > fireCost)
			{
				fire -= fireCost;
				
				
				tch = new FireBall(this);
				((FireBall) tch).setDammage(4);
				(this).startTech(tch);
				((FireBall) tch).update();	
				((FireBall) tch).setPosition((int) getPositionX() , (int) getPositionY());
				
				fireBalls.add((FireBall) tch);
			}
		}
		
		//update fire ball
     	for(int i=0; i < fireBalls.size(); i++)
		{
			fireBalls.get(i).update();
			if(fireBalls.get(i).shouldRemove())
			{
				fireBalls.remove(i);
				i--;
			}
			
		}
		
		
		// set animation
     	if(ko)
     	{
     		if(currentAction != actions.get("KO"))
			{
				currentAction = actions.get("KO");
				animation.setFrames(sprites.get(actions.get("KO")));
				animation.setDelay(50);
				
			}
     	}
     	else
		if(punching)
		{
			if(actions.get("LPUNCH") != null && currentAction != actions.get("LPUNCH"))
			{
				currentAction = actions.get("LPUNCH");
				animation.setFrames(sprites.get(actions.get("LPUNCH")));
				animation.setDelay(50);
				
			}
		}
		
		else
			if(m_punch)
			{
				if(actions.get("MPUNCH") != null && currentAction != actions.get("MPUNCH"))
				{
				currentAction = actions.get("MPUNCH");
				animation.setFrames(sprites.get(actions.get("MPUNCH")));
				animation.setDelay(100);
				}
			}
			
		else
			if(fpunch)
			{
				if(actions.get("FPUNCH") != null && currentAction != actions.get("FPUNCH"))
				{
				currentAction = actions.get("FPUNCH");
				animation.setFrames(sprites.get(actions.get("FPUNCH")));
				animation.setDelay(50);
				}
			}
		else
			if(fmpunch)
			{
				if(actions.get("FMPUNCH") != null && currentAction != actions.get("FMPUNCH"))
				{
				currentAction = actions.get("FMPUNCH");
				animation.setFrames(sprites.get(actions.get("FMPUNCH")));
				animation.setDelay(50);
				}
			}
		else
			if(fhpunch)
			{
				if(actions.get("FHPUNCH") != null && currentAction != actions.get("FHPUNCH"))
				{
				currentAction = actions.get("FHPUNCH");
				animation.setFrames(sprites.get(actions.get("FHPUNCH")));
				animation.setDelay(50);
				}
			}
		else
			if(head_stomp)
			{
				if(actions.get("HEAD_STOMP") != null && currentAction != actions.get("HEAD_STOMP"))
				{
					currentAction = actions.get("HEAD_STOMP");
					animation.setFrames(sprites.get(actions.get("HEAD_STOMP")));
					animation.setDelay(50);
				}
			}
		else
			if(soulder_toss)
			{
				if(actions.get("SOULDER TOSS") != null && currentAction != actions.get("SOULDER TOSS"))
				{
					currentAction = actions.get("SOULDER TOSS");
					animation.setFrames(sprites.get(actions.get("SOULDER TOSS")));
					animation.setDelay(50);
				}
			}
		else
			if(electric_shock)
			{
				if(actions.get("ELECTRIC_SHOCK") != null && currentAction != actions.get("ELECTRIC_SHOCK"))
				{
					currentAction = actions.get("ELECTRIC_SHOCK");
					animation.setFrames(sprites.get(actions.get("ELECTRIC_SHOCK")));
					animation.setDelay(50);
				}
			}
		else
			if(rolling_attack)
			{
				if(actions.get("ROLLING_ATTACK") != null && currentAction != actions.get("ROLLING_ATTACK"))
				{
					currentAction = actions.get("ROLLING_ATTACK");
					animation.setFrames(sprites.get(actions.get("ROLLING_ATTACK")));
					animation.setDelay(50);
				}
			}
		else
			if(vertical_rolling_attack)
			{
				if(actions.get("VERTICAL_ROLLING_ATTACK") != null && currentAction != actions.get("VERTICAL_ROLLING_ATTACK"))
				{
					currentAction = actions.get("VERTICAL_ROLLING_ATTACK");
					animation.setFrames(sprites.get(actions.get("VERTICAL_ROLLING_ATTACK")));
					animation.setDelay(50);
				}
			}
		else
			if(beast_leap)
			{
				if(actions.get("BEAST_LEAP") != null && currentAction != actions.get("BEAST_LEAP"))
				{
					currentAction = actions.get("BEAST_LEAP");
					animation.setFrames(sprites.get(actions.get("BEAST_LEAP")));
					animation.setDelay(50);
				}
			}
		else
			if(head_bit_crash)
			{
				if(actions.get("HEAD_BIT_CRASH") != null && currentAction != actions.get("HEAD_BIT_CRASH"))
				{
					currentAction = actions.get("HEAD_BIT_CRASH");
					animation.setFrames(sprites.get(actions.get("HEAD_BIT_CRASH")));
					animation.setDelay(50);
				}
			}		
		else
			if(firing)
			{
				if(actions.get("HADUKEN") != null && currentAction != actions.get("HADUKEN"))
				{
					currentAction = actions.get("HADUKEN");
					animation.setFrames(sprites.get(actions.get("HADUKEN")));
					animation.setDelay(100);
					
					
				}
			}
			else
				if(kicking)
				{
					if(actions.get("LKICK") != null && currentAction != actions.get("LKICK"))
					{
						currentAction = actions.get("LKICK");
						animation.setFrames(sprites.get(actions.get("LKICK")));
						animation.setDelay(100);
					
				
					}
					
				}
				else
					if(heightKiking)
					{
						if(actions.get("HKICK") != null && currentAction != actions.get("HKICK"))
						{
							currentAction = actions.get("HKICK");
							animation.setFrames(sprites.get(actions.get("HKICK")));
							animation.setDelay(120);
							
					
						}
						
					}
		
					else
						if(flkick)
						{
							if(actions.get("FLKICK") != null && currentAction != actions.get("FLKICK"))
							{
								currentAction = actions.get("FLKICK");
								animation.setFrames(sprites.get(actions.get("FLKICK")));
								animation.setDelay(120);
								
						
							}
							
						}
		
				else
					if(fmkick)
					{
						if(actions.get("FMKICK") != null && currentAction != actions.get("FMKICK"))
						{
						currentAction = actions.get("FMKICK");
						animation.setFrames(sprites.get(actions.get("FMKICK")));
						animation.setDelay(120);
						}
							
					}
				else
					if(fhkick)
					{
						if(actions.get("FHKICK") != null && currentAction != actions.get("FHKICK"))
						{
						currentAction = actions.get("FHKICK");
						animation.setFrames(sprites.get(actions.get("FHKICK")));
						animation.setDelay(120);
						}
							
					}
				else	
					if(iscombo)
					{
						if(actions.get("FMPUNCH") != null && currentAction != actions.get("FMPUNCH"))
						{
							currentAction = actions.get("FMPUNCH");
							animation.setFrames(sprites.get(actions.get("FMPUNCH")));
							animation.setDelay(200);
							
						}
							
					}
				else
					if(blockStunned)
					{
						if(actions.get("BLOCKING") != null && currentAction != actions.get("BLOCKING"))
						{
							currentAction = actions.get("BLOCKING");
							animation.setFrames(sprites.get(actions.get("BLOCKING")));
							animation.setDelay(100);
							
						
						}
					}
		
					else
						if(crouch_h_kick)
						{
							if(actions.get("CROUCH_H_KICK") != null && currentAction != actions.get("CROUCH_H_KICK"))
							{
								currentAction = actions.get("CROUCH_H_KICK");
								animation.setFrames(sprites.get(actions.get("CROUCH_H_KICK")));
								animation.setDelay(100);
								
							
							}
							
						}
					else
						if(crouch_l_kick)
						{
							if(actions.get("CROUCH_L_KICK") != null && currentAction != actions.get("CROUCH_L_KICK"))
							{
							currentAction = actions.get("CROUCH_L_KICK");
							animation.setFrames(sprites.get(actions.get("CROUCH_L_KICK")));
							animation.setDelay(120);
							}
								
						}
					else
							if(crouch_m_kick)
							{
								if(actions.get("CROUCH_M_KICK") != null && currentAction != actions.get("CROUCH_M_KICK"))
								{
								currentAction = actions.get("CROUCH_M_KICK");
								animation.setFrames(sprites.get(actions.get("CROUCH_M_KICK")));
								animation.setDelay(120);
								}
									
							}
					
		
					else
						if(isTechning() && !blockStunned)
						{
							if(!blockStunned && actions.get("HIT") != null &&  currentAction != actions.get("HIT"))
							{
								techHit = true;
								currentAction = actions.get("HIT");
								animation.setFrames(sprites.get(actions.get("HIT")));
								animation.setDelay(50);
								
							
							}
						}
					else
						if(down)
						{
							if(actions.get("CROUCH") != null && currentAction != actions.get("CROUCH"))
							{
								currentAction = actions.get("CROUCH");
								animation.setFrames(sprites.get(actions.get("CROUCH")));
								animation.setDelay(100);
								
							}
						}
					else
						if(fjumping) 
						{
							if(actions.get("FJUMP") != null  && currentAction != actions.get("FJUMP"))
							{
								currentAction = actions.get("FJUMP");
								animation.setFrames(sprites.get(actions.get("FJUMP")));
								animation.setDelay(100);
							}
							
						}
					else
						if(jump_l_punch)
						{
							if(actions.get("JUMP_L_PUNCH") != null && currentAction != actions.get("JUMP_L_PUNCH"))
							{
							currentAction = actions.get("JUMP_L_PUNCH");
							animation.setFrames(sprites.get(actions.get("JUMP_L_PUNCH")));
							animation.setDelay(120);
							}
									
						}
		
					else
						if(jump_lm_kick)
						{
							if(actions.get("JUMP_LM_KICK") != null && currentAction != actions.get("JUMP_LM_KICK"))
							{
							currentAction = actions.get("JUMP_LM_KICK");
							animation.setFrames(sprites.get(actions.get("JUMP_LM_KICK")));
							animation.setDelay(120);
							}
									
						}
						else
							if(jump_h_kick)
							{
								if(actions.get("JUMP_H_KICK") != null && currentAction != actions.get("JUMP_H_KICK"))
								{
								currentAction = actions.get("JUMP_H_KICK");
								animation.setFrames(sprites.get(actions.get("JUMP_H_KICK")));
								animation.setDelay(120);
								}
										
							}
						else
							if(fjl_punch)
							{
								if(actions.get("FJL_PUNCH") != null && currentAction != actions.get("FJL_PUNCH"))
								{
								currentAction = actions.get("FJL_PUNCH");
								animation.setFrames(sprites.get(actions.get("FJL_PUNCH")));
								animation.setDelay(120);
								}
										
							}
							else
								if(forward_jump_kick)
								{
									if(actions.get("FORWARD_JUMP_KICK") != null && currentAction != actions.get("FORWARD_JUMP_KICK"))
									{
									currentAction = actions.get("FORWARD_JUMP_KICK");
									animation.setFrames(sprites.get(actions.get("FORWARD_JUMP_KICK")));
									animation.setDelay(120);
									}
											
								}
							else
								if( shouryuken)
								{
									if(actions.get("SHOURYUKEN") != null && currentAction != actions.get("SHOURYUKEN"))
									{
									currentAction = actions.get("SHOURYUKEN");
									animation.setFrames(sprites.get(actions.get("SHOURYUKEN")));
									animation.setDelay(150);
									}
											
								}
							else
								if(tatsumaki_senpu_kyaku)
								{
									if(actions.get("TATSUMAKI_SENPU_KYAKU") != null && currentAction != actions.get("TATSUMAKI_SENPU_KYAKU"))
									{
									currentAction = actions.get("TATSUMAKI_SENPU_KYAKU");
									animation.setFrames(sprites.get(actions.get("TATSUMAKI_SENPU_KYAKU")));
									animation.setDelay(120);
									}
											
								}
							else
								if(soulder_toss)
								{
									if(actions.get("SOULDER_TOSS") != null && currentAction != actions.get("SOULDER_TOSS"))
									{
									currentAction = actions.get("SOULDER_TOSS");
									animation.setFrames(sprites.get(actions.get("SOULDER_TOSS")));
									animation.setDelay(120);
									}
											
								}
							else
								if(bloocking_down)
								{
									if(actions.get("BLOCKING_DOWN") != null && currentAction != actions.get("BLOCKING_DOWN"))
									{
									currentAction = actions.get("BLOCKING_DOWN");
									animation.setFrames(sprites.get(actions.get("BLOCKING_DOWN")));
									animation.setDelay(70);
									}
											
								}
			
						else
		
						if(dy > 0 && falling)
						{
							if(actions.get("JUMPING") != null && currentAction != actions.get("JUMPING"))
							{
								currentAction = actions.get("JUMPING");
								animation.setFrames(sprites.get(actions.get("JUMPING")));
								animation.setDelay(50);
								
							}
						}
					else
						if(dy < 0 && jumping)
						{
						
							if(actions.get("JUMPING") != null && currentAction != actions.get("JUMPING"))
							{
								currentAction = actions.get("JUMPING");
								animation.setFrames(sprites.get(actions.get("JUMPING")));
								animation.setDelay(100);
								
							}
						}

					else
						if(getLeft() || getRight())
						{
							if(currentAction != actions.get("WALK"))
							{
								currentAction = actions.get("WALK");
								animation.setFrames(sprites.get(actions.get("WALK")));
								animation.setDelay(100);
								
							}
						}
						else
						{
							if( actions.get("IDLE") != null && currentAction != actions.get("IDLE"))
							{
								currentAction =actions.get("IDLE");
								animation.setFrames(sprites.get(actions.get("IDLE")));
								animation.setDelay(400);
								
							}
						}
					
				animation.update();
				
				// set direction
			
				
				if(actions.get("LPUNCH") != null &&  actions.get("HADUKEN") != null && currentAction != actions.get("LPUNCH") && currentAction != actions.get("HADUKEN"))
				{
					if(right) setFaceRight(true);
					if(left) setFaceRight(false);
					
				}
	}
	
	public void draw(Graphics2D g)
	{
	
		//draw fireBall
		for(int i = 0; i < fireBalls.size(); i++)
		{
			fireBalls.get(i).draw(g);
		}
		
		// draw player
		if(fliching)
		{
			long elapsed = (System.nanoTime() - flinchTime) / 1000000;
			if(elapsed / 100 % 2 == 0)
				return;
		}
		
		
		if(isFaceRight() )
		{		
			g.drawImage(
			  animation.getImage(),
			  (int) (getPositionX() - getWidth() / 2),
			  (int) (getPositionY()  - (getHeight() + 65) / 2),
			  null);
			
			
			
		}
		else
			if(!isFaceRight()  )
			
			{
				g.drawImage(
				  animation.getImage(),
				  (int) (getPositionX()  + getWidth()),
				  (int) (getPositionY() - (getHeight() + 67)  / 2),
				  -(getWidth() + 30)   , getHeight() + 45 , null);	
			}
		
		
		
		
		
	}
	
	
	public int getFire() {return fire;}
	public int getMaxFire() {return maxFire;	}





	@Override
	public Map<String, Integer> getActions() {
		
		return this.actions;
	}





	@Override
	public boolean isFalling() {
		// TODO Auto-generated method stub
		return falling;
	}





	@Override
	public boolean isShouryuken() {
		// TODO Auto-generated method stub
		return shouryuken;
	}





	@Override
	public boolean isJumping() {
		// TODO Auto-generated method stub
		return jumping;
	}

	}

