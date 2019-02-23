package raptor.modelLibrary.model.util.point;

public class Point implements IRotatedPoint {
	private int x;
	private int y;
	private int rotation;

	public Point(final int initX, final int initY, final int initRotation) {
		this.x = initX;
		this.y = initY;
		this.rotation = initRotation;
	}

	public Point(final int initX, final int initY) {
		this(initX, initY, 0);
	}

	public Point() {
		this(0, 0, 0);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getRotation() {
		return rotation;
	}

	public void setX(final int newX) {
		this.x = newX;
	}

	public void setY(final int newY) {
		this.y = newY;
	}

	public void setRotation(final int newRotation) {
		this.rotation = newRotation;
	}
}
