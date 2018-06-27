package impl;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import main.Background;
import main.GameStateManager;
import services.Engine;
import services.Player;



public class EngineImpl extends JPanel implements Runnable, Engine, KeyListener{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  int width ;
	private  int height;
	private int scale ;
	private Player player1;
	private Player player2;
	

	/************************************ partie graphic *************************************/
	
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	private boolean running;
	
	//image
	private BufferedImage image;
	private Graphics2D g;
	
	
	// game state manager
	private	GameStateManager gsm;
	
	// backgroud
	private Background bg;
	/**********************************************************************************/
	
	public EngineImpl() {
		super();
		
	}
	
	
	
	@Override
	public int getScale() {
		
		return this.scale;
	}

	@Override
	public Player getPlayer(int i) {
		
		return ((i == 1) ?  player1 :  player2);
	}

	@Override
	public boolean isGameOver() {
		
		return player1.isDead() || player2.isDead();
	}
	
	@Override
	public int getHeight() {
		return this.height;
	};
	
	@Override
	public int getWidth() {
		return this.width;
	};

	@Override
	public void init(int h, int w, int s, Player p1, Player p2) {
		this.height = h;
		this.width = w;
		
		this.scale = s;
		this.player1 = p1;
		this.player2 = p2;
		
		
		this.player1.setPositionX(width / scale / 4 - getScale() / 2);
		
		this.player1.setPositionY(this.height / this.scale - this.player2.getHeight() / 2);
		
		this.player2.setPositionX(width / scale / 2 + getScale() * 20);
		this.player2.setPositionY(this.height / this.scale - this.player2.getHeight() / 2);
		
		this.player1.setFaceRight(true);
		this.player2.setFaceRight(false);
		
		List<Integer> commandsP1 = new ArrayList<Integer>();
		List<Integer> commandsP2 = new ArrayList<Integer>();
		
		commandsP2.add(KeyEvent.VK_LEFT);
		commandsP2.add(KeyEvent.VK_RIGHT);
		commandsP2.add(KeyEvent.VK_DOWN);
		commandsP2.add(KeyEvent.VK_UP);
		commandsP2.add(KeyEvent.VK_NUMPAD0);
		commandsP2.add(KeyEvent.VK_NUMPAD1);
		commandsP2.add(KeyEvent.VK_NUMPAD2);
		commandsP2.add(KeyEvent.VK_NUMPAD3);
		commandsP2.add(KeyEvent.VK_NUMPAD4);
		commandsP2.add(KeyEvent.VK_NUMPAD5);
		commandsP2.add(KeyEvent.VK_NUMPAD6);
		commandsP2.add(KeyEvent.VK_NUMPAD7);
		commandsP2.add(KeyEvent.VK_NUMPAD8);
		commandsP2.add(KeyEvent.VK_NUMPAD9);
		commandsP2.add(KeyEvent.VK_ADD);
		commandsP2.add(KeyEvent.VK_SUBTRACT);
		
		
		commandsP1.add(KeyEvent.VK_A);
		commandsP1.add(KeyEvent.VK_B);
		commandsP1.add(KeyEvent.VK_C);
		commandsP1.add(KeyEvent.VK_D);
		commandsP1.add(KeyEvent.VK_E);
		commandsP1.add(KeyEvent.VK_F);
		commandsP1.add(KeyEvent.VK_G);
		commandsP1.add(KeyEvent.VK_H);
		commandsP1.add(KeyEvent.VK_I);
		commandsP1.add(KeyEvent.VK_J);
		commandsP1.add(KeyEvent.VK_K);
		commandsP1.add(KeyEvent.VK_L);
		commandsP1.add(KeyEvent.VK_M);
		commandsP1.add(KeyEvent.VK_N);
		commandsP1.add(KeyEvent.VK_O);
		commandsP1.add(KeyEvent.VK_P);
		commandsP1.add(KeyEvent.VK_Q);
		commandsP1.add(KeyEvent.VK_R);
		commandsP1.add(KeyEvent.VK_S);
		commandsP1.add(KeyEvent.VK_T);
		commandsP1.add(KeyEvent.VK_U);
		commandsP1.add(KeyEvent.VK_V);
		commandsP1.add(KeyEvent.VK_W);
		commandsP1.add(KeyEvent.VK_X);
		commandsP1.add(KeyEvent.VK_Y);
		commandsP1.add(KeyEvent.VK_Z);
		commandsP1.add(KeyEvent.VK_ALT);
		
		this.player1.initCommande(commandsP2);
		this.player2.initCommande(commandsP1);
		
		
		
		
		
		/************************* partie graphique ***********************/
		setPreferredSize(new Dimension(width  , height ));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		
		g = (Graphics2D) image.getGraphics();
		running = true;
		
		gsm = new GameStateManager(this);
		setBg(new Background("/Backgrounds/edobath.png", 0.1, gsm.getEngine()));
		/*****************************************************************/
	}

	@Override
	public void step(int c1, int c2) {
		
		this.player1.step(c1);
		this.player2.step(c2);
		
	}
	
	@Override
	public void endStep(int c1, int c2) {
		
		this.player1.endStep(c1);
		this.player2.endStep(c2);
		
	}
		
	/********************************* partie graphique ****************************/
	
	
	
	
	private void drawToScreen()
	{
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, width * scale, height * scale, null);
		g2.dispose();
	}
	
	
	private void update()
	{
		gsm.update();
	}
	
	
	private void draw()
	{
		gsm.draw(g);
		
	}
	
	
	public void run()
	{
		
		long start;
		long elapsed;
		long wait;
		
		//game loop
		while (running)
		{
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = Math.abs(targetTime - elapsed / 1000000);
			
			
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	
		
	public void keyPressed(KeyEvent e) {
		gsm.KeyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		
		gsm.KeyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	public Background getBg() {
		return bg;
	}



	public void setBg(Background bg) {
		this.bg = bg;
	}

}
