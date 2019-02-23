package raptor.modelLibrary.model;

import java.util.AbstractList;

import raptor.modelLibrary.model.animation.Animation;
import raptor.modelLibrary.model.animation.frame.Frame;

public class ModelData<T extends Frame> {
	private final AbstractList<Animation<T>> animations;
	private final int defaultAnimation;
	private final int directionsCount;
	private final int width;
	private final int height;

	public ModelData(final AbstractList<Animation<T>> animations,
			final int defaultAnimation, final int directionsCount,
			final int width, final int height) {
		this.animations = animations;
		this.defaultAnimation = defaultAnimation;
		this.directionsCount = directionsCount;
		this.width = width;
		this.height = height;
	}

	public AbstractList<Animation<T>> getAnimations() {
		return animations;
	}

	public int getDefaultAnimation() {
		return defaultAnimation;
	}

	public int getDirectionsCount() {
		return directionsCount;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
