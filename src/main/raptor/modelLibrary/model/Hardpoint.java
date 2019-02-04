package raptor.modelLibrary.model;

public class Hardpoint implements IHardpointPosition {
	private int x;
	private int y;
	private int rotation;

	public Hardpoint(final int initX, final int initY, final int initRotation) {
		x = initX;
		y = initY;
		rotation = initRotation;
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
		x = newX;
	}

	public void setY(final int newY) {
		y = newY;
	}

	public void setRotation(final int newRotation) {
		rotation = newRotation;
	}
}
