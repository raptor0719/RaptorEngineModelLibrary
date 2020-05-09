package raptor.modelLibrary.temp;

import java.awt.Image;

public class DimensionalSprite {
	private final Image[] sprites;

	public DimensionalSprite(final Image[] sprites) {
		this.sprites = sprites;
	}

	public int dimensions() {
		return sprites.length;
	}

	public Image getSprite(final int dimension) {
		return sprites[dimension];
	}
}
