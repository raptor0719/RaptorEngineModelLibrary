package raptor.modelLibrary.model;

import java.util.AbstractList;

import raptor.modelLibrary.model.animation.Animation;
import raptor.modelLibrary.model.animation.frame.WiredFrame;

public class WiredModelData extends ModelData<WiredFrame> {
	private final int hardpointCount;

	public WiredModelData(AbstractList<Animation<WiredFrame>> animations,
			int defaultAnimation, int directionsCount,
			int width, int height,
			int hardpointCount) {
		super(animations, defaultAnimation, directionsCount, width, height);
		this.hardpointCount = hardpointCount;
	}

	public int getHardpointCount() {
		return hardpointCount;
	}
}
