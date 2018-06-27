package decorator;


import services.HitBox;

public abstract class HitBoxDeco implements HitBox {

	private HitBox delegate;
	
	public HitBoxDeco(HitBox hb) {
		this.delegate = hb;
	}

	@Override
	public double getPositionX() {
		// TODO Auto-generated method stub
		return delegate.getPositionX();
	}

	@Override
	public double getPositionY() {
		// TODO Auto-generated method stub
		return delegate.getPositionY();
	}

	@Override
	public boolean belongsTo(double a, double b, int w, int h) {
		// TODO Auto-generated method stub
		return delegate.belongsTo(a, b, w, h);
	}

	@Override
	public boolean collidesWith(HitBox b) {
		// TODO Auto-generated method stub
		return delegate.collidesWith(b);
	}

	@Override
	public boolean equalsTo(HitBox b) {
		// TODO Auto-generated method stub
		return delegate.equals(b);
	}

	@Override
	public void init(double x, double y, int w, int h) {
		// TODO Auto-generated method stub
		delegate.init(x, y, w, h);
	}

	@Override
	public void moveTo(double x, double y) {
		delegate.moveTo(x, y);
		
	}

	@Override
	public void setPositionX(double x) {
		delegate.setPositionX(x);
		
	}

	@Override
	public void setPositionY(double y) {
		delegate.setPositionY(y);
		
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
	
	
}
