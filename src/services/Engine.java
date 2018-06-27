package services;

public interface Engine extends Runnable{

	// observators
	
	public int getWidth();
	public int getHeight();
	public int getScale();
  // \pre : i = 1 \or i = 2
	public Player getPlayer(int i);
	
	public boolean isGameOver();
	
	
	// cnstructor
	// \pre : h >  \and s>0 \and w > s \and p1 != p2
	
	public void init(int h, int w, int s, Player p1, Player p2);
	
	//operators
	// \pre : !isGameOver()
	public void step(int c1,int c2);
	// \pre : :isGameOver()
	public void endStep(int c1,int c2);
	
	

	
	public void run();
	
	//invariant
	//\inv : isGameOver = \exist i in [1,2] {isDead(player(i)}
	
	// [init]:
	// \post: getHeight() = h;
	// \post: getWidth() = w;
	// \post: getPlayer(1) = p1;
	// \post: getPlayer(2) = p2;
	// \post: getPostionX(player1) = w/2 - s/2
	// \post: PositionX(player2) = w/2 + s/2
	// \post: PostionY(player1) = 0
	// \post: PositionY(player2) = 0
	// \post: faceRight(player1) = true
	// \post: faceRight(Player2) = false
	
	
	//[step]:
	// \post: getPlayer(1).step(c1)
	// \post: getPlayer(2).step(c2)
	
	//[endStep]:
	// \post: getPlayer(1).endStep(c1)
	// \post: getPlayer(2).endStep(c2)

	//[setGameOver]
	// \post : isGameOver() = !isGameOver@pre
	
}
