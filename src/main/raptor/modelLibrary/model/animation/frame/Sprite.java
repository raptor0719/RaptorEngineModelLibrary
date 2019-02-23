package raptor.modelLibrary.model.animation.frame;

import java.awt.Image;

public class Sprite {
	private final Image image;

	public Sprite(final Image i) {
		this.image = i;
	}

	public Sprite() {
		this.image = null;
	}

	public boolean hasImage() {
		return image == null;
	}

	public Image getImage() {
		return image;
	}
}
