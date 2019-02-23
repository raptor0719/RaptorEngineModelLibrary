package raptor.modelLibrary.model;

import raptor.modelLibrary.model.animation.frame.Sprite;
import raptor.modelLibrary.model.util.point.IRotatedPoint;

public class AttachableWiredModel extends WiredModel {
	private final Sprite[] attached;

	public AttachableWiredModel(final WiredModelData data, final IRotatedPoint positionReference) {
		super(data, positionReference);
		attached = new Sprite[data.getHardpointCount()];
	}

	public void attachSprite(final int hardpoint, final Sprite sprite) {
		if (hardpoint < 0 || hardpoint >= attached.length)
			return;

		attached[hardpoint] = sprite;
	}

	/**
	 * @param hardpoint the hardpoint of which sprite to remove
	 * @return The attached sprite if one was attached, or null if no sprite was attached, or null
	 * if an invalid hardpoint was specified
	 */
	public Sprite removeSprite(final int hardpoint) {
		if (hardpoint < 0 || hardpoint >= attached.length)
			return null;

		final Sprite removed = attached[hardpoint];
		attached[hardpoint] = null;

		return removed;
	}

	/**
	 * @param hardpoint the hardpoint of which sprite to retrieve
	 * @return The attached sprite if one was attached or an empty sprite if an invalid hardpoint was
	 * specified or if no sprite is attached to the hardpoint.
	 */
	public Sprite getSprite(final int hardpoint) {
		if (hardpoint < 0 || hardpoint >= attached.length)
			return getEmptySprite();

		return attached[hardpoint];
	}

	/* HELPER METHODS */

	private Sprite getEmptySprite() {
		return new Sprite();
	}
}
