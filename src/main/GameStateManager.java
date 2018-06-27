package main;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import services.Engine;
import services.Player;

public class GameStateManager {
 
	public ArrayList<GameState> gameStates;
	private int currentState;
	public static final int MENUSTATE = 0;
	public static final int SELECTION = 1;
	public static final int LEVEL1STATE = 2;	
	private Engine engine;
	
	public Player player1;
	public Player player2;
	
 public GameStateManager(Engine e) {
	// TODO Auto-generated constructor stub
	 this.setEngine(e);
	 gameStates = new ArrayList<GameState>();
	 currentState = MENUSTATE;
	 gameStates.add(new MenuState(this));
	 gameStates.add(new Selection(this));
	// gameStates.add(new BeginGame(this));
 }
 
 public void setState(int state)
 {
	 currentState = state;
	
	 gameStates.get(currentState).init();
	 
 }
 
 public void update()
 {
	 gameStates.get(currentState).update();
	 
 }
 
 public void draw(Graphics2D g)
 {
	 gameStates.get(currentState).draw(g);
 }
 
 public void KeyPressed(KeyEvent e)
 {
	 gameStates.get(currentState).keyPressed(e);
 }
 
 public void KeyReleased(KeyEvent e)
 {
	 gameStates.get(currentState).keyReleased(e);
 }

public Engine getEngine() {
	return engine;
}

public void setEngine(Engine engine) {
	this.engine = engine;
}
 
 
 
}
