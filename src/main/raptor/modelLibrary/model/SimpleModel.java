package raptor.modelLibrary.model;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import raptor.modelLibrary.model.animation.Animation;
import raptor.modelLibrary.model.animation.frame.Frame;
import raptor.modelLibrary.model.animation.frame.Sprite;
import raptor.modelLibrary.model.util.DimensionalList;
import raptor.modelLibrary.model.util.ListRotation;
import raptor.modelLibrary.model.util.MultipliedList;
import raptor.modelLibrary.model.util.TimingList;
import raptor.modelLibrary.model.util.point.IRotatedPoint;

public class SimpleModel {
	private final ModelData<Frame> data;
	private final IRotatedPoint positionReference;

	// Animation Infrastructure
	private final List<DimensionalList<Frame>> animationFrames;
	private final List<MultipliedList<Frame>> timedAnimations;
	protected final ListRotation<Frame> animator;
	private int currentAnimation;
	private int currentDimension;

	public SimpleModel(final ModelData<? extends Frame> initData, final IRotatedPoint positionReference) {
		// FIXME ModelData is read-only by design, casting to frame should be done elsewhere
		data = (ModelData<Frame>) initData;
		animationFrames = buildFrames(data);
		timedAnimations = buildTiming(data, animationFrames);
		animator = new ListRotation<>(timedAnimations.get(data.getDefaultAnimation()));
		currentAnimation = data.getDefaultAnimation();
		currentDimension = 0;

		this.positionReference = positionReference;
	}

	public int setAnimation(final int animation) {
		if (animation < 0 || animation >= timedAnimations.size())
			return -1;

		animationFrames.get(animation).setDimension(currentDimension);
		animator.setProvider(timedAnimations.get(animation));
		currentAnimation = animation;

		return animator.size();
	}

	public void setDirection(final int direction) {
		animationFrames.get(currentAnimation).setDimension(direction);
	}

	public void advanceFrame(final int count) {
		animator.advance(count);
	}

	public void advanceFrame() {
		this.advanceFrame(1);
	}

	public int framesRemainingInAnimation() {
		return animator.size();
	}

	public Sprite getCurrentSprite() {
		return animator.getCurrent().getSprite();
	}

	public IRotatedPoint getPosition() {
		return positionReference;
	}

	public int getWidth() {
		return data.getWidth();
	}

	public int getHeight() {
		return data.getHeight();
	}

	/* HELPER METHODS */

	private List<DimensionalList<Frame>> buildFrames(final ModelData<Frame> data) {
		final List<DimensionalList<Frame>> frames = new ArrayList<>();

		for (final Animation<Frame> a : data.getAnimations()) {
			frames.add(new DimensionalList<Frame>(a.getFrames()));
		}

		return frames;
	}

	private List<MultipliedList<Frame>> buildTiming(final ModelData<Frame> data, final List<DimensionalList<Frame>> frames) {
		final List<MultipliedList<Frame>> timings = new ArrayList<>();

		for (int i = 0; i < frames.size(); i++) {
			final Animation<Frame> animation = data.getAnimations().get(i);

			timings.add(new MultipliedList<Frame>(frames.get(i), buildTimingList(animation.getTimings())));
		}

		return timings;
	}

	private AbstractList<Integer> buildTimingList(final int[] timings) {
		return new TimingList(timings);
	}
}
