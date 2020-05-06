package raptor.modelLibrary.temp;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import raptor.modelLibrary.api.Model;
import raptor.modelLibrary.model.animation.frame.Frame;
import raptor.modelLibrary.model.animation.frame.Sprite;
import raptor.modelLibrary.model.util.DimensionalList;
import raptor.modelLibrary.model.util.ListRotation;
import raptor.modelLibrary.model.util.MultipliedList;
import raptor.modelLibrary.model.util.TimingList;
import raptor.modelLibrary.model.util.point.IRotatedPoint;
import raptor.modelLibrary.model.util.point.Point;

public class ConcreteModel implements Model {
	private final AttachmentManager attachments;

	// Animation Infrastructure
	private final List<DimensionalList<Frame>> animationFrames;
	private final List<MultipliedList<Frame>> timedAnimations;
	private final ListRotation<Frame> animator;
	private int currentAnimation;
	private int currentDimension;

	protected ConcreteModel(final AnimationSet animations) {
		attachments = new AttachmentManager(animations.getHardpointCount());
		animationFrames = buildFrames(animations);
		timedAnimations = buildTiming(animations, animationFrames);
		animator = new ListRotation<Frame>(null);
		currentAnimation = -1;
		currentDimension = 0;
	}

	@Override
	public int getRemainingFrames() {
		return animator.size();
	}

	@Override
	public int setAnimation(final int id) {
		if (id < 0 || id >= timedAnimations.size())
			return -1;

		animationFrames.get(id).setDimension(currentDimension);
		animator.setProvider(timedAnimations.get(id));
		currentAnimation = id;

		return animator.size();
	}

	public int advanceFrame(final int frameCount) {
		animator.advance(frameCount);
		return animator.size();
	}

	@Override
	public int advanceFrame() {
		return advanceFrame(1);
	}

	@Override
	public void setDirection(final int direction) {
		animationFrames.get(currentAnimation).setDimension(direction);
	}

	@Override
	public void attachModel(Model model, int hardpointId, int offX, int offY, int offRot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeModel(int hardpointId) {
		// TODO Auto-generated method stub

	}

	@Override
	public IRotatedPoint getHardpointPosition(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(Model model, int hardpointId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unsetModel(int hardpointId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Sprite getModelSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	/* HELPER METHODS */
	private List<DimensionalList<Frame>> buildFrames(final AnimationSet animations) {
		final List<DimensionalList<Frame>> frames = new ArrayList<>();

		for (final Animation a : animations.getAnimations()) {
			frames.add(new DimensionalList<Frame>(a.getFrames()));
		}

		return frames;
	}

	private List<MultipliedList<Frame>> buildTiming(final AnimationSet data, final List<DimensionalList<Frame>> frames) {
		final List<MultipliedList<Frame>> timings = new ArrayList<>();

		for (int i = 0; i < frames.size(); i++) {
			final Animation animation = data.getAnimations().get(i);

			timings.add(new MultipliedList<Frame>(frames.get(i), buildTimingList(animation.getTimings())));
		}

		return timings;
	}

	private AbstractList<Integer> buildTimingList(final int[] timings) {
		return new TimingList(timings);
	}

	/* HELPER CLASSES */
	private class AttachmentManager {
		private final boolean[] hasAttached;
		private final Attachment[] attachments;

		public AttachmentManager(final int hardpointCount) {
			hasAttached = new boolean[hardpointCount];
			attachments = new Attachment[hardpointCount];

			Arrays.fill(hasAttached, false);
			for (int i = 0; i < attachments.length; i++)
				attachments[i] = new Attachment();
		}

		public boolean hasAttachment(final int hardpointId) {
			return hardpointId >= 0 && hardpointId < hasAttached.length && hasAttached[hardpointId];
		}

		private class Attachment {
			public final Point offset;
			public final Model model;

			public Attachment() {
				offset = null;
				model = null;
			}
		}
	}
}
