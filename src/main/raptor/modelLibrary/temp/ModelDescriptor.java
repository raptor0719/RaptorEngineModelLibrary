package raptor.modelLibrary.temp;

import java.awt.Image;

public class ModelDescriptor {
	private final int[] pointsX;
	private final int[] pointsY;
	private final int[] pointsRotation;
	private final Image[] sprites;

	public ModelDescriptor(final int[] pointsX, final int[] pointsY, final int[] pointsRotation, final Image[] sprites) {
		this.pointsX = pointsX;
		this.pointsY = pointsY;
		this.pointsRotation = pointsRotation;
		this.sprites = sprites;

		if (pointsX.length != pointsY.length || pointsX.length != pointsRotation.length || pointsX.length != sprites.length)
			throw new RuntimeException("Invalid model descriptor formed.");
	}

	public int size() {
		return pointsX.length;
	}

	public int getX(final int index) {
		return pointsX[index];
	}

	public int getY(final int index) {
		return pointsY[index];
	}

	public int getRotation(final int index) {
		return pointsRotation[index];
	}

	public Image getSprite(final int index) {
		return sprites[index];
	}
}
