package raptor.modelLibrary.model.animation.frame;

import java.util.Arrays;

import raptor.modelLibrary.model.util.point.IRotatedPoint;

public class WiredFrame extends Frame {
	private final IRotatedPoint[] hardpointPositions;

	public WiredFrame(final Sprite sprite, final IRotatedPoint[] hardpointPositions) {
		super(sprite);
		this.hardpointPositions = hardpointPositions;
	}

	public IRotatedPoint[] getHardpointPositions() {
		return Arrays.copyOf(hardpointPositions, hardpointPositions.length);
	}
}
