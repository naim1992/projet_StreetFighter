package decorator;

import services.Engine;
import services.Player;

public abstract class EngineDeco implements Engine{

	
	private Engine delegate;
	
	public EngineDeco(Engine e) {
		this.delegate = e;
	}
	
	@Override
	public int getWidth() {
		
		return delegate.getWidth();
	}

	@Override
	public int getHeight() {
		
		return delegate.getHeight();
	}

	@Override
	public int getScale() {
		return delegate.getScale();
	}
	
	@Override
	public Player getPlayer(int i) {
		
		return delegate.getPlayer(i);
	}

	@Override
	public boolean isGameOver() {
		
		return delegate.isGameOver();
	}

	@Override
	public void init(int h, int w, int s, Player p1, Player p2) {
		delegate.init(h, w, s, p1, p2);
	
	}

	@Override
	public void step(int c1, int c2) {
		delegate.step(c1, c2);
		
	}

	
	
	@Override
	public void run()
	{
		delegate.run();
	}

	@Override
	public void endStep(int c1, int c2) {
		delegate.endStep(c1, c2);
		
	}
	

	

}
