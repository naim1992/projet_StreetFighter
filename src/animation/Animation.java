package animation;

import java.awt.image.BufferedImage;

public class Animation {

	
	private BufferedImage[] frames;
	private int currentframe;
	
	private long startTime;
	private long delay;
	
	private boolean playedOnce;
	
	public Animation() {
		playedOnce = false;
	}

	public BufferedImage getImage() {
		return frames[currentframe];
	}

	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		setFrame(0);
		startTime = System.nanoTime();
		playedOnce = false;
	}

	public int getFrame() {
		return currentframe;
	}

	public void setFrame(int currentframe) {
		this.currentframe = currentframe;
	}
	
	public void update()
	{
		if(delay == -1 )return;
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > delay)
		{
			currentframe++;
			startTime = System.nanoTime();
		}
		
		if(frames != null && currentframe == frames.length)
		{
			currentframe = 0;
			playedOnce = true;
		}
		
	}
	
	public boolean hasPlayedOnce()
	{
		return playedOnce;
	}

	public void setDelay(long i) {
		
		delay = i;
		
	}
	
}
