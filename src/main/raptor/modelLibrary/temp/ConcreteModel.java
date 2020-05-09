package raptor.modelLibrary.temp;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import raptor.modelLibrary.api.Model;
import raptor.modelLibrary.model.animation.frame.WiredFrame;
import raptor.modelLibrary.model.util.DimensionalList;
import raptor.modelLibrary.model.util.ListRotation;
import raptor.modelLibrary.model.util.MultipliedList;
import raptor.modelLibrary.model.util.TimingList;
import raptor.modelLibrary.model.util.point.IRotatedPoint;

public class ConcreteModel implements Model {
	// Animation Infrastructure
	private final List<DimensionalList<WiredFrame>> animationFrames;
	private final List<MultipliedList<WiredFrame>> timedAnimations;
	private final ListRotation<WiredFrame> animator;
	private int currentAnimation;
	private int currentDimension;

	protected ConcreteModel(final AbstractList<Animation> animations, final AbstractList<String> hardpointNames) {
		animationFrames = buildFrames(animations);
		timedAnimations = buildTiming(animations, animationFrames);
		animator = new ListRotation<WiredFrame>(null);
		currentAnimation = -1;
		currentDimension = 0;
	}

	@Override
	public int getRemainingFrames() {
		return animator.size();
	}

	@Override
	public int setAnimation(final int animationId) {
		if (animationId < 0 || animationId >= timedAnimations.size())
			return -1;

		animationFrames.get(animationId).setDimension(currentDimension);
		animator.setProvider(timedAnimations.get(animationId));
		currentAnimation = animationId;

		return animator.size();
	}

	@Override
	public int setAnimation(final String animationName) {
		return -1;
	}

	@Override
	public void setAnimationFrameCount(final int animationId, final int frameCount) {
	}

	@Override
	public void setAnimationFrameCount(final String animationName, final int frameCount) {
	}

	@Override
	public int advanceFrame() {
		return advanceFrames(1);
	}

	@Override
	public int advanceFrames(final int frameCount) {
		animator.advance(frameCount);
		return animator.size();
	}

	@Override
	public void setDirection(final int direction) {
		animationFrames.get(currentAnimation).setDimension(direction);
	}

	@Override
	public void setSprite(final int hardpointId, final DimensionalSprite sprite) {
	}

	@Override
	public void setSprite(final String hardpointName, final DimensionalSprite sprite) {
	}

	@Override
	public void unsetSprite(int hardpointId) {
	}

	@Override
	public void unsetSprite(String hardpointName) {
	}

	@Override
	public IRotatedPoint getHardpointPosition(final int hardpointId) {
		return null;
	}

	@Override
	public IRotatedPoint getHardpointPosition(final String hardpointName) {
		return null;
	}

	@Override
	public DimensionalSprite getAttachedSprite(final int hardpointId) {
		return null;
	}

	@Override
	public DimensionalSprite getAttachedSprite(final String hardpointName) {
		return null;
	}

	@Override
	public ModelDescriptor getDescriptor() {
		return null;
	}

	/* HELPER METHODS */
	private List<DimensionalList<WiredFrame>> buildFrames(final AnimationSet animations) {
		final List<DimensionalList<WiredFrame>> frames = new ArrayList<>();

		for (final Animation a : animations.getAnimations()) {
			frames.add(new DimensionalList<WiredFrame>(a.getFrames()));
		}

		return frames;
	}

	private List<MultipliedList<WiredFrame>> buildTiming(final AnimationSet data, final List<DimensionalList<WiredFrame>> frames) {
		final List<MultipliedList<WiredFrame>> timings = new ArrayList<>();

		for (int i = 0; i < frames.size(); i++) {
			final Animation animation = data.getAnimations().get(i);

			timings.add(new MultipliedList<WiredFrame>(frames.get(i), buildTimingList(animation.getTimings())));
		}

		return timings;
	}

	private AbstractList<Integer> buildTimingList(final int[] timings) {
		return new TimingList(timings);
	}
}
