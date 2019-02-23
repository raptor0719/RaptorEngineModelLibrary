package raptor.modelLibrary.model;

import raptor.modelLibrary.model.animation.frame.WiredFrame;
import raptor.modelLibrary.model.util.point.IRotatedPoint;
import raptor.modelLibrary.model.util.point.Point;

public class WiredModel extends SimpleModel {
	private final Point[] hardpoints;

	public WiredModel(final WiredModelData data, final IRotatedPoint positionReference) {
		super(data, positionReference);

		hardpoints = new Point[data.getHardpointCount()];

		for (int i = 0; i < hardpoints.length; i++) {
			hardpoints[i] = new Point();
		}

		updateHardpointPositions(hardpoints, getCurrentFrame(this).getHardpointPositions());
	}

	public int getHardpointCount() {
		return hardpoints.length;
	}

	/**
	 * This returns a reference to the requested point, meaning that the values this object
	 * returns will change as the point properties change.
	 * @param point the hardpoint to retrieve
	 * @return a reference to the requested hardpoint
	 */
	public IRotatedPoint getHardpoint(final int point) {
		if (point < 0 || point >= hardpoints.length)
			return null;

		return hardpoints[point];
	}

	/**
	 * This returns a static point representing the current position of the requested point.
	 * This object will NOT update as the point properties change.
	 * @param point the hardpoint position to retrieve
	 * @return the current position of the requested point
	 */
	public IRotatedPoint getHardpointPosition(final int point) {
		if (point < 0 || point >= hardpoints.length)
			return null;

		final Point reference = hardpoints[point];

		return new Point(reference.getX(), reference.getY(), reference.getRotation());
	}

	@Override
	public void advanceFrame(final int count) {
		super.advanceFrame(count);

		updateHardpointPositions(hardpoints, getCurrentFrame(this).getHardpointPositions());
	}

	/* HELPER METHODS */

	private void updateHardpointPositions(final Point[] hardpoints, final IRotatedPoint[] positions) {
		for (int i = 0; i < hardpoints.length; i++) {
			final Point point = hardpoints[i];
			final IRotatedPoint pos = positions[i];

			point.setX(pos.getX() + this.getPosition().getX());
			point.setY(pos.getY() + this.getPosition().getY());
			point.setRotation(pos.getRotation() + this.getPosition().getRotation());

			//System.out.println("For point " + i + " x=" + point.getX() + " y=" + point.getY());
		}
	}

	private WiredFrame getCurrentFrame(final SimpleModel model) {
		return (WiredFrame) model.animator.getCurrent();
	}
}
